package org.androidtown.ui.tab;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        start();
    }
        private void start(){
        Handler handler = new Handler(){
        	public void handleMessage(Message msg){
        		finish();
        	}
        };
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}
 

