package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@ViewScoped

public class MainClass {
	private LineChartModel model;

	@PostConstruct
	public void init() {

		CreateAnimateModel();

	}

	public LineChartModel getModel() {
		return model;
	}

	public void setModel(LineChartModel model) {
		this.model = model;
	}

	public void CreateAnimateModel() {

		model = initLinearModel();
		model.setTitle("Primefaces Chart");
		model.setAnimate(true);
		model.setLegendPosition("se");
	    Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);

	}

	private LineChartModel initLinearModel() {
		LineChartModel ChartModel = new LineChartModel();
		
		
		LineChartSeries serie1 = new LineChartSeries();
		serie1.setLabel("serie 1");

		serie1.set(1, 2);
		serie1.set(2, 5);
		serie1.set(3, 6);
		serie1.set(4, 9);
		serie1.set(5, 4);
		serie1.set(6, 1);
		serie1.set(7, 9);

		LineChartSeries serie2 = new LineChartSeries();
		serie2.setLabel("serie 2");

		serie2.set(1, 1);
		serie2.set(2, 9);
		serie2.set(3, 4);
		serie2.set(4, 3);
		serie2.set(5, 6);
		serie2.set(6, 4);
		serie2.set(7, 2);

		ChartModel.addSeries(serie1);
		ChartModel.addSeries(serie2);

		return ChartModel;
	}

}