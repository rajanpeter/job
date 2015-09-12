package com.Carmatec; 

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewJobDetails extends Fragment{
	 public ViewJobDetails() {
         // Empty constructor required for fragment subclasses
     }
	static int withoutlogin=0;
	 private ProgressDialog pDialog;
	 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	Button applyjob,share; 
	JSONObject jobj;
	String Shareapp="";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.viewjobdetails, container, false);
		MainActivityfragment.actionname.setText("Job Details");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		 this.setRetainInstance(true);
		applyjob=(Button)v.findViewById(R.id.applyjob); 
		share=(Button)v.findViewById(R.id.share); 
		TextView jobtitle=(TextView)v.findViewById(R.id.jobtitle); 
		TextView skills=(TextView)v.findViewById(R.id.skills); 
		TextView experience=(TextView)v.findViewById(R.id.experience); 
		TextView jobdes=(TextView)v.findViewById(R.id.jobdes); 
		jobtitle=(TextView)v.findViewById(R.id.jobtitle);
		jobdes=(TextView)v.findViewById(R.id.jobdes);
	TextView	startdate=(TextView)v.findViewById(R.id.startdate);
	TextView	enddate=(TextView)v.findViewById(R.id.enddate);
		skills=(TextView)v.findViewById(R.id.skills);
		
		if(SearchJobResult.checking==1)
		{
			MainActivityfragment.pagevalue=11;
			jobtitle.setText(JobSearch.job_title.get(SearchJobResult.searchvalue));
			jobdes.setText(JobSearch.job_description.get(SearchJobResult.searchvalue));
			startdate.setText(JobSearch.job_startdate.get(SearchJobResult.searchvalue));
			enddate.setText(JobSearch.job_end_date.get(SearchJobResult.searchvalue));
			skills.setText(JobSearch.skill.get(SearchJobResult.searchvalue));
			experience.setText(JobSearch.experience.get(SearchJobResult.searchvalue));
			
			Shareapp="\nJob Title \n\n"+JobSearch.job_title.get(SearchJobResult.searchvalue)+"\n\nSkill Set Required : "+JobSearch.skill.get(SearchJobResult.searchvalue)+"\n\n"+"Experience : "+JobSearch.experience.get(SearchJobResult.searchvalue)+"\n\nJob Posted on : "+JobSearch.job_startdate.get(SearchJobResult.searchvalue)+"\n\n"+"Listing Ends on : "+JobSearch.job_end_date.get(SearchJobResult.searchvalue)+"\n\nJob Description : \n"+JobSearch.job_description.get(SearchJobResult.searchvalue)+"\n\n";
		}
		else
		{
			MainActivityfragment.pagevalue=13;
		jobtitle.setText(ImageSliderCarma.job_title.get(FullJobSearch.flag));
		jobdes.setText(ImageSliderCarma.job_description.get(FullJobSearch.flag));
		startdate.setText(ImageSliderCarma.job_startdate.get(FullJobSearch.flag));
		enddate.setText(ImageSliderCarma.job_end_date.get(FullJobSearch.flag));
		skills.setText(ImageSliderCarma.skill.get(FullJobSearch.flag));
		experience.setText(ImageSliderCarma.experience.get(FullJobSearch.flag));
		Shareapp="\nJob Title \n\n"+ImageSliderCarma.job_title.get(FullJobSearch.flag)+"\n\nSkill Set Required : "+ImageSliderCarma.skill.get(FullJobSearch.flag)+"\n\n"+"Experience : "+ImageSliderCarma.experience.get(FullJobSearch.flag)+"\n\nJob Posted on : "+ImageSliderCarma.job_startdate.get(FullJobSearch.flag)+"\n\n"+"Listing Ends on : "+ImageSliderCarma.job_end_date.get(FullJobSearch.flag)+"\n\nJob Description : \n"+ImageSliderCarma.job_description.get(FullJobSearch.flag)+"\n\n";
		}
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
				animation.setDuration(100); // duration - half a second
				animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
				animation.setRepeatCount(0); // Repeat animation infinitely
//				animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in    
				share.startAnimation(animation);
				// TODO Auto-generated method stub
				 Intent share = new Intent(android.content.Intent.ACTION_SEND);
			        share.setType("text/plain");
			        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			 
			        // Add data to the intent, the receiving app will decide
			        // what to do with it.
			        share.putExtra(Intent.EXTRA_SUBJECT, "CARMATEC IT SOLUTIONS PVT LTD \n");
			        share.putExtra(Intent.EXTRA_TEXT, Shareapp+"\n\n Apply Job here  : http://careers.carmatec.com/ \n\nDownload Carmatec_Job_Portal Mobile APP from here.. www.carmatec.com");
			 
			        startActivity(Intent.createChooser(share, "Share link!"));
			}
		});
		applyjob.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
				animation.setDuration(100); // duration - half a second
				animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
				animation.setRepeatCount(0); // Repeat animation infinitely
//				animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in    
				applyjob.startAnimation(animation);
				if(Login.success.equals("1"))
				{
				nameValuePairs.clear();
				try
				{
					if(SearchJobResult.checking==1)
					{
			    		SearchJobResult.checking=0;
			    		   nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
			   	        nameValuePairs.add(new BasicNameValuePair("job_id", JobSearch.job_id.get(SearchJobResult.searchvalue)));
			   	     if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
				       }
					}
					else
					{
			        nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
			        nameValuePairs.add(new BasicNameValuePair("job_id", ImageSliderCarma.job_id.get(FullJobSearch.flag)));
			        if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
				       }
					}	
						
					}catch(Exception ee2)
					{
						
					}
				}
				else
				{
				 	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
				    alertDialogBuilder.setTitle("Login First");
				    alertDialogBuilder.setMessage("Please login first");
				    // set positive button: Yes message
				    alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog,int id) {
				               // go to a new activity of the app
				        	   withoutlogin=1;
				        	   dialog.dismiss();
				        	startActivity(new Intent(getActivity(),Login.class));
					   	      
				           }
				         });
				    alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog,int id) {
				               // go to a new activity of the app
				        	   dialog.dismiss();
				           }
				         });

				    AlertDialog alertDialog = alertDialogBuilder.create();
				    // show alert
				    alertDialog.show();
				}
				
			}
		});

		return v;
	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}  

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			  try{
		        	 jobj=JsonCall.postData(nameValuePairs, ImageSliderCarma.urlvalue+"users/login_job_apply_api.json?");
		        }
		            catch (Exception e)
		            {
		            	e.printStackTrace();
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
				JSONObject obj=jobj.getJSONObject("result");
	        	String s=obj.getString("success");
	        	//
	     	    if(s.equals("1"))
	     	    {
					Toast.makeText(getActivity(), "Successfully Applied", 1).show();
					Login.appliedjob="true";
	     	    }
	     	    else
	     	    {
					Toast.makeText(getActivity(), "Already Applied", 1).show();	
	     	    }
				
				
			}
			catch(Exception e)
			{
				Toast.makeText(getActivity(), "Failed Submit", 1).show();
			}
		}

	}
	private boolean isNetworkConnected() {
		  ConnectivityManager cm = (ConnectivityManager) getActivity()
				  .getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo ni = cm.getActiveNetworkInfo();
		  if (ni == null) {
		   // There are no active networks.
		   return false;
		  } else
		   return true;
		 }
}
