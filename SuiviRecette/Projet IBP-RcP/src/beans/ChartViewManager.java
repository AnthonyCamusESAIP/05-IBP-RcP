package beans;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;
 
@ManagedBean
public class ChartViewManager implements Serializable {
 
    private PieChartModel pieModel;
    private LineChartModel lineModel;
	private BarChartModel barModel;
	private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/ibp-rcp", "root", "");
 
    @PostConstruct
    public void init() {
    	initData();
        createPieModels();
    }
    
    public void initData() {
    	List<String> tables = new ArrayList<String>();
    	tables.add("projet");
    	tables.add("campagne");
    	tables.add("test");
    	List<String> attributs = new ArrayList<String>();
    	attributs.add("nomProjet");
    	attributs.add("nomCampagne");
    	attributs.add("nomTest");

    	ArrayList<ArrayList<String>> data = mysqlConnect.MysqlSelect(tables, attributs, "");
    	System.out.println(data);
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
         
        pieModel.set("Brand 1", 540);
        pieModel.set("Brand 2", 325);
        pieModel.set("Brand 3", 702);
        pieModel.set("Brand 4", 421);
         
        pieModel.setTitle("Simple Pie");
        pieModel.setLegendPosition("w");
    }
    
    private void createLineModel() {
    	lineModel = new LineChartModel();
    }
    
    private void createBarModel() {
    	barModel = new BarChartModel();
    }
    
     
}
