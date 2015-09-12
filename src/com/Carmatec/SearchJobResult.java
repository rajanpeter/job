package com.Carmatec;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchJobResult extends Fragment
{
	 public SearchJobResult() {
         // Empty constructor required for fragment subclasses
     }
	static int searchvalue,checking;
	ListView joblist;
	JobAdapter myadapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_main, container, false);
		MainActivityfragment.actionname.setText("Search Jobs");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		MainActivityfragment.pagevalue=14;
		 this.setRetainInstance(true);
		joblist=(ListView)v.findViewById(R.id.joblist);
		myadapter = new JobAdapter(getActivity(), JobSearch.job_title);
		joblist.setAdapter(myadapter);
		return v; 
}
public class JobAdapter extends BaseAdapter
{

	public String title[];
	public String description[];
	ArrayList<String> arr_calllog_name = new ArrayList<String>();
	public Activity context;
	 ArrayList<Bitmap> imageId;

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
		
		TextView txtName,expyear,compname;
		RelativeLayout row;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		final ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.ccustom, null);
			

			holder.txtName = (TextView) convertView.findViewById(R.id.jobname);
			holder.expyear = (TextView) convertView.findViewById(R.id.expyear);
			holder.compname = (TextView) convertView.findViewById(R.id.compname);
			
			holder.row = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout1);
			convertView.setTag(holder);
		}
		else
			holder=(ViewHolder)convertView.getTag();
		holder.txtName.setText(arr_calllog_name.get(position));
		holder.expyear.setText(JobSearch.experience.get(position));
		holder.compname.setText(JobSearch.company.get(position));

		holder.row.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				flag=position;
				checking=1;
				searchvalue=position;
				 Fragment fragment = new ViewJobDetails();
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
