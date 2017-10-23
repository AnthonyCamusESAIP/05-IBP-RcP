package beans;

import java.io.Serializable;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

//@SuppressWarnings("serial")
@ManagedBean
public class MainClass implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel1;
	private ReaderExcel excel;
    public int values[]={0} ;
    public int nbr;

	@PostConstruct
	public void init(){
        createPieModel1();
		createReaderExcel();
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

	public void createReaderExcel(){

		excel = new ReaderExcel();
		
		excel.setNameFile("C:/Users/Mama/Dropbox/PCPI-05_IBP-RCP_2017/RE PCPI-05_IBP-RcP_2017  Recueil des besoins/Liste des tests exécutés MOA VPS05-01 (Conflit lié au codage Unicode).xls");		
		excel.setNameFeuille("Query1");
		excel.initReader();		
		
		excel.read();
	}
}
