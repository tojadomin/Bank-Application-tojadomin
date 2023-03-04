package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class Transfer extends JFrame implements ActionListener {

    private int transferSum; // kwota ktora przelewamy
    private int accountNumber; // numer konta na który przelewamy kase
    private int balanceBeforeTransaction; // saldo konta na którym jesteśmy
    private int balanceAfterTransaction;// saldo konta na którym jesteśmy po transakcji
    private int balanceBeforeTransaction2;//saldo konta na które przelewamy
    private int balanceAfterTransaction2;// saldo konta na które przelewamy po transakcji
    private int myAccountNumber; // moj numer konta
    private String recipientName;//imie odbiorcy
    private String recipientLastName;//nazwisko odbiorcy

    JButton buttonDo = new JButton();
    JButton buttonBack = new JButton();
    JFrame frameTransfer = new JFrame();
    JLabel accNumberLabel = new JLabel();
    JLabel nameLabel = new JLabel();
    JLabel lastNameLabel = new JLabel();
    JLabel sumLabel = new JLabel();
    JLabel titleLabel = new JLabel();
    JLabel messageLabel = new JLabel();

    JTextField accNumberField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField sumField = new JTextField();
    JTextArea titleArea = new JTextArea();
    JScrollPane titlePane = new JScrollPane(titleArea);
    JPanel panel = new JPanel();
    ClientData client = new ClientData();
    Date date = new Date();

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientLastName() {
        return recipientLastName;
    }

    public void setRecipientLastName(String recipientLastName) {
        this.recipientLastName = recipientLastName;
    }

    public int getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(int balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public int getBalanceAfterTransaction2() {
        return balanceAfterTransaction2;
    }

    public void setBalanceAfterTransaction2(int balanceAfterTransaction2) {
        this.balanceAfterTransaction2 = balanceAfterTransaction2;
    }

    public int getBalanceBeforeTransaction2() {
        return balanceBeforeTransaction2;
    }

    public void setBalanceBeforeTransaction2(int balanceBeforeTransaction2) {
        this.balanceBeforeTransaction2 = balanceBeforeTransaction2;
    }

    public int getMyAccountNumber() {
        return myAccountNumber;
    }

    public void setMyAccountNumber(int myAccountNumber) {
        this.myAccountNumber = myAccountNumber;
    }

    public int getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public void setBalanceBeforeTransaction(int balanceBeforeTransaction) {
        this.balanceBeforeTransaction = balanceBeforeTransaction;
    }

    public int getTransferSum() {
        return transferSum;
    }

    public void setTransferSum(int transferSum) {
        this.transferSum = transferSum;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    Transfer() {
        panel.setPreferredSize(new Dimension(600,450));
        panel.setBackground(Color.GRAY);


        messageLabel.setFont(new Font(null, Font.ITALIC, 20));
        messageLabel.setBounds(200, 270, 200, 50);
        messageLabel.setForeground(Color.BLACK);
        frameTransfer.add(messageLabel);

        accNumberLabel.setText("Numer Konta: ");
        accNumberLabel.setBounds(25, 50, 300, 25);
        frameTransfer.add(accNumberLabel);

        accNumberField.setBounds(125, 50, 300, 25);
        frameTransfer.add(accNumberField);

        nameLabel.setText("Imię: ");
        nameLabel.setBounds(25, 80, 300, 25);
        frameTransfer.add(nameLabel);

        nameField.setBounds(125, 80, 300, 25);
        frameTransfer.add(nameField);

        lastNameLabel.setText("Nazwisko: ");
        lastNameLabel.setBounds(25, 110, 300, 25);
        frameTransfer.add(lastNameLabel);

        lastNameField.setBounds(125, 110, 300, 25);
        frameTransfer.add(lastNameField);

        sumLabel.setText("Kwota: ");
        sumLabel.setBounds(25, 140, 300, 25);
        frameTransfer.add(sumLabel);

        sumField.setBounds(125, 140, 300, 25);
        frameTransfer.add(sumField);

        titleLabel.setText("Tytuł przelewu: ");
        titleLabel.setBounds(25, 170, 300, 25);
        frameTransfer.add(titleLabel);

        titlePane.setBounds(125, 170, 300, 100);
        frameTransfer.add(titlePane);

        buttonDo.setBounds(150, 320, 100, 50);
        buttonDo.setFont(new Font("Arial", Font.BOLD, 14));
        buttonDo.setText("Wykonaj.");
        buttonDo.setBackground(Color.DARK_GRAY);
        buttonDo.setForeground(Color.WHITE);
        buttonDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    setTransferSum(Integer.parseInt(sumField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }

                try {
                    setAccountNumber(Integer.parseInt(accNumberField.getText()));
                } catch (NumberFormatException e) {
                    System.err.println("This is not a number!");
                }
                setTitle(titleArea.getText());

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

                    String sql = "SELECT * FROM klient WHERE Pesel =" + HomePage.peselValueFromField;

                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        setBalanceBeforeTransaction(resultSet.getInt("Saldo"));
                        setMyAccountNumber(resultSet.getInt("numer_konta"));
                        setBalanceAfterTransaction(getBalanceBeforeTransaction() - getTransferSum());
                        client.setName(resultSet.getString("imie"));
                        client.setLastName(resultSet.getString("Nazwisko"));
                    }

                    String sql2 = "SELECT * FROM klient WHERE numer_konta =" + getAccountNumber();

                    resultSet2 = statement.executeQuery(sql2);
                    while (resultSet2.next()) {
                        setRecipientName(resultSet2.getString("Imie"));
                        setRecipientLastName(resultSet2.getString("Nazwisko"));
                        client.setPesel(resultSet2.getInt("pesel"));
                        setBalanceBeforeTransaction2(resultSet2.getInt("Saldo"));
                        setBalanceAfterTransaction2(getBalanceBeforeTransaction2() + getTransferSum());
                    }

                    String sqlUpdate = "UPDATE klient SET Saldo = '" + getBalanceAfterTransaction() + "' WHERE pesel = " + HomePage.peselValueFromField;

                    String sqlHistory = "INSERT INTO historia "
                            + "(pesel, data, kwota, typ, saldo, numer_konta, saldoprzed, tytuł, odbiorca, nadawca)"
                            + " VALUES ('" + HomePage.peselValueFromField + "'," +
                            "'" + date.toString() + "','" + getTransferSum() + "','Obciążenie','"
                            + getBalanceAfterTransaction() + "','" + getAccountNumber() + "','"
                            + getBalanceBeforeTransaction() + "','"+ getTitle()+"','"
                            + getRecipientName() + " " + getRecipientLastName() + "','"+ client.getName() + " " + client.getLastName()+"')";

                    String sqlUpdate2 = "UPDATE klient SET Saldo = " + getBalanceBeforeTransaction2() + " WHERE pesel = " + client.getPesel();

                    String sqlHistory2 = "INSERT INTO historia "
                            + "(pesel, data, kwota, typ, saldo, numer_konta, saldoprzed, tytuł, nadawca,odbiorca)"
                            + " VALUES ('" + client.getPesel() + "', '"
                            + date.toString() + "', '" + getTransferSum() + "', 'Uznanie','"
                            + getBalanceAfterTransaction2() + "','" + getAccountNumber() + "','"
                            + getBalanceBeforeTransaction2() + "','"+getTitle()+"','"
                            + client.getName() + " " + client.getLastName() + "','"+ getRecipientName() + " " + getRecipientLastName()+"')";

                    String accNumber = getAccountNumber() + "";
                    String str = "";

                    if (getTransferSum() <= getBalanceBeforeTransaction() && getAccountNumber() != getMyAccountNumber() &&  !accNumberField.getText().equals("")) {
                        System.out.println("0");
                        statement.executeUpdate(sqlUpdate);
                        System.out.println("1");
                        statement.executeUpdate(sqlHistory);
                        System.out.println("2");
                        statement.executeUpdate(sqlUpdate2);
                        System.out.println("3");
                        statement.executeUpdate(sqlHistory2);
                        System.out.println("4");
                        messageLabel.setForeground(Color.BLACK);
                        messageLabel.setText("Operacja wykonana!");
                        accNumberField.setText("");
                        nameField.setText("");
                        lastNameField.setText("");
                        titleArea.setText("");
                        sumField.setText("");
                    } else if (getTransferSum() > getBalanceBeforeTransaction()) {
                        messageLabel.setBounds(140, 270, 300, 50);
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Brak wystarczających środków.");
                    } else if (getAccountNumber() == getMyAccountNumber() || accNumberField.getText().equals("")) {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Błędny numer konta.");
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
                        resultSet2.close();
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
        frameTransfer.add(buttonDo);
        buttonBack.setBounds(300, 320, 100, 50);
        buttonBack.setFont(new Font("Arial", Font.BOLD, 14));
        buttonBack.setText("Wstecz.");
        buttonBack.setBackground(Color.DARK_GRAY);
        buttonBack.setForeground(Color.WHITE);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomePage welcomePage = new WelcomePage();
                frameTransfer.dispose();
            }
        });
        frameTransfer.add(buttonBack);
        frameTransfer.add(panel);
        frameTransfer.setSize(600, 450);
        frameTransfer.setVisible(true);
        frameTransfer.setLocationRelativeTo(null);
        frameTransfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
