package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReaderExcel {

	//private Workbook workbook2;
	private Sheet sheet ;
	private Workbook workbook1;
	
	private String nameFile;
	
	public void setNameFile(String e){
		this.nameFile=e;
		initReader();
	}
	
	public String getNameFile(){
		return this.nameFile;
	}

	public void initReader(){
		if(getNameFile().equals(null)){
			System.out.println("Set the name File please");
			
		}else{
		FileInputStream file;
        try {
			file = new FileInputStream(new File(getNameFile()));
			workbook1 = WorkbookFactory.create(file);
			sheet = workbook1.getSheet("list1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		}
	}
	
	@SuppressWarnings("deprecation")
	public void readExcel(){		
		try
        {
    		
    		Iterator<org.apache.poi.ss.usermodel.Row> iterator = sheet.iterator();
    		
    		
    		while (iterator.hasNext()) {
    			
    			Row nextRow = iterator.next();
    			Iterator<Cell> cellIterator = nextRow.cellIterator();
    			
    			while (cellIterator.hasNext()) {
    				Cell cell = cellIterator.next();
    				
    				switch (cell.getCellType()) {
    					case Cell.CELL_TYPE_STRING:
    						System.out.print(cell.getStringCellValue());
    						break;
    					case Cell.CELL_TYPE_BOOLEAN:
    						System.out.print(cell.getBooleanCellValue());
    						break;
    					case Cell.CELL_TYPE_NUMERIC:
    						System.out.print(cell.getNumericCellValue());
    						break;
    				}
    				System.out.print(" - ");
    			}
    			System.out.println();
    		}
    		
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

	@SuppressWarnings("deprecation")
	public ArrayList<String>  tabExcel(){
		ArrayList<String> e= new ArrayList<String>();
		Iterator<org.apache.poi.ss.usermodel.Row> iterator = sheet.iterator();
		
		while (iterator.hasNext()) {
			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						e.add(cell.getStringCellValue());
						System.out.print(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						e.add((int)cell.getNumericCellValue());
						System.out.print(cell.getNumericCellValue());
						break;
				}
				System.out.print(" - ");
			}
			System.out.println(e);
		}
		
		return e;
	}
}
