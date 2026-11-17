import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
public class Main extends JFrame {
    private final List<Point> points = new ArrayList<>();
    private final JLabel count = new JLabel("Clicks: 0");
    private final JPanel canvas = new JPanel() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            for (Point p : points) g.fillOval(p.x-4, p.y-4, 8, 8);
        }
    };
    public Main() {
        super("Drawing Pad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setBackground(Color.WHITE);
        canvas.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());
                count.setText("Clicks: " + points.size());
                canvas.repaint();
            }
        });
        add(canvas, BorderLayout.CENTER);
        add(count, BorderLayout.SOUTH);
        setSize(600,400); setLocationRelativeTo(null);
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new Main().setVisible(true)); }
}
