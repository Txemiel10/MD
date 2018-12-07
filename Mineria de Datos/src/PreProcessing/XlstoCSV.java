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
                while (rowIterator.hasNext()) {
                    row = rowIterator.next();
                    // For each row, iterate through each columns
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int columnNumber = 1;
                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();
                        if (columnNumber > 1)
                        {
                            data.append(",");
                            System.out.println("Lee correctamente");
                        }

                        switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue());
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            data.append(cell.getNumericCellValue() );
                            break;

                        case Cell.CELL_TYPE_STRING:
                            data.append(cell.getStringCellValue());
                            break;

                        case Cell.CELL_TYPE_BLANK:
                            data.append("");
                            break;

                        default:
                            data.append(cell);
                        }
                         ++columnNumber;
                    }
                    data.append('\n');
                }

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
        File inputFile = new File(args[0]);//new File(System.getProperty("C:/tmp/") + "prueba.xlsx");
        File outputFile = new File(args[1]);
        xls(inputFile, outputFile);
    }
}