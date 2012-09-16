package org.androidtown.ui.tab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Bk_UtilityActivity extends Activity implements OnChildClickListener, OnGroupClickListener, OnGroupExpandListener {
	int lastExpandedGroupPosition = 0; 
	private ExpandableListView listView;
	private Context context;
	LayoutInflater inflater;
	Intent intent;
	Bundle bundle;
	int whichGroup;
	Intent specificintent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bk__utility);
        inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this);
        intent = getIntent();
        bundle = intent.getExtras();
        whichGroup = bundle.getInt("getNumber");
        listView = (ExpandableListView) findViewById(R.id.listView2);
        listView.setAdapter(adapter);
               
        listView.setOnChildClickListener(this);
        listView.setOnGroupClickListener(this);
        listView.setOnGroupExpandListener(this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bk__utility, menu);
        return true;
    }
    
    //listAdapter start
    public class ExpandableListAdapter extends BaseExpandableListAdapter {
    	private String[] group = {"마법의통장_부자지수", "마법의통장_적정집값","마법의통장_은퇴적립금","응급자산"};
    	private String[] group2 = {"재무설계_72법칙", "재무설계_단기목표","재무설계_생애가치법","재무설계_니즈분석법","재무설계_주택연금"};
    	private String[] group3 = {"마법의통장_부자지수", "마법의통장_적정집값","마법의통장_은퇴적립금","응급자산"};
    	private String[][] childs = {{"- 이 책은 이러이러한 기능을 수행하며\n -!!!합니다."},{"- 이 책은 이러이러한 기능을 수행하며\n - 헐"}};
    	private Context context;
        @Override
        public boolean areAllItemsEnabled()
        {
            return false;
        }

        public ExpandableListAdapter(Context context) {
            this.context = context;
        }

        public void addItem() {
     
        }

        public Object getChild(int groupPosition, int childPosition) {
        	return childs[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        
        // Return a child view. You can load your custom layout here.

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
        	   if (convertView == null) {
                   LayoutInflater infalInflater = (LayoutInflater) context
                           .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                   convertView = infalInflater.inflate(R.layout.child_layout, null);
               }
        	   TextView textView = (TextView) convertView.findViewById(R.id.tvChild);
    			textView.setText(childs[groupPosition][childPosition]);
           /*    
           	ImageView child = (ImageView)convertView.findViewById(R.id.ImageView01);
           	if(groupPosition == 0){
        		child.setBackgroundResource(R.drawable.bk_magicmanagement);
            }
        	else if(groupPosition == 1){
        		child.setBackgroundResource(R.drawable.bk_letsstart);
        	}
        	else if(groupPosition == 2){
        		child.setBackgroundResource(R.drawable.bk_wealthnote);
        	}
        	else if(groupPosition == 3){
        		child.setBackgroundResource(R.drawable.bk_retirement);
        	}*/
       
            
            // Depending upon the child type, set the imageTextView01
            return convertView;
        }


        public int getChildrenCount(int groupPosition) {
        	Log.d("asd22", "asdw에러씨발");
        	return childs[groupPosition].length;
        }


        public Object getGroup(int groupPosition) {
        	if(whichGroup == 0)
        	return group[groupPosition];
           	else if(whichGroup ==1)
            	return group2[groupPosition];
           	else if(whichGroup ==2)
            	return group3[groupPosition];
        	return group[groupPosition];
        }


        public int getGroupCount() {
        	if(whichGroup == 0)
            	return group.length;
               	else if(whichGroup ==1)
                	return group2.length;
               	else if(whichGroup ==2)
                	return group3.length;
        	
        	return group.length;
        }
        

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        // Return a group view. You can load your custom layout here.

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            String group = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.group_layout, null);
            }
            ImageView child = (ImageView)convertView.findViewById(R.id.ImageView02);
        	if(groupPosition == 0){
        		child.setBackgroundResource(R.drawable.bk_magicmanagement);
            }
        	else if(groupPosition == 1){
        		child.setBackgroundResource(R.drawable.bk_letsstart);
        	}
        	else if(groupPosition == 2){
        		child.setBackgroundResource(R.drawable.bk_wealthnote);
        	}
        	else if(groupPosition == 3){
        		child.setBackgroundResource(R.drawable.bk_retirement);
        	}
            TextView tv = (TextView) convertView.findViewById(R.id.tvGroup);
    
            	tv.setText(group);
           
            
            return convertView;
        }


        public boolean hasStableIds() {
            return true;
        }


        public boolean isChildSelectable(int arg0, int arg1) {
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
			specificintent = new Intent(this, Bk_Specific_Activity.class);
			specificintent.putExtra("getNumber", groupPosition);
			startActivity(specificintent);
		}
		else if(groupPosition==1){
			specificintent = new Intent(this, Bk_Specific_Activity.class);
			specificintent.putExtra("getNumber", groupPosition);
			startActivity(specificintent);
		}
		else if(groupPosition==2){
			specificintent = new Intent(this, Bk_Specific_Activity.class);
			specificintent.putExtra("getNumber", groupPosition);
			startActivity(specificintent);
		}
		else if(groupPosition==3){
			specificintent = new Intent(this, Bk_Specific_Activity.class);
			specificintent.putExtra("getNumber", groupPosition);
			startActivity(specificintent);
		}
		// TODO Auto-generated method stub
		return false;
	}  
    
}
