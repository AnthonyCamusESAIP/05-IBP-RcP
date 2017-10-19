package beans;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Random;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class MainClass implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3762344431747571496L;
	
	@PostConstruct
    public void init() {

        createPieModel1(CsvReader.ReadCSV());
        createLineModel1();
        /* 
        try {
        
			MysqlConnect.setCsvData(CsvReader.ReadCSV());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
    }
	
	private static PieChartModel pieModel1;
    private static LineChartModel lineModel1;
    private static Tuple tu;
    private static ArrayList<Tuple> tab = new ArrayList<Tuple>();
    
    public PieChartModel getPieModel1() {

        return pieModel1;
    }
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
    
    public static void createPieModel1(ArrayList<String[]> tabCsv) {
        pieModel1 = new PieChartModel();
        int cmptP = 0, cmptF = 0, cmptNA = 0; 
        
        for(String[] ligne : tabCsv){

        	String Statut = ligne[2];
    		System.out.println(Statut);
    		
        	if(Statut == "Passed"){
        		cmptP++;
        		System.out.println("Passed");
        	}
        	if (Statut == "Failed"){
        		cmptF++;
        		System.out.println("Failed");
        	}
        	if (Statut == "N/A"){
        		cmptNA++;
        	}
		}
        pieModel1.set("Test Passed", cmptP );
        pieModel1.set("Test Failed", cmptF );
        pieModel1.set("Test N/A", cmptNA );
       
        pieModel1.setTitle("Nombre Test suivant le statut");
        pieModel1.setLegendPosition("w");
    }
    public static void createLineModel1() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
         
    }
    private static LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        
        series1.setLabel("Nombre Brand (*100)");
        series2.setLabel("Total au Brand");
        int k=0;
        for(Tuple t : tab)
        {
            series1.set(k, ((t.getX())*100));
            series2.set(k, t.getY());
            k++;
        } 
        
        
        model.addSeries(series1);
        model.addSeries(series2);
        return model;
    }
}
