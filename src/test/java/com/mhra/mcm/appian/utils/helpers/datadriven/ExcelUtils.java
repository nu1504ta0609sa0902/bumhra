package com.mhra.mcm.appian.utils.helpers.datadriven;

import com.mhra.mcm.appian.domain.excelpojo.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 *
 */
public class ExcelUtils {

    private final String resourceFolder = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

//    public List<DO_Submitter> getListOfSubmitters(String fileName, String sheet){
//
//        //Point to the resource file
//        String dataFile = getDataFileFullPath(fileName);
//
//        //Get all the data as string separated by \n
//        String linesOfData = getDataFromFile(dataFile, sheet);
//
//        //Create arraylist
//        List<DO_Submitter> listOfItems = new ArrayList<DO_Submitter>();
//        String[] linesOfCSVData = linesOfData.split("\n");
//        int lineCount = 0;
//        for(String line: linesOfCSVData){
//            try {
//                //First line is heading
//                if (lineCount > 0) {
//                    listOfItems.add(new DO_Submitter(line));
//                }
//                lineCount++;
//            }catch (Exception e){
//                break;
//            }
//        }
//
//        return listOfItems;
//    }
//
//    public List<DO_Ingredient> getListOfIngredient(String fileName, String sheet){
//
//        //Point to the resource file
//        String dataFile = getDataFileFullPath(fileName);
//
//        //Get all the data as string separated by \n
//        String linesOfData = getDataFromFile(dataFile, sheet);
//
//        //Create arraylist
//        List<DO_Ingredient> listOfItems = new ArrayList<DO_Ingredient>();
//        String[] linesOfCSVData = linesOfData.split("\n");
//        int lineCount = 0;
//        for(String line: linesOfCSVData){
//            try {
//                //First line is heading
//                if (lineCount > 0) {
//                    listOfItems.add(new DO_Ingredient(line));
//                }
//                lineCount++;
//            }catch (Exception e){
//                break;
//            }
//        }
//
//        return listOfItems;
//    }
//
//    public List<DO_Emission> getListOfEmission(String fileName, String sheet){
//
//        //Point to the resource file
//        String dataFile = getDataFileFullPath(fileName);
//
//        //Get all the data as string separated by \n
//        String linesOfData = getDataFromFile(dataFile, sheet);
//
//        //Create arraylist
//        List<DO_Emission> listOfItems = new ArrayList<DO_Emission>();
//        String[] linesOfCSVData = linesOfData.split("\n");
//        int lineCount = 0;
//        for(String line: linesOfCSVData){
//            try {
//                //First line is heading
//                if (lineCount > 0) {
//                    listOfItems.add(new DO_Emission(line));
//                }
//                lineCount++;
//            }catch (Exception e){
//                break;
//            }
//        }
//
//        return listOfItems;
//    }
//
//    public List<DO_Manufacturer> getListOfManufacturer(String fileName, String sheet){
//
//        //Point to the resource file
//        String dataFile = getDataFileFullPath(fileName);
//
//        //Get all the data as string separated by \n
//        String linesOfData = getDataFromFile(dataFile, sheet);
//
//        //Create arraylist
//        List<DO_Manufacturer> listOfItems = new ArrayList<DO_Manufacturer>();
//        String[] linesOfCSVData = linesOfData.split("\n");
//        int lineCount = 0;
//        for(String line: linesOfCSVData){
//            try {
//                //First line is heading
//                if (lineCount > 0) {
//                    listOfItems.add(new DO_Manufacturer(line));
//                }
//                lineCount++;
//            }catch (Exception e){
//                break;
//            }
//        }
//
//        return listOfItems;
//    }
//
//    public List<DO_Presentation> getListOfPresentation(String fileName, String sheet){
//
//        //Point to the resource file
//        String dataFile = getDataFileFullPath(fileName);
//
//        //Get all the data as string separated by \n
//        String linesOfData = getDataFromFile(dataFile, sheet);
//
//        //Create arraylist
//        List<DO_Presentation> listOfItems = new ArrayList<DO_Presentation>();
//        String[] linesOfCSVData = linesOfData.split("\n");
//        int lineCount = 0;
//        for(String line: linesOfCSVData){
//            try {
//                //First line is heading
//                if (lineCount > 0) {
//                    listOfItems.add(new DO_Presentation(line));
//                }
//                lineCount++;
//            }catch (Exception e){
//                break;
//            }
//        }
//
//        return listOfItems;
//    }
//
//    public List<DO_Design> getListOfDesign(String fileName, String sheet){
//
//        //Point to the resource file
//        String dataFile = getDataFileFullPath(fileName);
//
//        //Get all the data as string separated by \n
//        String linesOfData = getDataFromFile(dataFile, sheet);
//
//        //Create arraylist
//        List<DO_Design> listOfItems = new ArrayList<DO_Design>();
//        String[] linesOfCSVData = linesOfData.split("\n");
//        int lineCount = 0;
//        for(String line: linesOfCSVData){
//            try {
//                //First line is heading
//                if (lineCount > 0) {
//                    listOfItems.add(new DO_Design(line));
//                }
//                lineCount++;
//            }catch (Exception e){
//                break;
//            }
//        }
//
//        return listOfItems;
//    }
//
//    public List<DO_Address> getListOfAddresses(String fileName, String sheet){
//
//        //Point to the resource file
//        String dataFile = getDataFileFullPath(fileName);
//
//        //Get all the data as string separated by \n
//        String linesOfData = getDataFromFile(dataFile, sheet);
//
//        //Create arraylist
//        List<DO_Address> listOfItems = new ArrayList<DO_Address>();
//        String[] linesOfCSVData = linesOfData.split("\n");
//        int lineCount = 0;
//        for(String line: linesOfCSVData){
//            try {
//                //First line is heading
//                if (lineCount > 0) {
//                    //line = replace(",", "+++", line);
//                    listOfItems.add(new DO_Address(line));
//                }
//                lineCount++;
//            }catch (Exception e){
//                break;
//            }
//        }
//
//        return listOfItems;
//    }
//
//    public List<DO_ToxicologyDetails> getListOfToxicologyDetails(String fileName, String sheet){
//
//        //Point to the resource file
//        String dataFile = getDataFileFullPath(fileName);
//
//        //Get all the data as string separated by \n
//        String linesOfData = getDataFromFile(dataFile, sheet);
//
//        //Create arraylist
//        List<DO_ToxicologyDetails> listOfItems = new ArrayList<DO_ToxicologyDetails>();
//        String[] linesOfCSVData = linesOfData.split("\n");
//        int lineCount = 0;
//        for(String line: linesOfCSVData){
//            try {
//                //First line is heading
//                if (lineCount > 0) {
//                    listOfItems.add(new DO_ToxicologyDetails(line));
//                }
//                lineCount++;
//            }catch (Exception e){
//                break;
//            }
//        }
//
//        return listOfItems;
//    }
//
//    public List<DO_Product> getListOfProduct(String fileName, String sheet){
//
//        //Point to the resource file
//        String dataFile = getDataFileFullPath(fileName);
//
//        //Get all the data as string separated by \n
//        String linesOfData = getDataFromFile(dataFile, sheet);
//
//        //Create arraylist
//        List<DO_Product> listOfItems = new ArrayList<DO_Product>();
//        String[] linesOfCSVData = linesOfData.split("\n");
//        int lineCount = 0;
//        for(String line: linesOfCSVData){
//            try {
//                //First line is heading
//                if (lineCount > 0) {
//                    listOfItems.add(new DO_Product(line));
//                }
//                lineCount++;
//            }catch (Exception e){
//                break;
//            }
//        }
//
//        return listOfItems;
//    }


