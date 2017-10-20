/*
 *	Nom de la Classe : ExcelReader 
 * 
 * 	Description : Lecture de Excel
 * 
 *  Version : 1.0
 *  
 *  Date : 20/10/2017
 *  
 *  Copyright : Maryan CIMIA
 */

package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@ManagedBean
public class ExcelReader {

	//private Workbook workbook2;
	private Sheet sheet;
	private Workbook workbook1;
	private String nameFile;
	
	public void setNameFile(String e){ 
		this.nameFile=e;
	}
	public String getNameFile(){
		return this.nameFile;
	}

	public void initExcelReader(){
        try {

        	FileInputStream file = new FileInputStream(new File(getNameFile()));
			workbook1 = WorkbookFactory.create(file); 
			sheet = workbook1.getSheet("list1");


		} catch(Exception e) {
			System.out.println("Probleme : "+e);
		}
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<ArrayList<String>> ReadExcel(){		
		
		ArrayList<ArrayList<String>> tabDonneeExcel = new ArrayList<ArrayList<String>>();
		ArrayList<String> tabLigneDonnee = new ArrayList<String>();
		
		try
        {
    		
    		Iterator<org.apache.poi.ss.usermodel.Row> iterator = sheet.iterator();
    		
    		
    		while (iterator.hasNext()) {
    			tabLigneDonnee = new ArrayList<String>();
    			Row nextRow = iterator.next();
    			Iterator<Cell> cellIterator = nextRow.cellIterator();
    			
    			while (cellIterator.hasNext()) {
    				Cell cell = cellIterator.next();
    				
    				switch (cell.getCellType()) {
    					case Cell.CELL_TYPE_STRING:
    						tabLigneDonnee.add(cell.getStringCellValue());
    						break;
    					case Cell.CELL_TYPE_NUMERIC:
    						tabLigneDonnee.add(Float.toString((float) cell.getNumericCellValue()));
    						break;
    				}
    			}
    			
    		}
    		tabDonneeExcel.add(tabLigneDonnee);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return tabDonneeExcel;
	}
	
	public ArrayList<String>  tabExcel(){
		ArrayList<String> e= new ArrayList<String>();
		
		
		return e;
	}
}

