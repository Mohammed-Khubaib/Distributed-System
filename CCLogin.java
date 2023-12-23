import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class CCLogin implements ActionListener {
    JFrame frame1;
    JTextField tf, tf1;
    JButton button;
    JLabel heading;
    JLabel label, label1;

    public static void main(String[] paramArrayOfString) {
        new CCLogin();
    }

    public CCLogin() {
        this.frame1 = new JFrame("Login Page");
        this.tf = new JTextField(10);
        this.button = new JButton("Login");
        this.heading = new JLabel("Chat Server");
        this.heading.setFont(new Font("Impact", 1, 40));
        this.label = new JLabel("Enter you Login Name");
        this.label.setFont(new Font("Serif", 0, 24));
        JPanel localJPanel = new JPanel();
        this.button.addActionListener(this);
        localJPanel.add(this.heading);
        localJPanel.add(this.label);
        localJPanel.add(this.tf);
        localJPanel.add(this.button);
        this.heading.setBounds(30, 20, 280, 50);
        this.label.setBounds(20, 100, 250, 60);
        this.tf.setBounds(50, 150, 150, 30);
        this.button.setBounds(70, 190, 90, 30);
        this.frame1.add(localJPanel);
        localJPanel.setLayout(null);
        this.frame1.setSize(300, 300);
        this.frame1.setVisible(true);
        this.frame1.setDefaultCloseOperation(3);
    }

    public void actionPerformed(ActionEvent paramActionEvent) {
        String str = "";
        try {
            str = this.tf.getText();
            this.frame1.dispose();
            Client1 c1 = new Client1(str);
            c1.main(null);
        } catch (Exception localIOException) {
        }
    }
}