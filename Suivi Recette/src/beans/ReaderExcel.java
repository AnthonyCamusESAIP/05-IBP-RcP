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
	private String nameFeuille;
	
	//Configuration du chemin du fichier
	public void setNameFile(String e){
		this.nameFile=e;
	}
	
	//recuperation du nom du fichier
	public String getNameFile(){
		return this.nameFile;
	}
	
	public String getNameFeuille() {
		return nameFeuille;
	}

	public void setNameFeuille(String nameFeuille) {
		this.nameFeuille = nameFeuille;
	}
	
	//init des parametre chemin
	public void initReader(){
		if(getNameFile().equals(null)){
			
		}else{
		FileInputStream file;
		
	        try {
				file = new FileInputStream(new File(getNameFile()));
				workbook1 = WorkbookFactory.create(file);
				sheet = workbook1.getSheet(nameFeuille);
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
	
	//Lecture en dure de la feuille excel
	@SuppressWarnings("deprecation")
	public void read(){		
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
    						System.out.print((char)cell.getNumericCellValue());
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
	
	//lecture plus tableau
	@SuppressWarnings("deprecation")
	public ArrayList<ArrayList<String>> ReadExcel(){		

		ArrayList<ArrayList<String>> tabDonneeExcel = new ArrayList<ArrayList<String>>();		//Note (Alban) : Création d'un tableau de données
		ArrayList<String> tabLigneDonnee = new ArrayList<String>();		//Note (Alban) : Création d'une ligne de données
		
		try
        {
    		
    		Iterator<Row> iterator = sheet.iterator();
    		
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
        		tabDonneeExcel.add(tabLigneDonnee);
    		}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
		for(ArrayList<String> ligne : tabDonneeExcel) {
			for(String cellule : ligne) {
				System.out.println(cellule);
			}
		}
		return tabDonneeExcel;
	}
	
	public ArrayList<String>  tabExcel(){
		ArrayList<String> e= new ArrayList<String>();
		
		
		return e;
	}

}
