import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CompassAnimationWithBackground extends JPanel implements ActionListener {
    private Timer timer;
    private double angle = 0;
    private final int centerX = 300;
    private final int centerY = 300;
    private final int radius = 100;
    private Image backgroundImage;

    public CompassAnimationWithBackground() {
        timer = new Timer(100, this); // Animation timer (update every 100 ms)
        timer.start();

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("E:\\Javalesson\\CompassAnimationwithJava\\background.jpg")); // Replace
                                                                                                                  // with
                                                                                                                  // your
                                                                                                                  // image
                                                                                                                  // path
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the background image
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw Compass (a simple cross to represent directions)
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(centerX - 50, centerY, centerX + 50, centerY); // Horizontal line (East-West)
        g2d.drawLine(centerX, centerY - 50, centerX, centerY + 50); // Vertical line (North-South)

        // Add Directions (N, E, S, W)
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("N", centerX - 10, centerY - 60);
        g2d.drawString("S", centerX - 10, centerY + 75);
        g2d.drawString("E", centerX + 60, centerY + 5);
        g2d.drawString("W", centerX - 75, centerY + 5);

        // Draw Rotating Text "රට අනුරට"
        // Set font to a Sinhala compatible font (e.g., Iskoola Pota)
        g2d.setFont(new Font("Iskoola Pota", Font.BOLD, 24)); // Change to a Sinhala-supporting font
        g2d.setColor(Color.RED);

        int textX = centerX + (int) (radius * Math.cos(angle));
        int textY = centerY + (int) (radius * Math.sin(angle));

        g2d.drawString("රට අනුරට", textX, textY);

        // Update angle for rotation
        angle += Math.toRadians(5); // Rotate by 5 degrees each frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Redraw the frame for animation
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Compass Animation - රට අනුරට");
        CompassAnimationWithBackground animation = new CompassAnimationWithBackground();
        frame.add(animation);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
