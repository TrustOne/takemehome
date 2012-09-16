package org.androidtown.ui.tab;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private String[] group = {"마법의돈관리","지금당장 재무설계 공부하라","부자공책" ,"은퇴설계"};
	private String[][] childs = {{"- 이 책은 이러이러한 기능을 수행하며\n -!!!합니다."},{"- 이 책은 이러이러한 기능을 수행하며\n - 헐"},{"- 이 책은 며\n러이러 헐"},{"- 이 책하며\n - 헐"}};
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
    	return group[groupPosition];
    }


    public int getGroupCount() {
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

