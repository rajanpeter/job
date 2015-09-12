package com.Carmatec;




import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.Carmatec.GoogleAnalyticsApp.TrackerName;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity{
	static InputStream is = null;
	Button login,registration;
	static String pass="";
	static JSONObject jObj = null;
	static String json = "",appliedjob;
	static String success,project,personal_details,user_id,certifications,education,candidate_id,company,fresher;
	JSONObject jobj;
	CheckBox checkBox1;
	int shareprefval=0;
	View view;
	private ProgressDialog pDialog;
	EditText username,password; 
	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	String s="";
	static int loginvalue,mainvalue,regval; 
	RelativeLayout asas;
//	private Animation animation,animation1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		 this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		checkBox1=(CheckBox)findViewById(R.id.checkBox1);		
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.actionbar);

	view=	getActionBar().getCustomView();
	TextView actionname=(TextView)view.findViewById(R.id.textView1);
    actionname.setText("Login"); 

		try
		{
		Tracker t = ((GoogleAnalyticsApp) getApplication()).getTracker(TrackerName.APP_TRACKER);
		t.setScreenName("Login");
		t.send(new HitBuilders.AppViewBuilder().build());
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		username=(EditText)findViewById(R.id.username);
		password=(EditText)findViewById(R.id.password);
		s=ImageSliderCarma.urlvalue+"users/login_api.json?";
		 SharedPreferences  sharedPreferences=  getSharedPreferences("MyPref", MODE_PRIVATE); 
		 try
		 {
			 if(sharedPreferences.getString("username", "")=="")
			 {
				 
			 }
			 else
			 {
		 username.setText( sharedPreferences.getString("username", ""));
		  password.setText( sharedPreferences.getString("password", ""));
		  checkBox1.setChecked(true);
		  shareprefval=1;
			 }
		 }
		 catch(Exception e)
		 {
			 shareprefval=0; 
		 }
			checkBox1.setOnClickListener(new OnClickListener() {
				 
				  @Override
				  public void onClick(View v) {
			                //is chkIos checked?
					if (((CheckBox) v).isChecked()) {
						shareprefval=1;						
					}
					else
					{
						shareprefval=0;
					}
			 
				  }
				});
		 login=(Button)findViewById(R.id.login);
		    ActionBar actionBar = getActionBar();
		    actionBar.setTitle("Login");
			actionBar.setIcon(R.drawable.logo_2);
TextView textView6=(TextView)findViewById(R.id.textView6);
textView6.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub	
		startActivity(new Intent(getApplicationContext(),ForgotPass.class));
	}
});
		 registration=(Button)findViewById(R.id.registration);
		registration.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        
				registration.setBackgroundResource(R.drawable.whitehigh);
				regval=1;
			startActivity(new Intent(getApplicationContext(),HomePage.class));	
			finish();
			}
		});
		login.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				if(username.getText().toString().equals(""))
				{
					username.setError("Mandatory");
				}
				
				else if(password.getText().toString().equals(""))
				{
					password.setError("Mandatory");
				}
				else
				{
					login.setBackgroundResource(R.drawable.violetsmallhigh);
					try
					{
						nameValuePairs.clear();
					    nameValuePairs.add(new BasicNameValuePair("username", username.getText().toString()));
				        nameValuePairs.add(new BasicNameValuePair("password", password.getText().toString()));
				        if(ViewJobDetails.withoutlogin==1)
				        {
				        	   nameValuePairs.add(new BasicNameValuePair("job_id", "0"));
				        	   if(SearchJobResult.checking==1)
								{
								}
								else
								{
						        nameValuePairs.add(new BasicNameValuePair("job_id", ImageSliderCarma.job_id.get(FullJobSearch.flag)));
								}	
				        }
				        else
				        {
				        	   nameValuePairs.add(new BasicNameValuePair("job_id", "0"));	
				        }
//				        System.out.println(isNetworkConnected()+"?????????????????");
				       if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
				       }
						
						
						
					
				}
				catch(Exception eex)
				{
					login.setBackgroundResource(R.drawable.next_btn);
					Toast.makeText(getApplicationContext(), "Invalid username or password", 1).show();
				}
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
	 private class GetContacts extends AsyncTask<Void, Void, Void> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(Login.this);
				pDialog.setMessage("Please wait...");
				pDialog.setCancelable(false);
				pDialog.show();

			}  

			@Override
			protected Void doInBackground(Void... arg0) {				 
		        try{
		        	 jobj=JsonCall.postData(nameValuePairs, s);
		        }
		            catch (Exception e)
		            {
//		            	e.printStackTrace();
		            	asas.setVisibility(View.GONE);
		            	login.setBackgroundResource(R.drawable.next_btn);
		            } 
		          
		           
				return null;
			}
		@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				if (pDialog.isShowing())
					pDialog.dismiss();
				System.out.println(jobj+">>>>>>>>>>>>>>>");
		        if(ViewJobDetails.withoutlogin==1)
		        {
		        	try
					{
		        	JSONObject object = jobj.getJSONObject("result");
					 personal_details=object.getString("personal_details");
					 user_id=object.getString("user_id");
					 certifications=object.getString("certifications");
					 education=object.getString("education");
					 candidate_id=object.getString("candidate_id");
					 success=object.getString("success");
					 company=object.getString("company");
					  fresher=object.getString("fresher");
					  appliedjob=object.getString("applied_jobs");
					  project="false";
					
					if(success.equals("1"))
					{
						pass=password.getText().toString();
						 SharedPreferences  sharedPreferencess=  getSharedPreferences("MyPref", MODE_PRIVATE); 
							Editor editors = sharedPreferencess.edit();
					        editors.putString("value", "1");
							 editors.commit();
						if(shareprefval==1)
						{
						 SharedPreferences  sharedPreferences=  getSharedPreferences("MyPref", MODE_PRIVATE); 
							Editor editor = sharedPreferences.edit();
					        editor.putString("username",username.getText().toString() );
					        editor.putString("password",password.getText().toString() );
							 editor.commit();
						} 
						else
						{
							 SharedPreferences  sharedPreferences=  getSharedPreferences("MyPref", MODE_PRIVATE); 
    							Editor editor = sharedPreferences.edit();
    					        editor.putString("username","");
    					        editor.putString("password","");
    							 editor.commit();
						}
						mainvalue=1;
						loginvalue=1;
						Toast.makeText(getApplicationContext(), "Success", 1).show();
						finish();
					}
					else
					{
						login.setBackgroundResource(R.drawable.next_btn);
						Toast.makeText(getApplicationContext(), "Invalid Username or Password", 1).show();
					}
					}
		        	catch(Exception e)
		        	{
		        		e.printStackTrace();
		        		login.setBackgroundResource(R.drawable.next_btn);
		        	}
		        }
		        else
		        {  
					try
					{
						JSONObject object = jobj.getJSONObject("result");
						System.out.println(object+"Result");
						 personal_details=object.getString("personal_details");
						 user_id=object.getString("user_id");
						 certifications=object.getString("certifications");
						 education=object.getString("education");
						 candidate_id=object.getString("candidate_id");
						 success=object.getString("success");
						 company=object.getString("company");
						  fresher=object.getString("fresher");
						  appliedjob=object.getString("applied_jobs");
						  project="false";
						if(success.equals("1"))
						{
							pass=password.getText().toString();
							SharedPreferences  sharedPreferencesss=  getSharedPreferences("MyPref", MODE_PRIVATE); 
							Editor editorss = sharedPreferencesss.edit();
					        editorss.putString("value", "1");
							 editorss.commit();
							mainvalue=1;
							loginvalue=1;
							if(shareprefval==1)
    						{
    						 SharedPreferences  sharedPreferences=  getSharedPreferences("MyPref", MODE_PRIVATE); 
    							Editor editor = sharedPreferences.edit();
    					        editor.putString("username",username.getText().toString() );
    					        editor.putString("password",password.getText().toString() );
    							 editor.commit();
    						} 
    						else
    						{
    							 SharedPreferences  sharedPreferences=  getSharedPreferences("MyPref", MODE_PRIVATE); 
	    							Editor editor = sharedPreferences.edit();
	    					        editor.putString("username","");
	    					        editor.putString("password","");
	    							 editor.commit();
    						}
							Toast.makeText(getApplicationContext(), "Success", 1).show();
							startActivity(new Intent(getApplicationContext(),MainActivityfragment.class));
//							finish();
						}
						else
						{
							login.setBackgroundResource(R.drawable.next_btn);
							Toast.makeText(getApplicationContext(), "Invalid Username or Password", 1).show();
						}

			}
			catch(Exception ee)
			{
				ee.printStackTrace();
				login.setBackgroundResource(R.drawable.next_btn);
				Toast.makeText(getApplicationContext(), "Invalid username or password", 1).show();
			}
		}
			}
		}
		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
//			GoogleAnalytics.getInstance(this).reportActivityStart(this);
			GoogleAnalytics.getInstance(Login.this).reportActivityStart(this);
		}


		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
//			GoogleAnalytics.getInstance(this).reportActivityStop(this);
			GoogleAnalytics.getInstance(Login.this).reportActivityStop(this);
		}
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			login.setBackgroundResource(R.drawable.next_btn);
			registration.setBackgroundResource(R.drawable.upload_image_btn);
		}
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
//			super.onBackPressed();
			startActivity(new Intent(getApplicationContext(),HomePage.class));
		}
}
