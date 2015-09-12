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

public class JobSearch extends Fragment
{
	 public JobSearch() {
         // Empty constructor required for fragment subclasses
     }
	private ProgressDialog pDialog;
	JobAdapter myadapter;
	String skillvalues=""; 
	String valreturn=""; 
	int i=0;
	JSONObject jsonParam8 = new JSONObject();
	JSONObject jsonParam9 = new JSONObject();
	JSONObject jsonParam10= new JSONObject();
	static	   ArrayList <String> job_id= new ArrayList<String>();
	static	   ArrayList <String> job_title= new ArrayList<String>();
	static	   ArrayList <String> job_description= new ArrayList<String>();
	static	   ArrayList <String> job_startdate= new ArrayList<String>();
	static	   ArrayList <String> job_end_date= new ArrayList<String>();
	static	   ArrayList <String> job_status= new ArrayList<String>();
	static	   ArrayList <String> skill= new ArrayList<String>();
	static	   ArrayList <String> experience= new ArrayList<String>();
	static	   ArrayList <String> company= new ArrayList<String>();
	static int flag;
    String name;
String url="";
    ListView joblist;
    EditText skills; 
	Button search,button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,clear;
	View v;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	Bundle savedInstanceState) {
	MainActivityfragment.pagevalue=5;
	v = inflater.inflate(R.layout.jobsearch, container, false);
	MainActivityfragment.actionname.setText("Job Search");
	MainActivityfragment.actionname.setGravity(Gravity.CENTER);
	 this. setRetainInstance(true);
	 url=ImageSliderCarma.urlvalue+"users/jobs_by_skills_api.json?";
	skills=(EditText)v.findViewById(R.id.skills);
	search=(Button)v.findViewById(R.id.search);
	button1=(Button)v.findViewById(R.id.button1);
	button2=(Button)v.findViewById(R.id.button2);
	button3=(Button)v.findViewById(R.id.button3);
	button4=(Button)v.findViewById(R.id.button4);
	button5=(Button)v.findViewById(R.id.button5);
	button6=(Button)v.findViewById(R.id.button6);
	button7=(Button)v.findViewById(R.id.button7);
	button8=(Button)v.findViewById(R.id.button8);
	button9=(Button)v.findViewById(R.id.button9);
	button10=(Button)v.findViewById(R.id.button10);
	button11=(Button)v.findViewById(R.id.button11);
	button12=(Button)v.findViewById(R.id.button12);
	
	try{button1.setText(MainActivityfragment.Jobtitle.get(0));}catch(Exception e){button1.setVisibility(v.GONE); }
	try{button2.setText(MainActivityfragment.Jobtitle.get(1));}catch(Exception e){button2.setVisibility(v.GONE); }
	try{button3.setText(MainActivityfragment.Jobtitle.get(2));}catch(Exception e){button3.setVisibility(v.GONE); }
	try{button4.setText(MainActivityfragment.Jobtitle.get(3));}catch(Exception e){button4.setVisibility(v.GONE); }
	try{button5.setText(MainActivityfragment.Jobtitle.get(4));}catch(Exception e){button5.setVisibility(v.GONE); }
	try{button6.setText(MainActivityfragment.Jobtitle.get(5));}catch(Exception e){button6.setVisibility(v.GONE); }
	try{button7.setText(MainActivityfragment.Jobtitle.get(6));}catch(Exception e){button7.setVisibility(v.GONE); }
	try{button8.setText(MainActivityfragment.Jobtitle.get(7));}catch(Exception e){button8.setVisibility(v.GONE); }
	try{button9.setText(MainActivityfragment.Jobtitle.get(8));}catch(Exception e){button9.setVisibility(v.GONE); }
	try{button10.setText(MainActivityfragment.Jobtitle.get(9));}catch(Exception e){button10.setVisibility(v.GONE); }
	try{button11.setText(MainActivityfragment.Jobtitle.get(10));}catch(Exception e){button11.setVisibility(v.GONE); }
	try{button12.setText(MainActivityfragment.Jobtitle.get(11));}catch(Exception e){button12.setVisibility(v.GONE); }
	try
	{
	button2.setText(MainActivityfragment.Jobtitle.get(1));
	button3.setText(MainActivityfragment.Jobtitle.get(2));
	button4.setText(MainActivityfragment.Jobtitle.get(3));
	button5.setText(MainActivityfragment.Jobtitle.get(4));
	button6.setText(MainActivityfragment.Jobtitle.get(5));
	button7.setText(MainActivityfragment.Jobtitle.get(6));
	button8.setText(MainActivityfragment.Jobtitle.get(7));
	button9.setText(MainActivityfragment.Jobtitle.get(8));
	button10.setText(MainActivityfragment.Jobtitle.get(9));
	
	button11.setText(MainActivityfragment.Jobtitle.get(10));
	button12.setText(MainActivityfragment.Jobtitle.get(11));
	
	}
	catch(Exception e)
	{
		
	}
	
	
	
	
	
	
	
	
	clear=(Button)v.findViewById(R.id.clear);
		clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=0;
				skillvalues="";
				skills.setText("");

				jsonParam8= new JSONObject();
				jsonParam10= new JSONObject();
			}
		});
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(skills.getText().toString().equals(""))
				{
					i=0;
					skillvalues="";
					jsonParam8= new JSONObject();
					jsonParam10= new JSONObject();
				}
				
				if(skillvalues.equals(""))
				{
					skillvalues=button1.getText().toString();
					skills.setText(skillvalues);	
				}
				else
				{
				skillvalues=skillvalues+","+button1.getText().toString();
				skills.setText(skillvalues);
				}
				i=i+1;
				try
				{
				jsonParam10.put(i+"",button1.getText().toString());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(skills.getText().toString().equals(""))
				{
					i=0;
					skillvalues="";
					jsonParam8= new JSONObject();
					jsonParam10= new JSONObject();
				}
				if(skillvalues.equals(""))
				{
					skillvalues=button2.getText().toString();
					skills.setText(skillvalues);	
				}
				else
				{
				skillvalues=skillvalues+","+button2.getText().toString();
				skills.setText(skillvalues);
				}
				i=i+1;
				try
				{
				jsonParam10.put(i+"",button2.getText().toString());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
button3.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(skills.getText().toString().equals(""))
		{
			i=0;
			skillvalues="";
			jsonParam8= new JSONObject();
			jsonParam10= new JSONObject();
		}
		if(skillvalues.equals(""))
		{
			skillvalues=button3.getText().toString();
			skills.setText(skillvalues);	
		}
		else
		{
		skillvalues=skillvalues+","+button3.getText().toString();
		skills.setText(skillvalues);
		}
		i=i+1;
		try
		{
		jsonParam10.put(i+"",button3.getText().toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	});
	button4.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(skills.getText().toString().equals(""))
			{
				i=0;
				skillvalues="";
				jsonParam8= new JSONObject();
				jsonParam10= new JSONObject();
			}
			if(skillvalues.equals(""))
			{
				skillvalues=button4.getText().toString();
				skills.setText(skillvalues);	
			}
			else
			{
			skillvalues=skillvalues+","+button4.getText().toString();
			skills.setText(skillvalues);
			}
			i=i+1;
			try
			{
			jsonParam10.put(i+"",button4.getText().toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	});
	button5.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(skills.getText().toString().equals(""))
			{
				i=0;
				skillvalues="";
				jsonParam8= new JSONObject();
				jsonParam10= new JSONObject();
			}
			if(skillvalues.equals(""))
			{
				skillvalues=button5.getText().toString();
				skills.setText(skillvalues);	
			}
			else
			{
			skillvalues=skillvalues+","+button5.getText().toString();
			skills.setText(skillvalues);
			}
			i=i+1;
			try
			{
			jsonParam10.put(i+"",button5.getText().toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	});
	button6.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(skills.getText().toString().equals(""))
			{
				i=0;
				skillvalues="";
				jsonParam8= new JSONObject();
				jsonParam10= new JSONObject();
			}
			if(skillvalues.equals(""))
			{
				skillvalues=button6.getText().toString();
				skills.setText(skillvalues);	
			}
			else
			{
			skillvalues=skillvalues+","+button6.getText().toString();
			skills.setText(skillvalues);
			}
			i=i+1;
			try
			{
			jsonParam10.put(i+"",button6.getText().toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	});
	///////////////////////
button7.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(skills.getText().toString().equals(""))
			{
				i=0;
				skillvalues="";
				jsonParam8= new JSONObject();
				jsonParam10= new JSONObject();
			}
			if(skillvalues.equals(""))
			{
				skillvalues=button7.getText().toString();
				skills.setText(skillvalues);	
			}
			else
			{
			skillvalues=skillvalues+","+button7.getText().toString();
			skills.setText(skillvalues);
			}
			i=i+1;
			try
			{
			jsonParam10.put(i+"",button7.getText().toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	});
button8.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(skills.getText().toString().equals(""))
		{
			i=0;
			skillvalues="";
			jsonParam8= new JSONObject();
			jsonParam10= new JSONObject();
		}
		if(skillvalues.equals(""))
		{
			skillvalues=button8.getText().toString();
			skills.setText(skillvalues);	
		}
		else
		{
		skillvalues=skillvalues+","+button8.getText().toString();
		skills.setText(skillvalues);
		}
		i=i+1;
		try
		{
		jsonParam10.put(i+"",button8.getText().toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
});
button9.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(skills.getText().toString().equals(""))
		{
			i=0;
			skillvalues="";
			jsonParam8= new JSONObject();
			jsonParam10= new JSONObject();
		}
		if(skillvalues.equals(""))
		{
			skillvalues=button9.getText().toString();
			skills.setText(skillvalues);	
		}
		else
		{
		skillvalues=skillvalues+","+button9.getText().toString();
		skills.setText(skillvalues);
		}
		i=i+1;
		try
		{
		jsonParam10.put(i+"",button9.getText().toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
});
button10.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(skills.getText().toString().equals(""))
		{
			i=0;
			skillvalues="";
			jsonParam8= new JSONObject();
			jsonParam10= new JSONObject();
		}
		if(skillvalues.equals(""))
		{
			skillvalues=button10.getText().toString();
			skills.setText(skillvalues);	
		}
		else
		{
		skillvalues=skillvalues+","+button10.getText().toString();
		skills.setText(skillvalues);
		}
		i=i+1;
		try
		{
		jsonParam10.put(i+"",button10.getText().toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
});
button11.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(skills.getText().toString().equals(""))
		{
			i=0;
			skillvalues="";
			jsonParam8= new JSONObject();
			jsonParam10= new JSONObject(); 
		}
		if(skillvalues.equals(""))
		{
			skillvalues=button11.getText().toString();
			skills.setText(skillvalues);	
		}
		else
		{
		skillvalues=skillvalues+","+button11.getText().toString();
		skills.setText(skillvalues);
		}
		i=i+1;
		try
		{
		jsonParam10.put(i+"",button11.getText().toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
});
button12.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(skills.getText().toString().equals(""))
		{
			i=0;
			skillvalues="";
			jsonParam8= new JSONObject();
			jsonParam10= new JSONObject();
		}
		if(skillvalues.equals(""))
		{
			skillvalues=button12.getText().toString();
			skills.setText(skillvalues);	
		}
		else
		{
		skillvalues=skillvalues+","+button12.getText().toString();
		skills.setText(skillvalues);
		}
		i=i+1;
		try
		{
		jsonParam10.put(i+"",button12.getText().toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
});

	//////////////////////
	search.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			search.setBackgroundResource(R.drawable.violetlonghigh);
			if(skills.getText().toString().equals(""))
			{
				skills.setError("Mandatory");
			}
			
			else
			{
				System.out.println(i+"...................");
				if(i==0)
				{
					try {
						jsonParam10.put("1",skills.getText().toString());
						System.out.println("value of i==0");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(url);
				System.out.println(jsonParam10);
				search.setBackgroundResource(R.drawable.violetsmallhigh);
				 job_id.clear();
		         job_title.clear();
		          job_description.clear();
		          job_startdate.clear();
		          job_end_date.clear();
		          job_status.clear();
		          skill.clear();
		          experience.clear();
		          company.clear();
		          if( isNetworkConnected()==true)
			       {
			    	  
			    	   new GetContacts().execute();
			       } 
			       else
			       {
			    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
			       }
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
			 valreturn=calljson();
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
			
			JSONObject job;
			try {
				job = new JSONObject(valreturn);
			
			JSONObject candidatedetails=job.getJSONObject("candidatedetails");
			 Iterator<?> keys = candidatedetails.keys();
		   	  while( keys.hasNext() ){
		             String key = (String)keys.next();
		             if( candidatedetails.get(key) instanceof JSONObject ){
		          	   JSONObject c = (JSONObject) candidatedetails.get(key);
	//	   				 for(int i=0;i<c.length();i++)
	//	   				 {
		          	  
		          	   job_id.add(c.getString("job_id"));
		          	 job_title.add(c.getString("job_title"));
		          	job_description.add(c.getString("job_description"));
		          	job_startdate.add(c.getString("job_startdate"));
		          	job_end_date.add(c.getString("job_end_date"));
		          	job_status.add(c.getString("job_status"));
		          	experience.add(c.getString("experience"));
		          	company.add(c.getString("company"));
		   				
		   				 JSONObject skil =c.getJSONObject("skill");
		   				 String s="";
		   				
		   				 Iterator<?> keys1 = skil.keys();
		   		   	  while( keys1.hasNext() ){		   		   	  
		   		             String key1 = (String)keys1.next();
		   		          s=s+skil.getString(key1)+","; 
		   		         
		   		   	  }
		   		   skill.add(s);
		             }
		   	  }
				 Fragment fragment = new SearchJobResult();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);
			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				if(skillvalues.equals(""))
//				{
//					Toast.makeText(getActivity(), "Currently there are no opening for '"+skills.getText().toString()+"'", 1).show();
//				}
//				else 
//				{
				Toast.makeText(getActivity(), "Currently there are no opening for '"+skills.getText().toString()+"'", 1).show();
//				}
				skillvalues="";
				jsonParam8= new JSONObject();
				jsonParam10= new JSONObject();
				skills.setText("");
				i=0;
				search.setBackgroundResource(R.drawable.next_btn);
			}	
			}
	}
	private String calljson() { 
	// TODO Auto-generated method stub

	InputStream inputStream = null;
    String result = "";
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    try {
    	jsonParam8.put("action", "view");
//    	jsonParam8.put("candidate", Login.candidate_id);

    	jsonParam8.put("skills", jsonParam10);
    	System.out.println(jsonParam8);
//    	System.out.println("user id"+Login.user_id);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        String json1 ="";
        json1 = jsonParam8.toString();
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
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		search.setBackgroundResource(R.drawable.button1);
		skills.setText("");
		skillvalues="";
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
