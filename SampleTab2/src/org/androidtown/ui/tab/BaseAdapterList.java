package org.androidtown.ui.tab;
import java.util.ArrayList; 
import android.content.Context; 
import android.util.Log; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.BaseAdapter; 
import android.widget.TextView; 

public class BaseAdapterList extends BaseAdapter{
	      public Context mContext; 
	      public LayoutInflater mInflater; 
	      public ArrayList<UserInfor> mUserDataList; 
	      public int mLayout; 
	public BaseAdapterList(Context context, int layout, ArrayList<UserInfor> UserDataList)
	{	
		   this.mContext = context; 
		   this.mLayout = layout; 
		   this.mUserDataList = UserDataList; 
	       this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	       
	       	
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		
		return mUserDataList.size(); 
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mUserDataList.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		  final int index = position; 
		  if(convertView == null){
	          convertView = mInflater.inflate(mLayout, parent, false); 
	          
	         }  		  

		          TextView UserName = (TextView)convertView.findViewById(R.id.user_name); 
		          UserName.setText(mUserDataList.get(position).getname()); 
		          
		           TextView UserStock = (TextView)convertView.findViewById(R.id.user_stock); 
		           UserStock.setText(mUserDataList.get(position).getstock()); 
		           
		           TextView UserLand = (TextView)convertView.findViewById(R.id.user_land); 
		           UserLand.setText(mUserDataList.get(position).getland()); 
		           
		           TextView UserCaegun = (TextView)convertView.findViewById(R.id.user_caegun); 
		           UserCaegun.setText(mUserDataList.get(position).getcaegun()); 
		          
		           return convertView; 

	}

}
