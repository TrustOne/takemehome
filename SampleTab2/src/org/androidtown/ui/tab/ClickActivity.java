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

public class ClickActivity extends Activity {
	double[] values = new double[] {20, 40, 30, 10};
	String[] caegun = new String[] {"회사채","국채","지방채","특수채"};
	String[] stock = new String[] {"보통주","우선주","대형주","실권주"};
	String[] land = new String[] {"빌딩","아파트","상가","땅"};
	int[] colors = new int[] {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        Bundle bundle = getIntent().getExtras();
        
        printUser(bundle.getInt("키"));


        
    }

	private void printUser(int i) {
		// TODO Auto-generated method stub
		
        String title = null;
        if(i == 0)
        	title = "채권";
        else if(i == 1)
        	title = "주식";
        else if(i == 2)
        	title = "부동산";
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setChartTitle("당신의"+title +"비율");
        renderer.setChartTitleTextSize(35);
        
        CategorySeries series = new CategorySeries("현재 비율");
        if(i == 0){
        series.add(caegun[0], values[0]);
        series.add(caegun[1], values[1]);
        series.add(caegun[2], values[2]);
        series.add(caegun[3], values[3]);
        }
        
        else if(i == 1){
            series.add(stock[0], values[0]);
            series.add(stock[1], values[1]);
            series.add(stock[2], values[2]);
            series.add(stock[3], values[3]);
            }
        else if(i == 2){
            series.add(land[0], values[0]);
            series.add(land[1], values[1]);
            series.add(land[2], values[2]);
            series.add(land[3], values[3]);
            }
        
        renderer.setLegendTextSize(25);
        
        for(int color : colors){
        	SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        	r.setColor(color);
        	renderer.addSeriesRenderer(r);
        }
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        
        GraphicalView gv = ChartFactory.getPieChartView(this, series, renderer);
        LinearLayout test = (LinearLayout)findViewById(R.id.test2);
        test.addView(gv); 
	}



    
}
