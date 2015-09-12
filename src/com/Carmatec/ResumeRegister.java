package com.Carmatec;


import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.Carmatec.GoogleAnalyticsApp.TrackerName;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResumeRegister extends Activity
{
	 Button prev,nextaddrs;
	private ProgressDialog pDialog;
	JSONObject jobj;
	JSONObject finalvalz= new JSONObject();
	 JSONObject jsonParam11 = new JSONObject();
     JSONObject jsonParam12 = new JSONObject();
     JSONObject jsonParam13 = new JSONObject();
     JSONObject jsonParam14 = new JSONObject();
	 HashMap<String, Object> map = new HashMap<String, Object>();
	 HashMap<String, Object> c= new HashMap<String, Object>();
	 List<NameValuePair>ss= new ArrayList<NameValuePair>();
	 String s,st;
	 ObjectOutputStream oos; 
	 static InputStream is = null;
	 static JSONObject jObj = null;
	 static String json = ""; 
	 EditText releventexpmonth,monthexp,experience,releventexperience,currentctc,expectedctc,currentrole,noticeperiod,resonchange;
	 List<List<NameValuePair>> Candidate = new ArrayList<List<NameValuePair>>();
	 JSONObject tabledetails=null;
	 View view;
	 @Override
	 protected void onCreate(Bundle savedInstanceState)
	 { 
		 // TODO Auto-generated method stub
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.resumeregister);
		 this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		 getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
			getActionBar().setCustomView(R.layout.actionbar);

		view=	getActionBar().getCustomView();
		    TextView actionname=(TextView)view.findViewById(R.id.textView1);
		    actionname.setText("Edit Profile");
//			actionname.setGravity(Gravity.CENTER);
		    if( isNetworkConnected()==true)
		       {
		    	  
		    	 try
					{
					Tracker t = ((GoogleAnalyticsApp) getApplication()).getTracker(TrackerName.APP_TRACKER);
					t.setScreenName("Profile Edit");
					t.send(new HitBuilders.AppViewBuilder().build());
//					ActionBar bar = getActionBar();
					}
					catch(Exception e)
					{
						System.out.println(e);
						e.printStackTrace();
					}
				
		       }
		       else
		       {
		    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
		       }
		

		  prev=(Button)findViewById(R.id.prev);
		 prev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				prev.setBackgroundResource(R.drawable.whitehigh);
				finish();
			}
		});
		 Candidate.clear();
		 experience=(EditText)findViewById(R.id.experience);
		 monthexp=(EditText)findViewById(R.id.monthexp);
		 releventexpmonth=(EditText)findViewById(R.id.releventexpmonth);
		 releventexperience=(EditText)findViewById(R.id.releventexperience);
		 currentctc=(EditText)findViewById(R.id.currentctc);
		 expectedctc=(EditText)findViewById(R.id.expectedctc);
		 currentrole=(EditText)findViewById(R.id.currentrole);
		 noticeperiod=(EditText)findViewById(R.id.noticeperiod);
		 resonchange=(EditText)findViewById(R.id.resonchange);
		try
		{
			if(UserProfileEdit.experience.equals("null"))
			{
				experience.setText("");
			}
			else
			{
				int exp=Integer.parseInt(UserProfileEdit.experience);
				experience.setText((exp/12)+"");
				monthexp.setText((exp%12)+"");
			}
			if(UserProfileEdit.relevant_exp.equals("null"))
			{
				releventexperience.setText("");
			}
			else
			{
				
				int exp=Integer.parseInt(UserProfileEdit.relevant_exp);
				releventexperience.setText((exp/12)+"");
				releventexpmonth.setText((exp%12)+"");
//				releventexperience.setText(UserProfileEdit.relevant_exp);
			}
			if(UserProfileEdit.current_ctc.equals("null"))
			{
				currentctc.setText("");
			}
			else
			{
				currentctc.setText(UserProfileEdit.current_ctc);
			}
			if(UserProfileEdit.expected_ctc.equals("null"))
			{
				expectedctc.setText("");
			}
			else
			{
				expectedctc.setText(UserProfileEdit.expected_ctc);
			}
			if(UserProfileEdit.current_role.equals("null"))
			{
				currentrole.setText("");
			}
			else
			{
				currentrole.setText(UserProfileEdit.current_role);
			}
			if(UserProfileEdit.notice_period.equals("null"))
			{
				noticeperiod.setText("");
			}
			else
			{
				noticeperiod.setText(UserProfileEdit.notice_period);
			}
			if(UserProfileEdit.reason_for_change.equals("null"))
			{
				resonchange.setText("");
			}
			else
			{
				resonchange.setText(UserProfileEdit.reason_for_change);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 nextaddrs= (Button)findViewById(R.id.submit);
		nextaddrs.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			nextaddrs.setBackgroundResource(R.drawable.violetsmallhigh);
			int relexp=0;
			int exper=0;
			try
			{
				relexp=(Integer.parseInt(releventexperience.getText().toString())*12)+(Integer.parseInt(releventexpmonth.getText().toString()));
			}
			catch(Exception e)
			{
				
			}
			try
			{
				exper=(Integer.parseInt(experience.getText().toString())*12)+(Integer.parseInt(monthexp.getText().toString()));
			}
			catch(Exception e)
			{
				
			}
			try 
			{ 
				Register.nameValuePairs.add(new BasicNameValuePair("experience",exper+""));
				Register.nameValuePairs.add(new BasicNameValuePair("relevant_exp",relexp+""));
				Register.nameValuePairs.add(new BasicNameValuePair("current_ctc",currentctc.getText().toString()));
				Register.nameValuePairs.add(new BasicNameValuePair("expected_ctc", expectedctc.getText().toString()));
				Register.nameValuePairs.add(new BasicNameValuePair("current_role", currentrole.getText().toString()));
				Register.nameValuePairs.add(new BasicNameValuePair("notice_period",noticeperiod.getText().toString()));
				Register.nameValuePairs.add(new BasicNameValuePair("reason_for_change",resonchange.getText().toString()));
System.out.println(Register.nameValuePairs+">>>>>>>>>>>>");
				 if( isNetworkConnected()==true)
			       {
			    	  
			    	   new GetContacts().execute();
			       }
			       else
			       {
			    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
			       }
//			
			
			}
			catch(Exception e)
			{
				nextaddrs.setBackgroundResource(R.drawable.next_btn);
				e.printStackTrace();
			}

		}
	});
	
}
	 private boolean isNetworkConnected() {
	 	  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	 	  NetworkInfo ni = cm.getActiveNetworkInfo();
	 	  if (ni == null) {
	 	   // There are no active networks.
	 	   return false;
	 	  } else
	 	   return true;
	 	 }
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	super.onBackPressed();
	finish();
}
private class GetContacts extends AsyncTask<Void, Void, Void> {

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// Showing progress dialog
		pDialog = new ProgressDialog(ResumeRegister.this);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		pDialog.show();

	}  

	@Override
	protected Void doInBackground(Void... arg0) {
		// Creating service handler class instance
        try{
        	 jobj=JsonCall.postData(Register.nameValuePairs,ImageSliderCarma.urlvalue+"users/candidate_edit_api.json?");
        }
            catch (Exception e)
            {
            	e.printStackTrace();
            	nextaddrs.setBackgroundResource(R.drawable.next_btn);
            }
          
           
		return null;
	}
@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (pDialog.isShowing())
			pDialog.dismiss();
		try
		{
			if(jobj.get("candidatedetails").equals("Saved."))
			{
				AddrsRegister.s2="";
				Register.registerdatas.clear();
				Register.nameValuePairs.clear();
				MainActivityfragment.fragval=1;
				Toast.makeText(getApplicationContext(), "Profile updated", 1).show();
				Intent intent = new Intent(getApplicationContext(), MainActivityfragment.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("EXIT", true);
				startActivity(intent);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Failed Submit", 1).show();
				nextaddrs.setBackgroundResource(R.drawable.next_btn);
			}
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "Error", 1).show();
			nextaddrs.setBackgroundResource(R.drawable.next_btn);
		}
	}

}
@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
//	GoogleAnalytics.getInstance(this).reportActivityStart(this);
	GoogleAnalytics.getInstance(ResumeRegister.this).reportActivityStart(this);
}


@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
//	GoogleAnalytics.getInstance(this).reportActivityStop(this);
	GoogleAnalytics.getInstance(ResumeRegister.this).reportActivityStop(this);
}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	nextaddrs.setBackgroundResource(R.drawable.next_btn);
}
}
