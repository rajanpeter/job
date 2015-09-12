package com.Carmatec;



import java.util.ArrayList;
import org.json.JSONObject;

import com.Carmatec.GoogleAnalyticsApp.TrackerName;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
public class ImageSliderCarma extends Activity
{
		static ArrayList<String> company=new ArrayList<String>(); 
		static ArrayList<String> experience=new ArrayList<String>(); 
	   static ArrayList<String> job_id=new ArrayList<String>(); 
	    static  ArrayList<String> job_title=new ArrayList<String>(); 
	    static  ArrayList<String> job_description=new ArrayList<String>(); 
	    static  ArrayList<String> job_startdate=new ArrayList<String>(); 
	    static  ArrayList<String> job_end_date=new ArrayList<String>(); 
	    static  ArrayList<String> job_status=new ArrayList<String>(); 
	    static ArrayList<String> skill=new ArrayList<String>(); 
	    JSONObject tabledetails=null;
		String url="";
		 static String urlvalue="http://careers.carmatec.com/";
//	    static String urlvalue="http://192.168.0.116/HRManagement/";

	int value=0;
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
				setContentView(R.layout.splashscreen);
				
				final ActionBar ab = getActionBar();
			    ab.hide();
				url=urlvalue+"job_descriptions/list_jobs.json";
				 if( isNetworkConnected()==true)
			       {
			    	  
					 try
						{
						Tracker t = ((GoogleAnalyticsApp) getApplication()).getTracker(TrackerName.APP_TRACKER);
						t.setScreenName("Splash Screen");
						t.send(new HitBuilders.AppViewBuilder().build());
//						ActionBar bar = getActionBar();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
			       }
			       else
			       {
			    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
			       }
				   new Handler().postDelayed(new Runnable() {
					   
			            /*
			             * Showing splash screen with a timer. This will be useful when you
			             * want to show case your app logo / company
			             */
			 
			            @Override
			            public void run() {
			                // This method will be executed once the timer is over
			                // Start your app main activity
			            	  try
					        	 {
					        	 SharedPreferences  sharedPreferencess=  getSharedPreferences("MyPref", MODE_PRIVATE); 
					        	 //
					        	      				String restoredText = sharedPreferencess.getString("value", "");
					        	      				System.out
															.println(restoredText+"  preference value");
					        	      				if (restoredText.equals("1"))
					        	      				{
					        	      					startActivity(new Intent(getApplicationContext(),Login.class));
					        	      				  finish(); 
					        	      				}
					        	      				else
					        	      				{
					        	      					startActivity(new Intent(getApplicationContext(),HomePage.class));
					        	      				}				        finish(); 
					        	 }
					        	 catch(Exception e)
					        	 {
					        		 startActivity(new Intent(getApplicationContext(),HomePage.class)); 
					        		  finish(); 
					        	 }
			            }
			        }, 2000);

							    }

			@Override
			protected void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
//				GoogleAnalytics.getInstance(this).reportActivityStart(this);
				try
				{
				GoogleAnalytics.getInstance(ImageSliderCarma.this).reportActivityStart(this);
				}
				catch(Exception e) 
				{ 
				} 
			}
 

			@Override
			protected void onStop() {
				// TODO Auto-generated method stub
				super.onStop();
				try
				{
				GoogleAnalytics.getInstance(ImageSliderCarma.this).reportActivityStop(this);
			}
			catch(Exception e)
			{
				
//				internetconnection();
			}
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