    /***
     * Get MAP OF DATA
     */

    public Map<String, DO_Submitter> getMapOfSubmitters(String fileName, String sheet){

        //Point to the resource file
        String dataFile = getDataFileFullPath(fileName);

        //Get all the data as string separated by \n
        String linesOfData = getDataFromFile(dataFile, sheet);

        //Create arraylist
        Map<String, DO_Submitter> mapOfItems = new HashMap<>();
        List<String> headers = new ArrayList<>();

        String[] linesOfCSVData = linesOfData.split("\n");
        int lineCount = 0;
        boolean errors = false;
        String message = null;
        for(String line: linesOfCSVData){
            try {
                //First line is heading
                if (lineCount > 0 && !isEmptyLine(line)) {
                    message = areAllTheLinesValidInExcelDataSheet(dataFile, sheet, headers, line, lineCount);
                    if(message!=null){
                        errors = true;
                        break;
                    }
                    int columnCount = 0;
                    String[] data = line.split(",");
                    String key = data[0];
                    String[] dataUpdated = createUpdatedData(data, headers, columnCount);
                    mapOfItems.put(key, new DO_Submitter(dataUpdated));
                }else{
                    String [] headings = line.split(",");
                    for(String hd: headings){
                        if(!hd.trim().equals(""))
                        headers.add(hd);
                    }
                }
                lineCount++;
            }catch (Exception e){
                break;
            }
        }

        return mapOfItems;
    }

