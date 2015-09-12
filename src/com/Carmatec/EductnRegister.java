package com.Carmatec;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class EductnRegister extends Fragment
{
	String s;
	ArrayList<String> gradspinner=new ArrayList<String>();
	EditText specialin,university,year,score;
	JSONObject jobj;
	Spinner graduationspinner;
	private ProgressDialog pDialog;
	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	Button nextaddrs,delete;
	int graduationspinnerval,years,month,day;
	final Calendar c = Calendar.getInstance();
	public EductnRegister() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.eductnregister, container, false);
		MainActivityfragment.pagevalue=18;
		MainActivityfragment.actionname.setText("Educational Qualification");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		 this. setRetainInstance(true);
	    years = c.get(Calendar.YEAR);
	    month=c.get(Calendar.MONTH);
	    month=c.get(Calendar.DAY_OF_MONTH);
		gradspinner.clear();
		gradspinner.add("SSLC");
		gradspinner.add("B.A");
		gradspinner.add("B.Arch");
		gradspinner.add("BCA");
		gradspinner.add("B.B.A");
		gradspinner.add("B.Com");
		gradspinner.add("B.Ed");
		gradspinner.add("BDS");
		gradspinner.add("BHM");
		gradspinner.add("B.Pharma");
		gradspinner.add("B.Sc");
		gradspinner.add("B.Tech/B.E");
		gradspinner.add("LLB");
		gradspinner.add("MBBS");
		gradspinner.add("Diploma");
		gradspinner.add("BVSC");
		gradspinner.add("CS");
		gradspinner.add("ICWA (CMA)");
		gradspinner.add("Integrated PG");
		gradspinner.add("LLM");
		gradspinner.add("M.A");
		gradspinner.add("M.Arch");
		gradspinner.add("M.Com");
		gradspinner.add("M.Ed");
		gradspinner.add("M.Pharma");
		gradspinner.add("M.Sc");
		gradspinner.add("M.Tech");
		gradspinner.add("MBA/PGDM");
		gradspinner.add("MCA");
		gradspinner.add("PG Diploma");
		gradspinner.add("MVSC");
		gradspinner.add("MCM");
		gradspinner.add("Ph.D/Doctorate");
		gradspinner.add("MPHIL");

		graduationspinner=(Spinner)v.findViewById(R.id.graduationspinner);
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getActivity(),
		android.R.layout.simple_spinner_item, gradspinner);
		adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		graduationspinner.setAdapter(adapter_state);
		nextaddrs= (Button)v.findViewById(R.id.nextedu);
		delete= (Button)v.findViewById(R.id.delete);
		specialin=(EditText)v.findViewById(R.id.specialin);
		university=(EditText)v.findViewById(R.id.university);
		year=(EditText)v.findViewById(R.id.year);
		score=(EditText)v.findViewById(R.id.score);
		if(ViewEducationDetails.eduval==1)
		{
			for(int i=0;i<gradspinner.size();i++)
			{
				if(gradspinner.get(i).equals(UserProfileEdit.edugraduation.get(ViewEducationDetails.positionval)))
				{
					graduationspinner.setSelection(i);
					graduationspinnerval=i;
				}
			}
			specialin.setText(UserProfileEdit.eduspecialization.get(ViewEducationDetails.positionval));
			university.setText(UserProfileEdit.eduinstitute.get(ViewEducationDetails.positionval));
			year.setText(UserProfileEdit.eduyear.get(ViewEducationDetails.positionval));
			score.setText(UserProfileEdit.eduscore.get(ViewEducationDetails.positionval));
			delete.setVisibility(View.VISIBLE);
		}
		else
		{
			specialin.setText("");
			university.setText("");
			year.setText("");
			score.setText("");
			delete.setVisibility(View.GONE);
		}
		graduationspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				graduationspinnerval=position;
			}
	
			@Override
			public void onNothingSelected(AdapterView<?> parent) {				
			}
			});
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				delete.setBackgroundResource(R.drawable.whitehigh);
				 nameValuePairs.clear();
				 nameValuePairs.add(new BasicNameValuePair("action", "delete"));
	    		 nameValuePairs.add(new BasicNameValuePair("education_id", UserProfileEdit.eduid.get(ViewEducationDetails.positionval)));
	    		 nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
				ViewEducationDetails.eduval=3;
				try
					{
					if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
				       }				
					}catch(Exception ee2)
					{
						
					}
			}
		});
	nextaddrs.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		
			if(university.getText().toString().equals(""))
			{
				university.setError("Mandatory");
			}
			else if(!university.getText().toString().matches("[a-z A-Z]+"))
			{
				university.setError("Invalid Character");
			}
			else if(year.getText().toString().equals(""))
			{
				year.setError("Mandatory");
			}
			else if(Integer.parseInt(year.getText().toString())>years)
			{
				year.setError("Invalid Year");
			}
			else if(score.getText().toString().equals(""))
			{
				score.setError("Mandatory");
			}
			else if(Float.parseFloat(score.getText().toString())>100.00)
			{
				score.setError("Please enter valid score");
			}
			
			else
			{
				nextaddrs.setBackgroundResource(R.drawable.violetsmallhigh);
			nameValuePairs.clear();
			if(ViewEducationDetails.eduval==1)
			{
				try
				{
					 nameValuePairs.add(new BasicNameValuePair("action", "edit"));
		    		 nameValuePairs.add(new BasicNameValuePair("education_id", UserProfileEdit.eduid.get(ViewEducationDetails.positionval)));
		    		 nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
		    		 nameValuePairs.add(new BasicNameValuePair("basic_graduation",gradspinner.get(graduationspinnerval)));
		    		 nameValuePairs.add(new BasicNameValuePair("specialization", specialin.getText().toString()));
		    		 nameValuePairs.add(new BasicNameValuePair("university", university.getText().toString()));	
		    		 nameValuePairs.add(new BasicNameValuePair("year", year.getText().toString()));	
		    		 nameValuePairs.add(new BasicNameValuePair("score", score.getText().toString()));	
		    		 if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
				       }
				
				
				}catch(Exception ee)
				{
		
				}
			}
			else
			{
				try
				{
					 nameValuePairs.add(new BasicNameValuePair("action", "add"));
		    		 nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
		    		 nameValuePairs.add(new BasicNameValuePair("basic_graduation", gradspinner.get(graduationspinnerval)));
		    		 nameValuePairs.add(new BasicNameValuePair("specialization", specialin.getText().toString()));
		    		 nameValuePairs.add(new BasicNameValuePair("university", university.getText().toString()));	
		    		 nameValuePairs.add(new BasicNameValuePair("year", year.getText().toString()));	
		    		 nameValuePairs.add(new BasicNameValuePair("score", score.getText().toString()));	
					
		    		 if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
				       }
				
				
				}catch(Exception ee)
				{
		
				}
			
			}
