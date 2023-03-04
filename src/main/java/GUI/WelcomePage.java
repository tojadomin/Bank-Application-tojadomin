package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class WelcomePage extends JFrame implements ActionListener {

    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;

    ClientData client = new ClientData();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel balanceLabel = new JLabel();
    JLabel helloLabel = new JLabel();

    WelcomePage() {
        Connection con = null;
        Statement statement = null;
        ResultSet resultSetWelcome = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bank", "root", "");
            if (con == null) {
                System.out.println("Brak polączenia z bazą danych.");
            } else {
                System.out.println("Jest połączenie z bazą danych.");
            }

            statement = con.createStatement();

            String sqlSelect = "SELECT * FROM klient WHERE Pesel =" + HomePage.peselValueFromField;
            resultSetWelcome = statement.executeQuery(sqlSelect);

            while (resultSetWelcome.next()) {
                String name = resultSetWelcome.getString("Imie");
                String lastName = resultSetWelcome.getString("Nazwisko");
                int balance = resultSetWelcome.getInt("Saldo");
                client.setName(name);
                client.setLastName(lastName);
                client.setBalance(balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSetWelcome.close();
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
        button1 = new JButton("Wykonaj przelew.");
        button1.setBounds(0, 0, 200, 100);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.DARK_GRAY);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transfer transfer = new Transfer();
                frame.dispose();
            }
        });
        frame.add(button1);
        button2 = new JButton("Wpłać.");
        button2.setBounds(0, 100, 200, 100);
        button2.setBackground(Color.DARK_GRAY);
        button2.setForeground(Color.WHITE);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deposit deposit = new Deposit();
                frame.dispose();
            }
        });
        frame.add(button2);
        button3 = new JButton("Wypłać.");
        button3.setBounds(0, 200, 200, 100);
        button3.setBackground(Color.DARK_GRAY);
        button3.setForeground(Color.WHITE);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Withdraw withdraw = new Withdraw();
                frame.dispose();
            }
        });
        frame.add(button3);
        button4 = new JButton("Historia.");
        button4.setBounds(650, 100, 200, 100);
        button4.setBackground(Color.DARK_GRAY);
        button4.setForeground(Color.WHITE);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                History history = new History();
                frame.dispose();
            }
        });
        frame.add(button4);
        button5 = new JButton("Wyloguj.");
        button5.setForeground(Color.WHITE);
        button5.setBounds(650, 200, 200, 100);
        button5.setBackground(Color.DARK_GRAY);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage homePage = new HomePage();
                frame.dispose();
            }
        });
        frame.add(button5);
        button6 = new JButton("Weź kredyt.");
        button6.setBounds(650, 0, 200, 100);
        button6.setForeground(Color.WHITE);
        button6.setBackground(Color.DARK_GRAY);
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Credit credit = new Credit();
                frame.dispose();
            }
        });
        frame.add(button6);

        helloLabel.setText("Konto użytkownika " + client.getName() +" " + client.getLastName() + "!");
        helloLabel.setForeground(Color.BLACK);//kolor gówny
        helloLabel.setBounds(210,5,500,30);
        helloLabel.setFont(new Font("Dialog", Font.BOLD, 22));

        balanceLabel.setText("Twój stan konta wynosi: " + client.getBalance() +" złotych.");
        balanceLabel.setForeground(Color.BLACK);//kolor główny
        balanceLabel.setBounds(210, 50, 500, 30);
        balanceLabel.setFont(new Font("Dialog", Font.BOLD, 22));

        frame.add(helloLabel);
        frame.add(balanceLabel);
        panel.setBackground(Color.gray);
        panel.setPreferredSize(new Dimension(650, 450));
        frame.add(panel);
        frame.setSize(850, 330);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
