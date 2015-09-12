package com.Carmatec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.Carmatec.GoogleAnalyticsApp.TrackerName;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders; 
import com.google.android.gms.analytics.Tracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistration extends Activity
{
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	EditText username,email,mobile,experiece,lastname;
	Button loginbutton,registration;
	private ProgressDialog pDialog;
	static InputStream is = null;
	static JSONObject jObj = null;
	JSONObject jobj;
	static String json = "";
	View view;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.userregister);
	 this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	 getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.actionbar);

	view=	getActionBar().getCustomView();
    TextView actionname=(TextView)view.findViewById(R.id.textView1);
    actionname.setText("Register");
//    actionname.setGravity(Gravity.CENTER);
    if( isNetworkConnected()==true)
    {
 	  
    	try
    	{
    	Tracker t = ((GoogleAnalyticsApp) getApplication()).getTracker(TrackerName.APP_TRACKER);
    	t.setScreenName("Register");
    	t.send(new HitBuilders.AppViewBuilder().build());
//    	ActionBar bar = getActionBar();
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
	
	lastname=(EditText)findViewById(R.id.lastname);
	username=(EditText)findViewById(R.id.uname);
	email=(EditText)findViewById(R.id.email);
	mobile=(EditText)findViewById(R.id.mobile);
	experiece=(EditText)findViewById(R.id.experiece);
	loginbutton=(Button)findViewById(R.id.loginbutton);
	registration=(Button)findViewById(R.id.registration);


    if(HomePage.expfresh==1||HomePage.expfresh==3)
	{
    	experiece.setVisibility(View.GONE);
	}
	else if(HomePage.expfresh==2)
	{
		experiece.setVisibility(View.VISIBLE);
	}
	else
	{
		
	}
	loginbutton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			loginbutton.setBackgroundResource(R.drawable.whitehigh);
			startActivity(new Intent(getApplicationContext(),Login.class));
			finish();
		}
	});
	registration.setOnClickListener(new OnClickListener() { 
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub 
			
			if(username.getText().toString().equals(""))
			{
				username.setError("Mandatory"); 
			}
			else if(!username.getText().toString().matches("[a-z A-Z]+"))
			{
				username.setError("Invalid Character");
			}
			else if(lastname.getText().toString().equals(""))
			{
				lastname.setError("Mandatory");
			}
			else if(!lastname.getText().toString().matches("[a-z A-Z]+"))
			{
				lastname.setError("Invalid Character");
			}
			else if(email.getText().toString().equals(""))
			{
				email.setError("Mandatory");
			}
			else if(!isValidEmail(email.getText().toString()))
			{
				email.setError("Invalid email id");
			}
			else if(mobile.getText().toString().equals(""))
			{
				mobile.setError("Mandatory");
			}
			else if(mobile.getText().toString().length() < 10||mobile.getText().toString().length() > 10)
			{
				mobile.setError("10 digit mobile number");
			}
			
			else
			{
				registration.setBackgroundResource(R.drawable.violetlonghigh);
				nameValuePairs.clear();
		        nameValuePairs.add(new BasicNameValuePair("email", email.getText().toString()));
		        nameValuePairs.add(new BasicNameValuePair("first_name", username.getText().toString()));
		        nameValuePairs.add(new BasicNameValuePair("last_name", lastname.getText().toString()));
		       if(HomePage.expfresh==1)
				{
		    	   nameValuePairs.add(new BasicNameValuePair("role", "N"));
		    	   if( isNetworkConnected()==true)
			       {
			    	  
			    	   new GetContacts().execute();
			       }
			       else
			       {
			    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
			       }
		    	  
				}
				else if(HomePage.expfresh==2)
				{
					 if(experiece.getText().toString().equals(""))
						{
							experiece.setError("Mandatory");
						}
					 else 
					 {
						 nameValuePairs.add(new BasicNameValuePair("role", "Y"));
					        nameValuePairs.add(new BasicNameValuePair("experience", experiece.getText().toString()));
					       
					 }
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
					 nameValuePairs.add(new BasicNameValuePair("role", "I"));
					 if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
				       }
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
private boolean isValidEmail(String email) {
	String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	Matcher matcher = pattern.matcher(email);
	return matcher.matches();
}
public JSONObject postData() { 
    // Create a new HttpClient and Post Header
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(ImageSliderCarma.urlvalue+"users/register_user_api.json?");

    try {
        // Add your data


        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpResponse response = httpclient.execute(httppost);
		HttpEntity httpEntity = response.getEntity();
		is = httpEntity.getContent();	
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				} 
				is.close();
				json = sb.toString(); 
			} catch (Exception e) {
				Log.e("Buffer Error", "Error converting result " + e.toString());
			}

			// try parse the string to a JSON object
			try {
				jObj = new JSONObject(json);
				
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

			// return JSON String
			
    } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
    	e.printStackTrace();
    }
    return jObj;
} 

private class GetContacts extends AsyncTask<Void, Void, Void> {

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// Showing progress dialog
		pDialog = new ProgressDialog(UserRegistration.this);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		pDialog.show();
		
	}  

	@Override
	protected Void doInBackground(Void... arg0) {
		// Creating service handler class instance
        try{
        	jobj=null;
        	
        	 jobj=postData();
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

		  if(HomePage.expfresh==1)
			{
	  		        try {
					String returnval=jobj.getString("candidatedetails");
					System.out.println(returnval+"????????????????????????");
					if(returnval.equals("You are already registerd. Please login"))
					{
						
						    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserRegistration.this);
						     
						    alertDialogBuilder.setTitle("Already Registered"); 
						    alertDialogBuilder.setMessage("You are already registerd. Please login");
						    alertDialogBuilder.setPositiveButton("Login",new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog,int id) {
						        	   dialog.dismiss();
						        	   startActivity(new Intent(getApplicationContext(),Login.class));
						        	  
						        	   finish();
						        	 
						           }
						         });
						  

						    AlertDialog alertDialog = alertDialogBuilder.create();
						    alertDialog.show();
						
						
					}
					else
					{
					    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserRegistration.this);
					     
					    alertDialogBuilder.setTitle("Success"); 
					    alertDialogBuilder.setMessage("Your account has been registered. Please check your inbox for login details to proceed.");
					    alertDialogBuilder.setPositiveButton("Login",new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog,int id) {
//					        	   Toast.makeText(getApplicationContext(), "Registration success please check your mail for login credentials", 1).show();
									 SharedPreferences  sharedPreferences=  getSharedPreferences("MyPref", MODE_PRIVATE); 
									Editor editor = sharedPreferences.edit();
							        editor.putString("value", "1");
									 editor.commit();
									  dialog.dismiss();
									startActivity(new Intent(getApplicationContext(),Login.class));
									finish();
									
					        	 
					           }
					         });
					  

					    AlertDialog alertDialog = alertDialogBuilder.create();
					    alertDialog.show();
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Check your internet connection.", 1).show();
					registration.setBackgroundResource(R.drawable.button1);
				}
			}
			else if(HomePage.expfresh==2)
			{
			        try {
						String returnval=jobj.getString("candidatedetails");
						System.out.println(returnval+"?????????????");
						if(returnval.equals("You are already registerd. Please login"))
						{
							  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserRegistration.this);
							     
							    alertDialogBuilder.setTitle("Already Registered"); 
							    alertDialogBuilder.setMessage("You are already registerd. Please login");
							    alertDialogBuilder.setPositiveButton("Login",new DialogInterface.OnClickListener() {
							           public void onClick(DialogInterface dialog,int id) {
							        	   dialog.dismiss();
							        	   startActivity(new Intent(getApplicationContext(),Login.class));
							        	  
							        	   finish();
							        	 
							           }
							         });
							  

							    AlertDialog alertDialog = alertDialogBuilder.create();
							    alertDialog.show();
							
						}
						else
						{
							 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserRegistration.this);
						     
							    alertDialogBuilder.setTitle("Success"); 
							    alertDialogBuilder.setMessage("Your account has been registered. Please check your inbox for login details to proceed.");
							    alertDialogBuilder.setPositiveButton("Login",new DialogInterface.OnClickListener() {
							           public void onClick(DialogInterface dialog,int id) {
//							        	   Toast.makeText(getApplicationContext(), "Registration success please check your mail for login credentials", 1).show();
											 SharedPreferences  sharedPreferences=  getSharedPreferences("MyPref", MODE_PRIVATE); 
											Editor editor = sharedPreferences.edit();
									        editor.putString("value", "1");
											 editor.commit();
											  dialog.dismiss();
											startActivity(new Intent(getApplicationContext(),Login.class));
											finish();
											
							        	 
							           }
							         });
							  

							    AlertDialog alertDialog = alertDialogBuilder.create();
							    alertDialog.show();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), "Check your internet connection.", 1).show();
						registration.setBackgroundResource(R.drawable.button1);
					}
			}
			else
			{

  		        try {
				String returnval=jobj.getString("candidatedetails");
				if(returnval.equals("You are already registerd. Please login"))
				{
					Toast.makeText(getApplicationContext(), "You are already registerd. Please login", 1).show();
					startActivity(new Intent(getApplicationContext(),Login.class));
					finish();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Registration success please check your mail for login credentials", 1).show();
					 SharedPreferences  sharedPreferences=  getSharedPreferences("MyPref", MODE_PRIVATE); 
					Editor editor = sharedPreferences.edit();
			        editor.putString("value", "1");
					 editor.commit();
					startActivity(new Intent(getApplicationContext(),Login.class));
					finish();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "Check your internet connection.", 1).show();
				registration.setBackgroundResource(R.drawable.button1);
			}
		
			}
	}

}
@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
//	GoogleAnalytics.getInstance(this).reportActivityStart(this);
	GoogleAnalytics.getInstance(UserRegistration.this).reportActivityStart(this);
}


@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
//	GoogleAnalytics.getInstance(this).reportActivityStop(this);
	GoogleAnalytics.getInstance(UserRegistration.this).reportActivityStop(this);
}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	registration.setBackgroundResource(R.drawable.button1);
	loginbutton.setBackgroundResource(R.drawable.upload_image_btn);
	
}

}
