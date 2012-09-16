package org.androidtown.ui.tab;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class SubPage05Activity extends Activity {
	TextView tv1,tv2,tv3,tv4,tv5;
	DatabaseOperator databaseOperator;
	double d1,d2,d3,d4;
	String s1,s2,s3,s4;
	NumberToString nts;
	Intent intent = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_page05);
        Log.d("아 씨발", "asdadsadad");
        nts = new NumberToString();
        tv1 = (TextView)findViewById(R.id.tvuserasset);	//금융 자산
        tv2 = (TextView)findViewById(R.id.tvuseretc);	//기타 자산
        tv3 = (TextView)findViewById(R.id.tvuserdebit);	//부채 자산
        tv4 = (TextView)findViewById(R.id.tvuserjicul);	//지출입
        tv5 = (TextView)findViewById(R.id.tvuserprivate);	//개인정보
        
        databaseOperator = new DatabaseOperator(this);
        databaseOperator.open();
        
        d1 = databaseOperator.getSumDB("금융자산");
        d2 = databaseOperator.getSumDB("기타자산");
        d3 = databaseOperator.getSumDB("부채");
        d4 = databaseOperator.getSumDB("지출입");
  
        s1 = nts.returnToString(0.0d);

        s2 = nts.returnToString(d2);
        s3 = nts.returnToString(d3);
        s4 = nts.returnToString(d4);
        
        tv1.setText(s1);
        tv2.setText(s2);
        tv3.setText(s3);
        tv4.setText(s4);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sub_page05, menu);
        return true;
    }
    public void onButtonClicked(View v){
    	switch (v.getId()) {
		case R.id.imgasset:
			Log.d("아 씨발", "asdadsadad");
			 intent = new Intent(this, UserSpecificActivity.class);
			intent.putExtra("getimg", 1);
			
			startActivity(intent);
			break;
			
		case R.id.imgetc:
			intent = new Intent(this, UserSpecificActivity.class);
			intent.putExtra("getimg", 2);
			startActivity(intent);
			break;
		case R.id.imgdebit:
			intent = new Intent(this, UserSpecificActivity.class);
			intent.putExtra("getimg", 3);
			startActivity(intent);
			break;
		case R.id.imgjicul:
			intent = new Intent(this, UserSpecificActivity.class);
			intent.putExtra("getimg", 4);
			startActivity(intent);
			break;
		case R.id.imguserprivate:
			intent = new Intent(this, UserSpecificActivity.class);
			intent.putExtra("getimg", 5);
			startActivity(intent);
			break;
		
		default:
			break;
		}
       }
    
}
