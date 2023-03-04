package GUI;

import GUI.IDandPasswords;
import GUI.LoginPage;

public class LoginSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        IDandPasswords idAndPass = new IDandPasswords();


        LoginPage loginPage = new LoginPage(idAndPass.getLoginInfo());

    }

}
