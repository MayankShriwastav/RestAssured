package Udemy.RestAssured;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataDriven {

	public ArrayList getData(String SheetName, String ColmName, String officeName ) throws IOException{
		ArrayList arr = new ArrayList();
		FileInputStream fis = new FileInputStream("D:\\My_Projects\\ExcelUtility\\DataFile.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheetsCount =  workbook.getNumberOfSheets();
		
		for (int i=0; i<sheetsCount ; i++){			
			if (workbook.getSheetName(i).equalsIgnoreCase(SheetName)){
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator(); //sheet is collection of Rows
				Row firstRow = rows.next();
				Iterator<Cell> cell  = firstRow.cellIterator();//row are collection of cell			
				int k=0;
				int Coloumn = 0;				
				while (cell.hasNext()){
					Cell value  = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase(ColmName)){
						Coloumn =k;
					}k++;
				}
				//System.out.println(Coloumn);
				
				//once coloumn is identified then scan entire office column to identify tcs office row 
				while (rows.hasNext()){
					Row row = rows.next();
					if(row.getCell(Coloumn).getStringCellValue().equalsIgnoreCase(officeName)){
						//After you grab Office Name row, pull all the data from that row values
						Iterator<Cell> celValue = row.cellIterator();
						while(celValue.hasNext()){
							Cell cel = celValue.next();
							if(cel.getCellTypeEnum() == CellType.STRING){
								arr.add(cel.getStringCellValue());
							}else{
								arr.add(NumberToTextConverter.toText(cel.getNumericCellValue()));
							}
						}
					}
				}								
			}		
		}
		return arr;		
	}
	

	public static void main(String[] args) throws IOException {
		
	}

}
