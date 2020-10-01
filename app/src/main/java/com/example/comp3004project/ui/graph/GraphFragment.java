package com.example.comp3004project.ui.graph;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.comp3004project.R;

import org.achartengine.*;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import static com.example.comp3004project.R.id.chart_bottom;
import static com.example.comp3004project.R.id.chart_up;


import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {
//
//    private GraphViewModel graphViewModel;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        graphViewModel =
//                ViewModelProviders.of(this).get(GraphViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_graph, container, false);
//        final TextView textView = root.findViewById(R.id.text_graph);
//        graphViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
//    }
//
//
//}
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    //return super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.fragment_graph, container, false);
}


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        LinearLayout ly = (LinearLayout)getActivity().findViewById(chart_up);

        String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };

        List<double[]> x = new ArrayList<double[]>();//x坐标值
        for (int i = 0; i < titles.length; i++) {
            x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
        }

        List<double[]> values = new ArrayList<double[]>();//y坐标值
        values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2, 13.9 });
        values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
        values.add(new double[] { 20, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
        values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });


        //点的颜色
        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
        //点的形状
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.TRIANGLE, PointStyle.SQUARE };
        //图表渲染器
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//填充点
        }
        setChartSettings(renderer, "Average temperature", "月份", "温度", 0.5, 12.5, -10, 40, Color.LTGRAY, Color.LTGRAY);


        renderer.setXLabels(10);//x轴显示多少个刻度
        renderer.setYLabels(8);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Paint.Align.RIGHT);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);

        renderer.setZoomButtonsVisible(false);//是否显示缩放按钮
        renderer.setPanLimits(new double[] { -10, 20, -10, 40 });//设置拉动的范围
        renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });//设置缩放的范围

        renderer.setRange(new double[]{0d, 10d, 0d, 30d});//显示范围
        renderer.setFitLegend(true);//调整合适的位置


        View view = ChartFactory.getLineChartView(getActivity().getApplicationContext(), buildDataset(titles, x, values), renderer);
        view.setBackgroundColor(Color.BLACK);
        //getActivity().setContentView(view);
        ly.addView(view);
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


}