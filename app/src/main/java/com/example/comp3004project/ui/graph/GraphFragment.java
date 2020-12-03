package com.example.comp3004project.ui.graph;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.comp3004project.R;
import com.example.comp3004project.ui.dashboard.HelperNewEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.achartengine.*;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import static com.example.comp3004project.R.id.chart_bottom;
import static com.example.comp3004project.R.id.chart_up;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GraphFragment extends Fragment {

    Context myContext;
    TextView showStartDate,showEndDate,show;
    Button setStartDate,setEndDate,searchDate;
    public GraphicalView graphicalView;
    LinearLayout br;

    Date startDate,endDate;
    Calendar calendar = Calendar.getInstance();
    Locale ca = new Locale("en","CA");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY",ca);

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // TODO Auto-generated method stub
        //return super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_graph, container, false);
        myContext = root.getContext();
        showStartDate = root.findViewById(R.id.textView_startDate);
        showEndDate = root.findViewById(R.id.textView_endDate);
        setStartDate = root.findViewById(R.id.button_startDate);
        setEndDate = root.findViewById(R.id.button_endDate);
        show = root.findViewById(R.id.textView_showC);
        searchDate = root.findViewById(R.id.button_showC);

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        LinearLayout ly = (LinearLayout)getActivity().findViewById(chart_up);
        br = (LinearLayout)getActivity().findViewById(chart_bottom);

//        // String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };
//        String[] titles = new String[] { "Weight" };
//
//        List<double[]> x = new ArrayList<double[]>();//x坐标值
//        for (int i = 0; i < titles.length; i++) {
//            x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
//        }
//
//        List<double[]> values = new ArrayList<double[]>();//y坐标值
//        values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2, 13.9 });
////        values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
////        values.add(new double[] { 20, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
////        values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
//
//
//
//
//        //点的颜色
////        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
//        int[] colors = new int [] {Color.GREEN};
//        //点的形状
////        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.TRIANGLE, PointStyle.SQUARE };
//        PointStyle[] styles = new PointStyle[] {PointStyle.CIRCLE};
//        //图表渲染器
//        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
//        int length = renderer.getSeriesRendererCount();
//        for (int i = 0; i < length; i++) {
//            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//填充点
//        }
//        setChartSettings(renderer, "Weight graph", "Month", "Weight", 0.5, 20, 30, 120, Color.LTGRAY, Color.LTGRAY);
//
//
//        renderer.setXLabels(12);//x轴显示多少个刻度
//        renderer.setYLabels(20);
//        renderer.setShowGrid(true);
//        renderer.setXLabelsAlign(Paint.Align.RIGHT);
//        renderer.setYLabelsAlign(Paint.Align.RIGHT);
//        renderer.setChartTitle("Weight Record");
//        renderer.setChartTitleTextSize(100);
//
//        renderer.setZoomButtonsVisible(false);//是否显示缩放按钮
//        renderer.setPanLimits(new double[] { -10, 20, -10, 40 });//设置拉动的范围
//        renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });//设置缩放的范围
//
//        renderer.setRange(new double[]{0d, 10d, 0d, 30d});//显示范围
//        renderer.setFitLegend(true);//调整合适的位置
//
//        LinearLayout br = (LinearLayout)getActivity().findViewById(chart_bottom);
////        String[] titles2 = new String[] {"Calories"};
////        String[] x2 = {"Monday","Tuesday","Thursday","Wednesday","Thursday","Friday","Saturday","Sunday"};
////        List<double[]> values2 = new ArrayList<double[]>();//y坐标值
////        values2.add(new double[] {1200,2000,2200,3000,1900,1400,1800});
//        String[] titles2={"test"};
//        List<double[]> values2=new ArrayList<double[]>();
//        values2.add(new double[]{1200,2000,2200,3000,1900,1400,1800});
//        XYMultipleSeriesDataset dataset=buildBarDataset(titles2, values2);
//
//        int[] colors2={Color.BLUE};
//        XYMultipleSeriesRenderer renderer2=buildBarRenderer(colors2);
//        BarChart.Type type= BarChart.Type.DEFAULT;
//        //renderer2.setZoomEnabled(false);//怎么失效了----使用下面的方式
//        renderer2.setZoomEnabled(false, false);//成功控制--嘿嘿
//        setChartSettings(renderer2, "Weekly Calories", "day", "calories(cal)", 0, 8, 0, 4000, Color.GRAY, Color.LTGRAY);
//        renderer2.getSeriesRendererAt(0).setDisplayChartValues(true);
//        renderer2.setChartTitleTextSize(100);
//        renderer2.setLabelsColor(Color.GREEN);
//        renderer.setChartValuesTextSize(50);
//        //renderer2.getSeriesRendererAt(1).setDisplayChartValues(true);
//        renderer2.setXLabels(0);//设置x轴上的下标数量
//        renderer2.setYLabels(10); //设置y轴上的下标数量
//        renderer2.setXLabelsAlign(Paint.Align.RIGHT);
//        renderer2.setYLabelsAlign(Paint.Align.LEFT);//y轴 数字表示在坐标还是右边
//        renderer2.setPanEnabled(false, false);//设置是否允许平移
//        renderer2.addXTextLabel(1.0, "Mon");//在指定坐标处显示文字
//        renderer2.addXTextLabel(2.0, "Tue");//在指定坐标处显示文字
//        renderer2.addXTextLabel(3.0, "Wed");//在指定坐标处显示文字
//        renderer2.addXTextLabel(4.0, "Thu");//在指定坐标处显示文字
//        renderer2.addXTextLabel(5.0, "Fri");//在指定坐标处显示文字
//        renderer2.addXTextLabel(6.0, "Sat");//在指定坐标处显示文字
//        renderer2.addXTextLabel(7.0, "Sun");//在指定坐标处显示文字
//        // renderer2.clearXTextLabels();//清除 labels
//        //renderer2.setZoomRate(1.1f);//设置放缩比
//        renderer2.setBarSpacing(1f);// 设置柱状的间距
//        renderer2.setLabelsTextSize(23);//设置坐标轴上数字的大小
//        renderer2.setXLabelsAngle(300.0f);//设置文字旋转角度 对文字顺时针旋转
//        renderer2.setXLabelsPadding(10);//设置文字和轴的距离
//        renderer2.setFitLegend(true);// 调整合适的位置
//
//        View view = ChartFactory.getLineChartView(getActivity().getApplicationContext(), buildDataset(titles, x, values), renderer);
//        View view2 = ChartFactory.getBarChartView(getActivity().getApplicationContext(),buildBarDataset(titles2,values2),renderer2, type);
//        view.setBackgroundColor(Color.BLACK);
        //getActivity().setContentView(view);
//        ly.addView(view);
//        br.addView(view2);


        searchDate.setEnabled(false);
        setStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        showStartDate.setText(simpleDateFormat.format(calendar.getTime()));
                        startDate = calendar.getTime();

                        if(showEndDate.getText().toString().isEmpty()){
                            searchDate.setEnabled(false);
                        }else {
                            searchDate.setEnabled(true);
                        }

                    }

                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        setEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        showEndDate.setText(simpleDateFormat.format(calendar.getTime()));
                        endDate = calendar.getTime();

                        if(showStartDate.getText().toString().isEmpty()){
                            searchDate.setEnabled(false);
                        }else {
                            searchDate.setEnabled(true);
                        }


                    }

                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        searchDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference eventReference = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Events").child("Food");

                Query query = eventReference.orderByChild("date").startAt(startDate.getTime()).endAt(endDate.getTime());

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    // ArrayList<HelperNewEvent> helperNewEvents = new ArrayList<>();
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HelperNewEvent events = null;

                        int drinkTotalInt = 0;
                        int mainTotalInt = 0;
                        int totalInt = 0;

                        for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                            events = eventSnapshot.getValue(HelperNewEvent.class);
                            drinkTotalInt += events.getDrinkCaloriesInt();
                            mainTotalInt += events.getMainFoodCaloriesInt();


                        }
                        totalInt = drinkTotalInt +mainTotalInt;
                        String total = Integer.toString(totalInt);
