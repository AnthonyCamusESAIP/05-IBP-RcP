package beans;
/**
 * 
 * Nom de classe : ReaderExcel
 * 
 * Description : Cette classe permet de lire et et placer les valeur dans un tableau
 *
 * Version : 1.0
 * 
 * Date : 23/10/2017
 * 
 * Copyright : Maryan Cimia
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
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

	// Note (Maryan) : implementation des variables
	private Iterator<org.apache.poi.ss.usermodel.Row> iterator = null ;
	private Workbook workbook1;	
	private String nameFile;
	private String nameFeuille;
	private FileInputStream file;
	private Sheet sheet ;

	//Note (Maryan): Setter et Getter
	public Sheet getSheet() {
		return sheet;
	}
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	//Note (Maryan): Setter et Getter
	public Workbook getWorkbook1() {
		return workbook1;
	}
	public void setWorkbook1(Workbook workbook1) {
		this.workbook1 = workbook1;
	}

	//Note (Maryan): Setter et Getter du inputstream de File
	public FileInputStream getFile() {
		return file;
	}
	public void setFile(FileInputStream file) {
		this.file = file;
	}
	
	// Note (Maryan) :Setter et getter du chemin du fichier
	public void setNameFile(String e){
		this.nameFile=e;
	}
	public String getNameFile(){
		return this.nameFile;
	}
	
	//Note (Maryan) :Setter et getter de la feuille
	public String getNameFeuille() {
		return nameFeuille;
	}
	public void setNameFeuille(String nameFeuille) {
		this.nameFeuille = nameFeuille;
	}
	
	
	// Note (Maryan) :init des parametre chemin
	public void initReader(String fileName, String sheetName){
		if(!(getNameFile().equals(null))){
		
	        try {
				file = new FileInputStream(new File(getNameFile()));
				workbook1 = WorkbookFactory.create(file);
				sheet = workbook1.getSheet(nameFeuille);
	    		iterator = sheet.iterator();
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
	
	// Note (Maryan) :fermeture
	public void close(){
        try {
			file.close();
			workbook1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
}
	// Note (Maryan) :Lecture en dure de la feuille excel
	public void read(){
		String date = "";
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		try
        {

    		
    		while (iterator.hasNext()) {		
    			Row nextRow = iterator.next();
    			Iterator<Cell> cellIterator = nextRow.cellIterator();
    					
    			while (cellIterator.hasNext()) {
    				Cell cell = cellIterator.next();
    				
    				//System.out.println(cell.getCellTypeEnum()+"___getCellTypeEnum___");
    				
    				switch (cell.getCellTypeEnum()) {
    					case STRING:
    						//System.out.print(cell.getStringCellValue());
    						break;
    					case BOOLEAN:
    						//System.out.print(cell.getBooleanCellValue());
    						break;
    					case NUMERIC:
    						//System.out.print(cell.getNumericCellValue());
    						date = formatter.format(cell.getDateCellValue());
    						//System.out.println(date);
    						break;
					default:
						break;
    				}
    				
    				//System.out.print(" _#_ ");
    			}
    			//System.out.println();
    		}
    		
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	// Note (Maryan) :lecture plus tableau
	public ArrayList<ArrayList<String>> ReadExcel(){		
		String date = "";
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");		//Note (Maryan) : cette ligne permet de convertir la date et choisire son format
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
    				switch (cell.getCellTypeEnum()) {
    				
						case STRING:
	    						tabLigneDonnee.add(cell.getStringCellValue());
	    						break;
	    				case NUMERIC:
	    					date = formatter.format(cell.getDateCellValue()); // Note (Maryan) : Convertion des Dates en String
	    						tabLigneDonnee.add(date);
	    						break;
						default:
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
		return tabDonneeExcel;
	}
}
