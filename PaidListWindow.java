import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class PaidListWindow {
    private JFrame frame;
    private JTable orderTable;
    private JLabel backButton, processedButton;
    private LinkedList<String> ordersList;  
    
    public PaidListWindow() {
        frame = new JFrame("Paid Orders");
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Paid Orders");
        titleLabel.setBounds(380, 40, 250, 30);
        titleLabel.setFont(new Font("Super Bubble", Font.BOLD, 25));
        frame.add(titleLabel);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Order");
        tableModel.addColumn("Paid");

        orderTable = new JTable(tableModel);
        orderTable.setBounds(210, 100, 581, 278);
        orderTable.setEnabled(true);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(210, 100, 581, 278);
        frame.add(scrollPane);

        FileHandler fileHandler = new FileHandler("paid.txt");
        List<String> orders = fileHandler.readOrders();
        for (String order : orders) {
            Vector ord = new Vector();
            ord.add(order);
            ord.add("paid");
            tableModel.addRow(ord);
        }

        processedButton = new JLabel();
        processedButton.setIcon(new ImageIcon("buttons/processed.png"));
        processedButton.setBounds(435, 431, 250, 45);
        processedButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = orderTable.getSelectedRow();
                if (selectedRow != -1) {
                    String selectedOrder = (String) tableModel.getValueAt(selectedRow, 0);
                    selectedOrder = selectedOrder.replace("(paid)", "(processed)");

                    FileHandler processedFileHandler = new FileHandler("processed.txt");
                    processedFileHandler.writeOrder(selectedOrder);

                    FileHandler transactionsFileHandler = new FileHandler("paid.txt");
                    transactionsFileHandler.writeOrder(selectedOrder);

                    tableModel.setValueAt("Processed", selectedRow, 1); // Set the paid status

                }
            }
        });
        frame.add(processedButton);

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