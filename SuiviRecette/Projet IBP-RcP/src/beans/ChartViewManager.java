package beans;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.sun.glass.ui.Size;
 
@ManagedBean
public class ChartViewManager implements Serializable {
 
    private PieChartModel pieModel;
    private LineChartModel lineModel;
	private BarChartModel barModel;
	private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/ibp-rcp", "root", "");
	
	private List<String> testPassedThisWeek;
	private List<String> testNAThisWeek;
	private List<String> testFailedThisWeek;
	private List<String> testNotCompletedThisWeek;
	
	private List<String> testPassedWeek1;
	private List<String> testNAWeek1;
	private List<String> testFailedWeek1;
	private List<String> testNotCompletedWeek1;
	
	private List<String> testPassedWeek2;
	private List<String> testNAWeek2;
	private List<String> testFailedWeek2;
	private List<String> testNotCompletedWeek2;
	
	private List<String> testPassedWeek3;
	private List<String> testNAWeek3;
	private List<String> testFailedWeek3;
	private List<String> testNotCompletedWeek3;
	
	private List<String> testPassedWeek4;
	private List<String> testNAWeek4;
	private List<String> testFailedWeek4;
	private List<String> testNotCompletedWeek4;
	
	private List<String> testPassedWeek5;
	private List<String> testNAWeek5;
	private List<String> testFailedWeek5;
	private List<String> testNotCompletedWeek5;
	
	private int nbTestThisWeek;
	private int nbTestWeek1;
	private int nbTestWeek2;
	private int nbTestWeek3;
	private int nbTestWeek4;
	private int nbTestWeek5;
	
	private String nomProjet;
	private String date;
	protected final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public ChartViewManager(String _nomProjet, String _date) {
		this.nomProjet = _nomProjet;
		this.date = _date;
	}
	public ChartViewManager() {
		this.date = "2017-12-22";
		this.nomProjet ="GSP13044 - PPG - PARME - Sujets Prioritaires";
	}
 
    @PostConstruct
    public void init() {
    	initData();
        createModels();
    }
    
