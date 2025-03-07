import javax.swing.*;

public class MainApplication
{
    public static void main(String[] str)
    {
        // Load fake accounts into memory
        // Can insert/delete but will always remove accounts at the end of the program

        LoginAccounts currentUser = new LoginAccounts();
        currentUser.makeJunkAccounts();
        currentUser.printLoginInfo();
        FirstTimeUserPrompt ftup = new FirstTimeUserPrompt();


        // Create an application
        // User logs in via login page
        // Need to create a UI for login (Java swing)
        // LoginPageView.java
        //
        // Application will need a way of storing data
        //
        // {
        // createLoginBox
        // createButton
        //
        // button.addListener(Action -> getScrapedData -> popupWindow)
        //
        // return null
        // }
        //
        // Could use database for now will just use a hashmap
        // Map<String, String> loginAccounts = new HashMap<>();
        //TODO: Link a database to store LoginAccount objects
        //
        // Login Accounts:
        //  1. Username, password, access privileges (anything else you can think of)
        //
        // User logs in -> Go to a new page. MainPageView.java
        // This view will store parameter boxes next to a button to run the data scraper.
        //
        // Scraper.java
        // ^ This will scrape the main application data
        //
        // On the main page, use Java swing for a popup window.
        // That popup window will need it's own class most likely. GraphDataView.java
        //
        // MainApplication.java
        // LoginPageView.java <-- The view of the login for a user to input Username and Password
        // --> Box for username/password, and a button to validateUser()
        // For validateUser, check the hashmap of valid users
        // LoginUtilities.java <-- Methods called in the LoginPageView ex. validateUser()
        // MainPageView.java <-- Button for running the scraper, boxes for parameters for the scraper
        // Scraper.java <-- This will be code that is run when the scraper button is clicked (API)
        // GraphView.java <-- Scraper.java returns data, turns it into a graph, pops up the GraphView
        //                    when the scraper has finished running.
        //
        // HW/GOAL: Create the login window and open up to the main page with a button and parameters
        //          for the scraper.
        //
        //**ExcelExporter.java** export data to Excel.
    }
}