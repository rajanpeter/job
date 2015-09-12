package com.Carmatec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import com.Carmatec.FullJobSearch.JobAdapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Change_Password extends Fragment
{
	 public Change_Password() {
         // Empty constructor required for fragment subclasses
     }
	private ProgressDialog pDialog;
	
	JSONObject jsonParam8 = new JSONObject();
	JSONObject jsonParam10= new JSONObject();

String url="";
   View v;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	Bundle savedInstanceState) {
	MainActivityfragment.pagevalue=5;
	v = inflater.inflate(R.layout.jobsearch, container, false);
	MainActivityfragment.actionname.setText("Change Password");
	MainActivityfragment.actionname.setGravity(Gravity.CENTER);
	 this. setRetainInstance(true);
	 url=ImageSliderCarma.urlvalue+"users/change_password_api.json?";
	//////////////////////


				
				
				System.out.println(url);
				System.out.println(jsonParam10);
				
		          if( isNetworkConnected()==true)
			       {
			    	  
			    	   new GetContacts().execute();
			       } 
			       else
			       {
			    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
			       }
		
	
	return v;
}
	private String calljson() { 
	// TODO Auto-generated method stub

	InputStream inputStream = null;
    String result = "";
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    try {
    	try {
			jsonParam8.put("id",Login.user_id);
			jsonParam8.put("password","qazplm123");
			jsonParam10.put("User",jsonParam8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	System.out.println("user id"+Login.user_id);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        String json1 ="";
        json1 = jsonParam10.toString();
        StringEntity se = new StringEntity(json1);
        httpPost.setEntity(se);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse httpResponse = httpclient.execute(httpPost);
        inputStream = httpResponse.getEntity().getContent();
        if(inputStream != null)
        {
            result = convertInputStreamToString(inputStream);
        }
        else
        {
            result = "Did not work!";
        }
      
    	} catch (Exception e) {
    	e.printStackTrace();
    	}
		return result;
		}
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
	    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"), 8192);
	    String line = "";
	    String result = "";
	    while((line = bufferedReader.readLine()) != null)
	        result += line;
	
	    inputStream.close();
	    return result;
	
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
			 String valreturn = calljson();
			 System.out.println(valreturn+">>>>>>>>>>>>>>>.");
	        }
	        catch(Exception e)
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
			
			
			}
	}
	}
