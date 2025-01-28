import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class GFG extends JFrame {
    public GFG() {
        setTitle("Welcome to the Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centers the window

        JLabel welcomeLabel = new JLabel("Welcome, User!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel);

        setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        new GFG(); // Start the application
    }
}
