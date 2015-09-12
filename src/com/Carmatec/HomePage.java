package com.Carmatec;


import com.Carmatec.GoogleAnalyticsApp.TrackerName;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends Activity  
{
	Button fresher,experienced,intership;
	static int expfresh=0;
	View view;
	@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState); 
			setContentView(R.layout.homepage);
			getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
			getActionBar().setCustomView(R.layout.actionbar);

			view=	getActionBar().getCustomView();
		    TextView actionname=(TextView)view.findViewById(R.id.textView1);
		    actionname.setText("Home"); 
		    if( isNetworkConnected()==true)
		       {
		    	  
		    	try
				{
				Tracker t = ((GoogleAnalyticsApp) getApplication()).getTracker(TrackerName.APP_TRACKER);
				t.setScreenName("Home Page");
				t.send(new HitBuilders.AppViewBuilder().build());
//				ActionBar bar = getActionBar();
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
			
			
			Login.regval=0;
			  getActionBar().hide();
			fresher=(Button)findViewById(R.id.fresher);
			experienced=(Button)findViewById(R.id.experienced);
			intership=(Button)findViewById(R.id.internship);
				fresher.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				fresher.setBackgroundResource(R.drawable.fresherphigh);
					expfresh=1;
					startActivity(new Intent(getApplicationContext(),UserRegistration.class));
				}
			});
			experienced.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					experienced.setBackgroundResource(R.drawable.expncdh);
					expfresh=2;
					startActivity(new Intent(getApplicationContext(),UserRegistration.class));	
					}
			});
			intership.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intership.setBackgroundResource(R.drawable.experiencephigh);
				expfresh=3;
				startActivity(new Intent(getApplicationContext(),UserRegistration.class));	
			}
		});
		}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		GoogleAnalytics.getInstance(this).reportActivityStart(this);
		GoogleAnalytics.getInstance(HomePage.this).reportActivityStart(this);
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
//		GoogleAnalytics.getInstance(this).reportActivityStop(this);
		GoogleAnalytics.getInstance(HomePage.this).reportActivityStop(this);
	}
	@Override
	public void onBackPressed() {
	 	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomePage.this);
	    alertDialogBuilder.setTitle("Close Carmatec Application");
	    alertDialogBuilder.setMessage("Are sure want to close application");
	    alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog,int id) {
	        	   
	        	   UserProfileEdit.bitmap=null;
	        	   UserProfileEdit.oldbitmap=null;
	        	   UserProfileEdit.companyid.clear();
	        	   UserProfileEdit.companyname.clear();
	       	 
	        	   UserProfileEdit.issued_date.clear();
	        	   UserProfileEdit.certiid.clear();
	        	   UserProfileEdit.certifications.clear();
	        	   UserProfileEdit. candidate_id.clear();
	        	   UserProfileEdit.issued_by.clear();
	       	 
	        	   UserProfileEdit. eduid.clear();
	        	   UserProfileEdit.eduinstitute.clear();
	        	   UserProfileEdit. eduscore.clear();
	        	   UserProfileEdit. eduyear.clear();
	        	   UserProfileEdit. educandidate_id.clear();
	        	   UserProfileEdit. eduspecialization.clear();
	        	   UserProfileEdit. edugraduation.clear();
	       	 
	        	   UserProfileEdit.compto.clear();
	        	   UserProfileEdit.compid.clear();
	        	   UserProfileEdit. complast_salary_drawn.clear();
	        	   UserProfileEdit. compcompany_name.clear();
	        	   UserProfileEdit. compfunctioning.clear();
	        	   UserProfileEdit. comprole.clear();
	        	   UserProfileEdit. compfrom.clear();
	        	   UserProfileEdit. compcandidate_id.clear();
	       	 
	        	   UserProfileEdit. proid.clear();
	        	   UserProfileEdit. proend_date.clear();
	        	   UserProfileEdit. procandidate_previous_detail_id.clear();
	        	   UserProfileEdit. proproject_title.clear();
	        	   UserProfileEdit. prodescription.clear();
	        	   UserProfileEdit. prostart_date.clear();
	        	   UserProfileEdit. prosize.clear();
	        	   UserProfileEdit. prorole.clear();
	        	   
	        	   dialog.dismiss();
	        	   Intent intent = new Intent(Intent.ACTION_MAIN); 
		   	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		   	        intent.addCategory(Intent.CATEGORY_HOME); 
		   	        startActivity(intent);
		   	        finish();
	           }
	         });
	    alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog,int id) {
	               // go to a new activity of the app
	        	   dialog.dismiss();
	        	  
	           }
	         });

	    AlertDialog alertDialog = alertDialogBuilder.create();
	    alertDialog.show();      
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		intership.setBackgroundResource(R.drawable.intershipphigh);
		fresher.setBackgroundResource(R.drawable.fresher);
		experienced.setBackgroundResource(R.drawable.experienced);
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
}
