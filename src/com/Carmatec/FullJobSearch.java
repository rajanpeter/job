package com.Carmatec;


import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.NetworkInfo.DetailedState;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unused")
public class FullJobSearch extends Fragment 
{  
		private ProgressDialog pDialog;
		JobAdapter myadapter;
		static int flag;
	    String name;
	    ArrayList<String> company_description=new ArrayList<String>(); 
	    ArrayList<String> brand_name=new ArrayList<String>(); 
	    ArrayList<String> brand_description=new ArrayList<String>(); 
	    ArrayList<ArrayList<String>> nodes = new ArrayList<ArrayList<String>>();
	    ArrayList<String> nodeList = new ArrayList<String>();
	    ArrayList<String> nodeList1 = new ArrayList<String>();

	    String url="";
	    ListView joblist;
	    
	    JSONObject tabledetails=null;
	    JSONObject tabledetails1=null; 
	    @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_main, container, false);
		MainActivityfragment.actionname.setText("View Jobs");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		MainActivityfragment.pagevalue=1;
		 this.setRetainInstance(true);
		url=ImageSliderCarma.urlvalue+"job_descriptions/list_jobs.json";
        joblist=(ListView)v.findViewById(R.id.joblist);
		myadapter = new JobAdapter(getActivity(), ImageSliderCarma.job_title);
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
    		
    		TextView txtName,compname,expyear;
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
    			holder.compname = (TextView) convertView.findViewById(R.id.compname);
    			holder.expyear = (TextView) convertView.findViewById(R.id.expyear);
    			
    			holder.row = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout1);
    			convertView.setTag(holder);
    		}
    		else
    			holder=(ViewHolder)convertView.getTag();
    		holder.txtName.setText(arr_calllog_name.get(position));
    		holder.compname.setText(ImageSliderCarma.company.get(position));
    		holder.expyear.setText(ImageSliderCarma.experience.get(position));
    		holder.row.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					flag=position;				
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
