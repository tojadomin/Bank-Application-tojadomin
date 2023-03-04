package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {

    private int withdrawValue;
    private int balanceBeforeTransaction;
    private int balanceAfterTransaction;
    private int accountNumber;// numer konta z ktorego dokonywana jest wypłata

    JButton button1;
    JButton button2;
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JLabel sumLabel = new JLabel();
    JLabel messageLabel = new JLabel();
    JTextField sumField = new JTextField();
    ClientData client = new ClientData();
    Date date = new Date();

    public int getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(int balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public int getWithdrawValue() {
        return withdrawValue;
    }

    public void setWithdrawValue(int withdrawValue) {
        this.withdrawValue = withdrawValue;
    }

    public int getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public void setBalanceBeforeTransaction(int balanceBeforeTransaction) {
        this.balanceBeforeTransaction = balanceBeforeTransaction;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    Withdraw() {
        messageLabel.setFont(new Font(null, Font.ITALIC, 15));
        messageLabel.setBounds(200, 220, 200, 50);
        messageLabel.setForeground(Color.BLACK);

        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(600, 450));

        sumLabel.setText("Kwota wypłaty: ");
        sumLabel.setBounds(150, 150, 100, 30);
        frame.add(sumLabel);

        sumField.setBounds(250, 150, 100, 30);
        frame.add(sumField);

        button1 = new JButton("Wykonaj.");
        button1.setBounds(150, 300, 100, 30);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.DARK_GRAY);
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent f) {
                try {
                    setWithdrawValue(Integer.parseInt(sumField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("To nie jest liczba!");
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

                    statement = con.createStatement();
                    String sqlSelect2 = "SELECT * FROM klient WHERE Pesel =" + HomePage.peselValueFromField;
                    resultSet = statement.executeQuery(sqlSelect2);

                    while (resultSet.next()) {
                        client.setName(resultSet.getString("Imie"));
                        client.setLastName(resultSet.getString("Nazwisko"));
                        setBalanceBeforeTransaction(resultSet.getInt("Saldo"));
                        setAccountNumber(resultSet.getInt("numer_konta"));
                        setBalanceAfterTransaction(getBalanceBeforeTransaction() - getWithdrawValue());
                    }

                    String sql = "UPDATE klient SET Saldo = " + getBalanceAfterTransaction() + " WHERE pesel = " + HomePage.peselValueFromField;
                    String sqlHistory = "INSERT INTO historia "
                            + "(pesel, data, kwota, typ, saldo, numer_konta, saldoprzed, tytuł,odbiorca,nadawca)"
                            + " VALUES ('" + HomePage.peselValueFromField + "', '"
                            + date.toString() + "', '" + getWithdrawValue() + "', 'Obciążenie','"
                            + getBalanceAfterTransaction() + "','" + getAccountNumber() + "','" + getBalanceBeforeTransaction() + "','Wypłata','"
                            + client.getName() +" "+ client.getLastName() + "','" + client.getName()  +" "+ client.getLastName() + "',)";

                    if (getWithdrawValue() <= getBalanceBeforeTransaction()) {
                        messageLabel.setText("Operacja wykonana.");
                        int row = statement.executeUpdate(sqlHistory);
                        int row2 = statement.executeUpdate(sql);
                        sumField.setText("");
                    } else {
                        messageLabel.setText("Brak środków.");
                    }
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
        frame.add(button1);
        button2 = new JButton("Wstecz.");
        button2.setBounds(300, 300, 100, 30);
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.DARK_GRAY);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomePage welcomePage = new WelcomePage();
                frame.dispose();
            }
        });
        frame.add(button2);
        frame.add(messageLabel);
        frame.add(panel);
        frame.setSize(600, 450);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
