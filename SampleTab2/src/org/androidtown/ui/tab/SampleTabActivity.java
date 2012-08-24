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
        alert.setTitle("사용자 선택");
       alert.setMessage( "선택하세요" );
       alert.setPositiveButton( "기존 사용자", new DialogInterface.OnClickListener()
       {
           public void onClick( DialogInterface dialog, int which ) {
               setContentView(R.layout.main);
               setupTabs();
           }
      });
    //   this.startActivity(new Intent(this, SplashActivity.class));
       



       alert.setNegativeButton( "처음 사용자",new DialogInterface.OnClickListener()
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
 	    
 	    // TAB 01 //자산비교
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
 	    
 	    // TAB 02 //자산진단
 	    spec = tabs.newTabSpec("tab02");
 	    intent = new Intent(this, SubPage02Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);
        
 	    spec.setIndicator("", getResources().getDrawable(R.drawable.diagnosis));
 	    




 	    tabs.addTab(spec);
 	    
 	    // TAB 03 //재무설계
 	    spec = tabs.newTabSpec("tab03");
 	    intent = new Intent(this, SubPage03Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);

 	    spec.setIndicator("",getResources().getDrawable(R.drawable.pencil2));
 	    tabs.addTab(spec);
 	    
 	    // TAB 04 //전문가찾기
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
 	   tabs.setBackgroundResource(R.drawable.whitebackg); // 위젯 아래 프레임
 	    
 	    // 상단 탭 메뉴 높이 조정 
 	   for(int i = 0; i < tabs.getTabWidget().getChildCount(); i++)

 	  {

 	   tabs.getTabWidget().getChildAt(i).getLayoutParams().height = 80;
 	  tabs.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.whitebackg); //배경그림중복

 	  }        

 


 	   
    }
    
    /**
     * setup tab widget
     */
    private void setupUserTabs() {
    	TabHost tabs = getTabHost();
 	    
 	    // TAB 01 //기본사항
 	    TabHost.TabSpec spec = null;
 	    Intent intent = null;
        
 	    
 	    
 	    spec = tabs.newTabSpec("tab01");
 	    intent = new Intent(this, SubUser01Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

 	    spec.setContent(intent);

 	    spec.setIndicator("개인정보");
 	    tabs.addTab(spec);
 	    
 	    // TAB 02 //자산진단
 	    spec = tabs.newTabSpec("tab02");
 	    intent = new Intent(this, SubUser02Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);
        
 	    spec.setIndicator("자산형태");
 	    




 	    tabs.addTab(spec);
 	    
 	    // TAB 03 //재무설계
 	    spec = tabs.newTabSpec("tab03");
 	    intent = new Intent(this, SubUser03Activity.class);
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);

 	    spec.setIndicator("수입지출");
 	    tabs.addTab(spec);
 	    
 	    // TAB 04 //전문가찾기
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
 	   tabs.setBackgroundResource(R.drawable.whitebackg); // 위젯 아래 프레임
 	    
 	    // 상단 탭 메뉴 높이 조정 
 	   for(int i = 0; i < tabs.getTabWidget().getChildCount(); i++)

 	  {

 	   tabs.getTabWidget().getChildAt(i).getLayoutParams().height = 80;
 	  tabs.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.whitebackg); //배경그림중복

 	  }        

 


 	   
    }
    
    
    
    
}