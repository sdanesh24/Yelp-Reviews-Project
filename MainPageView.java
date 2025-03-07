import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class MainPageView
{
    public MainPageView()
    {
        JFrame frame = new JFrame("Yelp Business Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLayout(new GridLayout(7, 1)); // More rows for descriptions & link

        // need to edit this to clean up UI
        JLabel locationLabel = new JLabel("Enter Location:");
        JTextField locationField = new JTextField();
        JLabel locationDescription = new JLabel("<html><i>This string indicates the geographic area to be used when searching for businesses.\n" +
                "Examples: 'New York City', 'NYC', '350 5th Ave, New York, NY 10118'.\n" +
                "Businesses returned in the response may not be strictly within the specified location.</i></html>");
        locationDescription.setForeground(Color.GRAY); // Make it visually distinct

        JLabel categoryLabel = new JLabel("Enter Business Category:");
        JTextField categoryField = new JTextField();
        JLabel categoryDescription = new JLabel("<html><i>Categories to filter the search results with. See the list of supported categories. The category filter can be a list of comma delimited categories.\n" +
                "e.g., 'bars,french' will filter by Bars OR French.\n" +
                "The category alias should be used (e.g. 'discgolf', not 'Disc Golf').</i></html>");
        categoryDescription.setForeground(Color.GRAY);

        JLabel categoryLink = new JLabel("<html><a href='#'>View Full List of Categories</a></html>");
        categoryLink.setForeground(Color.BLUE);
        categoryLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        categoryLink.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                try
                {
                    Desktop.getDesktop().browse(new URI("https://docs.developer.yelp.com/docs/resources-categories"));
                } catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(frame, "Failed to open the link.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton submitButton = new JButton("Search");

        submitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String userLocation = locationField.getText().trim();
                String userCategory = categoryField.getText().trim();

                // don't need to create an instance of the Scraper class because the scrapeReviews method is static
                    //Scraper scraper = new Scraper();

                if (!userLocation.isEmpty() && !userCategory.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "Searching for " + userCategory + " in " + userLocation);

                    Scraper.scrapeReviews(userLocation, userCategory);

                } else
                {
                    JOptionPane.showMessageDialog(frame, "Please enter both location and category.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to frame
        frame.add(locationLabel);
        frame.add(locationField);
        frame.add(locationDescription);
        frame.add(categoryLabel);
        frame.add(categoryField);
        frame.add(categoryDescription);
        frame.add(categoryLink);
        frame.add(submitButton);

        // Make frame visible
        frame.setVisible(true);
    }
}
