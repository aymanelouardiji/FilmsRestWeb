package beans;


import org.primefaces.model.chart.*;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import orm.FilmsService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name="dash",eager = true)
@SessionScoped
public class Dash {
    private static final long serialVersionUID = 8107634688016550766L;

    private BarChartModel filmByCategoryBcm;


    private org.primefaces.model.charts.bar.BarChartModel barModel;

    @PostConstruct
    public void init() {
        createModels();
    }

    private void createModels() {
        initBarChartModel();
        initBarBarChart_v2();
    }

    public void initBarChartModel() {
        filmByCategoryBcm = new BarChartModel();
        FilmsService filmService = new FilmsService();
        List<String> allCategories = filmService.getAllCategories();
        for (String category : allCategories) {
            ChartSeries series = new ChartSeries();
            series.setLabel(category);
            int filmCount = filmService.getFilmCountByCategory(category);
            series.set(category, filmCount);
            filmByCategoryBcm.addSeries(series);
        }
        filmByCategoryBcm.setAnimate(true);
        filmByCategoryBcm.setZoom(true);
        filmByCategoryBcm.setLegendPosition("ne");
        filmByCategoryBcm.setTitle("Number of Films by Category");
        Axis axisX = filmByCategoryBcm.getAxis(AxisType.X);
        axisX.setTickAngle(5);
    }
    public void initBarBarChart_v2() {
        barModel = new org.primefaces.model.charts.bar.BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Number of Films by Category Version 2");

        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        FilmsService filmService = new FilmsService();
        List<String> allCategories = filmService.getAllCategories();
        for (String category : allCategories) {
            values.add(filmService.getFilmCountByCategory(category)); // Assuming you have a method to get film count by category
            labels.add(category);
        }

        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);


        barModel.setOptions(options);
    }
    public BarChartModel getFilmByCategoryBcm() {
        return filmByCategoryBcm;
    }

    public void setFilmByCategoryBcm(BarChartModel filmByCategoryBcm) {
        this.filmByCategoryBcm = filmByCategoryBcm;
    }

    public org.primefaces.model.charts.bar.BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(org.primefaces.model.charts.bar.BarChartModel barModel) {
        this.barModel = barModel;
    }

}