    public Map<String, DO_Ingredient> getMapOfIngredients(String fileName, String sheet){

        //Point to the resource file
        String dataFile = getDataFileFullPath(fileName);

        //Get all the data as string separated by \n
        String linesOfData = getDataFromFile(dataFile, sheet);

        //Create arraylist
        Map<String, DO_Ingredient> mapOfItems = new HashMap<>();
        List<String> headers = new ArrayList<>();

        String[] linesOfCSVData = linesOfData.split("\n");
        int lineCount = 0;
        boolean errors = false;
        String message = null;
        for(String line: linesOfCSVData){
            try {
                //First line is heading
                if (lineCount > 0 && !isEmptyLine(line)) {
                    message = areAllTheLinesValidInExcelDataSheet(dataFile, sheet, headers, line, lineCount);
                    if(message!=null){
                        errors = true;
                        break;
                    }
                    int columnCount = 0;
                    String[] data = line.split(",");
                    String key = data[0];
                    String[] dataUpdated = createUpdatedData(data, headers, columnCount);
                    mapOfItems.put(key, new DO_Ingredient(dataUpdated));
                }else{
                    String [] headings = line.split(",");
                    for(String hd: headings){
                        if(!hd.trim().equals(""))
                        headers.add(hd);
                    }
                }
                lineCount++;
            }catch (Exception e){
                break;
            }
        }

        return mapOfItems;
    }


    public Map<String, DO_Emission> getMapOfEmissions(String fileName, String sheet){

        //Point to the resource file
        String dataFile = getDataFileFullPath(fileName);

        //Get all the data as string separated by \n
        String linesOfData = getDataFromFile(dataFile, sheet);

        //Create arraylist
        Map<String, DO_Emission> mapOfItems = new HashMap<>();
        List<String> headers = new ArrayList<>();

        String[] linesOfCSVData = linesOfData.split("\n");
        int lineCount = 0;
        boolean errors = false;
        String message = null;
        for(String line: linesOfCSVData){
            try {
                //First line is heading
                if (lineCount > 0 && !isEmptyLine(line)) {
                    message = areAllTheLinesValidInExcelDataSheet(dataFile, sheet, headers, line, lineCount);
                    if(message!=null){
                        errors = true;
                        break;
                    }
                    int columnCount = 0;
                    String[] data = line.split(",");
                    String key = data[0];
                    String[] dataUpdated = createUpdatedData(data, headers, columnCount);
                    mapOfItems.put(key, new DO_Emission(dataUpdated));
                }else{
                    String [] headings = line.split(",");
                    for(String hd: headings){
                        if(!hd.trim().equals(""))
                        headers.add(hd);
                    }
                }
                lineCount++;
            }catch (Exception e){
                break;
            }
        }

        return mapOfItems;
    }


    public Map<String, DO_Manufacturer> getMapOfManufacturers(String fileName, String sheet){

        //Point to the resource file
        String dataFile = getDataFileFullPath(fileName);

        //Get all the data as string separated by \n
        String linesOfData = getDataFromFile(dataFile, sheet);

        //Create arraylist
        Map<String, DO_Manufacturer> mapOfItems = new HashMap<>();
        List<String> headers = new ArrayList<>();

        String[] linesOfCSVData = linesOfData.split("\n");
        int lineCount = 0;
        boolean errors = false;
        String message = null;
        for(String line: linesOfCSVData){
            try {
                //First line is heading
                if (lineCount > 0 && !isEmptyLine(line)) {
                    message = areAllTheLinesValidInExcelDataSheet(dataFile, sheet, headers, line, lineCount);
                    if(message!=null){
                        errors = true;
                        break;
                    }
                    int columnCount = 0;
                    String[] data = line.split(",");
                    String key = data[0];
                    String[] dataUpdated = createUpdatedData(data, headers, columnCount);
                    mapOfItems.put(key, new DO_Manufacturer(dataUpdated));
                }else{
                    String [] headings = line.split(",");
                    for(String hd: headings){
                        if(!hd.trim().equals(""))
                        headers.add(hd);
                    }
                }
                lineCount++;
            }catch (Exception e){
                break;
            }
        }

        return mapOfItems;
    }

