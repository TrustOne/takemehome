package org.androidtown.ui.tab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubPage03Activity extends Activity  implements OnChildClickListener, 
			OnGroupClickListener, OnGroupExpandListener {
    /** Called when the activity is first created. */
	int lastExpandedGroupPosition = 0; 
	private ExpandableListView listView;
	private String[] group = {"하늘 아래 땅아래", "책2"};
	private String[][] childs = {{"- 이 책은 이러이러한 기능을 수행하며\n -!!!합니다."},{"- 이 책은 이러이러한 기능을 수행하며\n - 헐"}};
	DatabaseOperator databaseoperator= new DatabaseOperator(this);
	LayoutInflater inflater; 
	TextView recommand;
	Double test1,test2;
	String str1;
	AlertDialog dialogspec;
	 final static int DIALOG_1 = 0;
	 EditText e1,e2,e3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.subpage03);
        databaseoperator.open();
    //    Log.d("형규의에러", "엿같다");
        e1 = (EditText)findViewById(R.id.editbuche); //부채
        e2 = (EditText)findViewById(R.id.editbomul);	//보물
        e3 = (EditText)findViewById(R.id.editasset);	//순자산
   //     Log.d("형규의에러", "엿같다");
     // View 관련 데이타를 채운다
        str1 = (String) databaseoperator.getDB("사용자 거주지역");
        test1 = (Double) databaseoperator.getDB("금융자산 예금");
       test2 = (Double)databaseoperator.getDB("금융자산 펀드");
       Log.d(str1 + test1.toString() + test2.toString(), "엿같다");
      
       if(e1 != null){
        e1.setText("juyg");Log.d("형규의에러", "엿같다");
        e2.setText(""+test1.toString());
       e3.setText(""+test2.toString());
       }
        Log.d("형규의에러", "엿같다");
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
        	  
           }
      });
       Log.d("형규의에러", "엿같다");
       if(str1.equals("") && test1 == 0 && test2 ==0) 
    	   alert.show();
       Log.d("형규의에러", "엿같다");
       ExpandableListAdapter adapter = new MyExpandableListAdapter();
       
       listView = (ExpandableListView) findViewById(R.id.listView);
       listView.setAdapter(adapter);
              
       listView.setOnChildClickListener(this);
       listView.setOnGroupClickListener(this);
       listView.setOnGroupExpandListener(this);

        
    }
  
    @Override
    protected Dialog onCreateDialog(int id) {

     switch (id) {
     case DIALOG_1:
      final LinearLayout linear = (LinearLayout)inflater.inflate(R.layout.enterdialog, null);
      
      return dialogspec = new AlertDialog.Builder(this)
   //      .setTitle("입력")
    //     .setIcon(R.drawable.asset_manager2)
         .setView(linear)
         .setPositiveButton("확인", new DialogInterface.OnClickListener() {      
          public void onClick(DialogInterface dialog, int which) {
             ImageButton button1 = (ImageButton)findViewById(R.id.Imagebutton01);  
             ImageButton button2 = (ImageButton)findViewById(R.id.Imagebutton02);
             ImageButton button3 = (ImageButton)findViewById(R.id.Imagebutton03);
          
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
    	 
      break;   
     }   
     default:   
      break;   
     }   
        
     super.onPrepareDialog(id, dialog, args);   
    }

    
    public class MyExpandableListAdapter extends BaseExpandableListAdapter{

		public Object getChild(int groupPosition, int childPosition) {
			return childs[groupPosition][childPosition];
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

//		
		public TextView getGenericView(){
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
			
			TextView textView = new TextView(SubPage03Activity.this);
			textView.setLayoutParams(lp);
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			textView.setPadding(36, 0, 0, 0);
			return textView;
		}

		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			
			LayoutInflater inflater = SubPage03Activity.this.getLayoutInflater();
			View row = inflater.inflate(R.layout.list_row, null);
		
			TextView textView = (TextView) row.findViewById(R.id.label);
			textView.setText(childs[groupPosition][childPosition]);
			
			if(group[groupPosition].equals("책1")){
				ImageView imageView = (ImageView) row.findViewById(R.id.icon);
				imageView.setImageResource(R.drawable.ic_action_search);
			}else if(group[groupPosition].equals("책2")){
				ImageView imageView = (ImageView) row.findViewById(R.id.icon);
				imageView.setImageResource(R.drawable.ic_launcher);
			}
			
			return row;
		}

		
		public int getChildrenCount(int groupPosition) {
			return childs[groupPosition].length;
		}

		public Object getGroup(int groupPosition) {
			return group[groupPosition];
		}

		public int getGroupCount() {
			return group.length;
		}

		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView textView = getGenericView();
			textView.setText(getGroup(groupPosition).toString());
			
			listView.setGroupIndicator(null);
			
			return textView;
			
		}

		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}
    	
    }

    
    
    
    
	public void onGroupExpand(int groupPosition) {
		// TODO Auto-generated method stub

        if (groupPosition != lastExpandedGroupPosition) { 
            listView.collapseGroup(lastExpandedGroupPosition); 
        } 
        lastExpandedGroupPosition = groupPosition; 
	}

	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		if(groupPosition==0){
			setContentView(R.layout.portfolio);
		}
		// TODO Auto-generated method stub
		return false;
	}   

    
}


    
