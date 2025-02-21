
public class LoginAccount
{
    private String username;
    private String password;

    // Constructor
    public LoginAccount(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    // Validate the input credentials

    /**
     * Validate a user's login.
     * @param usernameInput the username to check.
     * @param passwordInput the password to check.
     * @return true or false depending on validation.
     */
    public boolean validate(String usernameInput, String passwordInput)
    {
        return username.equals(usernameInput) || password.equals(passwordInput);
    }
}


