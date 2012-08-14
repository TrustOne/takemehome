package org.androidtown.ui.tab;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SubPage03Activity extends Activity {
    /** Called when the activity is first created. */
	double[] values = new double[] {20, 40, 30, 10};
	int[] colors = new int[] {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subpage03);
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setChartTitle("당신의 자산 비율");
        renderer.setChartTitleTextSize(35);
        
        CategorySeries series = new CategorySeries("현재 비율");
        series.add("채권", values[0]);
        series.add("펀드", values[1]);
        series.add("주식", values[2]);
        series.add("부동산", values[3]);
        
        renderer.setLegendTextSize(25);
        
        for(int color : colors){
        	SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        	r.setColor(color);
        	renderer.addSeriesRenderer(r);
        }
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        
        GraphicalView gv = ChartFactory.getPieChartView(this, series, renderer);
        LinearLayout test = (LinearLayout)findViewById(R.id.test);
        test.addView(gv); 
  }

}