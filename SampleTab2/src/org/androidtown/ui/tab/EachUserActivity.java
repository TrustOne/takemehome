package org.androidtown.ui.tab;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class EachUserActivity extends Activity {
	ArrayList<UserInfor> user = new ArrayList<UserInfor>();
	SeekBar mseekbar;
	TextView get_secondbar,get_firstbar;
	final static int EDIT_ACT = 0;
	double[] values = new double[3];
	UserInfor GetAllUser;
	int[] colors = new int[] {Color.BLUE, Color.GREEN, Color.MAGENTA};
	Button select;
	private boolean[] CHECK_SELECTED_INDEX = null;
	int getcaegun;
	int getland;
	int getstock;
	double aver_getland;
	double aver_getstock;
	double aver_getcaegun;
	int Maxseekbar;
	int get_sercondaryProgress;
	int get_user_current_level;
	int get_firstProgress;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_user);
        Bundle bundle = getIntent().getExtras();
        mseekbar = (SeekBar)findViewById(R.id.levelseekbar);
        get_secondbar = (TextView)findViewById(R.id.seconbartextView);
        get_firstbar = (TextView)findViewById(R.id.firstbartextView);
   
        mseekbar.setSecondaryProgress(50);
        findViewById(R.id.btnselect).setOnClickListener(mClickListener);
        findViewById(R.id.btnresult).setOnClickListener(mClickListener);
        mseekbar.setOnSeekBarChangeListener(listenGenerator);
        select = (Button)findViewById(R.id.btnselect);
        
        user.add(new UserInfor("김신일","35","40","25"));
        user.add(new UserInfor("김재용","30","40","30"));
        user.add(new UserInfor("김형규","40","40","20"));
        user.add(new UserInfor("김트트","35","35","30"));
        user.add(new UserInfor("김니니","35","40","25"));
        user.add(new UserInfor("김미미","35","40","25"));
        user.add(new UserInfor("김비비","35","40","25"));
        user.add(new UserInfor("김지지","15","40","45"));
        user.add(new UserInfor("김시시","55","20","25"));
        user.add(new UserInfor("김키키","5","70","25"));
        user.add(new UserInfor("김피피","35","10","55"));
        Maxseekbar = user.size();
        mseekbar.setMax(Maxseekbar);
        calculCaegun(0,user.size());
        calculLand(0,user.size());
        calculStock(0,user.size());
        values[0] = Integer.parseInt(bundle.getString("caegun"));
        values[1] = Integer.parseInt(bundle.getString("stock"));
        values[2] = Integer.parseInt(bundle.getString("land"));
        get_user_current_level = bundle.getInt("level");
        mseekbar.setSecondaryProgress(get_user_current_level);
        get_firstProgress = mseekbar.getProgress();
		get_sercondaryProgress = mseekbar.getSecondaryProgress();
        
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setChartTitle(bundle.getString("name")+"님의 자산 비율");
        renderer.setChartTitleTextSize(17);
        
        Log.d("asdsadw", user.size() + "ss" +  bundle.getInt("stock"));
        CategorySeries series = new CategorySeries("현재 비율");
       series.add("채권:"+values[0]+"%", values[0]);
        series.add("주식"+values[1]+"%",values[1]);
        series.add("부동산"+values[2]+"%", values[2]);
        
        

     
        renderer.setLegendTextSize(25);
        
        for(int color : colors){
        	SimpleSeriesRenderer r2 = new SimpleSeriesRenderer();
        	r2.setColor(color);
        	renderer.addSeriesRenderer(r2);
        }
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
   //     renderer.setClickEnabled(true);
        renderer.setScale(0.5f);

   
        GraphicalView gv2 = ChartFactory.getPieChartView(this, series, renderer);
        LinearLayout test2 = (LinearLayout)findViewById(R.id.gvhalf2);
        test2.addView(gv2); 
    }
    
    private SeekBar.OnSeekBarChangeListener listenGenerator
    = new SeekBar.OnSeekBarChangeListener()
    {

		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			// TODO Auto-generated method stub
			get_secondbar.setText("나의순위:"+mseekbar.getSecondaryProgress());
			get_firstbar.setText("비교순위:"+mseekbar.getProgress());
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			get_firstProgress = mseekbar.getProgress();
			get_sercondaryProgress = mseekbar.getSecondaryProgress();
		}
    	
    };
    
    
  Button.OnClickListener mClickListener = new View.OnClickListener() {
		
  		public void onClick(View v) {
  			// TODO Auto-generated method stub
  			switch (v.getId()) {
  			
  			case R.id.btnselect:
  				ShowCheckListDialog();
  				
  	
  				break;
  			case R.id.btnresult:
  				
  				if(get_firstProgress > get_sercondaryProgress)
  				{
  					calculCaegun(get_sercondaryProgress, get_firstProgress);
  					calculLand(get_sercondaryProgress, get_firstProgress);
  					calculStock(get_sercondaryProgress, get_firstProgress);
  					LinearLayout test2 = (LinearLayout)findViewById(R.id.gvhalf1);
  					test2.removeAllViewsInLayout();
  					printaverage(get_sercondaryProgress, get_firstProgress);
  				}
  				else if(get_firstProgress < get_sercondaryProgress)
  				{
  					calculCaegun(get_firstProgress, get_sercondaryProgress);
  					calculLand(get_firstProgress, get_sercondaryProgress);
  					calculStock(get_firstProgress, get_sercondaryProgress);
  					LinearLayout test2 = (LinearLayout)findViewById(R.id.gvhalf1);
  					test2.removeAllViewsInLayout();
  					printaverage(get_firstProgress, get_sercondaryProgress);
  				}
  				else	
  				{Log.d("123", get_firstProgress+"sadw"+get_sercondaryProgress+"??" + getcaegun);
  					calculCaegun(get_sercondaryProgress, get_firstProgress);
  					calculLand(get_sercondaryProgress, get_firstProgress);
  					calculStock(get_sercondaryProgress, get_firstProgress);
  					LinearLayout test2 = (LinearLayout)findViewById(R.id.gvhalf1);
  					test2.removeAllViewsInLayout();
  					printaverage(get_sercondaryProgress, get_firstProgress);
  				}
  				
  				break;
  			default:
  				break;
  			}
  		}
  		
  	};    
