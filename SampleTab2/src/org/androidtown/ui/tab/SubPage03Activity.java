package org.androidtown.ui.tab;
import org.androidtown.ui.tab.ExpandableListAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubPage03Activity extends Activity  implements OnChildClickListener, 
			OnGroupClickListener, OnGroupExpandListener {
    /** Called when the activity is first created. */
	int lastExpandedGroupPosition = 0; 
	private ExpandableListView listView;
	private Context context;
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
	 Intent bk_subActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.subpage03);
        databaseoperator.open();
    //    Log.d("형규의에러", "엿같다");
   
   //     Log.d("형규의에러", "엿같다");
        databaseoperator.insertDB("사용자 거주지역", "부천");
//        databaseoperator.insertDB("금융자산 예금", 3000d);
//        databaseoperator.insertDB("금융자산 펀드", 2000d);
     // View 관련 데이타를 채운다
        str1 = (String) databaseoperator.getDB("사용자 거주지역");
        test1 = (Double) databaseoperator.getDB("금융자산 예금");
       test2 = (Double)databaseoperator.getDB("금융자산 펀드");
      
      
     
     
    
     
       
       
       
       ExpandableListAdapter adapter = new ExpandableListAdapter(this);
       
       listView = (ExpandableListView) findViewById(R.id.listView2);
       listView.setAdapter(adapter);
              
       listView.setOnChildClickListener(this);
       listView.setOnGroupClickListener(this);
       listView.setOnGroupExpandListener(this);

        
    }
  
  



  /*
    public class MyExpandableListAdapter extends BaseExpandableListAdapter{

    	public MyExpandableListAdapter(Context context) {
            this.context = context;

        }
    	
		public Object getChild(int groupPosition, int childPosition) {
			return childs[groupPosition][childPosition];
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		
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
			/*TextView textView = getGenericView();
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
    	
    }   */ 
    
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
			bk_subActivity = new Intent(this, Bk_UtilityActivity.class);
			bk_subActivity.putExtra("getNumber", groupPosition);
			startActivity(bk_subActivity);
		}
		else if(groupPosition==1){
			bk_subActivity = new Intent(this, Bk_UtilityActivity.class);
			bk_subActivity.putExtra("getNumber", groupPosition);
			startActivity(bk_subActivity);
		}
		else if(groupPosition==2){
			bk_subActivity = new Intent(this, Bk_UtilityActivity.class);
			bk_subActivity.putExtra("getNumber", groupPosition);
			startActivity(bk_subActivity);
		}
		else if(groupPosition==3){
			bk_subActivity = new Intent(this, Bk_UtilityActivity.class);
			bk_subActivity.putExtra("getNumber", groupPosition);
			startActivity(bk_subActivity);
		}
		// TODO Auto-generated method stub
		return false;
	}   

    
}


    
