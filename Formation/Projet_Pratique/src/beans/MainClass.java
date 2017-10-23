package beans;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

@SuppressWarnings("serial")
@ManagedBean
public class MainClass implements Serializable {

	private PieChartModel pieModel1;
	private LineChartModel lineModel1;
	
	
	@PostConstruct
    public void init() {
        createPieModel1();
        createLineModel1();
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    public LineChartModel getLineModel1() {
    	return lineModel1;
    }
    
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
  
        pieModel1.setLegendPosition("w");
    }
    private void createLineModel1() {
    	lineModel1 = new LineChartModel();
    	lineModel1.setTitle("Chart line test");
    	lineModel1.setLegendPosition("n");
    	
    	LineChartSeries series1 = new LineChartSeries();
    	series1.setLabel("Test");
    	series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
        
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Test n°2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
        
        lineModel1.addSeries(series1);
        lineModel1.addSeries(series2);
    }
	
}
