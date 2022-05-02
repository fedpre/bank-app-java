package com.example.bankappproject;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
// Service class to abstract the handling of CSV files
abstract class FileHandler {
    protected File myFile;
    protected String CSV_PATH_FILE;

    // Method to create a new CSV file to record all the information of an account owner
    protected void createCSV(String title, double balance) {
        CSV_PATH_FILE = "accounts/" + title + ".csv";
        this.myFile = new File(CSV_PATH_FILE);

        try {
            // Create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(this.myFile);

            // Create a CSVWriter
            CSVWriter writer = new CSVWriter(outputFile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            // Add header to the CSV
            String[] header = { "accountName", "balance", "dateCreated" };
            writer.writeNext(header);

            String date = createCurrentDate();

            // Add the initial bank account information
            String[] initialInformation = { title, String.valueOf(balance), date };
            writer.writeNext(initialInformation);

            String[] header_history = { "transaction", "amount", "newBalance", "dateCreated" };
            writer.writeNext(header_history);

            // Close the connection to the file
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Method to load a CSV file from a previously created account
    protected List<String[]> loadCSV(String title, int skipLines) {
        CSV_PATH_FILE = "accounts/" + title + ".csv";
        this.myFile = new File(CSV_PATH_FILE);
        try {
            FileReader reader = new FileReader(CSV_PATH_FILE);
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(skipLines)
                    .build();

            List<String[]> data = csvReader.readAll();
            csvReader.close();
            return data;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // Append a row of information (a transaction) to a CSV file
    protected void addRow(String[] data) {
        try {
            FileWriter output = new FileWriter(this.myFile, true);
            CSVWriter writer = new CSVWriter(output, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeNext(data);

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Method to create the current date
    protected String createCurrentDate(){
        // Create current date and time
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // Stringify the objects
        String currentDateString = String.valueOf(currentDate);
        String currentTimeString = String.valueOf(currentTime);

        // Concatenate the two strings
        return currentDateString + " " + currentTimeString;
    }
    // Method to check if a file already exists
    protected boolean csvExist(String name){
        String pathString = "accounts/" + name + ".csv";
        File tmpFile = new File(pathString);
        return tmpFile.exists();
    }
}
