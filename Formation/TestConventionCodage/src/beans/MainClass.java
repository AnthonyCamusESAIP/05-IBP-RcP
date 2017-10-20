/*
 *	Nom de la Classe : MainClass 
 * 
 * 	Description : 
 * 
 *  Version : 1.0
 *  
 *  Date : 20/10/2017
 *  
 *  Copyright : Alban ECOBICHON
 */

package beans;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class MainClass implements Serializable {

	private static final long serialVersionUID = 593544194954729597L; //Note (Alban) : Id de Serialization 
	private PieChartModel pieModel1; //Note (Alban) : graphique camembert 1
	private ExcelReader excelR = new ExcelReader();
	
	@PostConstruct
    public void init() {
        createPieModel1();
        excelR.setNameFile("C:/Users/AlbanEcobichon/Documents/test.xls");
        excelR.initExcelReader();
    } 
 
    public PieChartModel getPieModel1() { 
        return pieModel1;
    }
    
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
         
        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }
	
}
