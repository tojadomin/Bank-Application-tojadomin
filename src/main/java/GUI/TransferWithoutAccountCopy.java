package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class TransferWithoutAccountCopy extends JFrame implements ActionListener {

    int transferSum; // kwota przelewu
    int accountNumber; // numer konta na który przelewamy kase
    String name;
    String lastName;
    String title;

    JButton buttonDo = new JButton();
    JButton buttonBack = new JButton();
    JFrame framePrzelew = new JFrame();
    JLabel accNumber = new JLabel();
    JLabel nameLabel = new JLabel();
    JLabel lastNameLabel = new JLabel();
    JLabel sum = new JLabel();
    JLabel titleLabel = new JLabel();

    JTextField accNumberField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField sumField = new JTextField();
    JTextArea titleArea = new JTextArea();
    JScrollPane titlePane = new JScrollPane(titleArea);
    private int balance;

    public int getBalance() {
        int bal = balance;
        return bal;
    }

    public void setBalance(int bal) {
        this.balance = bal;
    }

    TransferWithoutAccountCopy() {

        accNumber.setText("Numer Konta: ");
        accNumber.setBounds(25, 50, 300, 25);
        framePrzelew.add(accNumber);

        accNumberField.setBounds(125, 50, 300, 25);
        framePrzelew.add(accNumberField);

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

        sum.setText("Kwota: ");
        sum.setBounds(25, 140, 300, 25);
        framePrzelew.add(sum);

        sumField.setBounds(125, 140, 300, 25);
        framePrzelew.add(sumField);

        titleLabel.setText("Tytuł przelewu: ");
        titleLabel.setBounds(25, 170, 300, 25);
        framePrzelew.add(titleLabel);

        titlePane.setBounds(125, 170, 300, 100);
        framePrzelew.add(titlePane);

        buttonDo.setBounds(150, 320, 100, 50);
        buttonDo.setFont(new Font("Arial", Font.BOLD, 14));
        buttonDo.setText("Wykonaj.");
        buttonDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {

                name = nameField.getText();
                lastName = lastNameField.getText();
                title = titleArea.getText();

                try {
                    transferSum = Integer.parseInt(sumField.getText());
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }

                try {
                    accountNumber = Integer.parseInt(accNumberField.getText());
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }

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

                    Date date = new Date();


                    statement = con.createStatement();

                    String sql3 = "SELECT saldo FROM historia WHERE numer_konta = " + accountNumber;


                    resultSet = statement.executeQuery(sql3);

                    while (resultSet.next()) {


                        balance = resultSet.getInt("saldo");


                        System.out.println(accountNumber);
                        transferSum = transferSum + resultSet.getInt(balance);
                    }

                    System.out.println(getBalance() + "getbalance");
                    System.out.println(transferSum + "trsfsum");
                    // transferSum = transferSum+  resultSet.getInt(balance);
                    System.out.println(transferSum + "trsfsum");
                    String sql = "UPDATE klient SET balance = " + transferSum + " WHERE numer_konta = " + accountNumber;

                    int row = statement.executeUpdate(sql);
                    String sqlHistory = "INSERT INTO historia ( pesel, data, kwota, typ, saldo, numer_konta) VALUES ('11','" +
                            date.toString() + "', '" + transferSum + "', 'Uznanie','" + balance + "','" + accountNumber + "')";

                    int row2 = statement.executeUpdate(sqlHistory);

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

        framePrzelew.add(buttonBack);
        framePrzelew.setLayout(null);
        framePrzelew.setSize(600, 450);
        framePrzelew.setVisible(true);
        framePrzelew.setLocationRelativeTo(null);
        framePrzelew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}