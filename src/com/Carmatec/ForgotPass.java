package com.Carmatec;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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

public class ForgotPass extends Activity
{
	EditText mailid;
	private ProgressDialog pDialog;
	JSONObject	 jobj; 
	Button submit;
	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	View view;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.forgotpassword); 
	 this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	 getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.actionbar);

	view=	getActionBar().getCustomView();
    TextView actionname=(TextView)view.findViewById(R.id.textView1);
    actionname.setText("Forgot Password");
	 submit=(Button)findViewById(R.id.submit);
	mailid=(EditText)findViewById(R.id.mailid);
	submit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
	submit.setBackgroundResource(R.drawable.violetlonghigh);
			// TODO Auto-generated method stub
			if(mailid.getText().toString().equals(""))
			{
				mailid.setError("Mandatory");
			}
			else if(!isValidEmail(mailid.getText().toString()))
			{
				mailid.setError("Invalid email id");
			}
			else
			{
				nameValuePairs.clear();
			    nameValuePairs.add(new BasicNameValuePair("email", mailid.getText().toString()));
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
	});
}
private boolean isValidEmail(String email) {
	String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	Matcher matcher = pattern.matcher(email);
	return matcher.matches();
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
		pDialog = new ProgressDialog(ForgotPass.this);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		pDialog.show();

	}  

	@Override
	protected Void doInBackground(Void... arg0) {				 
        try{
        	 jobj=JsonCall.postData(nameValuePairs, ImageSliderCarma.urlvalue+"users/forgotpassword_api.json?");
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
		JSONObject oob=null; 
    try {
		 oob=jobj.getJSONObject("result");
		 if(oob.getString("success").equals("1"))
		 {
			 Toast.makeText(getApplicationContext(), "Mail has been send. Please Check your mail", 1).show();
			 finish();
		 }
		 else
		 {
			 Toast.makeText(getApplicationContext(), "Email is not matching", 1).show();
		 }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		 Toast.makeText(getApplicationContext(), "Check your internet connection", 1).show();
	}
	}
}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	submit.setBackgroundResource(R.drawable.button1);
}
}
