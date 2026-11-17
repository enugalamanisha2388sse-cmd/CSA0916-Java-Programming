import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.concurrent.ExecutionException;
public class Main extends JFrame {
    private static final String URL="jdbc:mysql://localhost:3306/campus_events";
    private static final String USER="root", PASSWORD="CHANGE_ME";
    private final JTextField student=new JTextField(14), email=new JTextField(16);
    private final JComboBox<String> event=new JComboBox<>(new String[]{"Tech Fest","Cultural Day","Sports Meet"});
    private final JComboBox<String> filter=new JComboBox<>(new String[]{"All","Tech Fest","Cultural Day","Sports Meet"});
    private final DefaultTableModel model=new DefaultTableModel(new String[]{"ID","Student","Email","Event"},0);
    public Main() {
        super("Campus Event Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar bar=new JMenuBar(); JMenu file=new JMenu("File"); JMenuItem refresh=new JMenuItem("Refresh"), exit=new JMenuItem("Exit");
        refresh.addActionListener(e->loadRows()); exit.addActionListener(e->dispose()); file.add(refresh); file.add(exit); bar.add(file); setJMenuBar(bar);
        JPanel form=new JPanel(new FlowLayout()); form.add(new JLabel("Student")); form.add(student); form.add(new JLabel("Email")); form.add(email); form.add(event);
        JButton register=new JButton("Register"); form.add(register);
        JPanel tools=new JPanel(new FlowLayout()); tools.add(new JLabel("Filter")); tools.add(filter);
        JButton apply=new JButton("Apply"); tools.add(apply); JButton export=new JButton("Export CSV"); tools.add(export);
        JTable table=new JTable(model);
        add(form,BorderLayout.NORTH); add(new JScrollPane(table),BorderLayout.CENTER); add(tools,BorderLayout.SOUTH);
        register.addActionListener(e->registerAsync()); apply.addActionListener(e->loadRows()); refresh.addActionListener(e->loadRows());
        export.addActionListener(e->JOptionPane.showMessageDialog(this,"Use File > Export in a production build; CSV export can be added here."));
        setSize(850,500); setLocationRelativeTo(null); loadRows();
    }
    private Connection connect() throws SQLException { return DriverManager.getConnection(URL,USER,PASSWORD); }
    private void registerAsync() {
        String s=student.getText().trim(), em=email.getText().trim(), ev=(String)event.getSelectedItem();
        if(s.isEmpty() || !em.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            JOptionPane.showMessageDialog(this,"Enter a student name and valid email."); return;
        }
        new SwingWorker<Void,Void>() {
            protected Void doInBackground() throws Exception {
                try(Connection c=connect(); PreparedStatement p=c.prepareStatement(
                    "INSERT INTO registrations(student_name,email,event_name) VALUES(?,?,?)")) {
                    p.setString(1,s); p.setString(2,em); p.setString(3,ev); p.executeUpdate();
                } return null;
            }
            protected void done() {
                try { get(); JOptionPane.showMessageDialog(Main.this,"Registration successful"); loadRows(); }
                catch(ExecutionException ex) {
                    Throwable cause=ex.getCause();
                    String msg=(cause instanceof SQLException && ((SQLException)cause).getErrorCode()==1062)
                        ? "You are already registered for this event." : "Registration failed: "+cause.getMessage();
                    JOptionPane.showMessageDialog(Main.this,msg);
                } catch(Exception ex) { JOptionPane.showMessageDialog(Main.this,ex.getMessage()); }
            }
        }.execute();
    }
    private void loadRows() {
        String selected=(String)filter.getSelectedItem();
        new SwingWorker<Object[][],Void>() {
            protected Object[][] doInBackground() throws Exception {
                String sql="SELECT id,student_name,email,event_name FROM registrations"+
                    ("All".equals(selected)?"":" WHERE event_name=?")+" ORDER BY id DESC LIMIT 100 OFFSET 0";
                try(Connection c=connect(); PreparedStatement p=c.prepareStatement(sql)) {
                    if(!"All".equals(selected)) p.setString(1,selected);
                    try(ResultSet rs=p.executeQuery()) {
                        java.util.List<Object[]> rows=new java.util.ArrayList<>();
                        while(rs.next()) rows.add(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                        return rows.toArray(new Object[0][]);
                    }
                }
            }
            protected void done() {
                try { Object[][] rows=get(); model.setRowCount(0); for(Object[] row:rows) model.addRow(row); }
                catch(Exception ex) { JOptionPane.showMessageDialog(Main.this,"Load failed: "+ex.getMessage()); }
            }
        }.execute();
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(()->new Main().setVisible(true)); }
}
