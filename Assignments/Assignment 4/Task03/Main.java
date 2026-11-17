import javax.swing.*;
import java.awt.*;
public class Main extends JFrame {
    private final JTextField search = new JTextField(22);
    private final JComboBox<String> category = new JComboBox<>(new String[]{"All","Books","Journals","Theses"});
    private final DefaultListModel<String> results = new DefaultListModel<>();
    public Main() {
        super("Campus Library Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8,8));
        JLabel title = new JLabel("Campus Library", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        add(title, BorderLayout.NORTH);
        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchRow.add(new JLabel("Search:")); searchRow.add(search);
        searchRow.add(category);
        JButton go = new JButton("Search"); searchRow.add(go);
        add(searchRow, BorderLayout.PAGE_START);
        JList<String> list = new JList<>(results);
        list.setVisibleRowCount(8);
        add(new JScrollPane(list), BorderLayout.CENTER);
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prev = new JButton("Prev"), next = new JButton("Next"), reserve = new JButton("Reserve");
        bottom.add(prev); bottom.add(next); bottom.add(reserve);
        JPanel south = new JPanel(new BorderLayout());
        south.add(bottom, BorderLayout.CENTER);
        JLabel status = new JLabel("Ready"); south.add(status, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);
        go.addActionListener(e -> {
            results.clear();
            String q = search.getText().trim();
            if (q.isEmpty()) { status.setText("Enter a search term"); return; }
            results.addElement(q + " — Sample library result 1");
            results.addElement(q + " — Sample library result 2");
            status.setText("2 results (demo data)");
        });
        reserve.addActionListener(e -> status.setText(list.getSelectedValue()==null ? "Select a result first" : "Reservation requested (demo)"));
        prev.addActionListener(e -> status.setText("Previous page"));
        next.addActionListener(e -> status.setText("Next page"));
        setMinimumSize(new Dimension(420,300));
        setSize(700,480);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new Main().setVisible(true)); }
}
