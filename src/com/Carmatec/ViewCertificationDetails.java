package com.Carmatec;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONObject;
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
public class ViewCertificationDetails extends Fragment
{
	JobAdapter  myadapter ;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	int val;
	Button addcertification;
	JSONObject job;
static int	positionval;
static int	certval;
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

	 public ViewCertificationDetails() {
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewappliedjob, container, false);
		MainActivityfragment.pagevalue=2;
		MainActivityfragment.actionname.setText("Awarded");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		 this.setRetainInstance(true);
		addcertification=(Button)v.findViewById(R.id.addeducation);
	
	ListView  joblist=(ListView)v.findViewById(R.id.joblist);
	  myadapter = new JobAdapter(getActivity(),UserProfileEdit.certifications);
	     joblist.setAdapter(myadapter);
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
			certval=0;
			Fragment fragment = new CertificationRegister();
			Bundle args = new Bundle();
	        fragment.setArguments(args);
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		}
	});
//
	
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
		
		TextView certificationname,issueby,issuedate;
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
			convertView = inflater.inflate(R.layout.certificationcustom, null);
			

			holder.certificationname = (TextView) convertView.findViewById(R.id.certificationname);
			holder.issueby = (TextView) convertView.findViewById(R.id.issueby);
			holder.issuedate = (TextView) convertView.findViewById(R.id.issuedate);
			holder.edit = (Button) convertView.findViewById(R.id.edt);
			
			holder.row = (RelativeLayout) convertView.findViewById(R.id.RelativeLayout1);
			convertView.setTag(holder);
		}
		else
		
		holder=(ViewHolder)convertView.getTag();
		holder.certificationname.setText(arr_calllog_name.get(position));
		holder.issueby.setText(UserProfileEdit.issued_by.get(position));
		holder.issuedate.setText(UserProfileEdit.issued_date.get(position)+" to "+UserProfileEdit.issued_upto.get(position));
		
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
				certval=1;
				Fragment fragment = new CertificationRegister();
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
