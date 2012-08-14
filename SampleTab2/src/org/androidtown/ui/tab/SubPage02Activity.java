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
	private int which_image = 0;		//� �̹��� �������� �˾ƺ��� ���� ����
	ProgressBar mProgressBar1;
	ProgressBar mProgressBar2;
	ProgressBar mProgressBar3;
	ImageView imageview1;
	ImageView imageview2;
	ImageView imageview3;
	TextView textview1;
	private static final int cash = 60;	//���ݼ� �ڻ�
	private static final int invest = 300;	//�����ڻ�
	private static final int using_money= 50;	//����ڻ�
	private int total_money;
	private static final int fixed_using_money = 100;	//��������
	private static final int moving_using_money = 100;//��������
	private int total_using_money;	//���� ���� + ���� ����
	private int flexibility;	//������ �м� ���� ����
	private boolean single = true;		//��ȥ
	private boolean couple = false;		//��ȥ
	private int temp_value = 80; //�ӽ� �� (�׽�Ʈ)
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
        imageview1.setVisibility(View.INVISIBLE);		//ó���� �հ����� ����� �Ǵϱ� �Ⱥ��̰� ��
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
				if(cash >= flexibility)		//���ݼ� �ڻ��� �������� ���� ���
					mProgressBar1.setProgress(100); //100������ �� ## ���� �ʿ�
				else if(cash < flexibility) //���ݼ� �ڻ��� �������� ���� ���
					mProgressBar1.setProgress(0); //0���� �� ## ���� �ʿ�
				try {
					Thread.sleep(1500);		//��ư ������ 1.5�� ������ �� �����ϱ� ����...
					drawing();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textview1.setText("��~~ �հ�? ����ѵ�? �� ��ƺ��� ���ڵɰ��� ���������������������ä����Ϥ��ä�������");
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
				textview1.setText("��~~~�� �հ�? �ȴ���ѵ�? �� ��ƺ��� ���ڵɰ��� ����������������������asd�����Ϥ��ä���������");
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
				textview1.setText("�� �׳� ���~~~~~�ųʾظӤ����Ĥä������ߤĤä��ޤ�ü����í�ľ�����ʤ�ó�Ĺ��׾ߺ����Š����þƤӤ��ӾƤ�;�äĤ������Ĥ�����Ĥ��տ��Ӻ���");
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
		
		private void drawing(){ //�̹��� ������ ����ֱ� ���� �Լ�, OnClickListener ���� �Ʒ� ���� ���Խ� ������ ��, �׷��� �������� �ű�
	        Animation anim = AnimationUtils.loadAnimation(this, R.anim.my_ani);
	        if(which_image == 1)
	        {
			ImageView iv = (ImageView)findViewById(R.id.imageView1);
			iv.startAnimation(anim);					//�ִϸ��̼� �����ϴ� �Լ�
			imageview1.setVisibility(View.VISIBLE);		//������ ó���� �Ⱥ��̰� �ߴ����� �ٽ� ���̰� �ϱ� �����Լ�
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