import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;


public class OrderWindow {
    
    private JFrame frame;
    private JComboBox<String> packageComboBox = new JComboBox<>();
    private JPanel quantityPanel;
    private JLabel userDetailLabel, paymentLabel;
    private JTextField userDetailTextField;
    private JButton gcashButton, paymayaButton, bankTransferButton, takeOrderButton, backButton;
    private String activeUser;
    private JTextArea additionalNoteTextArea;
    private JTextField quantityTextField;
    private JTextField contInfoTextField;
    private static int orderNumber = 0;
    
    public OrderWindow() {
        frame = new JFrame("Order Window");
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.black, 2);

        JLabel packageLabel = new JLabel("Package:");
        packageLabel.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        packageLabel.setBounds(79, 75, 80, 30);
        frame.add(packageLabel);

        JLabel contInfoLabel = new JLabel("Please provide contact information:  (OPTIONAL)");
        contInfoLabel.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        contInfoLabel.setBounds(521, 120, 3000,30);
        frame.add(contInfoLabel);
;
        packageComboBox.addItem(" ");
        packageComboBox.addItem("League RP");
        packageComboBox.addItem("Valorant Points");
        packageComboBox.addItem("Genshin Crystals");
        packageComboBox.addItem("Spotify Premium");
        packageComboBox.addItem("Netflix");
        packageComboBox.addItem("Discord Nitro");
        packageComboBox.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
        packageComboBox.setBounds(79, 105, 150, 30);
        packageComboBox.setBorder(border);
        frame.add(packageComboBox);

        JLabel quantityLabel = new JLabel("Item:");
        quantityLabel.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        quantityLabel.setBounds(79, 134, 80, 30);
        frame.add(quantityLabel);

        quantityPanel = new JPanel();
        quantityPanel.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        quantityPanel.setBounds(79, 164, 375, 200);
        quantityPanel.setLayout(new GridLayout(0, 2, 10, 10));
        quantityPanel.setOpaque(false);
        frame.add(quantityPanel);

        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodLabel.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        paymentMethodLabel.setBounds(79, 364, 120, 30);
        frame.add(paymentMethodLabel);

