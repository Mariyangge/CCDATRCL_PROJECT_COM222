import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.List;

public class OrderListWindow {
    private JFrame frame;
    private JTable orderTable;
    private JScrollPane scrollPane;
    private JLabel paidButton;
    private JLabel backButton;
    private JLabel deleteButton;
 

    public OrderListWindow(List<String> orders) {
        frame = new JFrame("Order List Window");
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLayout(null);

        JLabel remainingOrdersLabel = new JLabel("Please pay your remaining orders for them to be processed");
        remainingOrdersLabel.setFont(new Font("Super Bubble", Font.PLAIN, 15));
        remainingOrdersLabel.setBounds(211, 130, 1000, 30);
        frame.add(remainingOrdersLabel);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Order");
        tableModel.addColumn("Paid"); // Add the new column for paid status

        orderTable = new JTable(tableModel);
        orderTable.setBounds(215, 172, 570, 200);
        orderTable.setEnabled(true);

        scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(215, 172, 570, 200);
        frame.add(scrollPane);

        for (String order : orders) {
            tableModel.addRow(new Object[]{order, ""}); // Add an empty string for the paid status
        }

        

        paidButton = new JLabel();
        paidButton.setIcon(new ImageIcon("buttons/paid.png"));
        paidButton.setBounds(446, 397, 130, 32);
        paidButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                File inputFile = new File("myFile.txt");
                File tempFile = new File("myTempFile.txt");
                int selectedRow = orderTable.getSelectedRow();
                if (selectedRow != -1) {
                    String selectedOrder = (String) tableModel.getValueAt(selectedRow, 0);

                    int result = JOptionPane.showConfirmDialog(frame, "Are you sure that your order is paid?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        String holder = selectedOrder;
                        selectedOrder = selectedOrder + " (paid)";
                        tableModel.setValueAt(selectedOrder, selectedRow, 0);
                        tableModel.setValueAt("Paid", selectedRow, 1); // Set the paid status

                        try {
                            BufferedReader read = new BufferedReader(new FileReader(inputFile));
                            BufferedWriter write = new BufferedWriter(new FileWriter(tempFile));

                            String lineToRemove = "bbb";
                            String currentLine;

                            while((currentLine = read.readLine()) != null) {
                            // trim newline when comparing with lineToRemove
                            String trimmedLine = currentLine.trim();
                            if(trimmedLine.equals(lineToRemove)) continue;
                            write.write(currentLine + System.getProperty("line.separator"));
                            }
                            write.close(); 
                            read.close(); 
                            boolean successful = tempFile.renameTo(inputFile);
                            
                            FileWriter writer = new FileWriter("paid.txt", true);
                            writer.write(selectedOrder + "\n");
                            writer.close();

                            FileHandler processedFileHandler = new FileHandler("order.txt");
                            if (holder.equals(selectedOrder)) {
                                processedFileHandler.writeOrder(selectedOrder);
                            }

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        frame.add(paidButton);

        deleteButton = new JLabel();
        deleteButton.setIcon(new ImageIcon("buttons/delete.png"));
        deleteButton.setBounds(590, 397, 130, 32);
        deleteButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = orderTable.getSelectedRow();
                if (selectedRow != -1) {
                    String selectedOrder = (String) tableModel.getValueAt(selectedRow, 0);
                    String deletedOrder = selectedOrder + " (DELETED)";

                    int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this order?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        tableModel.setValueAt(deletedOrder, selectedRow, 0);
                        tableModel.setValueAt("DELETED", selectedRow, 1); // Set the deleted status

                        try {
                            // Delete the original order from the file
                            FileHandler fileHandler = new FileHandler("src/orders.txt");
                            fileHandler.deleteOrder(selectedOrder);

                            // Write the deleted order to the file
                            fileHandler.writeOrder(deletedOrder);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        frame.add(deleteButton);

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
