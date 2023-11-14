import javax.swing.*;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AdminWindow {
    private JFrame frame;
    private JLabel viewOrdersButton, viewProcessedButton, viewTransactionsButton, logoutButton, welcomeLabel;

    public AdminWindow() {
        frame = new JFrame("Admin Window");
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLayout(null);

        welcomeLabel = new JLabel("Welcome, Admin!" );
        welcomeLabel.setFont(new Font("Super Bubble", Font.PLAIN, 40));
        welcomeLabel.setBounds(146, 250, 1000, 51);
        frame.add(welcomeLabel);

        viewOrdersButton = new JLabel();
        viewOrdersButton.setIcon(new ImageIcon("buttons/oadmin.png"));
        viewOrdersButton.setBounds(81, 420, 135, 44);
        viewOrdersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PaidListWindow paidListWindow = new PaidListWindow();
                paidListWindow.display();
            }
        });
        frame.add(viewOrdersButton);

        viewProcessedButton = new JLabel();
        viewProcessedButton.setIcon(new ImageIcon("buttons/poadmin.png"));
        viewProcessedButton.setBounds(242, 420, 190, 44);
        viewProcessedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileHandler fileHandler = new FileHandler("processed.txt");
                List<String> orders = fileHandler.readOrders();
                ProcessedWindow processedWindow = new ProcessedWindow(orders);
                processedWindow.display();
            }
        });
        frame.add(viewProcessedButton);

        viewTransactionsButton = new JLabel();
        viewTransactionsButton.setIcon(new ImageIcon("buttons/tadmin.png"));
        viewTransactionsButton.setBounds(459, 420, 180, 44);
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

    public void display() {
        ImageIcon backgroundImage = new ImageIcon("backgrounds/Homepage.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(backgroundLabel);

        frame.setVisible(true);
    }

}
