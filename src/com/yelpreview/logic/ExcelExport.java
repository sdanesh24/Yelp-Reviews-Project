package com.yelpreview.logic;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;

public class ExcelExport
{
    private static List<YelpReview> reviewsList = Scraper.getReviewsList();
    // creating column values for rating and reviewCount to calculate mean and median
    private static List<Double> ratingColumn = new ArrayList<>();
    private static List<Integer> reviewCountColumn = new ArrayList<>();

    public static void excelExporter(String filePath)
    {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Yelp API Output");

        Row rowHeader = sheet.createRow(0);

        rowHeader.createCell(0).setCellValue("Name");

        rowHeader.createCell(1).setCellValue("Location");

        rowHeader.createCell(2).setCellValue("Category");

        rowHeader.createCell(3).setCellValue("Rating");

        rowHeader.createCell(4).setCellValue("Review Count");

        rowHeader.createCell(5).setCellValue("SafaScore");

        // populating the rating and reviewCount columns so the mean and median (respectively) can be calculated for the SafaScore
        for (YelpReview object : reviewsList)
        {
            ratingColumn.add(object.getRating());
            reviewCountColumn.add(object.getReviewCount());
        }

        double meanRatingColumn = getColumnMean(ratingColumn);
        double medianReviewCountColumn = getColumnMedian(reviewCountColumn);

        // calculating the SafaScore for each object and then setting/saving them
        for (YelpReview object : reviewsList)
        {
            double safaScore = safaScoreCalculator(object.getRating(), object.getReviewCount(), medianReviewCountColumn, meanRatingColumn);
            object.setSafaScore(safaScore);
        }

        // sorting reviewsList observations by descending SafaScore
        reviewsList.sort((r1, r2) -> Double.compare(r2.getSafaScore(), r1.getSafaScore()));

        // creating the actual rows and cells of the Excel table
        int rowNum = 1;
        for (YelpReview object : reviewsList)
        {
            Row row = sheet.createRow(rowNum++);

            int cellNum = 0;

            // my way
            Cell cellName = row.createCell(cellNum++);
            cellName.setCellValue(object.getName());

            // gpt way - creates the cell and sets the value in one line
            row.createCell(cellNum++).setCellValue(object.getLocation());

            row.createCell(cellNum++).setCellValue(object.getCategory());

            row.createCell(cellNum++).setCellValue(object.getRating());

            row.createCell(cellNum++).setCellValue(object.getReviewCount());

            row.createCell(cellNum++).setCellValue(object.getSafaScore());
        }

        for (int i = 0; i < reviewsList.size(); i++)
        {
            sheet.autoSizeColumn(i);
        }

        // UI stuff to make display nicer

        XSSFSheet xssfSheet = (XSSFSheet) sheet;

        CellReference topLeft = new CellReference(0, 0);
        CellReference bottomRight = new CellReference(reviewsList.size(), 5);

        AreaReference areaRef = new AreaReference(topLeft, bottomRight, SpreadsheetVersion.EXCEL2007);

        XSSFTable table = xssfSheet.createTable(areaRef);

        CTTable ctTable = table.getCTTable();
        ctTable.setId(1);
        ctTable.setName("ReviewTable");
        ctTable.setDisplayName("ReviewTable");
        ctTable.setRef(areaRef.formatAsString());
        ctTable.setTotalsRowShown(false);

        CTTableStyleInfo styleInfo = ctTable.addNewTableStyleInfo();
        styleInfo.setName("TableStyleMedium9");
        styleInfo.setShowColumnStripes(false);
        styleInfo.setShowRowStripes(true);
        ctTable.setTableStyleInfo(styleInfo);

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

    /**
     * Calculates the SafaScore for a business using a Bayesian weighted average formula.
     * <p>
     * This score adjusts a business's raw average rating based on the number of reviews it has,
     * and weighs it against the global average rating across all businesses.
     * The formula used is:
     * <pre>
     *     SafaScore = ((r * v) + (C * m)) / (v + m)
     * </pre>
     * where:
     * <ul>
     *   <li><b>r</b> is the business's average rating</li>
     *   <li><b>v</b> is the number of reviews for the business</li>
     *   <li><b>C</b> is the mean rating across all businesses</li>
     *   <li><b>m</b> is the median number of reviews across all businesses</li>
     * </ul>
     * The result is rounded to two decimal places and is intended to remain within a 0–5 rating scale.
     *
     * @param rating the average rating of the business (r)
     * @param reviewCount the number of reviews the business has (v)
     * @param meanRating the global average rating across all businesses (C)
     * @param medianReviewCount the median number of reviews across all businesses (m)
     * @return the SafaScore as a double, rounded to two decimal places
     */

    private static double safaScoreCalculator(double rating, int reviewCount, double medianReviewCount, double meanRating)
    {
        double safaScore = ((rating * reviewCount) + (meanRating * medianReviewCount)) / (reviewCount + medianReviewCount);
        double roundedSafaScore = Math.round(safaScore * 100.0) / 100.0;
        return roundedSafaScore;
    }

    private static double getColumnMean(List<Double> columnName)
    {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (double cellValue : columnName)
        {
            stats.addValue(cellValue);
        }
        return stats.getMean();
    }

    private static double getColumnMedian(List<Integer> columnName)
    {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (int cellValue : columnName)
        {
            stats.addValue(cellValue);
        }
        return stats.getPercentile(50);

    }
}