    public void initData() {

    	testPassedThisWeek = new ArrayList<String>();
    	testNAThisWeek = new ArrayList<String>();
    	testFailedThisWeek = new ArrayList<String>();
    	testNotCompletedThisWeek = new ArrayList<String>();
    	
    	testPassedWeek1 = new ArrayList<String>();
    	testNAWeek1 = new ArrayList<String>();
    	testFailedWeek1 = new ArrayList<String>();
    	testNotCompletedWeek1 = new ArrayList<String>();
    	
    	testPassedWeek2 = new ArrayList<String>();
    	testNAWeek2 = new ArrayList<String>();
    	testFailedWeek2 = new ArrayList<String>();
    	testNotCompletedWeek2 = new ArrayList<String>();
    	
    	testPassedWeek3 = new ArrayList<String>();
    	testNAWeek3 = new ArrayList<String>();
    	testFailedWeek3 = new ArrayList<String>();
    	testNotCompletedWeek3 = new ArrayList<String>();
    	
    	testPassedWeek3 = new ArrayList<String>();
    	testNAWeek3 = new ArrayList<String>();
    	testFailedWeek3 = new ArrayList<String>();
    	testNotCompletedWeek3 = new ArrayList<String>();
    	
    	testPassedWeek4 = new ArrayList<String>();
    	testNAWeek4 = new ArrayList<String>();
    	testFailedWeek4 = new ArrayList<String>();
    	testNotCompletedWeek4 = new ArrayList<String>();
    	
    	testPassedWeek5 = new ArrayList<String>();
    	testNAWeek5 = new ArrayList<String>();
    	testFailedWeek5 = new ArrayList<String>();
    	testNotCompletedWeek5 = new ArrayList<String>();
    	
    	List<String> tables = new ArrayList<String>();
    	tables.add("projet");
    	tables.add("campagne");
    	tables.add("test");
    	List<String> attributs = new ArrayList<String>();
    	attributs.add("test.statut");
    	Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(date));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	String monday = df.format(cal.getTime());
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+date+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAThisWeek.add(string.get(0));
				break;
			case "Passed":
				testPassedThisWeek.add(string.get(0));
				break;
			case "Failed":
				testFailedThisWeek.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedThisWeek.add(string.get(0));
				break;
			}
		}
    	nbTestThisWeek = testNAThisWeek.size()+testFailedThisWeek.size()+testPassedThisWeek.size()+testNotCompletedThisWeek.size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	String friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek1.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek1.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek1.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek1.add(string.get(0));
				break;
			}
		}
    	nbTestWeek1 = testNAWeek1.size()+testPassedWeek1.size()+testFailedWeek1.size()+testNotCompletedWeek1.size();
    	//nbTestWeek1 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')").size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	//nbTestWeek2 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')").size();
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek2.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek2.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek2.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek2.add(string.get(0));
				break;
			}
		}
    	nbTestWeek2 = testNAWeek2.size()+testPassedWeek2.size()+testFailedWeek2.size()+testNotCompletedWeek2.size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	//nbTestWeek3 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')").size();
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek3.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek3.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek3.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek3.add(string.get(0));
				break;
			}
		}
    	nbTestWeek3 = testNAWeek3.size()+testPassedWeek3.size()+testFailedWeek3.size()+testNotCompletedWeek3.size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	//nbTestWeek4 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')").size();
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek4.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek4.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek4.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek4.add(string.get(0));
				break;
			}
		}
    	nbTestWeek4 = testNAWeek4.size()+testPassedWeek4.size()+testFailedWeek4.size()+testNotCompletedWeek4.size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	//nbTestWeek5 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')").size();
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek5.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek5.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek5.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek5.add(string.get(0));
				break;
			}
		}
    	nbTestWeek5 = testNAWeek5.size()+testPassedWeek5.size()+testFailedWeek5.size()+testNotCompletedWeek5.size();
    	
    	///////////////////////////////////////////////////////System.out.print\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    	System.out.println("nb test semaine -1 :"+nbTestWeek1);
    	System.out.println("nb test semaine -2 :"+nbTestWeek2);
    	System.out.println("nb test semaine -3 :"+nbTestWeek3);
    	System.out.println("nb test semaine -4 :"+nbTestWeek4);
    	System.out.println("nb test semaine -5 :"+nbTestWeek5);
    	
    }
  
    public LineChartModel getLineModel() {
		return lineModel;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}


	public PieChartModel getPieModel() {
        return pieModel;
    }
     
    private void createModels() {
        createPieModel();
        createLineModel();
    }
 
    private void createPieModel() {
        pieModel = new PieChartModel();
        if(testNAThisWeek.size() > 0) {
            pieModel.set("NA", testNAThisWeek.size());
        }
        if(testPassedThisWeek.size() > 0) {
            pieModel.set("Passed", testPassedThisWeek.size());
        }
        if(testFailedThisWeek.size() > 0) {
            pieModel.set("Failed", testFailedThisWeek.size());
        }
        if(testNotCompletedThisWeek.size() > 0) {
            pieModel.set("Not Completed", testNotCompletedThisWeek.size());
        }         
        pieModel.setTitle("Résultats des tests");
        pieModel.setLegendPosition("w");
    }
    
    private void createLineModel() {
    	
    	//Sytem.out.println
    	System.out.println("date :"+date);
    	System.out.println("week 1 NA :"+testNAWeek1.size());
    	System.out.println("week 1 Passed :"+testPassedWeek1.size());
    	System.out.println("week 1 Failed :"+testFailedWeek1.size());
    	System.out.println("week 1 NotCompleted :"+testNotCompletedWeek1.size());
    	
    	lineModel = new LineChartModel();
    	
    	ChartSeries testNA = new ChartSeries();
    	testNA.setLabel("Test N/A");
    	ChartSeries testPassed = new ChartSeries();
        testPassed.setLabel("Test passed");
        ChartSeries testFailed = new ChartSeries();
        testFailed.setLabel("Test failed");
        ChartSeries testNotCompleted = new ChartSeries();
        testNotCompleted.setLabel("Test not completed");
 
        Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(date));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek5.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek5.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek5.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek5.size());
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek4.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek4.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek4.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek4.size());
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek3.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek3.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek3.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek3.size());
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek2.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek2.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek2.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek2.size());
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek1.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek1.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek1.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek1.size());
    	
    	testNA.set(date, testNAThisWeek.size());
    	testPassed.set(date, testPassedThisWeek.size());
    	testFailed.set(date, testFailedThisWeek.size());
    	testNotCompleted.set(date, testNotCompletedThisWeek.size());

        lineModel.addSeries(testNA);
        lineModel.addSeries(testPassed);
        lineModel.addSeries(testFailed);
        lineModel.addSeries(testNotCompleted);
        lineModel.setTitle("Comparaison des test sur le mois passé");
        lineModel.setLegendPosition("n");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Date"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        
        int[] tab = {nbTestThisWeek, nbTestWeek1, nbTestWeek2, nbTestWeek3, nbTestWeek4, nbTestWeek5};
        int max = Arrays.stream(tab).max().getAsInt();
        yAxis.setLabel("Nombre de tests");
        yAxis.setMin(0);
        yAxis.setMax(max);
    }
    
    private void createBarModel() {
    	barModel = new BarChartModel();
    }
     
}
