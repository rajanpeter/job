package com.Carmatec;


import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.Carmatec.GoogleAnalyticsApp.TrackerName;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
	public class MainActivityfragment extends Activity {
	static ArrayList<String> Valuesarr= new ArrayList<String>();
	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	ArrayAdapter<String> adapter;
	   JSONObject tabledetails=null;
	static int pagevalue=0; 
	static int fragval,jobval;
	static ArrayList<String> Brand= new ArrayList<String>();
	static ArrayList<String> JobStatus= new ArrayList<String>();
	static ArrayList<String> Company= new ArrayList<String>();
	static ArrayList<String> Jobtitle= new ArrayList<String>();
	static ArrayList<Object> aptitude= new ArrayList<Object>();
	static ArrayList<Object> aptitudeval= new ArrayList<Object>();
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;	
	String mTitle=""; 
	JSONObject jobj;
	static TextView actionname;
	private ProgressDialog pDialog;
	View view;
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		 this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);	
		 getActionBar().setDisplayHomeAsUpEnabled(true);
			getActionBar().setCustomView(R.layout.actionbar);
			
		view=	getActionBar().getCustomView();
	     actionname=(TextView)view.findViewById(R.id.textView1);
	   
	     actionname.setGravity(Gravity.CENTER);
	    
		Valuesarr.clear();		
			Valuesarr.clear(); 
			Valuesarr.add("Applied Jobs");
			Valuesarr.add("Edit Profile");
			Valuesarr.add("View Jobs");
			Valuesarr.add("Search Job");
			Valuesarr.add("About Us");
			Valuesarr.add("Reach Us");
			Valuesarr.add("Change password");
			Valuesarr.add("Logout");

		mTitle = (String) getTitle();		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);		
		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		mDrawerToggle = new ActionBarDrawerToggle(	this, 
													mDrawerLayout, 
													R.drawable.ic_drawer, 
													R.string.drawer_open,
													R.string.drawer_close){
			
			/** Called when drawer is closed */
            public void onDrawerClosed(View view) {
//            	getActionBar().setTitle(mTitle);
            	invalidateOptionsMenu();
                
            }

            /** Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
			
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		adapter = new ArrayAdapter<String>(
					getBaseContext(), 
					R.layout.drawer_list_item  , 
					Valuesarr
				);
		mDrawerList.setAdapter(adapter);
		getActionBar().setHomeButtonEnabled(true); 
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setBackgroundDrawable(new 
				   ColorDrawable(Color.parseColor("#eaeaea"))); 
			if(Login.personal_details.equals("true")&&Login.certifications.equals("true")&&Login.education.equals("true")&&Login.company.equals("true"))
			{
				if(fragval==1)
				{
					Fragment fragment = new UserProfileEdit();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);
			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			        fragval=0;
				}
				else
				{
				if(Login.appliedjob.equals("true"))
				{
					 if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
				       }
				}
				else
				{
				Fragment fragment = new JobSearch();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		        actionname.setText("Search Jobs");
				}
				}
			}
			else
			{
				Fragment fragment = new UserProfileEdit();
				Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		        actionname.setText("Edit Profile");
			}
			if(pagevalue==15)
			{
				tracker("Applied Job Detail");
			}
			else if(pagevalue==17)
			{
				tracker("Certification Register");
			}
			else if(pagevalue==19)
			{
				tracker("Company Register");
			}
			else if(pagevalue==18)
			{
				tracker("Education Register");
			}
			else if(pagevalue==1)
			{
				tracker("All Jobs");
			}
			else if(pagevalue==5)
			{
				tracker("Search Job");
			}
			else if(pagevalue==20)
			{
				tracker("Project Register");
			}
			else if(pagevalue==14)
			{
				tracker("Search Job Result");
			}
			else if(pagevalue==8)
			{
				tracker("View and Edit Profile");
			}
			else if(pagevalue==9)
			{
				tracker("View Applied Job");
			}
			else if(pagevalue==2) 
			{
				tracker("View Certification Details");
			}
			else if(pagevalue==3)
			{
				tracker("View Company Details");
			}
			else if(pagevalue==10)
			{
				tracker("View Education Details");
			}
			else if(pagevalue==11||pagevalue==13)
			{
				tracker("View Job Details");
			}
			else if(pagevalue==12)
			{
				tracker("View Project Details");
			}
			else
			{
				tracker("User Details");
			}

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {			
			@Override
			public void onItemClick(AdapterView<?> parent,
							View view,
							int position,
							long id) {	
				if(Valuesarr.get(position).equals("Home"))
				{
						startActivity(new Intent(getApplicationContext(),HomePage.class));

				        mDrawerList.setItemChecked(position, true);
				        setTitle("broom");
				        mDrawerLayout.closeDrawer(mDrawerList);	
				}
				else if(Valuesarr.get(position).equals("View Jobs"))
				{
					jobval=1;
					 if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
				       }
					
				}
				else if(Valuesarr.get(position).equals("Edit Profile"))
				{
					  	Fragment fragment = new UserProfileEdit();
				        Bundle args = new Bundle();
				        fragment.setArguments(args);
				        FragmentManager fragmentManager = getFragmentManager();
				        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
					
			        mDrawerLayout.closeDrawer(mDrawerList);	
			        actionname.setText("Edit Profile");
				}

				else if(Valuesarr.get(position).equals("Search Job"))
				{
					  	Fragment fragment = new JobSearch();
				        Bundle args = new Bundle();
				        fragment.setArguments(args);

				        FragmentManager fragmentManager = getFragmentManager();
				        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
				        mDrawerList.setItemChecked(position, true);
				        setTitle("broom");
				        mDrawerLayout.closeDrawer(mDrawerList);	
				        actionname.setText("Search Jobs");
				}
				else if(Valuesarr.get(position).equals("About Us"))
				{
			        mDrawerLayout.closeDrawer(mDrawerList);	
					startActivity(new Intent(getApplicationContext(),AboutMain.class));
				}
				else if(Valuesarr.get(position).equals("Applied Jobs"))
				{
					if(Login.loginvalue==1)
					{
					        mDrawerList.setItemChecked(position, true);
					        setTitle("broom");
					        mDrawerLayout.closeDrawer(mDrawerList);
					        if( isNetworkConnected()==true)
						       {
						    	  
						    	   new GetContacts().execute();
						       }
						       else
						       {
						    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
						       }
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Login First", 1).show();
						mDrawerLayout.closeDrawer(mDrawerList);	
					}
				}
				else if(Valuesarr.get(position).equals("Change password"))
				{
					
					Fragment fragment = new Change_Password();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);

			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			        mDrawerList.setItemChecked(position, true);
			        setTitle("broom");
			        mDrawerLayout.closeDrawer(mDrawerList);	
			        actionname.setText("Search Jobs");
					
				}
				
				else if(Valuesarr.get(position).equals("Logout"))
				{
					if(Login.loginvalue==1)
					{
						mDrawerLayout.closeDrawer(mDrawerList);
						openAlert();
						}
					else
					{
						Toast.makeText(getApplicationContext(), "Login First", 1).show();
						mDrawerLayout.closeDrawer(mDrawerList);	
					}				
				}
				else if(Valuesarr.get(position).equals("Login"))
				{

					mDrawerLayout.closeDrawer(mDrawerList);	
					startActivity(new Intent(getApplicationContext(),com.Carmatec.Login.class));
				}
				else if(Valuesarr.get(position).equals("Reach Us"))
				{

		        mDrawerList.setItemChecked(position, true);
			        mDrawerLayout.closeDrawer(mDrawerList);	
			        if( isNetworkConnected()==true)
				       {
				    	  
//						
				        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
				        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
				              !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				          // Build the alert dialog
				          AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityfragment.this);
				          builder.setTitle("Location Services Not Active");
				          builder.setMessage("Please enable Location Services and GPS");
				          builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				          public void onClick(DialogInterface dialogInterface, int i) {
				            // Show location settings when the user acknowledges the alert dialog
				            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				            startActivity(intent);
//				            startActivity(new Intent(getApplicationContext(),MapView.class));
				            }
				          });
				          Dialog alertDialog = builder.create();
				          alertDialog.setCanceledOnTouchOutside(false);
				          alertDialog.show();
				        }
				        else
				        {
				        	 startActivity(new Intent(getApplicationContext(),MapView.class));
				        }
				       }
				       else
				       {
				    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
				       }
					
				}
				
			}
		});	
	}


	 @Override
	 protected void onPostCreate(Bundle savedInstanceState) {
		 super.onPostCreate(savedInstanceState);	     
	     mDrawerToggle.syncState();	
	 }
	
	/** Handling the touch event of app icon */
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {     
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	private void openAlert() {
	    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivityfragment.this);
	     
	    alertDialogBuilder.setTitle("Logout"); 
	    alertDialogBuilder.setMessage("Are sure want to logout");
	    alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog,int id) {
					Login.mainvalue=0;
					Login.loginvalue=0;
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
		        	   mDrawerLayout.closeDrawer(mDrawerList);	
		        	   Intent intent = new Intent(getApplicationContext(), Login.class);
		        	   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	   startActivity(intent);
//					startActivity(new Intent(getApplicationContext(),Login.class));
				       
	        	   dialog.dismiss();
	           }
	         });
	    alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog,int id) {
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
		
		if(pagevalue==15)
		{
			tracker("Applied Job Detail");
		}
		else if(pagevalue==17)
		{
			tracker("Certification Register");
		}
		else if(pagevalue==20)
		{
			tracker("About us");
		}
		else if(pagevalue==19)
		{
			tracker("Company Register");
		}
		else if(pagevalue==18)
		{
			tracker("Education Register");
		}
		else if(pagevalue==1)
		{
			tracker("All Jobs");
		}
		else if(pagevalue==5)
		{
			tracker("Search Job");
		}
		else if(pagevalue==20)
		{
			tracker("Project Register");
		}
		else if(pagevalue==14)
		{
			tracker("Search Job Result");
		}
		else if(pagevalue==8)
		{
			tracker("View and Edit Profile");
		}
		else if(pagevalue==9)
		{
			tracker("View Applied Job");
		}
		else if(pagevalue==2) 
		{
			tracker("View Certification Details");
		}
		else if(pagevalue==3)
		{
			tracker("View Company Details");
		}
		else if(pagevalue==10)
		{
			tracker("View Education Details");
		}
		else if(pagevalue==11||pagevalue==13)
		{
			tracker("View Job Details");
		}
		else if(pagevalue==12)
		{
			tracker("View Project Details");
		}
		else
		{
			tracker("User Details");
		}
	}
	private void tracker(String string) {
		// TODO Auto-generated method stub
		 if( isNetworkConnected()==true)
	       {
	    	  
			 try
				{
				Tracker t = ((GoogleAnalyticsApp) getApplication()).getTracker(TrackerName.APP_TRACKER);
				t.setScreenName(string);
				t.send(new HitBuilders.AppViewBuilder().build());
//				ActionBar bar = getActionBar();
				}
				catch(Exception e)
				{
				
				}
	       }
	       else
	       {
	    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
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
	 private class GetContacts extends AsyncTask<Void, Void, Void> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(MainActivityfragment.this);
				pDialog.setMessage("Please wait...");
				pDialog.setCancelable(false);
				pDialog.show();

			}  

			@Override
			protected Void doInBackground(Void... arg0) {
				if(jobval==1)
				{
					 try{
			               JSONParser jparser=new JSONParser();
			               JSONObject json=jparser.getJSONFromUrl(ImageSliderCarma.urlvalue+"job_descriptions/list_jobs.json");
			               ImageSliderCarma.job_id.clear();
			               ImageSliderCarma.job_title.clear();
			               ImageSliderCarma.job_description.clear();
			               ImageSliderCarma.job_startdate.clear();
			               ImageSliderCarma.job_end_date.clear();
			               ImageSliderCarma.job_status.clear();   
			               ImageSliderCarma.experience.clear();
			               ImageSliderCarma.experience.clear();
			               tabledetails=json.getJSONObject("job_description");
				           System.out.println("tabledetails????????????"+tabledetails);
				           Iterator<?> keys = tabledetails.keys();
				           while( keys.hasNext() ){
				           String key = (String)keys.next();
			               if( tabledetails.get(key) instanceof JSONObject ){
			           	   JSONObject c = (JSONObject) tabledetails.get(key);
			           	   System.out.println("job details >>>>>>>>>>>>>"+c);
			           	ImageSliderCarma.job_id.add(c.getString("job_id"));
			           	ImageSliderCarma.job_title.add(c.getString("job_title"));
			           	ImageSliderCarma.job_description.add(c.getString("job_description"));
			           	ImageSliderCarma.job_startdate.add(c.getString("job_startdate"));
			           	ImageSliderCarma.job_end_date.add(c.getString("job_end_date"));
			           	ImageSliderCarma.job_status.add(c.getString("job_status"));
			           	ImageSliderCarma.company.add(c.getString("company"));
			           	ImageSliderCarma.experience.add(c.getString("experience"));
			    				 String s=""; 
			    				try
			    				{
			    				 JSONObject skil =c.getJSONObject("skill");
			    				 Iterator<?> keys1 = skil.keys();
						           while( keys1.hasNext() ){
						           String key1 = (String)keys1.next();
						           s=s+skil.getString(key1)+",";
						           }
						          
			    				}
			    				catch(Exception ee)
			    				{
			    					ee.printStackTrace();
			    				}
			    				   
			    				ImageSliderCarma.skill.add(s);
			              }
			          }

			            }
			            catch (JSONException e)
			            {
			            	e.printStackTrace();
			            }
				}
				else
				{
				Brand.clear();
		    	JobStatus.clear();
		    	Company.clear();
		    	Jobtitle.clear();
		    	aptitude.clear();
	        	nameValuePairs.clear();
			    nameValuePairs.add(new BasicNameValuePair("action", "view"));
		        nameValuePairs.add(new BasicNameValuePair("candidate_id",Login.candidate_id));
		        try{
		        	 jobj=JsonCall.postData(nameValuePairs, ImageSliderCarma.urlvalue+"users/applied_jobs_api.json?");
		        }
		            catch (Exception e)
		            {
		            	e.printStackTrace();
		            }
				}
		           
				return null;
			}
		@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				if (pDialog.isShowing())
					pDialog.dismiss();
				if(jobval==1)
				{
					if(ImageSliderCarma.job_id.size()==0)
					{
						Toast.makeText(getApplicationContext(), "No jobs", 1).show();
					}
					else
					{
						Fragment fragment = new FullJobSearch();
				        Bundle args = new Bundle();
				        fragment.setArguments(args);
				        FragmentManager fragmentManager = getFragmentManager();
				        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
				        mDrawerLayout.closeDrawer(mDrawerList);
				        actionname.setText("View Jobs");
					}
					jobval=0;
				}
				else
				{
				try {
	        		ArrayList<String> interviewval = null;
	        		ArrayList<String> interviewval1 = null;
	        		
					JSONObject result1= jobj.getJSONObject("result");
					JSONObject applied_jobs= result1.getJSONObject("applied_jobs");
					 Iterator<?> keys = applied_jobs.keys();
			           while( keys.hasNext() ){
			           String key = (String)keys.next();
		               if( applied_jobs.get(key) instanceof JSONObject ){
		           	   JSONObject c = (JSONObject) applied_jobs.get(key);
			            interviewval= new ArrayList<String>();
			            interviewval1= new ArrayList<String>();
		           	Brand.add(c.getString("Brand"));
		           	JobStatus.add(c.getString("JobStatus"));
		           	Company.add(c.getString("Company"));
		           	Jobtitle.add(c.getString("JobTitle"));
		           	try
		           	{
		           	JSONObject recent_interviews=c.getJSONObject("recent_interviews");
		           
		           	Iterator<?> keys1 = recent_interviews.keys();

			           while( keys1.hasNext() ){
			        	  
			           String key1 = (String)keys1.next();
		               if( recent_interviews.get(key1) instanceof JSONObject ){
		           	   JSONObject c1 = (JSONObject) recent_interviews.get(key1);
		           	Iterator<?> keys2 = c1.keys();
		           
			           while( keys2.hasNext() ){
			          
			           String key2 = (String)keys2.next();
			           interviewval.add(c1.getString(key2));
			           interviewval1.add(key2);		           	 
			           }
			         
		               }
		               
			           }
			           aptitude.add(interviewval);
		               aptitudeval.add(interviewval1);
		           	}
		           	catch(Exception e)
		           	{
		           	 aptitude.add("Error");
		           	 aptitudeval.add("Error");
		           	}
		               }
			           }

	  Fragment fragment = new ViewAppliedJob();
      Bundle args = new Bundle();
      fragment.setArguments(args);

      FragmentManager fragmentManager = getFragmentManager();
      fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
      actionname.setText("Applied Jobs");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "No Jobs", 1).show();
				}
				}