private void ShowCheckListDialog(){
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("선택하세요");
	
	String[] data = new String[] {"주식", "채권", "부동산"};
	CHECK_SELECTED_INDEX = new boolean[] {true,true,true};
	builder.setMultiChoiceItems(data, CHECK_SELECTED_INDEX, new DialogInterface.OnMultiChoiceClickListener() {
		
		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			// TODO Auto-generated method stub
			
		}
	});
	
	builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
		
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
		}
	});
	builder.setPositiveButton("확인",new DialogInterface.OnClickListener() {
		
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			 aver_getland = getland/user.size();
			 aver_getstock = getstock/user.size();
			 aver_getcaegun = getcaegun/user.size();
			
			 printaverage(0,user.size());
			
		}
	});
	builder.create();
	builder.show();
}

public void getUserselect(){
	calculCaegun(get_sercondaryProgress, get_firstProgress);
}

public int calculCaegun(int i,int size){
	
	for(int j = i; j < size ; j++)
	{
		getcaegun += Integer.parseInt(user.get(j).getcaegun());
		Log.d("123", get_firstProgress+"sadw"+get_sercondaryProgress+"??" + getcaegun);
	}
	
	return getcaegun;
	
}
public int calculLand(int i,int size){
	
	for(int j = i; j < size ; j++)
	{
		getland += Integer.parseInt(user.get(j).getland());
		Log.d("123", get_firstProgress+"sadw"+get_sercondaryProgress+"??" + getland);
	}
	
	return getland;
	
}
public int calculStock(int i,int size){

	for(int j =i; j < size ; j++)
	{
		getstock += Integer.parseInt(user.get(j).getstock());
		Log.d("123", get_firstProgress+"sadw"+get_sercondaryProgress+"??" + getstock);
	}
	
	return getstock;
	
}
public void printaverage(int i,int size){
	 DefaultRenderer renderer = new DefaultRenderer();
     renderer.setChartTitle(i+"~"+size+"위사이의 "+"평균 비율");
     renderer.setChartTitleTextSize(17);
     if(size != i)
     {
     aver_getland = getland/(size-i);
	 aver_getstock = getstock/(size-i);
	 aver_getcaegun = getcaegun/(size-i);
     }
     else if(size == i)
     {
    	 Bundle bundle = getIntent().getExtras();
    	 
    	 aver_getcaegun = Integer.parseInt(bundle.getString("caegun"));
         aver_getstock = Integer.parseInt(bundle.getString("stock"));
         aver_getland = Integer.parseInt(bundle.getString("land"));
     }
  
     CategorySeries series = new CategorySeries("현재 비율");
    series.add("채권:"+aver_getcaegun+"%", aver_getcaegun);
     series.add("주식:"+aver_getstock+"%",aver_getstock);
     series.add("부동산:"+aver_getland+"%", aver_getland);


  
     renderer.setLegendTextSize(25);
     
     for(int color : colors){
     	SimpleSeriesRenderer r2 = new SimpleSeriesRenderer();
     	r2.setColor(color);
     	renderer.addSeriesRenderer(r2);
     }
     renderer.setZoomButtonsVisible(true);
     renderer.setZoomEnabled(true);
//     renderer.setClickEnabled(true);
     renderer.setScale(0.5f);


     GraphicalView gv2 = ChartFactory.getPieChartView(this, series, renderer);
     LinearLayout test2 = (LinearLayout)findViewById(R.id.gvhalf1);
     test2.addView(gv2); 
     aver_getcaegun = 0;
     aver_getland = 0;
     aver_getstock = 0;
     getcaegun = 0;
     getland = 0;
     getstock = 0;
     
     
}
    
}
