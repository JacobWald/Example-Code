package edu.virginia.cs.hw1;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class ExcelRead {
    public static HashMap<String, Integer> getexcel(String filename) throws IOException {

            //Checks if file is an Excel file
            FileInputStream file = new FileInputStream(filename);

            //Create workbook of Excel file
            XSSFWorkbook wb = new XSSFWorkbook(file);

            // Creates a sheet of the first page (there is only one page inputted so the getSheetAt is 0.)
            XSSFSheet houserep = wb.getSheetAt(0);

            //Create Hashmap to put excel sheet values in
            HashMap<String, Integer> hashrep = new HashMap<String, Integer>();

            // For loop to iterate through each row (Starts on 2nd row as first row is Header Row)
            for (int i = 1; i <= houserep.getLastRowNum(); i++) {

                //Getting State
                XSSFCell state = houserep.getRow(i).getCell(0);
                //Getting population
                XSSFCell population = houserep.getRow(i).getCell(1);

                //Making State and Population a String or Int

                //Checks for if Cells are empty. If they are, skip the row.
                if (state == null || population == null) {
                }

                else{
                    try{

                        //Create Key Value Pairs
                        String key = state.getStringCellValue().strip();
                        int value = (int) population.getNumericCellValue();

                        //Checks for Negative Population
                        if (value<=0){
                        }

                        else{
                            //Put into HashMap
                            hashrep.put(key, value);
                        }
                    }

                    //Catches and Skips row if the Population is not an integer.
                    catch(IllegalStateException e){
                    }
                }
            }
            //Close workbook
            wb.close();
        if (hashrep.size() == 0) {
            throw new RuntimeException("Error: No valid lines read");
        }
            return hashrep;
        }
}