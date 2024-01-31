import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingRectangle extends JPanel {

    private double angle = 0;
    private boolean bouncing = true;
    private int step = 1;
    private int centerX, centerY;
    private int sideLength;

    public BouncingRectangle() {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bouncing) {
                    angle += step;
                    if (angle >= 45) {
                        step = -1;
                    } else if (angle <= 0) {
                        step = 1;
                    }
                }
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        centerX = width / 2;
        centerY = height / 2;
        sideLength = Math.min(width, height) / 2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.rotate(Math.toRadians(angle), centerX, centerY);
        g2d.fillRect(centerX - sideLength / 2, centerY - sideLength / 2, sideLength, sideLength);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Rotating Rectangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new BouncingRectangle());
        frame.setVisible(true);
    }
}