    public Map<String, DO_Presentation> getMapOfPresentations(String fileName, String sheet){

        //Point to the resource file
        String dataFile = getDataFileFullPath(fileName);

        //Get all the data as string separated by \n
        String linesOfData = getDataFromFile(dataFile, sheet);

        //Create arraylist
        Map<String, DO_Presentation> mapOfItems = new HashMap<>();
        List<String> headers = new ArrayList<>();

        String[] linesOfCSVData = linesOfData.split("\n");
        int lineCount = 0;
        boolean errors = false;
        String message = null;
        for(String line: linesOfCSVData){
            try {
                //First line is heading
                if (lineCount > 0 && !isEmptyLine(line)) {
                    message = areAllTheLinesValidInExcelDataSheet(dataFile, sheet, headers, line, lineCount);
                    if(message!=null){
                        errors = true;
                        break;
                    }
                    int columnCount = 0;
                    String[] data = line.split(",");
                    String key = data[0];
                    String[] dataUpdated = createUpdatedData(data, headers, columnCount);
                    mapOfItems.put(key, new DO_Presentation(dataUpdated));
                }else{
                    String [] headings = line.split(",");
                    for(String hd: headings){
                        if(!hd.trim().equals(""))
                        headers.add(hd);
                    }
                }
                lineCount++;
            }catch (Exception e){
                break;
            }
        }

        return mapOfItems;
    }

    public Map<String, DO_Design> getMapOfDesigns(String fileName, String sheet){

        //Point to the resource file
        String dataFile = getDataFileFullPath(fileName);

        //Get all the data as string separated by \n
        String linesOfData = getDataFromFile(dataFile, sheet);

        //Create arraylist
        Map<String, DO_Design> mapOfItems = new HashMap<>();
        List<String> headers = new ArrayList<>();

        String[] linesOfCSVData = linesOfData.split("\n");
        int lineCount = 0;
        boolean errors = false;
        String message = null;
        for(String line: linesOfCSVData){
            try {
                //First line is heading
                if (lineCount > 0 && !isEmptyLine(line)) {
                    message = areAllTheLinesValidInExcelDataSheet(dataFile, sheet, headers, line, lineCount);
                    if(message!=null){
                        errors = true;
                        break;
                    }
                    int columnCount = 0;
                    String[] data = line.split(",");
                    String key = data[0];
                    String[] dataUpdated = createUpdatedData(data, headers, columnCount);
                    mapOfItems.put(key, new DO_Design(dataUpdated));
                }else{
                    String [] headings = line.split(",");
                    for(String hd: headings){
                        if(!hd.trim().equals(""))
                        headers.add(hd);
                    }
                }
                lineCount++;
            }catch (Exception e){
                break;
            }
        }

        return mapOfItems;
    }


    public Map<String, DO_Address> getMapOfAddress(String fileName, String sheet){

        //Point to the resource file
        String dataFile = getDataFileFullPath(fileName);

        //Get all the data as string separated by \n
        String linesOfData = getDataFromFile(dataFile, sheet);

        //Create arraylist
        Map<String, DO_Address> mapOfItems = new HashMap<>();
        List<String> headers = new ArrayList<>();

        String[] linesOfCSVData = linesOfData.split("\n");
        int lineCount = 0;
        boolean errors = false;
        String message = null;
        for(String line: linesOfCSVData){
            try {
                //First line is heading
                if (lineCount > 0 && !isEmptyLine(line)) {
                    message = areAllTheLinesValidInExcelDataSheet(dataFile, sheet, headers, line, lineCount);
                    if(message!=null){
                        errors = true;
                        break;
                    }
                    int columnCount = 0;
                    String[] data = line.split(",");
                    String key = data[0];
                    String[] dataUpdated = createUpdatedData(data, headers, columnCount);
                    mapOfItems.put(key, new DO_Address(dataUpdated));
                }else{
                    String [] headings = line.split(",");
                    for(String hd: headings){
                        if(!hd.trim().equals(""))
                            headers.add(hd);
                    }
                }
                lineCount++;
            }catch (ArrayIndexOutOfBoundsException e){
                //break;
            }catch (Exception e){
                //break;
            }
        }

        if(errors){
            throw new RuntimeException(message);
        }

        return mapOfItems;
    }

