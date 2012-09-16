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
        //    Log.d("�����ǿ���", "������");
       
       //     Log.d("�����ǿ���", "������");
            databaseoperator.insertDB("����� ��������", "��õ");
//            databaseoperator.insertDB("�����ڻ� ����", 3000d);
//            databaseoperator.insertDB("�����ڻ� �ݵ�", 2000d);
         // View ���� ����Ÿ�� ä���
            str1 = (String) databaseoperator.getDB("����� ��������");
            test1 = (Double) databaseoperator.getDB("�����ڻ� ����");
           test2 = (Double)databaseoperator.getDB("�����ڻ� �ݵ�");
           inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
           
           AlertDialog.Builder alert = new AlertDialog.Builder(this);
           alert.setTitle("�Է��� �����ϴ�");
          alert.setMessage( "�� ����� ����ϱ����ؼ� �Է��� �ʿ��մϴ�" );
          alert.setPositiveButton( "�Է�", new DialogInterface.OnClickListener()
          {
              public void onClick(DialogInterface dialog, int which) {
            	 
           	   showDialog(DIALOG_1);
           	 
              }
         });
       //   this.startActivity(new Intent(this, SplashActivity.class));
          alert.setNegativeButton( "���",new DialogInterface.OnClickListener()
          {
              public void onClick(DialogInterface dialog, int which ) {
            	  Log.d("�� ���������°ɱ�?", "������");
              }
         });
         
          if(str1 == null || test1 == null || test2 == null) 
       	   alert.show();
          
        
        TextView tv = (TextView) findViewById(R.id.tvbkinfor); 
        String str = "����������? ���������� ������� ���������� �����ִ� ���Դϴ�. �̰��� ���Ͽ� ����� �ڻ��� ������ �� ������ ���ݱ��� �ڻ������ ����� �ൿ�� ������ ǥ���� �� �ֽ��ϴ�.";    
        
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
      Log.d("�� ���������°ɱ�?", "������");
      return dialogspec = new AlertDialog.Builder(this)
   //      .setTitle("�Է�")
    //     .setIcon(R.drawable.asset_manager2)
         .setView(linear)
         .setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {      
          public void onClick(DialogInterface dialog, int which) {
             ImageButton button1 = (ImageButton)findViewById(R.id.Imagebutton01);  
             ImageButton button2 = (ImageButton)findViewById(R.id.Imagebutton02);
             ImageButton button3 = (ImageButton)findViewById(R.id.Imagebutton03);
             Log.d("�� ���������°ɱ�?", "������");
          }
         })
         
         .setNegativeButton("���", null)
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
    	 
         e1 = (EditText)dialogspec.findViewById(R.id.editbuche); //��ä
         e2 = (EditText)dialogspec.findViewById(R.id.editbomul);	//����
         e3 = (EditText)dialogspec.findViewById(R.id.editasset);	//���ڻ�
    
             e1.setText(""+str1);Log.d("�����ǿ���", "������");
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
