package com.example.comp3004project.ui.graph;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.comp3004project.R;

import org.achartengine.*;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import static com.example.comp3004project.R.id.chart_bottom;
import static com.example.comp3004project.R.id.chart_up;


import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // TODO Auto-generated method stub
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        LinearLayout ly = (LinearLayout)getActivity().findViewById(chart_up);

        // String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };
        String[] titles = new String[] { "Weight" };

        List<double[]> x = new ArrayList<double[]>();//x坐标值
        for (int i = 0; i < titles.length; i++) {
            x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
        }

        List<double[]> values = new ArrayList<double[]>();//y坐标值
        values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2, 13.9 });
//        values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
//        values.add(new double[] { 20, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
//        values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });




        //点的颜色
//        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
        int[] colors = new int [] {Color.GREEN};
        //点的形状
//        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.TRIANGLE, PointStyle.SQUARE };
        PointStyle[] styles = new PointStyle[] {PointStyle.CIRCLE};
        //图表渲染器
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//填充点
        }
        setChartSettings(renderer, "Weight graph", "Month", "Weight", 0.5, 20, 30, 120, Color.LTGRAY, Color.LTGRAY);


        renderer.setXLabels(12);//x轴显示多少个刻度
        renderer.setYLabels(20);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Paint.Align.RIGHT);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        renderer.setChartTitle("Weight Record");
        renderer.setChartTitleTextSize(100);

        renderer.setZoomButtonsVisible(false);//是否显示缩放按钮
        renderer.setPanLimits(new double[] { -10, 20, -10, 40 });//设置拉动的范围
        renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });//设置缩放的范围

        renderer.setRange(new double[]{0d, 10d, 0d, 30d});//显示范围
        renderer.setFitLegend(true);//调整合适的位置

        LinearLayout br = (LinearLayout)getActivity().findViewById(chart_bottom);
//        String[] titles2 = new String[] {"Calories"};
//        String[] x2 = {"Monday","Tuesday","Thursday","Wednesday","Thursday","Friday","Saturday","Sunday"};
//        List<double[]> values2 = new ArrayList<double[]>();//y坐标值
//        values2.add(new double[] {1200,2000,2200,3000,1900,1400,1800});
        String[] titles2={"test"};
        List<double[]> values2=new ArrayList<double[]>();
        values2.add(new double[]{1200,2000,2200,3000,1900,1400,1800});
        XYMultipleSeriesDataset dataset=buildBarDataset(titles2, values2);

        int[] colors2={Color.BLUE};
        XYMultipleSeriesRenderer renderer2=buildBarRenderer(colors2);
        BarChart.Type type= BarChart.Type.DEFAULT;
        //renderer2.setZoomEnabled(false);//怎么失效了----使用下面的方式
        renderer2.setZoomEnabled(false, false);//成功控制--嘿嘿
        setChartSettings(renderer2, "Weekly Calories", "day", "calories(cal)", 0, 8, 0, 4000, Color.GRAY, Color.LTGRAY);
        renderer2.getSeriesRendererAt(0).setDisplayChartValues(true);
        renderer2.setChartTitleTextSize(100);
        renderer2.setLabelsColor(Color.GREEN);
        renderer.setChartValuesTextSize(50);
        //renderer2.getSeriesRendererAt(1).setDisplayChartValues(true);
        renderer2.setXLabels(0);//设置x轴上的下标数量
        renderer2.setYLabels(10); //设置y轴上的下标数量
        renderer2.setXLabelsAlign(Paint.Align.RIGHT);
        renderer2.setYLabelsAlign(Paint.Align.LEFT);//y轴 数字表示在坐标还是右边
        renderer2.setPanEnabled(false, false);//设置是否允许平移
        renderer2.addXTextLabel(1.0, "Mon");//在指定坐标处显示文字
        renderer2.addXTextLabel(2.0, "Tue");//在指定坐标处显示文字
        renderer2.addXTextLabel(3.0, "Wed");//在指定坐标处显示文字
        renderer2.addXTextLabel(4.0, "Thu");//在指定坐标处显示文字
        renderer2.addXTextLabel(5.0, "Fri");//在指定坐标处显示文字
        renderer2.addXTextLabel(6.0, "Sat");//在指定坐标处显示文字
        renderer2.addXTextLabel(7.0, "Sun");//在指定坐标处显示文字
        // renderer2.clearXTextLabels();//清除 labels
        //renderer2.setZoomRate(1.1f);//设置放缩比
        renderer2.setBarSpacing(1f);// 设置柱状的间距
        renderer2.setLabelsTextSize(23);//设置坐标轴上数字的大小
        renderer2.setXLabelsAngle(300.0f);//设置文字旋转角度 对文字顺时针旋转
        renderer2.setXLabelsPadding(10);//设置文字和轴的距离
        renderer2.setFitLegend(true);// 调整合适的位置

        View view = ChartFactory.getLineChartView(getActivity().getApplicationContext(), buildDataset(titles, x, values), renderer);
        View view2 = ChartFactory.getBarChartView(getActivity().getApplicationContext(),buildBarDataset(titles2,values2),renderer2, type);
        view.setBackgroundColor(Color.BLACK);
        //getActivity().setContentView(view);
        ly.addView(view);
        br.addView(view2);
    }

    private XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setRenderer(renderer, colors, styles);
        return renderer;
    }

    private void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
        renderer.setAxisTitleTextSize(20);//x,y抽标题大小
        renderer.setChartTitleTextSize(20);//标题大小
        renderer.setLabelsTextSize(20);//Labels大小
        renderer.setLegendTextSize(20);//图例大小

        renderer.setPointSize(8f);//点的大小
        renderer.setMargins(new int[] { 20, 42, 35, 20 });//控制你图的边距  实现跟图例的分离上,左,下,右


        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            renderer.addSeriesRenderer(r);
        }
    }

    private void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle, String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor) {
        renderer.setChartTitle(title);//总标题
        renderer.setXTitle(xTitle);//x轴标题
        renderer.setYTitle(yTitle);//y轴标题
        renderer.setXAxisMin(xMin);//x数轴上限
        renderer.setXAxisMax(xMax);//数轴下限
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
    }

    private XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues, List<double[]> yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        addXYSeries(dataset, titles, xValues, yValues, 0);
        return dataset;
    }

    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }

    private void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues, List<double[]> yValues, int scale) {
        int length = titles.length;

        for (int i = 0; i < length; i++) {

            XYSeries series = new XYSeries(titles[i], scale);
            double[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
    }

    protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

}