import GUI.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AccountCopy extends JFrame implements ActionListener {

    public class Client {


        static private String name;
        static private String lastName;
        static private String city;
        static private String street;
        static private String idSeries;
        static private long pesel;
        static private int houseNumber;
        static private int doorNumber;
        static private int idNumber;
        static private long accountNumber;
        static private long postCode;


        static private long value = 1110501575100000000L;

        public Client(){

        }

        public Client(String nam, String lastNam, String cit, String stree, String idSerie,
                      long pese, int houseNumbe, int doorNumbe, int idNumbe) {

            name = nam;
            lastName = lastNam;
            city = cit;
            street = stree;
            idSeries = idSerie;
            pesel = pese;
            houseNumber = houseNumbe;
            doorNumber = doorNumbe;
            idNumber = idNumbe;
            accountNumber = value;
            value++;
        }

        public long getAccountNumber() {

            return accountNumber;
        }

        // public void setAccountNumber(long accountNumber) {
        //    this.accountNumber = accountNumber;
        // }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getIdSeries() {
            return idSeries;
        }

        public void setIdSeries(String idSeries) {
            this.idSeries = idSeries;
        }

        public long getPesel() {
            return pesel;
        }

        public void setPesel(long pesel) {
            this.pesel = pesel;
        }

        public int getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
        }

        public int getDoorNumber() {
            return doorNumber;
        }

        public void setDoorNumber(int doorNumber) {
            this.doorNumber = doorNumber;
        }

        public int getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(int idNumber) {
            this.idNumber = idNumber;
        }


        public static long getPostCode() {
            return postCode;
        }

        public static void setPostCode(long postCode) {
            Client.postCode = postCode;
        }

        static public void showClientData() {
            System.out.println(name);
            System.out.println(lastName);
            System.out.println(pesel);
            System.out.println(city);
            System.out.println(street);
            System.out.println(houseNumber);
            System.out.println(doorNumber);
            System.out.println(idSeries);
            System.out.println(idNumber);
            System.out.println(accountNumber);
        }

        public void Client() {
        }


    }
    Client cli = new Client();

    JButton buttonDo = new JButton();
    JButton buttonBack = new JButton();
    JFrame frameAccount = new JFrame();
    JLabel peselLabel = new JLabel();
    JLabel nameLabel = new JLabel();
    JLabel lastNameLabel = new JLabel();
    JLabel cityLabel = new JLabel();
    JLabel streetLabel = new JLabel();
    JLabel houseNumberLabel = new JLabel();
    JLabel doorNumberLabel = new JLabel();
    JLabel postCodeLabel = new JLabel();
    JLabel idNumberLabel = new JLabel();
    JLabel idSeriesLabel = new JLabel();

    JTextField peselField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JTextField cityField = new JTextField();
    JTextField streetField = new JTextField();
    JTextField houseNumberField = new JTextField();
    JTextField doorNumberField = new JTextField();
    JTextField postCodeField = new JTextField();
    JTextField idNumberField = new JTextField();
    JTextField idSeriesField = new JTextField();

    AccountCopy() {

        nameLabel.setText("Imię: ");
        nameLabel.setBounds(25, 80, 120, 25);
        frameAccount.add(nameLabel);

        nameField.setBounds(125, 80, 120, 25);
        frameAccount.add(nameField);



        lastNameLabel.setText("Nazwisko: ");
        lastNameLabel.setBounds(25, 110, 120, 25);
        frameAccount.add(lastNameLabel);

        lastNameField.setBounds(125, 110, 120, 25);
        frameAccount.add(lastNameField);
        cli.setLastName(nameField.getText());

        peselLabel.setText("Pesel: ");
        peselLabel.setBounds(25, 140, 120, 25);
        frameAccount.add(peselLabel);

        peselField.setBounds(125, 140, 120, 25);
        frameAccount.add(peselField);
        cli.setPesel(Long.parseLong(nameField.getText()));

        cityLabel.setText("Miasto: ");
        cityLabel.setBounds(25, 170, 120, 25);
        frameAccount.add(cityLabel);
        cli.setCity(nameField.getText());

        cityField.setBounds(125, 170, 120, 25);
        frameAccount.add(cityField);

        streetLabel.setText("Ulica: ");
        streetLabel.setBounds(25, 200, 120, 25);
        frameAccount.add(streetLabel);


        streetField.setBounds(125, 200, 120, 25);
        frameAccount.add(streetField);
        cli.setStreet(nameField.getText());

        houseNumberLabel.setText("Numer domu: ");
        houseNumberLabel.setBounds(300, 80, 120, 25);
        frameAccount.add(houseNumberLabel);

        houseNumberField.setBounds(425, 80, 120, 25);
        frameAccount.add(houseNumberField);
        cli.setHouseNumber(Integer.parseInt(nameField.getText()));

        doorNumberLabel.setText("Numer mieszkania: ");
        doorNumberLabel.setBounds(300, 110, 120, 25);
        frameAccount.add(doorNumberLabel);

        doorNumberField.setBounds(425, 110, 120, 25);
        frameAccount.add(doorNumberField);
        cli.setDoorNumber(Integer.parseInt(nameField.getText()));

        postCodeLabel.setText("Kod pocztowy: ");
        postCodeLabel.setBounds(300, 140, 120, 25);
        frameAccount.add(postCodeLabel);

        postCodeField.setBounds(425, 140, 120, 25);
        frameAccount.add(postCodeField);
        cli.setPostCode(Long.parseLong(nameField.getText()));

        idSeriesLabel.setText("Seria dowodu: ");
        idSeriesLabel.setBounds(300, 170, 120, 25);
        frameAccount.add(idSeriesLabel);

        idSeriesField.setBounds(425, 170, 120, 25);
        frameAccount.add(idSeriesField);
        cli.setIdSeries(nameField.getText());


        idNumberLabel.setText("Numer dowodu: ");
        idNumberLabel.setBounds(300, 200, 120, 25);
        frameAccount.add(idNumberLabel);

        idNumberField.setBounds(425, 200, 120, 25);
        frameAccount.add(idNumberField);
        cli.setIdNumber(Integer.parseInt(nameField.getText()));

        buttonDo.setBounds(150, 320, 100, 50);
        buttonDo.setFont(new Font("Arial", Font.BOLD, 14));
        buttonDo.setText("Wykonaj.");
        frameAccount.add(buttonDo);

        buttonBack.setBounds(300, 320, 100, 50);
        buttonBack.setFont(new Font("Arial", Font.BOLD, 14));
        buttonBack.setText("Wstecz.");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   HomePage home = new HomePage();
                frameAccount.dispose();
            }
        });
        frameAccount.add(buttonBack);

        frameAccount.setLayout(null);
        frameAccount.setSize(600, 450);
        frameAccount.setVisible(true);
        frameAccount.setLocationRelativeTo(null);
        frameAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}