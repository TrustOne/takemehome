package org.androidtown.ui.tab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SubUser01Activity extends Activity {
ArrayAdapter<CharSequence> adapter;
int user_age;
String user_job;
String user_resident;
String user_marry;
String user_child;
String user_sex;
dbControler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_user01);
        findViewById(R.id.btnbasicInfor).setOnClickListener(mClickListener);
      
        db = new dbControler(this);
   
        Spinner spin = (Spinner)findViewById(R.id.UserAge);
        Spinner spin2 = (Spinner)findViewById(R.id.UserArea);
        Spinner spin3 = (Spinner)findViewById(R.id.UserJob);
        Spinner spin4 = (Spinner)findViewById(R.id.UserMarry);
        Spinner spin5 = (Spinner)findViewById(R.id.UserSex);
        Spinner spin6 = (Spinner)findViewById(R.id.UserChild);
        spin.setPrompt("º±≈√");
        
        adapter = ArrayAdapter.createFromResource(this, R.array.age, android.R.layout.simple_list_item_checked);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin.setAdapter(adapter);
        
        spin.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                   // TODO Auto-generated method stub
                   String[] ages = getResources().getStringArray(R.array.age);
                   if(position == 0)
                	   user_age = 20;
                   else if(position == 1)
                	   user_age = 25;
                   else if(position == 2)
                	   user_age = 35;
                   else if(position == 3)
                	   user_age = 45;
                   else if(position == 4)
                	   user_age = 55;


            }
            public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub
            }
});

        adapter = ArrayAdapter.createFromResource(this, R.array.area, android.R.layout.simple_list_item_checked);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin2.setAdapter(adapter);
        
        spin2.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                   // TODO Auto-generated method stub
                   String[] area = getResources().getStringArray(R.array.area);
                   user_resident = area[position];

            }
            public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub
            }
});
        
        adapter = ArrayAdapter.createFromResource(this, R.array.job, android.R.layout.simple_list_item_checked);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin3.setAdapter(adapter);
        
        spin3.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                   // TODO Auto-generated method stub
                   String[] job = getResources().getStringArray(R.array.job);
                   user_job = job[position];

            }
            public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub
            }
});
        
        adapter = ArrayAdapter.createFromResource(this, R.array.marry, android.R.layout.simple_list_item_checked);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin4.setAdapter(adapter);
        
        spin4.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                   // TODO Auto-generated method stub
                   String[] marry = getResources().getStringArray(R.array.marry);
                   user_marry = marry[position];

            }
            public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub
            }
});
        
        adapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_list_item_checked);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin5.setAdapter(adapter);
        
        spin5.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                   // TODO Auto-generated method stub
                   String[] sex = getResources().getStringArray(R.array.sex);
                   user_sex = sex[position];

            }
            public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub
            }
});
        
        adapter = ArrayAdapter.createFromResource(this, R.array.child, android.R.layout.simple_list_item_checked);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin6.setAdapter(adapter);
        
        spin6.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                   // TODO Auto-generated method stub
                   String[] child = getResources().getStringArray(R.array.child);
                   user_child = child[position];

            }
            public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub
            }
});

    }
    
    Button.OnClickListener mClickListener = new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnbasicInfor:
				db.open();
			     Log.d("asdas", user_child+user_marry+user_sex+user_job+user_resident+user_age + "asdasdsax1121");
				db.createNoteMember(user_age, user_resident, user_job, user_sex, user_marry, user_child, 0, 0);
			     Log.d("asdas", "asdasdsax1121");
				break;
			default:
				break;
			}
		}
		
	};    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sub_user01, menu);
        return true;
    }

    
}
