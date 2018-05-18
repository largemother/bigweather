package weatherdata;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ShowGraph {

	public ShowGraph()
	{

		DefaultCategoryDataset data = new DefaultCategoryDataset();		
	for (int i= 0; i < 10; i++) {
		data.setValue(i, "temperature","2014"+""+i);
	}
		JFreeChart jchart = ChartFactory.createBarChart("Past Weather", "Year", "Temperature (°C)", data, PlotOrientation.VERTICAL, true, true, false );
		CategoryPlot plot = jchart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.blue);
		ChartFrame chartFrame = new ChartFrame ("Past Weather", jchart, true);
		chartFrame.setSize (1000, 1000);
		chartFrame.setVisible(true);
	
	} 
}
