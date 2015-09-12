package com.Carmatec;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONObject;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewAppliedJob extends Fragment
{
	 public ViewAppliedJob() {
     }
	static int rowval;
	JSONObject jobj;
	JobAdapter myadapter;
	 ListView  joblist;
	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	@SuppressWarnings("static-access")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewappliedjob, container, false);
		MainActivityfragment.actionname.setText("Applied Jobs");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		MainActivityfragment.pagevalue=9;
		 this.setRetainInstance(true);
		Button  addeducation=(Button)v.findViewById(R.id.addeducation);
		addeducation.setVisibility(v.GONE);
	  joblist=(ListView)v.findViewById(R.id.joblist);
	
	
	  myadapter = new JobAdapter(getActivity(), MainActivityfragment.Jobtitle);
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
		
		TextView jtitle,compname,braname,status;
		RelativeLayout row;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		final ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.customapplied, null);
			
			
			holder.status = (TextView) convertView.findViewById(R.id.status);
			holder.braname = (TextView) convertView.findViewById(R.id.braname);
			holder.jtitle = (TextView) convertView.findViewById(R.id.jobname);
			holder.compname = (TextView) convertView.findViewById(R.id.compname);
			holder.row = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout1);
			convertView.setTag(holder);
		}
		else
			holder=(ViewHolder)convertView.getTag();
		holder.jtitle.setText(arr_calllog_name.get(position));
		
		holder.compname.setText(MainActivityfragment.Company.get(position));
		holder.braname.setText(MainActivityfragment.Brand.get(position));
		
		
		if(MainActivityfragment.JobStatus.get(position).equals("Final Level"))
		{
			holder.status.setTextColor(Color.parseColor("#399647"));
			holder.status.setText(MainActivityfragment.JobStatus.get(position));
		}
		else if(MainActivityfragment.JobStatus.get(position).equals("Applied"))
		{
			holder.status.setTextColor(Color.parseColor("#F8B15A"));
			holder.status.setText(MainActivityfragment.JobStatus.get(position));
		}
		else if(MainActivityfragment.JobStatus.get(position).equals("Aptitude Tests Cleared"))
		{
			holder.status.setTextColor(Color.parseColor("#DE5AF8"));
			holder.status.setText(MainActivityfragment.JobStatus.get(position));
		}
		else if(MainActivityfragment.JobStatus.get(position).equals("Level 2 int"))
		{
			holder.status.setTextColor(Color.parseColor("#4555EA"));
			holder.status.setText(MainActivityfragment.JobStatus.get(position));
		}
		else if(MainActivityfragment.JobStatus.get(position).equals("Level 1 int"))
		{
			holder.status.setTextColor(Color.parseColor("#64CBF3"));
			holder.status.setText(MainActivityfragment.JobStatus.get(position));
		}
		else if(MainActivityfragment.JobStatus.get(position).equals("Accepted"))
		{
			holder.status.setTextColor(Color.parseColor("#0B615E"));
			holder.status.setText(MainActivityfragment.JobStatus.get(position));
		}
		else if(MainActivityfragment.JobStatus.get(position).equals("Rejected"))
		{
			holder.status.setTextColor(Color.parseColor("#FF0000")); 
			holder.status.setText(MainActivityfragment.JobStatus.get(position));
		}
		holder.row.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				rowval=position;
				 Fragment fragment = new AppliedJobDetails();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);

			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

			}
		});
		
		return convertView;
		
	
		
	}

}
}
