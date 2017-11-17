package beans;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import beans.DataManager;
import org.primefaces.model.chart.PieChartModel;

//@SuppressWarnings("serial")
@ManagedBean
public class MainClass implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel1;
	private ReaderExcel excel;
	private Tri tri; 
	
    public int values[]={0} ;
    public int nbr;

	@PostConstruct
	public void init(){
        createPieModel1();
        //System.out.println("Test fonctionnement inti()");
		//createReaderExcel();
		//afficher();
	}
	
	public void afficher(){
		tri = new Tri();
		tri.list(excel.ReadExcel());
		tri.okProjet();
	}
	
	//graphique
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
    //Valeur random
    private void randomValue(){
    	
    	Random rad =new Random();
    	int i;
    	nbr = rad.nextInt(10);
    	nbr =nbr +1;
		values = new int[nbr];
    	int val;
    	for(i=0;i<nbr;i++){
    		val=rad.nextInt(1000)+1;
    		values[i]=val;		
    	}
    }

	//graphique
	private void createPieModel1() {
		
		//appel des valeur ramdom
    	randomValue();
    	
    	//init des vals
    	int i;
    	int val;
    	
    	
    	//inti du model chart pieModel1
        pieModel1 = new PieChartModel();
        
        //ajout des valeurs alleatoire save
    	for(i=0;i<nbr;i++){
    			val = values[i];
    			pieModel1.set("Brand "+i,val);
    			//System.out.println(val+"un");
    	}
        
        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("e");
    }

	//Recuperation de l'ecel
	public void createReaderExcel(){

		excel = new ReaderExcel();
		String fileName = "C:\\Users\\Dante\\Desktop\\CPI4\\Projet\\Dev\\05-IBP-RcP\\SuiviRecette\\Projet IBP-RcP\\src\\beans\\Listetests.xls";		
		String sheetName = "Query1";
		
		excel.initReader(fileName, sheetName);		
		// ArrayList<ArrayList<String>> tabDonnee = excel.ReadExcel();
		// DataManager.initImportedProjects(tabDonnee);
		excel.close();
		//System.out.println(excel.ReadExcel());
	}
}

