package org.androidtown.ui.tab;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SubPage02Activity extends Activity {
    /** Called when the activity is first created. */
	private int which_image = 0;		//어떤 이미지 눌렀는지 알아보기 위한 변수
	ProgressBar mProgressBar1;
	ProgressBar mProgressBar2;
	ProgressBar mProgressBar3;
	ImageView imageview1;
	ImageView imageview2;
	ImageView imageview3;
	TextView textview1;
	private static final int cash = 60;	//현금성 자산
	private static final int invest = 300;	//투자자산
	private static final int using_money= 50;	//사용자산
	private int total_money;
	private static final int fixed_using_money = 100;	//고정지출
	private static final int moving_using_money = 100;//변동지출
	private int total_using_money;	//고정 지출 + 변동 지출
	private int flexibility;	//유동성 분석 최종 숫자
	private boolean single = true;		//미혼
	private boolean couple = false;		//기혼
	private int temp_value = 80; //임시 값 (테스트)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subpage02);
        textview1 = (TextView)findViewById(R.id.textView3);
        mProgressBar1 = (ProgressBar)findViewById(R.id.ProgressBar01);
        mProgressBar2 = (ProgressBar)findViewById(R.id.ProgressBar02);
        mProgressBar3 = (ProgressBar)findViewById(R.id.ProgressBar03);
        findViewById(R.id.button1).setOnClickListener(mClickListener);
        findViewById(R.id.button2).setOnClickListener(mClickListener);
        findViewById(R.id.button3).setOnClickListener(mClickListener);
        imageview1 = (ImageView)findViewById(R.id.imageView1);
        imageview2 = (ImageView)findViewById(R.id.imageView2);
        imageview3 = (ImageView)findViewById(R.id.imageView3);
        imageview1.setVisibility(View.INVISIBLE);		//처음에 합격인지 몰라야 되니까 안보이게 함
        imageview2.setVisibility(View.INVISIBLE);
        imageview3.setVisibility(View.INVISIBLE);
        

    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button1:
				which_image = 1;
				total_using_money = fixed_using_money + moving_using_money;
				if(single)
				flexibility = total_using_money/4;
				else if(couple)
					flexibility = total_using_money/2;
				if(cash >= flexibility)		//현금성 자산이 유동성이 있을 경우
					mProgressBar1.setProgress(100); //100점으로 함 ## 수정 필요
				else if(cash < flexibility) //현금성 자산이 유동성이 없을 경우
					mProgressBar1.setProgress(0); //0으로 함 ## 수정 필요
				try {
					Thread.sleep(1500);		//버튼 누른뒤 1.5초 딜레이 후 시작하기 위한...
					drawing();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textview1.setText("오~~ 합격? 대단한데? 더 모아보셈 부자될것임 ㅁㄴㅇㄴㅂㅁㅈㅇㅁ내ㅓㅊㅌ먀ㅐㅓㅊ뱌ㅓ재더");
				break;
				
			case R.id.button2:
				which_image = 2;
				mProgressBar2.setProgress(60);
				try {
					Thread.sleep(1500);
					drawing();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textview1.setText("에~~~불 합격? 안대단한데? 더 모아보셈 부자될것임 ㅁㄴㅇㄴㅂㅁㅈㅇㅁ내ㅓasdㅊㅌ먀ㅐㅓㅊ뱌ㅓ재ㅑ더");
				break;
			case R.id.button3:
				which_image = 3;
				Thread background = new Thread (new Runnable() {    
					public void run() {          
						try {	
				while(mProgressBar3.getProgress() <= temp_value)
				{
					
						Thread.sleep(10);
						 progressHandler.sendMessage(progressHandler.obtainMessage());
				}
						}
					 catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				});background.start();
				try {
					Thread.sleep(1500);
					drawing();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textview1.setText("넌 그냥 평균~~~~~매너앤머ㅐㅑㅔㅓㅇ제버야ㅔㅓㄴ메ㅓ체ㅑ머챠ㅔ어베저엠너ㅏ처ㅔ버네야베ㅑㅕ젣베ㅓ아ㅣㄴ머아ㅣ;ㅓㅔㅐㅂ댜ㅔㅐ벤어ㅔㅏ먼에머베ㅑ");
				break;
			default:
				break;
			}
		}
		
	};
	Handler progressHandler = new Handler() {  
		public void handleMessage(Message msg) {  
			mProgressBar3.incrementProgressBy(1);  
			}  
		};
		
		private void drawing(){ //이미지 도장을 찍어주기 위한 함수, OnClickListener 에서 아래 내용 삽입시 에러가 남, 그래서 이쪽으로 옮김
	        Animation anim = AnimationUtils.loadAnimation(this, R.anim.my_ani);
	        if(which_image == 1)
	        {
			ImageView iv = (ImageView)findViewById(R.id.imageView1);
			iv.startAnimation(anim);					//애니메이션 시작하는 함수
			imageview1.setVisibility(View.VISIBLE);		//위에서 처음에 안보이게 했던것을 다시 보이게 하기 위한함수
	        }
	        else if(which_image == 2)
	        {
			ImageView iv = (ImageView)findViewById(R.id.imageView2);
			iv.startAnimation(anim);
			imageview2.setVisibility(View.VISIBLE);
	        }
	        else if(which_image == 3)
	        {
			ImageView iv = (ImageView)findViewById(R.id.imageView3);
			iv.startAnimation(anim);
			imageview3.setVisibility(View.VISIBLE);
	        }
		}
	
	
    

}