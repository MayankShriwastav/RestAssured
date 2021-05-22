package Udemy.RestAssured;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class DataDriven2 {

ArrayList<String> lst = new ArrayList();	
	
	public ArrayList<String> getData(String SheetName, String colmName, String testCaseName) throws IOException{		
		
		FileInputStream fis = new FileInputStream("D:\\My_Projects\\RestAssured\\DataFile.xlsx");		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for(int i=0 ; i<sheets ; i++){
			if(workbook.getSheetName(i).equalsIgnoreCase(SheetName)){
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				Iterator <Row> rows = sheet.iterator();
				Row row = rows.next();
				Iterator <Cell> cell = row.cellIterator();
				
				int k=0;
				int column= 0 ;
								
				while(cell.hasNext()){
					Cell value = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase(colmName)){
						column=k;						
					} 
					k++;
				}				
			System.out.println("TestCases present in Column :"+column);
			
            while(rows.hasNext()){
           	 Row r = rows.next();
           	 if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)){
           		 Iterator<Cell> cel = r.cellIterator();
           		 while(cel.hasNext()){
           			 
           			Cell cell2 = cel.next();
					if(cell2.getCellTypeEnum() == CellType.STRING){
						lst.add(cell2.getStringCellValue());
					}else{
						lst.add(NumberToTextConverter.toText(cell2.getNumericCellValue()));
					}
           		 }
           	 }
            }            
			}
		}
		return lst;
	}
	
	
	/*public static void main(String[] args) throws IOException {
		DataDriven2 dd = new DataDriven2();
		ArrayList<String> ls = dd.getData("DeleteProfile");				
		System.out.println(ls);
	}*/

}