//                        show.setText("Based on the records you created, your total calories intake from "+showStartDate.getText().toString()+ " to "+ showEndDate.getText().toString()+ " is: " + total);
                        show.setText(total);
                        double v1;
                        if(show.getText().toString().isEmpty())
                            v1 = 1;
                        else
                            v1= Double.valueOf(show.getText().toString());
                        double[] values_1 = {v1,0,0,2};
                        CategorySeries dataset_1=buildCategoryDataset("测试饼图", values_1);
                        int[] colors_1 ={Color.BLUE,Color.GREEN,Color.MAGENTA,Color.RED};
                        DefaultRenderer renderer_1=buildCategoryRenderer(colors_1);
                        System.out.println(renderer_1);
                        graphicalView = ChartFactory.getPieChartView(getActivity().getApplicationContext(), dataset_1, renderer_1);//饼状图
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

                if(startDate.getTime() > endDate.getTime() || endDate.getTime() < startDate.getTime()){
                    Toast.makeText(myContext,"Date Selected Wrong",Toast.LENGTH_SHORT).show();
                }
                br.removeAllViews();
                br.addView(graphicalView);
            }
        });
        double[] values_1 = {1,2,3,4};
        CategorySeries dataset_1=buildCategoryDataset("测试饼图", values_1);
        int[] colors_1 ={Color.BLUE,Color.GREEN,Color.MAGENTA,Color.RED};
        DefaultRenderer renderer_1=buildCategoryRenderer(colors_1);
        System.out.println(renderer_1);
        graphicalView = ChartFactory.getPieChartView(getActivity().getApplicationContext(), dataset_1, renderer_1);//饼状图
        br.addView(graphicalView);




    }

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("Total Caloires", values[0]);
        series.add("不达标", values[1]);
        series.add("达标", values[2]);
        series.add("优秀",values[3]);
        return series;
    }

    protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLegendTextSize(20);//设置左下角表注的文字大小
        //renderer.setZoomButtonsVisible(true);//设置显示放大缩小按钮
        renderer.setZoomEnabled(false);//设置不允许放大缩小.
        renderer.setChartTitleTextSize(30);//设置图表标题的文字大小
        renderer.setChartTitle("统计结果");//设置图表的标题  默认是居中顶部显示
        renderer.setLabelsTextSize(20);//饼图上标记文字的字体大小
        //renderer.setLabelsColor(Color.WHITE);//饼图上标记文字的颜色
        renderer.setPanEnabled(false);//设置是否可以平移
        //renderer.setDisplayValues(true);//是否显示值
        renderer.setClickEnabled(true);//设置是否可以被点击
        renderer.setMargins(new int[] { 20, 30, 15,0 });
        //margins - an array containing the margin size values, in this order: top, left, bottom, right
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
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