package GUI;

import java.util.HashMap;

public class IDandPasswords {

    HashMap<String,String> loginInfo = new HashMap<String,String>();

    IDandPasswords() {
        loginInfo.put("dominik","abc123");
        loginInfo.put("a","a");
    }

    protected HashMap getLoginInfo(){
        return loginInfo;
    }
}
