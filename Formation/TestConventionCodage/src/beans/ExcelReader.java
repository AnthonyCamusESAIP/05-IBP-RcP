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
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@ManagedBean
public class ExcelReader {

	//private Workbook workbook2;
	private Sheet sheet;		//Note (Alban) : Feuille Excel
	private Workbook workbook1;	//Note (Alban) : Ensemble des feuilles de l'Excel
	private String nameFile;	//Note (Alban) : Lien du fichier
	
	public void setNameFile(String e){ 
		this.nameFile=e;
	}
	public String getNameFile(){
		return this.nameFile;
	}

	public void initExcelReader(){
        try {
        	
        	//Note (Alban) : Création d'un fichier avec le lien de l'Excel et récuperation de la feuille
        	FileInputStream file = new FileInputStream(new File(getNameFile()));
			workbook1 = WorkbookFactory.create(file); 
			sheet = workbook1.getSheet("list1");


		} catch(Exception e) {
			System.out.println("Probleme : "+e);
		}
	}
	
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

