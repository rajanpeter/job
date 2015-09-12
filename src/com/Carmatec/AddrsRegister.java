package com.Carmatec;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.message.BasicNameValuePair;
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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddrsRegister extends Activity
{
	String selectedImagePath=""; 
	File file;
	Button skipaddress,nextaddrs;
	JSONObject jobj;
	private static final int MY_INTENT_CLICK=302;
	EditText addess1,addess2,city,zip,email1,email2,contact1,contact2;
	Button resumebtn;
	static String s2=""; 
	String s;
	private ProgressDialog pDialog;   
	static String curFileName;
	View view;
@Override 
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.addrsregister);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.actionbar);
		view=	getActionBar().getCustomView();
	    TextView actionname=(TextView)view.findViewById(R.id.textView1);
	    actionname.setText("Edit Profile");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		try 
		{
		Tracker t = ((GoogleAnalyticsApp) getApplication()).getTracker(TrackerName.APP_TRACKER);
		t.setScreenName("Profile Edit");
		t.send(new HitBuilders.AppViewBuilder().build());
		}
		catch(Exception e) 
		{
		}
		nextaddrs= (Button)findViewById(R.id.nextaddrs);
		if(!(Login.fresher.equals("false")))
		{
			nextaddrs.setText("Submit");
		}
		addess1=(EditText)findViewById(R.id.addess1); 
		addess2=(EditText)findViewById(R.id.addess2);  
		city=(EditText)findViewById(R.id.city);   
		zip=(EditText)findViewById(R.id.zip); 
		email1=(EditText)findViewById(R.id.email1);
		email2=(EditText)findViewById(R.id.email2);
		contact1=(EditText)findViewById(R.id.contact1);
		contact2=(EditText)findViewById(R.id.contact2);
		if(UserProfileEdit.address_line1.equals("null"))
		{
			addess1.setText("");
		}
		else
		{
			addess1.setText(UserProfileEdit.address_line1);
		}
		if(UserProfileEdit.address_line2.equals("null"))
		{
			addess2.setText("");
		}
		else
		{
			addess2.setText(UserProfileEdit.address_line2);
		}
		if(UserProfileEdit.city.equals("null"))
		{
			city.setText("");
		}
		else
		{
			city.setText(UserProfileEdit.city);
		}
		if(UserProfileEdit.zip.equals("null"))
		{
			zip.setText("");
		}
		else
		{
			zip.setText(UserProfileEdit.zip);
		}
		if(UserProfileEdit.email.equals("null"))
		{
			email1.setText(""); 
		}
		else
		{
			email1.setText(UserProfileEdit.email);
		}
		if(UserProfileEdit.alt_email.equals("null"))
		{
			email2.setText("");
		}
		else
		{
			email2.setText(UserProfileEdit.alt_email);
		}
		if(UserProfileEdit.contact.equals("null"))
		{
			contact1.setText("");
		}
		else
		{
			contact1.setText(UserProfileEdit.contact);
		}
		if(UserProfileEdit.alt_contact.equals("null"))
		{
			contact2.setText("");
		}
		else
		{
			contact2.setText(UserProfileEdit.alt_contact);
		}
		 skipaddress=(Button)findViewById(R.id.skipaddress);
		resumebtn=(Button)findViewById(R.id.resume);
		resumebtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  Intent intent = new Intent();
				    intent.setType("*/*");
				    intent.setAction(Intent.ACTION_GET_CONTENT);
				    startActivityForResult(Intent.createChooser(intent, "Select File"),MY_INTENT_CLICK);
			}
		});
		skipaddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				skipaddress.setBackgroundResource(R.drawable.whitehigh);
				finish();
			}
		});
		
		nextaddrs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) { 
				// TODO Auto-generated method stub
				
				if(addess1.getText().toString().equals(""))
				{
					addess1.setError("Mandatory");
				}
				else if(addess2.getText().toString().equals(""))
				{
					addess2.setError("Mandatory");
				}
				else if(city.getText().toString().equals(""))
				{
					city.setError("Mandatory");
				}
				else if(!city.getText().toString().matches("[a-z A-Z]+"))
				{
					city.setError("Invalid Character");
				}
				else if(zip.getText().toString().equals(""))
				{
					zip.setError("Mandatory");
				}
				else if(!(zip.getText().toString().length()==6)||(!zip.getText().toString().matches("[0-9]+")))
				{
					zip.setError("Invalid zip code");
				}
				else if(email1.getText().toString().equals("")||(!isValidEmail(email1.getText().toString())))
				{
					email1.setError("Mandatory");
				}
				else if(contact1.getText().toString().equals("")|| contact1.getText().toString().length() < 10)
				{
					contact1.setError("Mandatory");
					
				}
				else
				{
					nextaddrs.setBackgroundResource(R.drawable.violetsmallhigh);
			        try {
			        	if(!contact2.getText().equals(""))
			        	{
			        		Register.nameValuePairs.add(new BasicNameValuePair("alt_phone_no",contact2.getText().toString()));	
			        	}
			        	if(!email2.getText().equals(""))
			        	{
			        		Register.nameValuePairs.add(new BasicNameValuePair("alt_email_id", email2.getText().toString()));
			        	}
						if(!(s2.equals("")))
						{
							if (selectedImagePath.contains(".")) { 

								 String foo = new String(selectedImagePath);
								 String[] data = foo.split("\\.");
								Register.nameValuePairs.add(new BasicNameValuePair("resume_name", Register.registerdatas.get(0)+"."+data[1]));
								Register.nameValuePairs.add(new BasicNameValuePair("base_64_resume",s2));
								
							} else {
							    throw new IllegalArgumentException("String " + selectedImagePath + " does not contain .");
							}
							
						}
						else
						{
//							Toast.makeText(getApplicationContext(), "Base 64 null : "+s2,1).show();
						}
			        	Register.nameValuePairs.add(new BasicNameValuePair("address_line1", addess1.getText().toString()));
			        	Register.nameValuePairs.add(new BasicNameValuePair("address_line2",addess2.getText().toString()) );
			        	Register.nameValuePairs.add(new BasicNameValuePair("city", city.getText().toString()));
			        	Register.nameValuePairs.add(new BasicNameValuePair("zip", zip.getText().toString()));
			        	Register.nameValuePairs.add(new BasicNameValuePair("user_id", Login.user_id));
			        	Register.nameValuePairs.add(new BasicNameValuePair("email_id", email1.getText().toString()));
			        	Register.nameValuePairs.add(new BasicNameValuePair("phone_no", contact1.getText().toString()));
	
						Register.registerdatas.add(addess1.getText().toString());
						Register.registerdatas.add(addess2.getText().toString());
						Register.registerdatas.add(city.getText().toString());
						Register.registerdatas.add(zip.getText().toString());
						Register.registerdatas.add(email1.getText().toString());
						Register.registerdatas.add(email2.getText().toString());
						Register.registerdatas.add(contact1.getText().toString());
						Register.registerdatas.add(contact2.getText().toString());
					} catch (Exception e) {	
						e.printStackTrace();
					}
			        if(Login.fresher.equals("true"))
					{
						try {
				        	 new GetContacts().execute();
							} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						} 
			        else if(Login.fresher.equals("intern"))
			        {
			        	try {
			        		
			        		 if( isNetworkConnected()==true)
						       {
						    	  
						    	   new GetContacts().execute();
						       }
						       else
						       {
						    	   Toast.makeText(getApplicationContext(), "Please check your internet connection", 1).show();
						       }
							} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
						else if(Login.fresher.equals("false"))
						{
							startActivity(new Intent(getApplicationContext(),ResumeRegister.class));
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
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		try
		{
	 if (resultCode == RESULT_OK)
	 {
	  if (requestCode == MY_INTENT_CLICK)
	  {
	   if (null == data) return;
	
	   Uri selectedImageUri = data.getData();
	   //MEDIA GALLERY
	   try
	   {
	   selectedImagePath = ImageFilePath.getPath(getApplicationContext(), selectedImageUri);
	   }
	   catch(Exception e)
	   {
//			 Toast.makeText(getApplicationContext(), "exception selectedImagePath. "+e, 1).show(); 
	   }
	   
//	   Toast.makeText(getApplicationContext(), "selectedImagePath "+selectedImagePath, 1).show();

	   byte[] b = null;
	   try
	   {
	    file = new File(selectedImagePath);
//	    Toast.makeText(getApplicationContext(), "file "+file, 1).show();
	   b = new byte[(int) file.length()];
//	   Toast.makeText(getApplicationContext(), "byte array "+b, 1).show();
	   }
	   catch(Exception e)
	   {
//		   Toast.makeText(getApplicationContext(), "exception file aray"+e, 1).show();
	   }
	   try {
	         @SuppressWarnings("resource")
			FileInputStream fileInputStream = new FileInputStream(file);
	         fileInputStream.read(b);
	         String encoded = Base64.encodeToString(b,Base64.DEFAULT);
	         s2=encoded;
//	         Toast.makeText(getApplicationContext(), "BASE 64", 1).show();
//	         Toast.makeText(getApplicationContext(), "encoded"+s2, 1).show();
	     	
	    } catch (FileNotFoundException e) {
	   	 Toast.makeText(getApplicationContext(), "File Not Found. ", 1).show();
	                e.printStackTrace();
	                s2="";
	    }
	   catch(Exception er)
	   {
	   	Toast.makeText(getApplicationContext(), "File Not Found. ", 1).show();
	   	s2="";
	   }
	  }
	 }
	 else
	 {
		 Toast.makeText(getApplicationContext(), "File Not Found. ", 1).show();
		   s2="";
	 }
		}
		catch(Exception e)
		{
		 	Toast.makeText(getApplicationContext(), "Invalid formate", 1).show();
		    s2="";
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		AddrsRegister.s2="";
		Register.registerdatas.clear();
		Register.nameValuePairs.clear();
		finish();
		}
	private class GetContacts extends AsyncTask<Void, Void, Void> {
	
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(AddrsRegister.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
	
		}  
	
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
	        try{
	        	 jobj=JsonCall.postData(Register.nameValuePairs, ImageSliderCarma.urlvalue+"users/candidate_edit_api.json?");
	        	
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
				if(jobj.get("candidatedetails").equals("Saved."))
				{ 
					s2="";
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
				}
			}
			catch(Exception e)
			{
				Toast.makeText(getApplicationContext(), "Error", 1).show();
			}
		}
	
	}
	private void internetconnection() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddrsRegister.this);
	    alertDialogBuilder.setTitle("Internet Problem");
	    alertDialogBuilder.setMessage("Please check WiFi or Mobile data connection");
	    alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog,int id) {
	        	   
	        	 
	        	   dialog.dismiss();
	        	   Intent intent = new Intent(Intent.ACTION_MAIN); 
		   	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		   	        intent.addCategory(Intent.CATEGORY_HOME); 
		   	        startActivity(intent);
		   	        finish();
	           }
	         });


	    AlertDialog alertDialog = alertDialogBuilder.create();
	    alertDialog.show(); 
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		GoogleAnalytics.getInstance(this).reportActivityStart(this);
		try
		{
		GoogleAnalytics.getInstance(AddrsRegister.this).reportActivityStart(this);
		}
		catch(Exception e)
		{
			internetconnection();
		}
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		try
		{
//		GoogleAnalytics.getInstance(this).reportActivityStop(this);
		GoogleAnalytics.getInstance(AddrsRegister.this).reportActivityStop(this);
	}
	catch(Exception e)
	{
		internetconnection();
	}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		skipaddress.setBackgroundResource(R.drawable.upload_image_btn);
		nextaddrs.setBackgroundResource(R.drawable.next_btn);
	}
	
}
