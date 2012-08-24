package org.androidtown.ui.tab;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class SubPage01Activity extends Activity implements OnScrollListener {
    /** Called when the activity is first created. */
	ArrayList<UserInfor> user = new ArrayList<UserInfor>();
	      private ListView mListView; 
	      private LayoutInflater mInflater; 
	      private BaseAdapterList mUserListAdapter; 
	     private ArrayList<UserInfor> mPartialBusData, mTotalBusData; 
	      private View mView; 
	      private TextView mFooterText; 
	      private int lastCountOfList; 
	      private boolean moreViewing = false, isMax = false; 
	      private static final int MAX_COUNT_PER_PAGE = 15; 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subpage01);
        lastCountOfList = 0; 
        user.add(new UserInfor("±è½ÅÀÏ","35","40","25"));
        user.add(new UserInfor("±èÀç¿ë","30","40","30"));
        user.add(new UserInfor("±èÇü±Ô","40","40","20"));
        user.add(new UserInfor("±èÆ®Æ®","35","35","30"));
        user.add(new UserInfor("±è´Ï´Ï","35","40","25"));
        user.add(new UserInfor("±è¹Ì¹Ì","35","40","25"));
        user.add(new UserInfor("±èºñºñ","35","40","25"));
        user.add(new UserInfor("±èÁöÁö","15","40","45"));
        user.add(new UserInfor("±è½Ã½Ã","55","20","25"));
        user.add(new UserInfor("±èÅ°Å°","5","70","25"));
        user.add(new UserInfor("±èÇÇÇÇ","35","10","55"));
        

        mUserListAdapter = new BaseAdapterList(this, R.layout.infor_list, user); 
        
        mListView = (ListView)findViewById(R.id.user_list_view); 
                 mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
   //              mView = mInflater.inflate(R.layout.user_list_footer, null); 
 //               mListView.addFooterView(mView); 
    //             mListView.setOnScrollListener(this);   
                 mListView.setOnItemClickListener(mItemClickListener); 
                mListView.setAdapter(mUserListAdapter); 

                 Log.d("asdasds", "asdasd");
    }



	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) { 
		          // TODO Auto-generated method stub 
		          int totalVisibleItem = firstVisibleItem + visibleItemCount; 
		          if(isMax == true){ 
		              mFooterText = (TextView)mView.findViewById(R.id.footer_text); 
		              mFooterText.setText("´õÀÌ»ó Á¤º¸¸¦ ¾ò¾î¿Ã ¼ö ¾ø½À´Ï´Ù."); 
		          }else{ 
		              if((totalVisibleItem == totalItemCount) && !(moreViewing)){         
		                  update(); 
		              } 
		          } 
		      } 




	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}



     private void update(){ 
	          moreViewing = true; 
	          Runnable moreViewListItems = new Runnable(){ 
	              public void run() { 
	                  // TODO Auto-generated method stub 
	                  int initCount = lastCountOfList; 
	                  int maxCount = lastCountOfList + MAX_COUNT_PER_PAGE; 
	                   
	                  if(maxCount >= mTotalBusData.size()){ 
	                      maxCount = mTotalBusData.size(); 
	                      isMax = true; 
	                  } 
	  
	                  try{ 
	                      Thread.sleep(1000); 
	                  }catch(Exception e){ 
	                      Log.d("Thread Exception", e.getMessage()); 
	                  } 
	  
	//                  for(int i = initCount; i < maxCount; i++){ 
	//                      BusDatas tempBusDatas = mTotalBusData.get(i); 
	//                      mPartialBusData.add(tempBusDatas); 
	 //                } 
	                  
	                 lastCountOfList = maxCount; 
	                 mHandler.sendEmptyMessage(0); 
	             } 
	         }; 
	          
	         Thread thread = new Thread(moreViewListItems); 
	         thread.start(); 
	     } 
     AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener(){ 
    	  
    	          public void onItemClick(AdapterView parent, View view, int position, long id) { 
    	              // TODO Auto-generated method stub 
    	              Log.d("Item Click Listener", Integer.toString(position) + "'s item is clicked"); 
    	              Intent intent = new Intent(SubPage01Activity.this, EachUserActivity.class);
    	              intent.putExtra("name", user.get(position).getname());
    	              intent.putExtra("stock", user.get(position).getstock());
    	              intent.putExtra("land", user.get(position).getland());
    	              intent.putExtra("caegun", user.get(position).getcaegun());
    	              intent.putExtra("level", position);
    	    //          intent.putExtra("AllUser", user);
    	              startActivity(intent);
    	       //       setResult(RESULT_OK, intent);
    //	              finish();

    	           

    	          } 
    	           
    	      }; 

    	       Handler mHandler = new Handler(){ 
    	  	           @Override 
    	    	           public void handleMessage(Message msg) { 
    	    	               // TODO Auto-generated method stub 
    	    	               if(msg.what == 0){ 
    	    	                   moreViewing = false; 
    	    	                   mUserListAdapter.notifyDataSetChanged(); 
    	    	               } 
    	    	           } 
    	    	       }; 
     
     
}

class UserInfor {
	private String user_name;
	private String user_stock;
	private String user_land;
	private String user_caegun;
	UserInfor(String n, String s, String l, String c)
	{
		user_name = n;
		user_stock = s;
		user_land = l;
		user_caegun =c;
	}
	public String getname(){
		return user_name;
	}
	public String getstock(){
		return user_stock;
	}
	public String getland(){
		return user_land;
	}
	public String getcaegun(){
		return user_caegun;
	}
  }
