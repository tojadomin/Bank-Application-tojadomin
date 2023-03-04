package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class History extends JFrame {

    JTable table = new JTable();
    JScrollPane scroll = new JScrollPane(table);
    JLabel dateLabel = new JLabel("Data");
    JLabel sumLabel = new JLabel("Kwota");
    JLabel typeLabel = new JLabel("Typ operacji");
    JLabel balanceLabel = new JLabel("Saldo po operacji");
    JLabel recipientLabel = new JLabel("Odbiorca");
    JLabel titleLabel = new JLabel("Tytuł");
    JLabel senderLabel = new JLabel("Nadawca");
    JLabel balanceBeforeLabel = new JLabel("Saldo przed operacją");
    JFrame frame = new JFrame();
    JButton buttonBack = new JButton("Wstecz");
    JPanel panel = new JPanel();
    Connection con;

    History() {
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(1200,600));

        String url = "jdbc:mysql://localhost/bank";
        String user = "root";
        String pass = "";

        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }

        String sql = "SELECT * FROM historia WHERE pesel = " + HomePage.peselValueFromField + " ORDER BY id DESC";

        try {

            PreparedStatement pst = con.prepareCall(sql);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.addColumn("Data");
            model.addColumn("Nadawca");
            model.addColumn("Odbiorca");
            model.addColumn("Tytuł");
            model.addColumn("Kwota");
            model.addColumn("Typ operacji");
            model.addColumn("Saldo przed operacją");
            model.addColumn("Saldo po operacji");

            while (rs.next()) {
                model.addRow(new String[]{rs.getString(3), rs.getString(13), rs.getString(7), rs.getString(6),
                        rs.getString(9), rs.getString(8), rs.getString(11), rs.getString(10)});
            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }

        dateLabel.setForeground(Color.BLACK);
        dateLabel.setBounds(50, 70, 100, 30);

        senderLabel.setForeground(Color.BLACK);
        senderLabel.setBounds(187, 70, 100, 30);

        recipientLabel.setForeground(Color.BLACK);
        recipientLabel.setBounds(325, 70, 100, 30);

        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(462, 70, 100, 30);

        typeLabel.setForeground(Color.BLACK);
        typeLabel.setBounds(600, 70, 100, 30);

        sumLabel.setForeground(Color.BLACK);
        sumLabel.setBounds(738, 70, 100, 30);

        balanceBeforeLabel.setForeground(Color.BLACK);
        balanceBeforeLabel.setBounds(875, 70, 150, 30);

        balanceLabel.setForeground(Color.BLACK);
        balanceLabel.setBounds(1013, 70, 100, 30);

        table.setLocation(50, 100);
        table.setSize(1100, 300);
        table.setVisible(true);

        buttonBack.setBackground(Color.DARK_GRAY);
        buttonBack.setForeground(Color.WHITE);
        buttonBack.setBounds(500, 500, 100, 50);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomePage welcomePage = new WelcomePage();
                frame.dispose();
            }
        });

        frame.add(buttonBack);
        frame.add(senderLabel);
        frame.add(titleLabel);
        frame.add(balanceBeforeLabel);
        frame.add(recipientLabel);
        frame.add(dateLabel);
        frame.add(sumLabel);
        frame.add(typeLabel);
        frame.add(balanceLabel);
        frame.add(table);
        frame.add(scroll);
        frame.add(panel);
        frame.setSize(1200, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }
}
