import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import com.formdev.flatlaf.FlatLightLaf;

public class MainPageView {
    private static String userLocation;
    private static String userCategory;

    public MainPageView() {
        // Set FlatLaf Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Yelp Business Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500); // Increased size for better spacing
        frame.setLayout(new GridBagLayout()); // Flexible layout
        frame.setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Labels & Input Fields
        JLabel locationLabel = new JLabel("Enter Location:");
        JTextField locationField = new JTextField(25); // Set preferred size
        JLabel locationDescription = new JLabel("<html><i>This string indicates the geographic area to be used when searching for businesses.<br>" +
                "Examples: 'New York City', 'NYC', '350 5th Ave, New York, NY 10118'.<br>" +
                "Businesses returned in the response may not be strictly within the specified location.</i></html>");
        locationDescription.setForeground(Color.DARK_GRAY);

        // Wrap long text in a scroll pane so it doesn’t get cut off
        JScrollPane locationScroll = new JScrollPane(locationDescription);
        locationScroll.setPreferredSize(new Dimension(500, 60));
        locationScroll.setBorder(BorderFactory.createEmptyBorder());

        JLabel categoryLabel = new JLabel("Enter Business Category:");
        JTextField categoryField = new JTextField(25);
        JLabel categoryDescription = new JLabel("<html><i>Categories to filter the search results with.<br>" +
                "The category filter can be a list of comma-delimited categories.<br>" +
                "e.g., 'bars,french' will filter by Bars OR French.<br>" +
                "The category alias should be used (e.g. 'discgolf', not 'Disc Golf').</i></html>");
        categoryDescription.setForeground(Color.DARK_GRAY);

        // Wrap long text in a scroll pane so it doesn’t get cut off
        JScrollPane categoryScroll = new JScrollPane(categoryDescription);
        categoryScroll.setPreferredSize(new Dimension(500, 60));
        categoryScroll.setBorder(BorderFactory.createEmptyBorder());

        JLabel categoryLink = new JLabel("<html><a href='#'>View Full List of Categories</a></html>");
        categoryLink.setForeground(Color.BLUE);
        categoryLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Open browser on link click
        categoryLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(new URI("https://docs.developer.yelp.com/docs/resources-categories"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to open the link.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton submitButton = new JButton("Search");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(0, 140, 215));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLocation = locationField.getText().trim();
                userCategory = categoryField.getText().trim();

                if (!userLocation.isEmpty() && !userCategory.isEmpty()) {
                    // Show pop-up with better spacing and text wrapping
                    JTextArea textArea = new JTextArea("Searching for " + userCategory + " in " + userLocation);
                    textArea.setFont(new Font("Arial", Font.PLAIN, 14));
                    textArea.setWrapStyleWord(true);
                    textArea.setLineWrap(true);
                    textArea.setOpaque(false);
                    textArea.setEditable(false);

                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(300, 80));

                    JOptionPane.showMessageDialog(frame, scrollPane, "Search Info", JOptionPane.INFORMATION_MESSAGE);

                    // Call scrapeReviews and excelExporter after input
                    Scraper.scrapeReviews(userLocation, userCategory);

                    String filePath = System.getProperty("user.home") + "/Downloads/YelpReviews.xlsx";
                    ExcelExport.excelExporter(filePath);

                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter both location and category.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Arrange components in GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(locationLabel, gbc);

        gbc.gridy++;
        frame.add(locationField, gbc);

        gbc.gridy++;
        frame.add(locationScroll, gbc);

        gbc.gridy++;
        frame.add(categoryLabel, gbc);

        gbc.gridy++;
        frame.add(categoryField, gbc);

        gbc.gridy++;
        frame.add(categoryScroll, gbc);

        gbc.gridy++;
        frame.add(categoryLink, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(submitButton, gbc);

        // Make frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainPageView();
    }
}

