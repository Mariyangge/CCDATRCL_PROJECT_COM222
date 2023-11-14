import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CustomerWindow {
    private JFrame frame;
    private JLabel orderButton, viewOrderButton, viewTransactionsButton, logoutButton, welcomeLabel, aboutLabel;
    private String activeUser;

    public CustomerWindow() {
        frame = new JFrame("Customer Window");
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        try {
            activeUser = new String(Files.readAllBytes(Paths.get("currentUser.txt")));
        } catch (IOException e) {

        }
        finally{
            welcomeLabel = new JLabel("Welcome, " + activeUser);
            welcomeLabel.setFont(new Font("Super Bubble", Font.PLAIN, 40));
            welcomeLabel.setBounds(146, 116, 1000, 51);
            frame.add(welcomeLabel);
        
        aboutLabel = new JLabel();
        aboutLabel.setIcon(new ImageIcon("backgrounds/about blitz.png"));
        aboutLabel.setBounds(47, 160, 678, 260);
        frame.add(aboutLabel);

        orderButton = new JLabel();
        orderButton.setIcon(new ImageIcon("buttons/placeorder.png"));
        orderButton.setBounds(50, 393, 185, 150);
        orderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OrderWindow orderWindow = new OrderWindow();
                orderWindow.display();
            }
        }); 
        frame.add(orderButton);

        viewOrderButton = new JLabel();
        viewOrderButton.setIcon(new ImageIcon("buttons/vieworders.png"));
        viewOrderButton.setBounds(265, 393, 185, 150);
        viewOrderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileHandler fileHandler = new FileHandler("src/orders.txt");
                List<String> orders = fileHandler.readOrders();
                OrderListWindow orderListWindow = new OrderListWindow(orders);
                orderListWindow.display();
            }
        });
        frame.add(viewOrderButton);

        viewTransactionsButton = new JLabel();
        viewTransactionsButton.setIcon(new ImageIcon("buttons/transactions.png"));
        viewTransactionsButton.setBounds(480, 393, 185, 150);
        viewTransactionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileHandler fileHandler = new FileHandler("transactions.txt");
                List<String> orders = fileHandler.readOrders();
                TransactionsWindow transactionsWindow = new TransactionsWindow(orders);
                transactionsWindow.display();
            }
        });
        frame.add(viewTransactionsButton);

        logoutButton = new JLabel();
        logoutButton.setIcon(new ImageIcon("buttons/logout.png"));
        logoutButton.setBounds(19, 21, 25, 25);
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Perform logout operation here
                frame.dispose();
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.display();
            }
        });
        frame.add(logoutButton);
    }
}

public void display() {
    ImageIcon backgroundImage = new ImageIcon("backgrounds/Homepage.png");
    JLabel backgroundLabel = new JLabel(backgroundImage);
    backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    frame.add(backgroundLabel);

    frame.setVisible(true);
}

}
