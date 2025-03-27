// mysql database
// hibernate
// jpa
// java
// yelp api
// maven
// apache poi
// java swing

// calculator app:
// java
// java swing

//LoginAccount and LoginFrame are first example classes made in the program

package com.yelpreview;

import com.yelpreview.logic.LoginAccounts;
import com.yelpreview.view.FirstTimeUserPrompt;


public class MainApplication
{
    public static void main(String[] str)
    {
        // Load fake accounts into memory
        // Can insert/delete but will always remove accounts at the end of the program

        //LoginAccounts currentUser = new LoginAccounts();
        //currentUser.makeJunkAccounts();
        //currentUser.printLoginInfo();
        FirstTimeUserPrompt ftup = new FirstTimeUserPrompt();
    }

        //TODO: prompt user to put in where they want the file to be downloaded to?

        // Create an application
        // User logs in via login page
        // Need to create a UI for login (Java swing)
        // com.yelpreview.view.LoginPageView.java
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
        // User logs in -> Go to a new page. com.yelpreview.view.MainPageView.java
        // This view will store parameter boxes next to a button to run the data scraper.
        //
        // com.yelpreview.logic.Scraper.java
        // ^ This will scrape the main application data
        //
        // On the main page, use Java swing for a popup window.
        // That popup window will need it's own class most likely. GraphDataView.java
        //
        // com.yelpreview.MainApplication.java
        // com.yelpreview.view.LoginPageView.java <-- The view of the login for a user to input Username and Password
        // --> Box for username/password, and a button to validateUser()
        // For validateUser, check the hashmap of valid users
        // LoginUtilities.java <-- Methods called in the com.yelpreview.view.LoginPageView ex. validateUser()
        // com.yelpreview.view.MainPageView.java <-- Button for running the scraper, boxes for parameters for the scraper
        // com.yelpreview.logic.Scraper.java <-- This will be code that is run when the scraper button is clicked (API)
        // GraphView.java <-- com.yelpreview.logic.Scraper.java returns data, turns it into a graph, pops up the GraphView
        //                    when the scraper has finished running.
        //
        // HW/GOAL: Create the login window and open up to the main page with a button and parameters
        //          for the scraper.
        //
        //**ExcelExporter.java** export data to Excel.
}