        gcashButton = new JButton("GCash");
        gcashButton.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        gcashButton.setBounds(79, 394, 120, 30);
        gcashButton.setBorder(border);
        gcashButton.setBackground(Color.white);
        gcashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentLabel.setText("GCash number: 09166437736");
            }
        });
        frame.add(gcashButton);

        paymayaButton = new JButton("PayMaya");
        paymayaButton.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        paymayaButton.setBounds(259, 394, 120, 30);
        paymayaButton.setBorder(border);
        paymayaButton.setBackground(Color.white);
        paymayaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentLabel.setText("PayMaya number: 09166437736");
            }
        });
        frame.add(paymayaButton);

        bankTransferButton = new JButton("Bank Transfer");
        bankTransferButton.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        bankTransferButton.setBounds(169, 442, 120, 30);
        bankTransferButton.setBorder(border);
        bankTransferButton.setBackground(Color.white);
        bankTransferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentLabel.setText("Paypal: marcelinobabyniella@gmail.com");
            }
        });
        frame.add(bankTransferButton);

        paymentLabel = new JLabel();
        paymentLabel.setFont(new Font("Super Bubble", Font.PLAIN, 15));
        paymentLabel.setBounds(100, 486, 4000, 30);
        frame.add(paymentLabel);

        takeOrderButton = new JButton("Take Order");
        takeOrderButton.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        takeOrderButton.setBounds(521, 280, 120, 30);
        takeOrderButton.setBorder(border);
        takeOrderButton.setBackground(Color.white);
        takeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String packageSelected = (String) packageComboBox.getSelectedItem();
                String quantity = getSelectedQuantity();
                String contInfo = contInfoTextField.getText();
                String userDetail = userDetailTextField.getText();
                String additionalNote = additionalNoteTextArea.getText();

                if (packageSelected.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select a package!");
        } else if (quantity.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select an item!");
        } else if (quantityTextField.getText().equals("0")) {
            JOptionPane.showMessageDialog(frame,"Please select a quantity greater than 0!");
        } else if (userDetail.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please provide user details!");
        } else if (paymentLabel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select a payment method!");

        } else {
            try {
                activeUser = new String(Files.readAllBytes(Paths.get("currentUser.txt")));
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                orderNumber++;
                String QueNumber = String.valueOf(orderNumber);
                
                String order = "Que:" + QueNumber + " " + activeUser + ": " + packageSelected + " - Item: " +
                quantity + " (Quantity: " + quantityTextField.getText() + "x)" + " - " +
                userDetail + " - " + paymentLabel.getText() +" Contact Info: " +contInfo;

                String orderWithRandomQue = order; // Append the random number
                String orderWithNote = orderWithRandomQue + "\n" + "Note: " + additionalNote;
                FileHandler fileHandler = new FileHandler("src/orders.txt");
                fileHandler.writeOrder(orderWithNote);
                JOptionPane.showMessageDialog(frame, "Order placed successfully!");
                frame.dispose();
            }
        }
    }
});
        frame.add(takeOrderButton);

        quantityTextField = new JTextField("0", SwingConstants.CENTER);
        quantityTextField.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
        quantityTextField.setBounds(250, 105, 80, 30);
        quantityTextField.setBorder(border);
        quantityTextField.setEditable(false);
        frame.add(quantityTextField);


        contInfoTextField = new JTextField();
        contInfoTextField.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
        contInfoTextField.setBounds(521, 150, 400, 30);
        contInfoTextField.setBorder(border);
        frame.add(contInfoTextField);

        userDetailLabel = new JLabel();
        userDetailLabel.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        userDetailLabel.setBounds(521, 189, 600, 30);
        frame.add(userDetailLabel);

        userDetailTextField = new JTextField();
        userDetailTextField.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
        userDetailTextField.setBounds(521, 219, 400, 30);
        userDetailTextField.setBorder(border);
        frame.add(userDetailTextField);

        packageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPackage = (String) packageComboBox.getSelectedItem();
                generateQuantityButtons(selectedPackage);
                setUserDetailLabel(selectedPackage);
            }
        });

        backButton = new JButton("Back");
        backButton.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        backButton.setBounds(658, 280, 120, 30);
        backButton.setBorder(border);
        backButton.setBackground(Color.white);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // Perform any additional back button functionality here
            }
        });
        frame.add(backButton);

        JButton minusButton = new JButton("-");
        minusButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        int currentValue = Integer.parseInt(quantityTextField.getText());
        if (currentValue > 0) {
            quantityTextField.setText(String.valueOf(currentValue - 1));
                }
            }
        });
        minusButton.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        minusButton.setBounds(339, 105, 40, 30);
        minusButton.setBorder(border);
        frame.add(minusButton);

        JButton plusButton = new JButton("+");
        plusButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        int currentValue = Integer.parseInt(quantityTextField.getText());
        quantityTextField.setText(String.valueOf(currentValue + 1));
            }
        });
        plusButton.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        plusButton.setBounds(380, 105, 40, 30);
        plusButton.setBorder(border);
        frame.add(plusButton);
        

        JLabel additionalNoteLabel = new JLabel("Additional Note:");
        additionalNoteLabel.setFont(new Font("Super Bubble", Font.PLAIN, 10));
        additionalNoteLabel.setBounds(521, 334, 150, 30);
        frame.add(additionalNoteLabel);

        additionalNoteTextArea = new JTextArea();
        additionalNoteTextArea.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
        additionalNoteTextArea.setBounds(521, 364, 400, 100);
        additionalNoteTextArea.setBorder(border);
        frame.add(additionalNoteTextArea);
    }

    public void display() {
        ImageIcon backgroundImage = new ImageIcon("backgrounds/mainbg.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 1000, 600);
        frame.add(backgroundLabel);
        frame.setVisible(true);
    }
    LinkedList<String> lolprice = new LinkedList<>();
    LinkedList<String> valoprice = new LinkedList<>();
    LinkedList<String> genshinprice = new LinkedList<>();
    LinkedList<String> spotprice = new LinkedList<>();
    LinkedList<String> netfprice = new LinkedList<>();
    LinkedList<String> blank = new LinkedList<>();
    LinkedList<String> discprice = new LinkedList<>();

    private LinkedList<String> getPackageQuantities(String packageName) {
        if (packageName.equals("League RP")) {
            lolprice.add("200 RP (PHP 46.55)");
            lolprice.add("625 RP (PHP 141.55)");
            lolprice.add("1525 RP (PHP 331.55)");
            lolprice.add("2900 RP (PHP 616.55)");
            lolprice.add("4600 RP (PHP 949)");
            lolprice.add("10000 RP (PHP 1985)");
            return lolprice;
        } else if (packageName.equals("Valorant Points")) {
            valoprice.add("125 VP (PHP 47)");
            valoprice.add("380 VP (PHP 142)");
            valoprice.add("790 VP (PHP 285)");
            valoprice.add("1650 VP (PHP 570)");
            valoprice.add("2850 VP (PHP 950)");
            valoprice.add("5800 (PHP 1900)");
            valoprice.add("12,500 VP(PHP 3,800)");
            return valoprice;
        } else if (packageName.equals("Genshin Crystals")) {
            genshinprice.add("60 Crystals (PHP 55)");
            genshinprice.add("300 Crystals (PHP 280)");
            genshinprice.add("980 Crystals (PHP 830)");
            genshinprice.add("1980 Crystals (PHP 1670)");
            genshinprice.add("3280 Crystals (PHP 2800)");
            genshinprice.add("6480 Crystals (PHP 5500)");
            return genshinprice;
        } else if (packageName.equals("Spotify Premium")) {
            spotprice.add("Premium (PHP 65)");
            return spotprice;
        } else if (packageName.equals("Netflix")) {
            netfprice.add("Solo Profile (PHP 85)");
            netfprice.add("Solo Account (PHP 380)");
            return netfprice;
        } else if (packageName.equals("Discord Nitro")) {
            discprice.add("1 Nitro boost (PHP 150)");
            discprice.add("2 Nitro boost (PHP 250)");
            return discprice;
        };
        return blank;
    }
    
    private void initializeQuantityButtons(LinkedList<String> quantities) {

        for (String quantity : quantities) {

            JButton button = new JButton(quantity);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetQuantityButtons();
                    button.setEnabled(false);
                }
            });
            quantityPanel.add(button);
        }
    }

    private void generateQuantityButtons(String selectedPackage) {
        quantityPanel.removeAll();

       LinkedList<String> quantities = getPackageQuantities(selectedPackage);
        initializeQuantityButtons(quantities);

        frame.revalidate();
        frame.repaint();
    }
    

    private void setUserDetailLabel(String selectedPackage) {
        if (selectedPackage.equals("League RP") || selectedPackage.equals("Valorant Points")) {
            userDetailLabel.setText("Please provide user details (ex. Teej#pogi)");
        } else if (selectedPackage.equals("Genshin Crystals")) {
            userDetailLabel.setText("Please provide UID (ex. 801985911)");
        } else if (selectedPackage.equals("Spotify Premium") || selectedPackage.equals("Netflix")) {
            userDetailLabel.setText("Please provide an email (we will send the receipt through your email)");
        } else if (selectedPackage.equals("Discord Nitro")) {
            userDetailLabel.setText("Please provide your Discord ID (ex. Teej#1234)");
        } else {
            userDetailLabel.setText("WELCOME TO BLITZ!");
        }
    }

    private String getSelectedQuantity() {
        Component[] components = quantityPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (!button.isEnabled()) {
                    return button.getText();
                }
            }
        }
        return "";
    }

    private void resetQuantityButtons() {
        Component[] components = quantityPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setEnabled(true);
            }
        }
    }
}
