package org.androidtown.ui.tab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Bk_Specific_Activity extends Activity {
	 AlertDialog dialogspec;
		DatabaseOperator databaseoperator= new DatabaseOperator(this);
		Double test1,test2;
		String str1;
		 final static int DIALOG_1 = 0;
		 EditText e1,e2,e3;
		 LayoutInflater inflater;
		 TextView recommand;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bk__specific_);
        
        databaseoperator.open();
        //    Log.d("형규의에러", "엿같다");
       
       //     Log.d("형규의에러", "엿같다");
            databaseoperator.insertDB("사용자 거주지역", "부천");
//            databaseoperator.insertDB("금융자산 예금", 3000d);
//            databaseoperator.insertDB("금융자산 펀드", 2000d);
         // View 관련 데이타를 채운다
            str1 = (String) databaseoperator.getDB("사용자 거주지역");
            test1 = (Double) databaseoperator.getDB("금융자산 예금");
           test2 = (Double)databaseoperator.getDB("금융자산 펀드");
           inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
           
           AlertDialog.Builder alert = new AlertDialog.Builder(this);
           alert.setTitle("입력이 없습니다");
          alert.setMessage( "이 기능을 사용하기위해선 입력이 필요합니다" );
          alert.setPositiveButton( "입력", new DialogInterface.OnClickListener()
          {
              public void onClick(DialogInterface dialog, int which) {
            	 
           	   showDialog(DIALOG_1);
           	 
              }
         });
       //   this.startActivity(new Intent(this, SplashActivity.class));
          alert.setNegativeButton( "취소",new DialogInterface.OnClickListener()
          {
              public void onClick(DialogInterface dialog, int which ) {
            	  Log.d("왜 오류가나는걸까?", "엿같다");
              }
         });
         
          if(str1 == null || test1 == null || test2 == null) 
       	   alert.show();
          
        
        TextView tv = (TextView) findViewById(R.id.tvbkinfor); 
        String str = "부자지수란? 부자지수는 사용자의 부자지수를 구해주는 것입니다. 이것을 통하여 당신의 자산을 평가해줄 수 있으며 지금까지 자산관리한 당신의 행동을 점수로 표현할 수 있습니다.";    
        
        final SpannableStringBuilder sps = new SpannableStringBuilder(str);   
        sps.setSpan(new ForegroundColorSpan(-6697729), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 
        sps.setSpan(new AbsoluteSizeSpan(30), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(sps);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bk__specific_, menu);
        return true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {

     switch (id) {
     case DIALOG_1:
      final LinearLayout linear = (LinearLayout)inflater.inflate(R.layout.enterdialog, null);
      Log.d("왜 오류가나는걸까?", "엿같다");
      return dialogspec = new AlertDialog.Builder(this)
   //      .setTitle("입력")
    //     .setIcon(R.drawable.asset_manager2)
         .setView(linear)
         .setPositiveButton("확인", new DialogInterface.OnClickListener() {      
          public void onClick(DialogInterface dialog, int which) {
             ImageButton button1 = (ImageButton)findViewById(R.id.Imagebutton01);  
             ImageButton button2 = (ImageButton)findViewById(R.id.Imagebutton02);
             ImageButton button3 = (ImageButton)findViewById(R.id.Imagebutton03);
             Log.d("왜 오류가나는걸까?", "엿같다");
          }
         })
         
         .setNegativeButton("취소", null)
         .create(); 


     }
     return null;
    }
    
    public void onButtonClicked(View v){
    	switch (v.getId()) {
		case R.id.Imagebutton01:
			recommand = (TextView) dialogspec.findViewById(R.id.textView0001);

			Log.d("??22", "asdsa???");
			recommand.setText("sdfdsf");
			break;
		case R.id.Imagebutton02:
			recommand = (TextView) dialogspec.findViewById(R.id.textView0001);
			Log.d("??22", "asdsa???");
			recommand.setText("sdhfsdf");
			break;
		case R.id.Imagebutton03:
			recommand = (TextView) dialogspec.findViewById(R.id.textView0001);
			Log.d("??22", "asdsa???");
			recommand.setText("sdfsfdscc");
			break;

		default:
			break;
		}
       }
    @SuppressLint("NewApi")
	@Override  
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {   
        
     switch (id) {   
     case DIALOG_1:   
     {   
    	 
         e1 = (EditText)dialogspec.findViewById(R.id.editbuche); //부채
         e2 = (EditText)dialogspec.findViewById(R.id.editbomul);	//보물
         e3 = (EditText)dialogspec.findViewById(R.id.editasset);	//순자산
    
             e1.setText(""+str1);Log.d("형규의에러", "엿같다");
             if(test1 != null)
             e2.setText(""+test1.toString());
             if(test2 != null)
            e3.setText(""+test2.toString());
            
      break;   
     }   
     default:   
      break;   
     }   
        
     super.onPrepareDialog(id, dialog, args);   
    }   
}
