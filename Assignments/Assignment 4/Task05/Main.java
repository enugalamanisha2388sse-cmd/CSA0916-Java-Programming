import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;
public class Main extends JFrame {
    private final JTextField amount = new JTextField("1.00", 10);
    private final JComboBox<String> from = new JComboBox<>(new String[]{"USD","EUR","INR"});
    private final JComboBox<String> to = new JComboBox<>(new String[]{"INR","USD","EUR"});
    private final JLabel result = new JLabel("Result: —");
    public Main() {
        super("Currency Converter - Listener Styles");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){ @Override public void windowClosing(WindowEvent e){ dispose(); }});
        JPanel p = new JPanel(new FlowLayout());
        p.add(new JLabel("Amount")); p.add(amount); p.add(from); p.add(new JLabel("to")); p.add(to);
        JButton convert = new JButton("Convert"); p.add(convert); p.add(result);
        add(p);
        convert.addActionListener(e -> convertAndShow());
        amount.addActionListener(e -> convertAndShow());
        pack(); setLocationRelativeTo(null);
    }
    private void convertAndShow() {
        try {
            double value = Double.parseDouble(amount.getText().trim());
            if (!Double.isFinite(value) || value < 0) throw new NumberFormatException();
            String f=(String)from.getSelectedItem(), t=(String)to.getSelectedItem();
            double usd = switch(f) { case "USD" -> value; case "EUR" -> value*1.08; default -> value/83.0; };
            double converted = switch(t) { case "USD" -> usd; case "EUR" -> usd/1.08; default -> usd*83.0; };
            String message = String.format("%.2f %s = %.2f %s (demo rates)", value,f,converted,t);
            result.setText(message);
            JOptionPane.showMessageDialog(this,message,"Conversion result",JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            result.setText("Invalid amount");
            JOptionPane.showMessageDialog(this,"Enter a valid non-negative amount.","Invalid amount",JOptionPane.ERROR_MESSAGE);
        }
    }
    // The same action can be wired as a named inner class, anonymous class or lambda.
    private class NamedConvertListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { convertAndShow(); }
    }
    private void demonstrateStyles() {
        JButton named = new JButton("Named"); named.addActionListener(new NamedConvertListener());
        JButton anonymous = new JButton("Anonymous");
        anonymous.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){ convertAndShow(); }});
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new Main().setVisible(true)); }
}
