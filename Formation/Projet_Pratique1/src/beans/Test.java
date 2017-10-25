package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped

public class Test implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pie;
	private BarChartModel bar;

	@PostConstruct
	public void init() {
		createPie();
		createBar();
	}

	public BarChartModel getBar() {

		return bar;
	}

	public PieChartModel getPie() {

		return pie;
	}

	private void createPie() {
		pie = new PieChartModel();

		pie.set("New Order", 100);
		pie.set("Delivred", 70);
		pie.set("returned", 10);
		
		pie.setTitle("Test titre");
		pie.setLegendPosition("w");

	}

	private void createBar() {
		bar = new BarChartModel();

		ChartSeries newOrder = new ChartSeries("New Order");
		newOrder.set(2015, 100);
		newOrder.set(2016, 70);
		newOrder.set(2017, 150);

		ChartSeries delivred = new ChartSeries("Delivred");
		delivred.set(2015, 90);
		delivred.set(2016, 60);
		delivred.set(2017, 130);

		ChartSeries returned = new ChartSeries("returned");
		returned.set(2015, 10);
		returned.set(2016, 20);
		returned.set(2017, 15);

		bar.addSeries(newOrder);
		bar.addSeries(delivred);
		bar.addSeries(returned);

		bar.setTitle("Rapport");
		bar.setLegendPosition("ne");

		bar.getAxis(AxisType.X).setLabel("Year");
		bar.getAxis(AxisType.Y).setLabel("Report");
		//bar.getAxis(AxisType.X).setMin(0);
		//bar.getAxis(AxisType.Y).setMax(250);

	}
}