    public Map<String, DO_ToxicologyDetails> getMapOfToxicologyDetails(String fileName, String sheet){

        //Point to the resource file
        String dataFile = getDataFileFullPath(fileName);

        //Get all the data as string separated by \n
        String linesOfData = getDataFromFile(dataFile, sheet);

        //Create arraylist
        Map<String, DO_ToxicologyDetails> mapOfItems = new HashMap<>();
        List<String> headers = new ArrayList<>();

        String[] linesOfCSVData = linesOfData.split("\n");
        int lineCount = 0;
        boolean errors = false;
        String message = null;
        for(String line: linesOfCSVData){
            try {
                //First line is heading
                if (lineCount > 0 && !isEmptyLine(line)) {
                    message = areAllTheLinesValidInExcelDataSheet(dataFile, sheet, headers, line, lineCount);
                    if(message!=null){
                        errors = true;
                        break;
                    }
                    int columnCount = 0;
                    String[] data = line.split(",");
                    String key = data[0];
                    String[] dataUpdated = createUpdatedData(data, headers, columnCount);
                    mapOfItems.put(key, new DO_ToxicologyDetails(dataUpdated));
                }else{
                    String [] headings = line.split(",");
                    for(String hd: headings){
                        if(!hd.trim().equals(""))
                        headers.add(hd);
                    }
                }
                lineCount++;
            }catch (Exception e){
                break;
            }
        }

        return mapOfItems;
    }

    public Map<String, DO_Product> getMapOfProduct(String fileName, String sheet){

        //Point to the resource file
        String dataFile = getDataFileFullPath(fileName);

        //Get all the data as string separated by \n
        String linesOfData = getDataFromFile(dataFile, sheet);

        //Create arraylist
        Map<String, DO_Product> mapOfItems = new HashMap<>();
        List<String> headers = new ArrayList<>();

        String[] linesOfCSVData = linesOfData.split("\n");
        int lineCount = 0;
        boolean errors = false;
        String message = null;
        for(String line: linesOfCSVData){
            try {
                //First line is heading
                if (lineCount > 0 && !isEmptyLine(line)) {
                    message = areAllTheLinesValidInExcelDataSheet(dataFile, sheet, headers, line, lineCount);
                    if(message!=null){
                        errors = true;
                        break;
                    }
                    int columnCount = 0;
                    String[] data = line.split(",");
                    String key = data[0];
                    String[] dataUpdated = createUpdatedData(data, headers, columnCount);
                    mapOfItems.put(key, new DO_Product(dataUpdated));
                }else{
                    String [] headings = line.split(",");
                    for(String hd: headings){
                        if(!hd.trim().equals(""))
                        headers.add(hd);
                    }
                }
                lineCount++;
            }catch (Exception e){
                break;
            }
        }

        return mapOfItems;
    }

    /**
     * Read any file and return each line separated by \n
     * @param dataFile
     * @return
     */
    private String getDataFromFile(String dataFile, String sheetName) {
        StringBuilder sb = new StringBuilder();

        try {
            File myFile = new File(dataFile);
            FileInputStream fis = new FileInputStream(myFile);

            // Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);

            // Return first sheet from the XLSX workbook
            XSSFSheet mySheet = myWorkBook.getSheet(sheetName);

            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = mySheet.iterator();

            // Traversing over each row of XLSX file
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    if (isCellEmpty(cell)) {
                        // This cell is empty
                        //sb.append("none,");
                    }else {
                        String v = cell.toString();
                        sb.append(v + ",");
                    }
                }
                sb.append("\n");

            }

            //System.out.println(sb.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static boolean isCellEmpty(final Cell cell) {
        if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return true;
        }

        if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().isEmpty()) {
            return true;
        }

        return false;
    }

    private String getDataFileFullPath(String fileName) {
        File file = new File("");
        String rootFolder = file.getAbsolutePath();
        String data = (rootFolder + File.separator + resourceFolder + File.separator + fileName);
        return data;
    }



    private Object[][] convertListTo2DArray(List<?> listOfCountries) {
        Object[][] o = new Object[listOfCountries.size()][1];
        int pos = 0;
        for(Object c: listOfCountries){
            o[pos][0] = c;
            pos++;
        }

        return o;
    }

    private boolean isEmptyLine(String line) {
        boolean empty = false;
        if(line == null || line.trim().equals(""))
            empty = true;

        return empty;
    }

    private String areAllTheLinesValidInExcelDataSheet(String dataFile, String sheet, List<String> headers, String line, int count) {
        String message = null;
        String[] data = line.split(",");
        if(headers.size() != data.length){
            message = ("\nExcel Data File : " + dataFile + "\nSheet : " + sheet + "\nLine number : " + count + "\nInvalid line : " + line + "\nEmpty cells are not allowed, replace with : 'none'" + "\nRead : HowToUse section of excel data file" + "\n");
        }
        return message;
    }


    private String[] createUpdatedData(String[] data, List<String> headers, int columnCount) {
        String[] dataUpdated = new String[data.length];
        for(String dt: data){
            dt = headers.get(columnCount)+"="+dt;
            dataUpdated[columnCount] = dt;
            columnCount++;
        }
        return dataUpdated;
    }



