/***
 * Nom de la class :Mainclass.java
 * 
 * Description : class général
 * 
 * version : 1.0
 * 
 * Copyrigth : Maryan Cimia
 * 
 * date :23/10/2017
 * 
 */
package beans;


import javax.annotation.PostConstruct;

import java.io.*;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import java.util.*;
import java.sql.*;
//import com.csvreader.*;
import beans.BDD;


@ManagedBean
public class MainClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel1;
    private LineChartModel lineModel1;
    private LineChartModel lineModel2;
    private LineChartModel lineModel3;
    private BDD baseDD;
    //private ReadExcel excel= new ReadExcel();
    private ReaderExcel excel ;
        
    List<String[]> data =new ArrayList<String[]>();
    //private final static char SEPARATOR = ';';
    
    public int values[]={0} ;
    public int nbr;
	Connection com = null;
	Statement stat;
    
	@PostConstruct
    public void init() {
		//System.out.println("coucou");
		excel = new ReaderExcel();
		excel.setNameFile("F:/ESAIP/Nouveau dossier/gitHub/05-IBP-RcP/Formation/primefaces/src/beans/P_EF_V1.xlsm");
		
		System.out.println("excel 2");
		
		excel.initReader();
    	excel.readExcel2();
    	
    	//excel.tabExcel();
		System.out.println("excel 3");
    	
        createPieModel1();
        createLineModels();
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
    public LineChartModel getLineModel3(){
    	return lineModel3;
    }
    
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
    
    private void randomValue(){
    	baseDD = new BDD();
    	baseDD.connection();
    	
    	Random rad =new Random();
    	int i;
    	nbr = rad.nextInt(10);
    	nbr =nbr +1;
		values = new int[nbr];
    	int val;
    	for(i=0;i<nbr;i++){
    		val=rad.nextInt(1000)+1;
    		values[i]=val;
    		//baseDD.addValues("Brand "+i,val);
    		//pieModel1.set("Brand "+i,rad.nextInt(1000)+1);
    		//lineModel3.getSeries(brd).set("Brand"+i, val);    		
    	}
    	//System.out.println(values.length + " et " + nbr);
    	baseDD.deconnection();
    }
    

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
    
    private void createLineModels() {
    	//setting du premier model lineaire
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        //setting du premier model chart
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Category Chart");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);

        lineModel3=initCategoryModel2();
        lineModel3.setTitle("line of pie");
        lineModel3.setLegendPosition("e");
        lineModel3.setShowPointLabels(true);
        lineModel3.getAxes().put(AxisType.X, new CategoryAxis("brand"));
        yAxis = lineModel3.getAxis(AxisType.Y);
        yAxis.setLabel("Brand");
        yAxis.setMin(0);
        yAxis.setMax(1000);
    }
    
   public LineChartModel initCategoryModel2(){
       LineChartModel model = new LineChartModel();

       ChartSeries brd = new ChartSeries();
       for(int i = 0;i<nbr;i++){
    	   brd.set(i, values[i]);
    	   //System.out.println(values[i]+ "deux");
       }
       brd.setLabel("Brand");
       model.addSeries(brd);
       
	   return model;
   }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
     
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
}
