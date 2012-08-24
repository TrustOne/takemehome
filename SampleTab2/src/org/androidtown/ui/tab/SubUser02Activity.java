package org.androidtown.ui.tab;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class SubUser02Activity extends Activity {
	int user_Inheritance;
	int user_InstallmentSavings;
	int user_InvestedAssets;
	int user_Debt;
	int user_UseAssets;
	dbControler db;
    EditText InstallmentSavings;
    EditText Debt;
    EditText Inheritance;
    EditText InvestedAssets;
    EditText UseAssets;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_user02);
        findViewById(R.id.btnProperty).setOnClickListener(mClickListener);
        db = new dbControler(this);
        InstallmentSavings = (EditText)findViewById(R.id.InstallmentSavings);
        Debt = (EditText)findViewById(R.id.Debt);
        Inheritance = (EditText)findViewById(R.id.Inheritance);
        InvestedAssets = (EditText)findViewById(R.id.InvestedAssets);
        UseAssets = (EditText)findViewById(R.id.UseAssets);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sub_user02, menu);
        return true;
    }

    Button.OnClickListener mClickListener = new View.OnClickListener() {
		
  		public void onClick(View v) {
  			// TODO Auto-generated method stub
  			switch (v.getId()) {
  			
  			case R.id.btnProperty:
  				user_InstallmentSavings = Integer.parseInt(InstallmentSavings.getText().toString());
  				user_Inheritance = Integer.parseInt(Inheritance.getText().toString());
  				user_InvestedAssets = Integer.parseInt(InvestedAssets.getText().toString());
  				user_Debt = Integer.parseInt(Debt.getText().toString());
  				user_UseAssets = Integer.parseInt(UseAssets.getText().toString());
  				db.open();
  			     Log.d("asdas", user_Inheritance+user_InstallmentSavings+user_InvestedAssets+user_Debt+user_UseAssets+ "asdasdsax1121");
  				db.createNoteCP(user_InstallmentSavings, user_Inheritance, user_UseAssets, user_Debt, user_InvestedAssets);
  			     Log.d("asdas", "asdasdsax1121");
  				break;
  			default:
  				break;
  			}
  		}
  		
  	};    
    
}
