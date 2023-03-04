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