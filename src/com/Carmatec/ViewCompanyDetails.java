package com.Carmatec;


import java.util.ArrayList;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class ViewCompanyDetails extends Fragment
{
	JobAdapter  myadapter ;
	Button addcertification;
static int	positionval;
static int	val,addval;


	 public ViewCompanyDetails() {
	 }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewappliedjob, container, false);
		MainActivityfragment.actionname.setText("Corporate Experience");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		 this.setRetainInstance(true);
		 MainActivityfragment.pagevalue=3;
		addcertification=(Button)v.findViewById(R.id.addeducation);
	ListView  joblist=(ListView)v.findViewById(R.id.joblist);
	addcertification.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
			animation.setDuration(100); // duration - half a second
			animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
			animation.setRepeatCount(0); // Repeat animation infinitely
//			animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in    
			addcertification.startAnimation(animation);
			addval=2;
			Fragment fragment = new CompanyRegister();
	        Bundle args = new Bundle();
	        fragment.setArguments(args);
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		}
	});
	  myadapter = new JobAdapter(getActivity(), UserProfileEdit.compcompany_name);
     joblist.setAdapter(myadapter);
	return v; 
}
public class JobAdapter extends BaseAdapter
{
	ArrayList<String> arr_calllog_name = new ArrayList<String>();
	public Activity context;
	public LayoutInflater inflater;
	
	 public JobAdapter(Activity context, ArrayList<String> arr_calllog_name) {
		 
		             super();    		
		             this.context = context;
		 
		             this.arr_calllog_name = arr_calllog_name;
		
		             this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
		         }
		 
		        
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr_calllog_name.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public class ViewHolder
	{
		TextView compname,fromcomp,rolecomp;
		RelativeLayout row;
		Button edit,delete;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.companycustom, null);
		
			holder.compname = (TextView) convertView.findViewById(R.id.compname);
			holder.fromcomp = (TextView) convertView.findViewById(R.id.fromcomp);
			holder.rolecomp = (TextView) convertView.findViewById(R.id.rolecomp);
			holder.edit = (Button) convertView.findViewById(R.id.edt);			
			holder.row = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout1);
			convertView.setTag(holder);
		}
		else

			holder=(ViewHolder)convertView.getTag();
		holder.compname.setText(UserProfileEdit.compcompany_name.get(position));
		holder.fromcomp.setText(UserProfileEdit.compfrom.get(position)+" to "+UserProfileEdit.compto.get(position));
		holder.rolecomp.setText(UserProfileEdit.comprole.get(position));
		holder.edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
				animation.setDuration(100); // duration - half a second
				animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
				animation.setRepeatCount(0); // Repeat animation infinitely
//				animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in    
				holder.edit.startAnimation(animation);
				positionval=position;
				addval=1;
				Fragment fragment = new CompanyRegister();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

			}
		}); 
		return convertView;
		
	
		
	}

}
@Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	myadapter.notifyDataSetChanged();
}
}
