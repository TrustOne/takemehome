package org.androidtown.ui.tab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class User001Activity extends Activity {
	Double user_asset_deposit;
	Double user_asset_fund;
	Double user_asset_insurance;
	Double user_asset_bond;
	Double user_asset_stock;
	
	NumberToString nts;
	TextView tv_deposit,tv_fund,tv_insurance,tv_bond,tv_stock;
	DatabaseOperator databaseOperator;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user001);
        Log.d("test first", "");
        databaseOperator = new DatabaseOperator(this);
        databaseOperator.open();
        nts = new NumberToString();
        
        user_asset_deposit = (Double) databaseOperator.getDB("±ÝÀ¶ÀÚ»ê ¿¹±Ý");
    	user_asset_fund = (Double) databaseOperator.getDB("±ÝÀ¶ÀÚ»ê ÆÝµå");
    	user_asset_insurance = (Double) databaseOperator.getDB("±ÝÀ¶ÀÚ»ê º¸Çè");
    	user_asset_bond = (Double) databaseOperator.getDB("±ÝÀ¶ÀÚ»ê Ã¤±Ç");
    	user_asset_stock = (Double) databaseOperator.getDB("±ÝÀ¶ÀÚ»ê ÁÖ½Ä");
    	
    	tv_deposit = (TextView)findViewById(R.id.tvsaving);
    	tv_fund = (TextView)findViewById(R.id.tvfund);
    	tv_insurance = (TextView)findViewById(R.id.tvinsurance);
    	tv_bond = (TextView)findViewById(R.id.tvbond);
    	tv_stock = (TextView)findViewById(R.id.tvstock);
    	Log.d("test first", "");
    	
    	/*
    	 *tv_deposit.setText(nts.returnToString(user_asset_deposit));
        	tv_fund.setText(nts.returnToString(user_asset_fund));
        	tv_insurance.setText(nts.returnToString(user_asset_insurance));
        	tv_bond.setText(nts.returnToString(user_asset_bond));
        	tv_stock.setText(nts.returnToString(user_asset_stock));  */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user001, menu);
        return true;
    }

    
}
