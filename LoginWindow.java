import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class LoginWindow {
    private JFrame frame;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton, exit;
    private FileHandler fileHandler;
    private JLabel registerButton;

    public LoginWindow() {
        frame = new JFrame("Login");
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        fileHandler = new FileHandler("users.txt");

        Border border = BorderFactory.createLineBorder(new Color(147, 112, 219), 3);
        Border exborder = BorderFactory.createLineBorder(new Color(255, 20, 147), 3);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(360, 250, 100, 30);
        usernameLabel.setFont(new Font("Super Bubble", Font.PLAIN, 13));
        frame.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(470, 250, 150, 30);
        usernameTextField.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
        usernameTextField.setBorder(border);
        usernameTextField.setBackground(new Color(251, 174, 210));
        frame.add(usernameTextField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(360, 300, 100, 30);
        passwordLabel.setFont(new Font("Super Bubble", Font.PLAIN, 13));
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(470, 300, 150, 30);
        passwordField.setBorder(border);
        passwordField.setBackground(new Color(251, 174, 210));
        frame.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(450, 370, 80, 30);
        loginButton.setFont(new Font("Super Bubble", Font.PLAIN, 13));
        loginButton.setBorder(border);
        loginButton.setBackground(new Color(251, 174, 210));
        loginButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String username = usernameTextField.getText();
            String password = String.valueOf(passwordField.getPassword());
               if (authenticateUser(username, password)) {
                try {
                    openUserWindow(username);
                } catch (IOException e1) {
                
                }
                 frame.dispose();
            } else if (username.equals("customer") && password.equals("123")) {
                CustomerWindow customerWindow = new CustomerWindow();
                customerWindow.display();
                frame.dispose();
            } else if (username.equals("teej") && password.equals("pogi")) {
                AdminWindow adminWindow = new AdminWindow();
                adminWindow.display();
                frame.dispose();
            } else if (username.equals("aaron") && password.equals("pogi")) {
                AdminWindow adminWindow = new AdminWindow();
                adminWindow.display();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password!");
        }
    }
});
        frame.add(loginButton);

        registerButton = new JLabel("Click here to Register");
        registerButton.setBounds(470, 330, 250, 30);
        registerButton.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        registerButton.setForeground(Color.white);
        //registerButton.setBorder(border);
        //registerButton.setBackground(new Color(222,93,131));
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                register registerwindow = new register();
                registerwindow.display();
                
                
            }
        });
        frame.add(registerButton);

        exit = new JButton("X");
        exit.setBounds(975, 5, 20, 20);
        exit.setBorder(BorderFactory.createBevelBorder(1));
        exit.setBorder(exborder);
        exit.setBackground(new Color(251, 174, 210));
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                frame.dispose();
            }
        });

        frame.add(exit);

        ImageIcon backgroundImage = new ImageIcon("backgrounds/mainmenu.gif");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 0, 0);
        frame.add(backgroundLabel);
    }

    public void display() {
        frame.setVisible(true);
    }

    private boolean authenticateUser(String username, String password) {
        String[] userDetails = fileHandler.readOrders().toArray(new String[0]);
        for (String user : userDetails) {
            String[] parts = user.split(":");
            if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void openUserWindow(String username) throws IOException {
        if (username.equals("")) {
            AdminWindow adminWindow = new AdminWindow();
            adminWindow.display();
        } else {
            FileWriter teej = new FileWriter("currentUser.txt");
            teej.write(username);
            teej.close();
            CustomerWindow customerWindow = new CustomerWindow();
            customerWindow.display();
        }
    }
}
