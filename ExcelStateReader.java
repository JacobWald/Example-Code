package edu.virginia.cs.hw3;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelStateReader extends StateReader{
    private String filename;
    private XSSFWorkbook wb;
    private XSSFSheet stateInfo;

    public ExcelStateReader(String filename) {
        if (!filename.toLowerCase().endsWith(".xlsx")) {
            throw new IllegalArgumentException("Error: cannot open non xlsx file " + filename);
        }
        this.filename = filename;
    }

    @Override
    public void readStates() {
        try {
            generateXSSFWorkbook();
            generateXSSFSheet();
            getStatesFromRestOfFile();
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateXSSFSheet() {
        stateInfo = wb.getSheetAt(0);
    }

    private void generateXSSFWorkbook() throws IOException {
        FileInputStream file = new FileInputStream(filename);
        wb = new XSSFWorkbook(file);
    }

    private void getStatesFromRestOfFile() throws IOException {
        for(int i = 1; i <= stateInfo.getLastRowNum(); i++) {

            XSSFCell state = stateInfo.getRow(i).getCell(0);
            XSSFCell population = stateInfo.getRow(i).getCell(1);

            if(state == null || population == null) {
            }
            else {
                try {
                    addNewState(state, population);
                }catch(IndexOutOfBoundsException | IllegalArgumentException ignored) {
                }
            }
        }
    }

    private static void addNewState(XSSFCell state, XSSFCell population) {
        String stringState = state.getStringCellValue().strip();
        int intPopulation = (int) population.getNumericCellValue();

        if(intPopulation <= 0) {
        }
        else {
            State newState = new State(stringState, intPopulation);
        }
    }
}