import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ProcessedWindow {
    private JFrame frame;
    private JTable orderTable;
    private JLabel orderDoneButton, backButton;

    public ProcessedWindow(List<String> orders) {
        frame = new JFrame("Processed Orders");
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Processed Orders");
        titleLabel.setBounds(355, 40, 1000, 30);
        titleLabel.setFont(new Font("Super Bubble", Font.BOLD, 25));
        frame.add(titleLabel);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Order");
        tableModel.addColumn("Status"); // Add the new column for order status

        orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(210, 100, 581, 278);
        frame.add(scrollPane);

        for (String order : orders) {
            Vector obj = new Vector();
            obj.add(order);
            obj.add("Processed");
            tableModel.addRow(obj); // Set the order status for each order
        }

        orderDoneButton = new JLabel();
        orderDoneButton.setIcon(new ImageIcon("buttons/orderdone.png"));
        orderDoneButton.setBounds(435, 431, 200, 40);
        orderDoneButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = orderTable.getSelectedRow();
                if (selectedRow != -1) {
                    String selectedOrder = (String) tableModel.getValueAt(selectedRow, 0);
                    selectedOrder = selectedOrder.replace("(processed)", "(Order Done)");

                    FileHandler processedFileHandler = new FileHandler("processed.txt");
                    processedFileHandler.writeOrder(selectedOrder);

                    FileHandler transactionsFileHandler = new FileHandler("transactions.txt");
                    transactionsFileHandler.writeOrder(selectedOrder);

                    tableModel.setValueAt("Order Done", selectedRow, 1); // Set the order status

                }
            }
        });
        frame.add(orderDoneButton);

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
