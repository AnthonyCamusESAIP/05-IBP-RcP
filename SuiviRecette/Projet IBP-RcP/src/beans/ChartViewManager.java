package beans;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
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
        createPieModels();
    }
    
    public void initData() {
    	testPassedThisWeek = new ArrayList<String>();
    	testNAThisWeek = new ArrayList<String>();
    	testFailedThisWeek = new ArrayList<String>();
    	testNotCompletedThisWeek = new ArrayList<String>();
    	List<String> tables = new ArrayList<String>();
    	tables.add("projet");
    	tables.add("campagne");
    	tables.add("test");
    	List<String> attributs = new ArrayList<String>();
    	attributs.add("test.statut");
    	
    	testDate();

    	Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(date));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+df.format(cal.getTime()).substring(0, 8)+(Integer.parseInt(df.format(cal.getTime()).substring(8))-4)+"' AND '"+date+"')")) {
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
    	//TODO: Ajuster les dates pour les counts
    	nbTestWeek1 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+getMondayDate(date)+"' AND '"+date+"')").size();
    	nbTestWeek2 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+getMondayDate(date)+"' AND '"+date+"')").size();
    	nbTestWeek3 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+getMondayDate(date)+"' AND '"+date+"')").size();
    	nbTestWeek4 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+getMondayDate(date)+"' AND '"+date+"')").size();
    	nbTestWeek5 = mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.nomProjet+"' AND (Date BETWEEN '"+getMondayDate(date)+"' AND '"+date+"')").size();
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
     
    private void createPieModels() {
        createPieModel();
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
        pieModel.setTitle("Résultats des test");
        pieModel.setLegendPosition("w");
    }
    
    private void createLineModel() {
    	lineModel = new LineChartModel();
    }
    
    private void createBarModel() {
    	barModel = new BarChartModel();
    }
    
    private String getMondayDate(String _fridayDate) {
    	Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(_fridayDate));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return (df.format(cal.getTime()).substring(0, 8)+(Integer.parseInt(df.format(cal.getTime()).substring(8))-4));
    }
    
    private String getFridayLastWeek(String _fridayDate) {
    	Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(_fridayDate));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return (df.format(cal.getTime()).substring(0, 8)+(Integer.parseInt(df.format(cal.getTime()).substring(8))-7));
    }
    
    private void testDate() {
    	
    	Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println("Date initiale :" + df.format(cal.getTime()));
    	cal.add(cal.DATE, -60);
    	System.out.println("Date -60j :"+df.format(cal.getTime()));
    	
    }
    
     
}
