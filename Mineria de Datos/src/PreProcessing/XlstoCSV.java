/*package PreProcessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import au.com.bytecode.opencsv.CSVWriter;

class XlStoCSV {
    static void xls(File inputFile , File outputFile) {
        // Get the workbook object for XLS file
        int count = 0;
        try {

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
                    inputFile));
            for (int l = workbook.getNumberOfSheets() - 1; l >= 0; l--) {

                // For storing data into CSV files
                CSVWriter fos = new CSVWriter(new FileWriter(outputFile));
                HSSFSheet sheet = workbook.getSheetAt(count);
                Cell cell;
                Row row;

                // Iterate through each rows from first sheet
                Iterator<Row> rowIterator = sheet.iterator();
                List<String[]> datosCompletos = new ArrayList<String[]>();
                while (rowIterator.hasNext()) {
                	StringBuffer data = new StringBuffer();
                    row = rowIterator.next();
                    // For each row, iterate through each columns
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int columnNumber = 1;
                    String datofila = new String();
                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();
                        if (columnNumber > 1)
                        {
                            data.append(",");
                            datofila = datofila + ",";
                            
                        }

                        switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue());
                            datofila = datofila + cell.getBooleanCellValue();
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            data.append(cell.getNumericCellValue() );
                            datofila = datofila + cell.getNumericCellValue();
                            break;

                        case Cell.CELL_TYPE_STRING:
                            data.append(cell.getStringCellValue());
                            datofila = datofila + cell.getStringCellValue();
                            break;

                        case Cell.CELL_TYPE_BLANK:
                            data.append("");
                            datofila = datofila + "";
                            break;

                        default:
                            data.append(cell);
                        }
                         ++columnNumber;
                    }

                    //add some stuff
                    String[] datos = new String[1];
                    datos[0] = datofila;
                    datosCompletos.add(datos);
                }
                String[] lfasdfasd = {"pruebalo","aver"};
                fos.writeNext(lfasdfasd);
                for (int i = 0 ; i < datosCompletos.size(); i++){
                	System.out.println(datosCompletos.get(i)[0]);
                	fos.writeNext(datosCompletos.get(i));
                };
                fos.writeAll(datosCompletos);
                fos.close();
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File inputFile = new File(args[0]);//new File(System.getProperty("C:/tmp/") + "prueba.xlsx");
        File outputFile = new File(args[1]);
        xls(inputFile, outputFile);
    }
}*/


package PreProcessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

class XlStoCSV {
    static void xls(File inputFile , File outputFile) {
        // Get the workbook object for XLS file
        int count = 0;
        try {

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
                    inputFile));
            for (int l = workbook.getNumberOfSheets() - 1; l >= 0; l--) {

                // For storing data into CSV files
                StringBuffer data = new StringBuffer();
                FileOutputStream fos = new FileOutputStream(outputFile);
                HSSFSheet sheet = workbook.getSheetAt(count);
                Cell cell;
                Row row;

                // Iterate through each rows from first sheet
                Iterator<Row> rowIterator = sheet.iterator();
                //row = rowIterator.next(); //obviamos la primera linea
                while (rowIterator.hasNext()) {
                    row = rowIterator.next();
                    // For each row, iterate through each columns
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int columnNumber = 1;
                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();
                        if ((columnNumber == 1)||(columnNumber == 4)||(columnNumber > 9)) {
	                        switch (cell.getCellType()) {
	                        case Cell.CELL_TYPE_BOOLEAN:
	                            data.append(cell.getBooleanCellValue());
	                            data.append(",");
	                            break;
	
	                        case Cell.CELL_TYPE_NUMERIC:
	                            data.append(cell.getNumericCellValue() );
	                            data.append(",");
	                            break;
	
	                        case Cell.CELL_TYPE_STRING:
	                            data.append(cell.getStringCellValue());
	                            data.append(",");
	                            break;
	
	                        case Cell.CELL_TYPE_BLANK:
	                            data.append("");
	                            data.append(",");
	                            break;
	
	                        default:
	                            data.append(cell);
	                            data.append(",");
	                        }
                        }
                         ++columnNumber;
                    }
                    data.append('\n');
                }
                //PROBAMOS QUE DATA ESTÉ BIEN GUARDADO
                	System.out.println(data.toString());
                //
                	
                //PROBAMOS QUE NO SEA PROBLEMA DEL DATA
                	/*
                	StringBuffer dataPRUEBA = new StringBuffer();
                	dataPRUEBA.append("Pila,0,2,03,10,031310");
                	fos.write(dataPRUEBA.toString().getBytes());
                	*/
                //
                fos.write(data.toString().getBytes());
                fos.close();
                count++;
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        xls(inputFile, outputFile);
    }
}