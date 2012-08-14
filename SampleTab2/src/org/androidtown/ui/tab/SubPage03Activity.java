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
        renderer.setChartTitle("����� �ڻ� ����");
        renderer.setChartTitleTextSize(35);
        
        CategorySeries series = new CategorySeries("���� ����");
        series.add("ä��", values[0]);
        series.add("�ݵ�", values[1]);
        series.add("�ֽ�", values[2]);
        series.add("�ε���", values[3]);
        
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