package org.androidtown.ui.tab;



import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;




public class SampleTabActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

               setContentView(R.layout.main);
               setupTabs();
    //   this.startActivity(new Intent(this, SplashActivity.class));

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
 	    
 	   // TAB 05 //개인자산정보
 	    spec = tabs.newTabSpec("tab05");
 	    intent = new Intent(this, SubPage05Activity.class); 
 	    intent.putExtra("mode", "new");
	   	intent.putExtra("initialize", true);
	   	intent.putExtra("request", true);
	   	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 	    spec.setContent(intent);
 	    
 	    spec.setIndicator("",getResources().getDrawable(R.drawable.myasset));
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