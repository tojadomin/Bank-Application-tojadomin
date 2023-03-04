package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    private int balanceAfterTransaction; //??
    private int depositValue; // wartość która chcemy wplacic
    private int balanceBeforeTransaction;// saldo konta
    private int accountNumber;// numer konta na które wplacamy pobierany z bazy danych i uzupelniany w tabeli historia

    ClientData client = new ClientData();
    JFrame frame = new JFrame();
    JLabel sumLabel = new JLabel();
    JLabel messageLabel = new JLabel();
    JTextField sumField = new JTextField();
    JPanel panel = new JPanel();
    JButton button1;
    JButton button2;
    Date date = new Date();

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(int balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public int getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(int depositValue) {
        this.depositValue = depositValue;
    }

    public int getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public void setBalanceBeforeTransaction(int balanceBeforeTransaction) {
        this.balanceBeforeTransaction = balanceBeforeTransaction;
    }

    Deposit() {
        messageLabel.setFont(new Font(null, Font.ITALIC, 15));
        messageLabel.setBounds(200, 220, 200, 50);
        messageLabel.setForeground(Color.BLACK);
        frame.add(messageLabel);

        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(600, 450));


        sumLabel.setText("Kwota wpłaty: ");
        sumLabel.setBounds(150, 150, 100, 30);
        frame.add(sumLabel);

        sumField.setBounds(250, 150, 100, 30);
        frame.add(sumField);

        button1 = new JButton("Wykonaj.");
        button1.setBounds(150, 300, 100, 30);
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent f) {

                try {
                    setDepositValue(Integer.parseInt(sumField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }


                Connection con = null;
                Statement statement = null;
                ResultSet resultSet = null;
                ResultSet resultSet2 = null;

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
                        String name = resultSet.getString("Imie");
                        String lastName = resultSet.getString("Nazwisko");
                        int balanceValue = resultSet.getInt("Saldo");
                        int accNum = resultSet.getInt("numer_konta");

                        client.setName(name);
                        client.setLastName(lastName);
                        setAccountNumber(accNum);
                        setBalanceBeforeTransaction(balanceValue);
                        setBalanceAfterTransaction(getBalanceBeforeTransaction() + getDepositValue());
                    }

                    String sql = "UPDATE klient SET Saldo = " + getBalanceAfterTransaction() + " WHERE pesel = " + HomePage.peselValueFromField;

                    String sqlHistory = "INSERT INTO historia "
                            + "(pesel, data, kwota, typ, saldo, numer_konta, saldoprzed, tytuł,odbiorca)"
                            + " VALUES ('" + HomePage.peselValueFromField + "', '"
                            + date.toString() + "', '" + getDepositValue() + "', 'Uznanie','"
                            + getBalanceAfterTransaction() + "','" + getAccountNumber() + "','" + client.getName() + "','" + client.getLastName()
                            + "','" + getBalanceBeforeTransaction() + "','Wpłata','" + client.getName()
                            + client.getLastName() + "','" + client.getName() + " " + client.getLastName() + "',)";


                    int row = statement.executeUpdate(sql);
                    int row2 = statement.executeUpdate(sqlHistory);
                    messageLabel.setText("Operacja wykonana.");
                    sumField.setText("");


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
        button1.setBackground(Color.DARK_GRAY);
        button1.setForeground(Color.WHITE);

        frame.add(button1);
        button2 = new JButton("Wstecz.");
        button2.setBounds(300, 300, 100, 30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomePage welcomePage = new WelcomePage();
                frame.dispose();
            }
        });
        button2.setBackground(Color.DARK_GRAY);
        button2.setForeground(Color.WHITE);

        frame.add(button2);
        frame.add(panel);
        frame.setSize(600, 450);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (button1.equals(e.getSource())) {
            System.out.println("Klik button 1.");
        } else if (button2.equals(e.getSource())) {
            System.out.println("Klik button 2.");
        }
    }
}
