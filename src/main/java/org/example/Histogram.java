package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Histogram {



    private CategoryDataset createDataset(ArrayList<String> countryList, ArrayList<Integer> valueList)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < countryList.size(); i++) {
            dataset.addValue(valueList.get(i), "Капитал", countryList.get(i));
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset)
    {
        JFreeChart chart = ChartFactory.createBarChart(
                "Гистограмма общего капитала участников Forbes, объединенных по странам.",
                null,                   // x-axis label
                "Капитал",                // y-axis label
                dataset);
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        return chart;
    }

    public void createPanel(ArrayList<String> countryList, ArrayList<Integer> valueList)
    {
        JFreeChart chart = createChart(createDataset(countryList, valueList));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(1920, 1080));


        try {

            OutputStream out = new FileOutputStream("chartName.png");
            ChartUtilities.writeChartAsPNG(out,
                    chart,
                    1920,
                    1080);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
