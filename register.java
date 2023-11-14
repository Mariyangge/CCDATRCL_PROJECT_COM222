import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

public class register {
    private JFrame frame;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton registerButton, exit;
    private FileHandler fileHandler;

    public register() {
        frame = new JFrame("Register");
        frame.setSize(550, 370);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(112, 77, 117));

        fileHandler = new FileHandler("users.txt");

        Border border = BorderFactory.createLineBorder(new Color(186, 104, 129), 3);
        Border exborder = BorderFactory.createLineBorder(new Color(255, 20, 147), 3);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.darkGray);
        usernameLabel.setBounds(157, 129, 100, 30);
        usernameLabel.setFont(new Font("Super Bubble", Font.PLAIN, 13));
        frame.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(294, 129, 150, 30);
        usernameTextField.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
        usernameTextField.setBorder(border);
        usernameTextField.setBackground(new Color(140, 92, 120));
        frame.add(usernameTextField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.darkGray);
        passwordLabel.setBounds(157, 192, 100, 30);
        passwordLabel.setFont(new Font("Super Bubble", Font.PLAIN, 13));
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(294, 192, 150, 30);
        passwordField.setBorder(border);
        passwordField.setBackground(new Color(140, 92, 120));
        frame.add(passwordField);

        registerButton = new JButton("Register");
        registerButton.setForeground(Color.darkGray);
        registerButton.setBounds(344, 240, 100, 30);
        registerButton.setFont(new Font("Super Bubble", Font.PLAIN, 13));
        registerButton.setBorder(border);
        registerButton.setBackground(new Color(140, 92, 120));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid username and password!");
                } else if (userExists(username)) {
                    JOptionPane.showMessageDialog(frame, "Username already exists! Please choose a different username.");
                } else {
                    registerUser(username, password);
                    JOptionPane.showMessageDialog(frame, "Registration successful! You can now log in.");
                    frame.dispose();
                    LoginWindow loginWindow = new LoginWindow();
                    loginWindow.display();
                }
            }
        });
        frame.add(registerButton);

        exit = new JButton("X");
        exit.setBounds(525, 5, 20, 20);
        exit.setBorder(BorderFactory.createBevelBorder(1));
        exit.setBorder(exborder);
        exit.setBackground(new Color(251, 174, 210));
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                frame.dispose();
            }
        });

        frame.add(exit);

        ImageIcon backgroundImage = new ImageIcon("backgrounds/registerbg.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(backgroundLabel);
    }
    

    public void display() {

        frame.setVisible(true);
    }

    private boolean userExists(String username) {
        String[] userDetails = fileHandler.readOrders().toArray(new String[0]);
        for (String user : userDetails) {
            String[] parts = user.split(":");
            if (parts.length == 2 && parts[0].equals(username)) {
                return true;
            }
        }
        return false;
    }

    private void registerUser(String username, String password) {
        String userDetails = username + ":" + password;
        fileHandler.writeOrder(userDetails);
    }
}
