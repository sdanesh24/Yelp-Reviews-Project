package com.yelpreview.view;

import com.yelpreview.logic.ExcelExport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class UserExcelSave {

    public UserExcelSave() {
        JFrame frame = new JFrame("Save Excel File");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1));

        JLabel promptLabel = new JLabel("Enter a name for your Excel file:");
        JTextField filenameField = new JTextField();

        JLabel hintLabel = new JLabel("<html><i>File will be saved to your Downloads folder. No need to add .xlsx.</i></html>");
        hintLabel.setForeground(Color.GRAY);

        JButton saveButton = new JButton("Save File");

        saveButton.addActionListener((ActionEvent e) ->
        {
            String filename = filenameField.getText().trim();

            if (filename.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a file name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Append .xlsx if not included
            if (!filename.endsWith(".xlsx")) {
                filename += ".xlsx";
            }

            String filePath = System.getProperty("user.home") + "/Downloads/" + filename;

            ExcelExport.excelExporter(filePath);

            JOptionPane.showMessageDialog(frame, "File exported to Downloads as: " + filename);

            try
            {
                Desktop.getDesktop().open(new File(filePath));
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            frame.dispose();

            System.exit(0);

        });

        frame.add(promptLabel);
        frame.add(filenameField);
        frame.add(hintLabel);
        frame.add(saveButton);

        frame.setVisible(true);
    }
}
