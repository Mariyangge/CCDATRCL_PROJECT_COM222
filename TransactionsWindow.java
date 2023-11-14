import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TransactionsWindow {
    private JFrame frame;
    private JTable orderTable;
    private JLabel backButton;

    public TransactionsWindow(List<String> orders) {
        frame = new JFrame("Transactions");
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null); 

        JLabel titleLabel = new JLabel("Transactions");
        titleLabel.setBounds(380, 40, 250, 30);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Super Bubble", Font.BOLD, 25));
        frame.add(titleLabel);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Transaction");
        for (String order : orders) {
            tableModel.addRow(new Object[]{order}); 
        }

        orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(210, 100, 581, 278);
        frame.add(scrollPane);

        backButton = new JLabel();
        backButton.setIcon(new ImageIcon("buttons/back.png"));
        backButton.setBounds(954, 13, 27, 27);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        frame.add(backButton);

        ImageIcon backgroundImage = new ImageIcon("backgrounds/mainbg.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(backgroundLabel);
    }

    public void display() {
        frame.setVisible(true);
    }
}
