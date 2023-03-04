package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class TransferWithoutAccount extends JFrame implements ActionListener {

    ClientData client = new ClientData();

    private int transferSum; // kwota przelewu
    private int accountNumber; // numer konta na który przelewamy kase
    private String title; // tytuł przelewu
    private int balanceAfterTransaction; //saldo konta na ktore przelewamy po dokonaniu wpłaty
    private String recipient;// zmienna przechowuje dane odbiorcy przelewu

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public int getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(int balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public int getTransferSum() {
        return transferSum;
    }

    public void setTransferSum(int transferSum) {
        this.transferSum = transferSum;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    JButton buttonDo = new JButton();
    JButton buttonBack = new JButton();
    JFrame framePrzelew = new JFrame();
    JLabel accNumberLabel = new JLabel();
    JLabel nameLabel = new JLabel();
    JLabel lastNameLabel = new JLabel();
    JLabel sumLabel = new JLabel();
    JLabel titleLabel = new JLabel();
    JLabel recipientLabel = new JLabel();
    JLabel messageLabel = new JLabel();

JTextField recipientField = new JTextField();
    JTextField accNumberField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField sumField = new JTextField();
    JTextArea titleArea = new JTextArea();
    JScrollPane titlePane = new JScrollPane(titleArea);
    JPanel panel = new JPanel();
    private int balanceBeforeTransaction;// saldo konta na które przelewamy przed wykonaniem transakcji

    public int getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public void setBalanceBeforeTransaction(int balanceBeforeTransaction) {
        this.balanceBeforeTransaction = balanceBeforeTransaction;
    }

    TransferWithoutAccount() {

        accNumberLabel.setText("Numer Konta: ");
        accNumberLabel.setBounds(25, 20, 300, 25);
        framePrzelew.add(accNumberLabel);

        accNumberField.setBounds(125, 20, 300, 25);
        framePrzelew.add(accNumberField);

        recipientLabel.setText("Odbiorca: ");
        recipientLabel.setBounds(25, 50, 300, 25);
        framePrzelew.add(recipientLabel);

        recipientField.setBounds(125, 50, 300, 25);
        framePrzelew.add(recipientField);

        nameLabel.setText("Imię: ");
        nameLabel.setBounds(25, 80, 300, 25);
        framePrzelew.add(nameLabel);

        nameField.setBounds(125, 80, 300, 25);
        framePrzelew.add(nameField);

        lastNameLabel.setText("Nazwisko: ");
        lastNameLabel.setBounds(25, 110, 300, 25);
        framePrzelew.add(lastNameLabel);

        lastNameField.setBounds(125, 110, 300, 25);
        framePrzelew.add(lastNameField);

        sumLabel.setText("Kwota: ");
        sumLabel.setBounds(25, 140, 300, 25);
        framePrzelew.add(sumLabel);

        sumField.setBounds(125, 140, 300, 25);
        framePrzelew.add(sumField);

        titleLabel.setText("Tytuł przelewu: ");
        titleLabel.setBounds(25, 170, 300, 25);
        framePrzelew.add(titleLabel);

        titlePane.setBounds(125, 170, 300, 100);
        framePrzelew.add(titlePane);

        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        messageLabel.setBounds(170, 265, 400, 50);
        messageLabel.setForeground(Color.BLACK);
        framePrzelew.add(messageLabel);

        buttonDo.setBackground(Color.DARK_GRAY);
        buttonDo.setForeground(Color.WHITE);
        buttonDo.setBounds(150, 320, 100, 50);
        buttonDo.setFont(new Font("Arial", Font.BOLD, 14));
        buttonDo.setText("Wykonaj.");
        buttonDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {

                client.setName(nameField.getText());
                client.setLastName(lastNameField.getText());
                setTitle(titleArea.getText());
                setRecipient(recipientField.getText());


                try {
                    setTransferSum(Integer.parseInt(sumField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }

                try {
                    client.setAccountNumber(Integer.parseInt(accNumberField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }
                Date date = new Date();
                Connection con = null;
                Statement statement = null;
                ResultSet resultSet = null;

                try {
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank", "root", "");
                    if (con == null) {
                        System.out.println("Brak polączenia z bazą danych.");
                    } else {
                        System.out.println("Jest połączenie z bazą danych.");
                    }

                    statement = con.createStatement();
                    String sql3 = "SELECT saldo FROM historia WHERE numer_konta = " + client.getAccountNumber();
                    resultSet = statement.executeQuery(sql3);

                    while (resultSet.next()) {
                        setBalanceBeforeTransaction(resultSet.getInt("saldo"));
                    }
                    setBalanceAfterTransaction(getBalanceBeforeTransaction() + getTransferSum());

                    String sql = "UPDATE klient SET Saldo = " + getBalanceAfterTransaction() + " WHERE numer_konta = " + client.getAccountNumber();

                    int row = statement.executeUpdate(sql);
                    String sqlHistory = "INSERT INTO historia " +
                            "(data, tytuł, odbiorca, kwota, typ, saldoprzed, saldo, numer_konta)" +
                            " VALUES ('" + date.toString() + "', '" + client.getName() + "', '" + client.getLastName() +
                            "','" + getTitle() + "' ,'" + getRecipient() + "', '" + getTransferSum() + "', 'Uznanie','" + getBalanceBeforeTransaction() +
                            "','" + getBalanceAfterTransaction() + "','" + client.getAccountNumber() + "')";

                    int row2 = statement.executeUpdate(sqlHistory);


                    messageLabel.setText("Wykonano przelew");
                    titleArea.setText("");
                    nameField.setText("");
                    lastNameField.setText("");
                    accNumberField.setText("");
                    recipientField.setText("");

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        resultSet.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        statement.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        framePrzelew.add(buttonDo);
        buttonBack.setBackground(Color.DARK_GRAY);
        buttonBack.setForeground(Color.WHITE);
        buttonBack.setBounds(300, 320, 100, 50);
        buttonBack.setFont(new Font("Arial", Font.BOLD, 14));
        buttonBack.setText("Wstecz.");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage homePage = new HomePage();
                framePrzelew.dispose();
            }
        });

        panel.setPreferredSize(new Dimension(600, 450));
        panel.setBackground(Color.GRAY);
        framePrzelew.add(buttonBack);
        framePrzelew.add(panel);
        framePrzelew.setSize(600, 450);
        framePrzelew.setVisible(true);
        framePrzelew.setLocationRelativeTo(null);
        framePrzelew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}