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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class ViewProjectDetails extends Fragment
{
	int val;
	JobAdapter  myadapter ;

	Button addcertification;
static int	positionval;
static int	proval;
	 public ViewProjectDetails() {
        // Empty constructor required for fragment subclasses
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewappliedjob, container, false);
		MainActivityfragment.actionname.setText("Project details");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		 this.setRetainInstance(true);
		 MainActivityfragment.pagevalue=12;
		addcertification=(Button)v.findViewById(R.id.addeducation);
	ListView  joblist=(ListView)v.findViewById(R.id.joblist);
	addcertification.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			proval=0;
			Fragment fragment = new ProjectRegister();
	        Bundle args = new Bundle();
	        fragment.setArguments(args);
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		}
	});

	  myadapter = new JobAdapter(getActivity(),UserProfileEdit.proproject_title);
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
		
		TextView protitle,prostartdate,proenddate,prorole,companyname;
		RelativeLayout row;
		Button edit;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.projectcustom, null);
			

			holder.protitle = (TextView) convertView.findViewById(R.id.protitle);
			holder.prostartdate = (TextView) convertView.findViewById(R.id.prostartdate);
			holder.proenddate = (TextView) convertView.findViewById(R.id.proenddate);
			holder.prorole = (TextView) convertView.findViewById(R.id.prorole);
			holder.companyname = (TextView) convertView.findViewById(R.id.companyname);
			holder.edit = (Button) convertView.findViewById(R.id.edt);			
			holder.row = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout1);
			convertView.setTag(holder);
		}
		else
		
			holder=(ViewHolder)convertView.getTag();
		holder.protitle.setText(UserProfileEdit.proproject_title.get(position));
		holder.prostartdate.setText("("+UserProfileEdit.prostart_date.get(position)+" to ");
		holder.proenddate.setText(UserProfileEdit.proend_date.get(position)+")");
		holder.prorole.setText(UserProfileEdit.prorole.get(position));
		for(int i=0;i<UserProfileEdit.companyid.size();i++)
		{
			if(UserProfileEdit.companyid.get(i).equals(UserProfileEdit.procandidate_previous_detail_id.get(position)))
			{
				holder.companyname.setText(UserProfileEdit.companyname.get(i));
			}
		}
		
		holder.edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				positionval=position;
				proval=1;
				Fragment fragment = new ProjectRegister();
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
