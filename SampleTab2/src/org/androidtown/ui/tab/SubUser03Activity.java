package org.androidtown.ui.tab;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class SubUser03Activity extends Activity {
	
	int user_salary;
	int user_InvariablExpense;
	int user_Expense;
	
	dbControler db;
	
    EditText Salary;
    EditText InvariableExpense;
    EditText Expense;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_user03);
        findViewById(R.id.btnCashFlow).setOnClickListener(mClickListener);
        db = new dbControler(this);
        Salary = (EditText)findViewById(R.id.Salary);
        InvariableExpense = (EditText)findViewById(R.id.InvariablExpense);
        Expense = (EditText)findViewById(R.id.Expense);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sub_user03, menu);
        return true;
    }
    
 Button.OnClickListener mClickListener = new View.OnClickListener() {
		
  		public void onClick(View v) {
  			// TODO Auto-generated method stub
  			switch (v.getId()) {
  			
  			case R.id.btnCashFlow:
  				user_salary = Integer.parseInt(Salary.getText().toString());
  				user_InvariablExpense = Integer.parseInt(InvariableExpense.getText().toString());
  				user_Expense = Integer.parseInt(Expense.getText().toString());
  				db.open();
  			     Log.d("asdas", user_salary+user_InvariablExpense+user_Expense+ "asdasdsax1121");
  				db.createNoteCF(user_salary, user_InvariablExpense, user_Expense);
  			     Log.d("asdas", "asdasdsax1121");
  				break;
  			default:
  				break;
  			}
  		}
  		
  	};    
    
}
