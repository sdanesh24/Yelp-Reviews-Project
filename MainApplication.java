public class MainApplication
{
    public static void main(String[] str)
    {
        // Create a LoginAccount object with test credentials
        LoginAccount loginAccount = new LoginAccount("test", "test");

        // Create and display the login window
        LoginFrame loginFrame = new LoginFrame(loginAccount);
        loginFrame.setVisible(true);
    }
}