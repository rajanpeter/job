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



public class ViewEducationDetails extends Fragment
{
	JobAdapter  myadapter ;
	int val;
	static int positionval,eduval;
	Button addeducation;
	 public ViewEducationDetails() {
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewappliedjob, container, false);
		MainActivityfragment.actionname.setText("Educational Qualifications");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		MainActivityfragment.pagevalue=10;
		 this. setRetainInstance(true);
		addeducation=(Button)v.findViewById(R.id.addeducation);
	ListView  joblist=(ListView)v.findViewById(R.id.joblist);
	addeducation.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			eduval=2;
			Fragment fragment = new EductnRegister();
	        Bundle args = new Bundle();
	        fragment.setArguments(args);
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		}
	});

	  myadapter = new JobAdapter(getActivity(), UserProfileEdit.edugraduation);
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
		
		TextView edutitlez,gradu,insti,score,year;
		RelativeLayout row;
		Button edt;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		final ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.educationcustom, null);
			holder.edutitlez = (TextView) convertView.findViewById(R.id.edutitlez);
			holder.gradu = (TextView) convertView.findViewById(R.id.gradu);
			holder.insti = (TextView) convertView.findViewById(R.id.insti);
			holder.score = (TextView) convertView.findViewById(R.id.scorez);
			holder.year = (TextView) convertView.findViewById(R.id.year);
			holder.edt = (Button) convertView.findViewById(R.id.edt);			
			holder.row = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout1);
			convertView.setTag(holder);
		}
		else
		
			holder=(ViewHolder)convertView.getTag();
		holder.edutitlez.setText(UserProfileEdit.edugraduation.get(position));
		holder.gradu.setText(UserProfileEdit.eduspecialization.get(position));
		holder.insti.setText(UserProfileEdit.eduinstitute.get(position));
		holder.score.setText(UserProfileEdit.eduscore.get(position)+"%");
		holder.year.setText(UserProfileEdit.eduyear.get(position));
		holder.edt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
				animation.setDuration(100); // duration - half a second
				animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
				animation.setRepeatCount(0); // Repeat animation infinitely
//				animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in    
				holder.edt.startAnimation(animation);
				positionval=position;
				eduval=1;
				Fragment fragment = new EductnRegister();
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