//    public Map<String,List> getAllData(String dataFileLocation) {
//
//        Map<String, List> mapOfExcelData = new HashMap<>();
//
//        List<DO_Submitter> submitter = this.getListOfSubmitters(dataFileLocation, "Submitter");
//        List<DO_Product> product = this.getListOfProduct(dataFileLocation, "Product");
//        List<DO_Ingredient> ingredient = this.getListOfIngredient(dataFileLocation, "Ingredient");
//        List<DO_Emission> emission = this.getListOfEmission(dataFileLocation, "Emission");
//        List<DO_Manufacturer> manufacturer = this.getListOfManufacturer(dataFileLocation, "Manufacturer");
//        List<DO_Presentation> presentation = this.getListOfPresentation(dataFileLocation, "Presentation");
//        List<DO_Design> design = this.getListOfDesign(dataFileLocation, "Design");
//        List<DO_ToxicologyDetails> toxicologicalDetails = this.getListOfToxicologyDetails(dataFileLocation, "ToxicologicalDetails");
//        List<DO_Address> addresses = this.getListOfAddresses(dataFileLocation, "Addresses");
//
//        mapOfExcelData.put("Submitter", submitter);
//        mapOfExcelData.put("Product", product);
//        mapOfExcelData.put("Ingredient", ingredient);
//        mapOfExcelData.put("Emission", emission);
//        mapOfExcelData.put("Manufacturer", manufacturer);
//        mapOfExcelData.put("Presentation", presentation);
//        mapOfExcelData.put("Design", design);
//        mapOfExcelData.put("ToxicologicalDetails", toxicologicalDetails);
//        mapOfExcelData.put("Addresses", addresses);
//
//        return mapOfExcelData;
//    }


    public Map<String, Map> getAllDataAsMap(String dataFileLocation) {

        Map<String, Map> mapOfExcelData = new HashMap<>();
        Map<String, DO_Submitter> submitter = this.getMapOfSubmitters(dataFileLocation, "Submitter");
        Map<String, DO_Product> product = this.getMapOfProduct(dataFileLocation, "Product");
        Map<String, DO_Ingredient> ingredient = this.getMapOfIngredients(dataFileLocation, "Ingredient");
        Map<String, DO_Emission> emission = this.getMapOfEmissions(dataFileLocation, "Emission");
        Map<String, DO_Manufacturer> manufacturer = this.getMapOfManufacturers(dataFileLocation, "Manufacturer");
        Map<String, DO_Presentation> presentation = this.getMapOfPresentations(dataFileLocation, "Presentation");
        Map<String, DO_Design> design = this.getMapOfDesigns(dataFileLocation, "Design");
        Map<String, DO_ToxicologyDetails> toxicologicalDetails = this.getMapOfToxicologyDetails(dataFileLocation, "ToxicologicalDetails");
        Map<String, DO_Address> addresses = this.getMapOfAddress(dataFileLocation, "Addresses");

        mapOfExcelData.put("Submitter", submitter);
        mapOfExcelData.put("Product", product);
        mapOfExcelData.put("Ingredient", ingredient);
        mapOfExcelData.put("Emission", emission);
        mapOfExcelData.put("Manufacturer", manufacturer);
        mapOfExcelData.put("Presentation", presentation);
        mapOfExcelData.put("Design", design);
        mapOfExcelData.put("ToxicologicalDetails", toxicologicalDetails);
        mapOfExcelData.put("Addresses", addresses);

        return mapOfExcelData;
    }

    public static String getFieldValue(String dt, int pos) {
        try{
            String[] split = dt.split("=");
            return split[pos];
        }catch (Exception e){

        }
        return null;
    }

    public static String getFieldName(String dt) {
        try{
            String[] split = dt.split("=");
            return split[0];
        }catch (Exception e){

        }
        return null;
    }
}
