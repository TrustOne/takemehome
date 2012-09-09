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
	String[] caegun = new String[] {"ȸ��ä","��ä","����ä","Ư��ä"};
	String[] stock = new String[] {"������","�켱��","������","�Ǳ���"};
	String[] land = new String[] {"����","����Ʈ","��","��"};
	int[] colors = new int[] {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        Bundle bundle = getIntent().getExtras();
        
        printUser(bundle.getInt("Ű"));


        
    }

	private void printUser(int i) {
		// TODO Auto-generated method stub
		
        String title = null;
        if(i == 0)
        	title = "ä��";
        else if(i == 1)
        	title = "�ֽ�";
        else if(i == 2)
        	title = "�ε���";
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setChartTitle("�����"+title +"����");
        renderer.setChartTitleTextSize(35);
        
        CategorySeries series = new CategorySeries("���� ����");
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