//		    
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
		// Creating service handler class instance
				 try{
		        	 jobj=JsonCall.postData(nameValuePairs, ImageSliderCarma.urlvalue+"users/education_api.json?");
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
			if(ViewEducationDetails.eduval==1)
			{
				
			
			JSONObject jobj1=jobj.getJSONObject("result");
			String stringval=jobj1.getString("success");
			if(stringval.equals("Record has been updated"))
			{

				UserProfileEdit.edugraduation.remove(ViewEducationDetails.positionval);
				UserProfileEdit.eduspecialization.remove(ViewEducationDetails.positionval);
				UserProfileEdit.eduinstitute.remove(ViewEducationDetails.positionval);
				UserProfileEdit.eduyear.remove(ViewEducationDetails.positionval);
				UserProfileEdit.eduscore.remove(ViewEducationDetails.positionval);
				
				UserProfileEdit.edugraduation.add(ViewEducationDetails.positionval,gradspinner.get(graduationspinnerval));
				UserProfileEdit.eduspecialization.add(ViewEducationDetails.positionval,specialin.getText().toString());
				UserProfileEdit.eduinstitute.add(ViewEducationDetails.positionval,university.getText().toString());
				UserProfileEdit.eduyear.add(ViewEducationDetails.positionval,year.getText().toString());
				UserProfileEdit.eduscore.add(ViewEducationDetails.positionval,score.getText().toString());
				ViewEducationDetails.eduval=0;
				Fragment fragment = new ViewEducationDetails();
		        Bundle args = new Bundle();
		        fragment.setArguments(args);
		        android.app.FragmentManager fragmentManager = getFragmentManager();
		        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			
			}
			else
			{
				Toast.makeText(getActivity(), "Failed to Add", 1).show();
				nextaddrs.setBackgroundResource(R.drawable.next_btn);
			}
			}
			else if(ViewEducationDetails.eduval==3)
			{
				String stringval=jobj.getString("result");
				
				if(stringval.equals("record has been removed"))
				{
					UserProfileEdit.eduid.remove(ViewEducationDetails.positionval);
					UserProfileEdit.edugraduation.remove(ViewEducationDetails.positionval);
					UserProfileEdit.eduspecialization.remove(ViewEducationDetails.positionval);
					UserProfileEdit.eduinstitute.remove(ViewEducationDetails.positionval);
					UserProfileEdit.eduscore.remove(ViewEducationDetails.positionval);
					UserProfileEdit.eduyear.remove(ViewEducationDetails.positionval);
					UserProfileEdit.educandidate_id.remove(ViewEducationDetails.positionval);
					Toast.makeText(getActivity(), "Item Removed", 1).show();
					ViewEducationDetails.eduval=0;
					if(UserProfileEdit.edugraduation.size()==0)
					{
						Login.education="false";
						startActivity(new Intent(getActivity(),MainActivityfragment.class));
					}
					else
					{
//						finish();
						Fragment fragment = new ViewEducationDetails();
				        Bundle args = new Bundle();
				        fragment.setArguments(args);
				        FragmentManager fragmentManager = getFragmentManager();
				        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
					
					}
				
				}
				else
				{
					Toast.makeText(getActivity(), "Failed to Remove", 1).show();
					delete.setBackgroundResource(R.drawable.upload_image_btn);
				}
				
			}
			else
			{
				try
				{

				JSONObject jobj1=jobj.getJSONObject("result");
				String stringval=jobj1.getString("success");
				if(stringval.equals("Record has been saved"))
				{
					
					JSONObject jobj3=jobj1.getJSONObject("CandidateEducationDetail");
					UserProfileEdit.eduid.add(jobj3.getString("id"));
					UserProfileEdit.edugraduation.add(jobj3.getString("graduation"));
					UserProfileEdit.eduspecialization.add(jobj3.getString("specialization"));
					UserProfileEdit.eduinstitute.add(jobj3.getString("institute"));
					UserProfileEdit.eduscore.add(jobj3.getString("score"));
					UserProfileEdit.eduyear.add(jobj3.getString("year"));
					UserProfileEdit.educandidate_id.add(jobj3.getString("candidate_id"));
					Login.education.equals("true");
					ViewEducationDetails.eduval=0;
					Toast.makeText(getActivity(), "Success", 1).show();
					Login.education="true";
					Fragment fragment = new ViewEducationDetails();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);
			        android.app.FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
				
				}
				else
				{
					Toast.makeText(getActivity(), "Failed to Add", 1).show();
					nextaddrs.setBackgroundResource(R.drawable.next_btn);
				}
				
				}catch(Exception ee)
				{
					Toast.makeText(getActivity(), "Failed to Add", 1).show();
					nextaddrs.setBackgroundResource(R.drawable.next_btn);
				}	
			}
			
		}
		catch(Exception e)
		{
			Toast.makeText(getActivity(), "Failed Submit", 1).show();
			delete.setBackgroundResource(R.drawable.upload_image_btn);
			nextaddrs.setBackgroundResource(R.drawable.next_btn);
		}
	}

}
@Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	delete.setBackgroundResource(R.drawable.upload_image_btn);
	nextaddrs.setBackgroundResource(R.drawable.next_btn);
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