//				
			}
//
		}
	 @Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
//			GoogleAnalytics.getInstance(this).reportActivityStart(this);
			GoogleAnalytics.getInstance(MainActivityfragment.this).reportActivityStart(this);
		}


		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
//			GoogleAnalytics.getInstance(this).reportActivityStop(this);
			GoogleAnalytics.getInstance(MainActivityfragment.this).reportActivityStop(this);
		}
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
		if(MainActivityfragment.pagevalue==20||MainActivityfragment.pagevalue==1||MainActivityfragment.pagevalue==12||MainActivityfragment.pagevalue==2||MainActivityfragment.pagevalue==10||MainActivityfragment.pagevalue==3||MainActivityfragment.pagevalue==6||MainActivityfragment.pagevalue==1||MainActivityfragment.pagevalue==5||MainActivityfragment.pagevalue==9)
		{
				
			 	Fragment fragment = new UserProfileEdit();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();	
		}
		else if(MainActivityfragment.pagevalue==13)
		{
				Fragment fragment = new FullJobSearch();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		}
		else if(MainActivityfragment.pagevalue==11)
		{
				Fragment fragment = new SearchJobResult();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		}
		else if(MainActivityfragment.pagevalue==15)
		{
				Fragment fragment = new ViewAppliedJob();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		}
		else if(MainActivityfragment.pagevalue==17)
		{
			if(Login.certifications.equals("true"))
			{
				Fragment fragment = new ViewCertificationDetails();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			}
			else
			{
				Fragment fragment = new UserProfileEdit();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();	
			}
		}
	
		else if(MainActivityfragment.pagevalue==18)
		{
			if(Login.education.equals("true"))
			{
				Fragment fragment = new ViewEducationDetails();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			}
			else
			{
				Fragment fragment = new UserProfileEdit();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();	
			}
		}
		else if(MainActivityfragment.pagevalue==19)
		{
			if(Login.company.equals("true"))
			{
				Fragment fragment = new ViewCompanyDetails();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			}
			else
			{
				Fragment fragment = new UserProfileEdit();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();	
			}
		}
		else if(MainActivityfragment.pagevalue==20)
		{
			if(Login.project.equals("true"))
			{
				Fragment fragment = new ViewProjectDetails();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			}
			else
			{
				Fragment fragment = new UserProfileEdit();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();	
			}
		}
		else if(MainActivityfragment.pagevalue==14)
		{
				Fragment fragment = new JobSearch();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		}
		else
		{
			openAlert();

		}
		}
}
