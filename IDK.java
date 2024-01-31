import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotatingSquare extends JPanel implements ActionListener {
    private double angle = 0;
    private Timer timer;

    public RotatingSquare() {
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int sideLength = Math.min(width, height) / 2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fillRect(centerX - sideLength / 2, centerY - sideLength / 2, sideLength, sideLength);
        g2d.rotate(Math.toRadians(angle), centerX, centerY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 1; // เพิ่มองศาทีละ 1 องศา
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotating Square");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new RotatingSquare());
        frame.setVisible(true);
    }
}
