import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExport
{
    public static void excelExporter(String filePath)
    {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Yelp API Output");

        // creating a list to store reviewsList values accessed with the getter method

        List<YelpReview> reviewsList = Scraper.getReviewsList();

        Row rowHeader = sheet.createRow(0);
        Cell cellHeader1 = rowHeader.createCell(0);
        cellHeader1.setCellValue("Name");

        Cell cellHeader2 = rowHeader.createCell(1);
        cellHeader2.setCellValue("Location");

        Cell cellHeader3 = rowHeader.createCell(2);
        cellHeader3.setCellValue("Category");

        Cell cellHeader4 = rowHeader.createCell(3);
        cellHeader4.setCellValue("Rating");

        Cell cellHeader5 = rowHeader.createCell(4);
        cellHeader5.setCellValue("Review Count");

        int rowNum = 1;
        for (YelpReview object : reviewsList)
        {
            Row row = sheet.createRow(rowNum++);

            int cellNum = 0;

            Cell cellName = row.createCell(cellNum++);
            cellName.setCellValue(object.getName());

            Cell cellLocation = row.createCell(cellNum++);
            cellLocation.setCellValue(object.getLocation());

            Cell cellCategory = row.createCell(cellNum++);
            cellCategory.setCellValue(object.getCategory());

            Cell cellRating = row.createCell(cellNum++);
            cellRating.setCellValue(object.getRating());

            Cell cellReviewCount = row.createCell(cellNum++);
            cellReviewCount.setCellValue(object.getReviewCount());
        }

        for (int i = 0; i < reviewsList.size(); i++)
        {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath))
        {
            workbook.write(fileOut);
            System.out.println("Excel file successfully created: " + filePath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            workbook.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
