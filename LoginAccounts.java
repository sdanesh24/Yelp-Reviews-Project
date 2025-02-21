import java.util.HashMap;

public class LoginAccounts {

    //instance fields of the LoginAccounts class

    String username;
    String password;
    //String adminPrivilege;

    // allowing for instances of LoginAccounts to be created without a username and password to be passed in constructor
    public LoginAccounts()
    {
     this.username = "";
     this.password = "";
    }

    public LoginAccounts(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    private static HashMap <String, String> loginInfo = new HashMap <String, String>();

    // creating a getter method so loginInfo HM can be accessed in other classes
    public HashMap <String, String> getLoginInfo()
    {
        return loginInfo;
    }

    public void makeJunkAccounts()
    {
        JunkAccounts();
    }

    private void JunkAccounts()
    {
        loginInfo.put("Safa", "Alaskas");
        loginInfo.put("Nevaeh", "Zeus123");
        loginInfo.put("Ben", "Jetta01");
        loginInfo.put("Tina", "WildCats05");
        loginInfo.put("Joe", "Wally!07");

    }

    public void storeUser(String username, String password)
    {
        loginInfo.put(username, password);
    }

    public void registerNewUser()
    {
        new NewUserRegistration(this);
    }

    public void printLoginInfo()
    {
        for (String key : loginInfo.keySet())
        {
            System.out.println("Username: " + key + " \n" + "Password: " + loginInfo.get(key));
        }

    }
}
