package org.androidtown.ui.tab;



import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;




public class SampleTabActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("����� ����");
       alert.setMessage( "�����ϼ���" );
       alert.setPositiveButton( "���� �����", new DialogInterface.OnClickListener()
       {
           public void onClick( DialogInterface dialog, int which ) {
               setContentView(R.layout.main);
               setupTabs();
           }
      });
    //   this.startActivity(new Intent(this, SplashActivity.class));
       



       alert.setNegativeButton( "ó�� �����",new DialogInterface.OnClickListener()
       {
           public void onClick(DialogInterface dialog, int which ) {
        	   setContentView(R.layout.user_infor);
        	    setupUserTabs();
           }
      });

       alert.show();

        
       
      
    }
    
    /**
     * setup tab widget
     */
    private void setupTabs() {
    	TabHost tabs = getTabHost();
 	    
 	    // TAB 01 //�ڻ��
 	    TabHost.TabSpec spec = null;
 	    Intent intent = null;
        
 	    
 	    
 	    spec = tabs.newTabSpec("tab01");
 	    intent = new Intent(this, SubPage01Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

 	    spec.setContent(intent);

 	    spec.setIndicator("",getResources().getDrawable(R.drawable.comparison));
 	    tabs.addTab(spec);
 	    
 	    // TAB 02 //�ڻ�����
 	    spec = tabs.newTabSpec("tab02");
 	    intent = new Intent(this, SubPage02Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);
        
 	    spec.setIndicator("", getResources().getDrawable(R.drawable.diagnosis));
 	    




 	    tabs.addTab(spec);
 	    
 	    // TAB 03 //�繫����
 	    spec = tabs.newTabSpec("tab03");
 	    intent = new Intent(this, SubPage03Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);

 	    spec.setIndicator("",getResources().getDrawable(R.drawable.pencil2));
 	    tabs.addTab(spec);
 	    
 	    // TAB 04 //������ã��
 	    spec = tabs.newTabSpec("tab04");
 	    intent = new Intent(this, SubPage04Activity.class); 
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);
 	    
 	    spec.setIndicator("",getResources().getDrawable(R.drawable.gps2));
 	    tabs.addTab(spec);
 	    
 	    // set current tab
 	    tabs.setCurrentTab(0);
 	   tabs.setBackgroundResource(R.drawable.whitebackg); // ���� �Ʒ� ������
 	    
 	    // ��� �� �޴� ���� ���� 
 	   for(int i = 0; i < tabs.getTabWidget().getChildCount(); i++)

 	  {

 	   tabs.getTabWidget().getChildAt(i).getLayoutParams().height = 80;
 	  tabs.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.whitebackg); //���׸��ߺ�

 	  }        

 


 	   
    }
    
    /**
     * setup tab widget
     */
    private void setupUserTabs() {
    	TabHost tabs = getTabHost();
 	    
 	    // TAB 01 //�⺻����
 	    TabHost.TabSpec spec = null;
 	    Intent intent = null;
        
 	    
 	    
 	    spec = tabs.newTabSpec("tab01");
 	    intent = new Intent(this, SubUser01Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

 	    spec.setContent(intent);

 	    spec.setIndicator("��������");
 	    tabs.addTab(spec);
 	    
 	    // TAB 02 //�ڻ�����
 	    spec = tabs.newTabSpec("tab02");
 	    intent = new Intent(this, SubUser02Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);
        
 	    spec.setIndicator("�ڻ�����");
 	    




 	    tabs.addTab(spec);
 	    
 	    // TAB 03 //�繫����
 	    spec = tabs.newTabSpec("tab03");
 	    intent = new Intent(this, SubUser03Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);

 	    spec.setIndicator("��������");
 	    tabs.addTab(spec);
 	    
 	    // TAB 04 //������ã��
 	    spec = tabs.newTabSpec("tab04");
 	    intent = new Intent(this, SubPage04Activity.class); 
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);
 	    
 	    spec.setIndicator("",getResources().getDrawable(R.drawable.gps2));
 	    tabs.addTab(spec);
 	    
 	    // set current tab
 	    tabs.setCurrentTab(0);
 	   tabs.setBackgroundResource(R.drawable.whitebackg); // ���� �Ʒ� ������
 	    
 	    // ��� �� �޴� ���� ���� 
 	   for(int i = 0; i < tabs.getTabWidget().getChildCount(); i++)

 	  {

 	   tabs.getTabWidget().getChildAt(i).getLayoutParams().height = 80;
 	  tabs.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.whitebackg); //���׸��ߺ�

 	  }        

 


 	   
    }
    
    
    
    
}