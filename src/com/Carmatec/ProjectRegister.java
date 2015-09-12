package com.Carmatec;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ProjectRegister extends Fragment
{
	Button nextaddrs,delete,prostartdateselect,proenddateselect;
	String s;
	private ProgressDialog pDialog;
	 private Calendar cal;
	 private int day;
	 private int month;
	 private int year;   
	 int flag=0;
	 int spinoldval;
	 JSONObject jobj;
	 int date1,month1,year1, date2,month2,year2;
static int spinnerval;
List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	EditText projecttitle,projectsize,prostartdate,proenddate,prodes,prorole;
	Spinner spinner1;
	public ProjectRegister() {
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.projectregister, container, false);
		MainActivityfragment.actionname.setText("Project Details");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		MainActivityfragment.pagevalue=20;
		 this.setRetainInstance(true);
		 cal = Calendar.getInstance();
		 day = cal.get(Calendar.DAY_OF_MONTH);
	
		 month = cal.get(Calendar.MONTH);
		 year = cal.get(Calendar.YEAR);
		 delete=(Button)v.findViewById(R.id.delete);
		 prostartdateselect=(Button)v.findViewById(R.id.prostartdateselect);
		 proenddateselect=(Button)v.findViewById(R.id.proenddateselect);
		spinner1=(Spinner)v.findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getActivity(),
		    android.R.layout.simple_spinner_item, UserProfileEdit.companyname);
		  adapter_state
		    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  spinner1.setAdapter(adapter_state);
		  
		projecttitle=(EditText)v.findViewById(R.id.projecttitle);
		projectsize=(EditText)v.findViewById(R.id.projectsize);
		prostartdate=(EditText)v.findViewById(R.id.prostartdate);	
		proenddate=(EditText)v.findViewById(R.id.proenddate);
		prodes=(EditText)v.findViewById(R.id.prodes);
		prorole=(EditText)v.findViewById(R.id.prorole);
		if(ViewProjectDetails.proval==0)
		{
			delete.setVisibility(View.GONE);
		}
		else if(ViewProjectDetails.proval==1)
		{
			
		}
		else if(ViewProjectDetails.proval==2)
		{
			
		}
		else 
		{
			ViewProjectDetails.proval=0;
			delete.setVisibility(View.GONE);
		}
		
		if(ViewProjectDetails.proval==1)
		{
		for(int i=0;i<UserProfileEdit.companyid.size();i++)
		{
			if(UserProfileEdit.companyid.get(i).equals(UserProfileEdit.procandidate_previous_detail_id.get(ViewProjectDetails.positionval)))
			{
				spinner1.setSelection(i);
				spinoldval=i;
			}
		}
		projecttitle.setText(UserProfileEdit.proproject_title.get(ViewProjectDetails.positionval));
		projectsize.setText(UserProfileEdit.prosize.get(ViewProjectDetails.positionval));
		prostartdate.setText(UserProfileEdit.prostart_date.get(ViewProjectDetails.positionval));
		proenddate.setText(UserProfileEdit.proend_date.get(ViewProjectDetails.positionval));
		prodes.setText(UserProfileEdit.prodescription.get(ViewProjectDetails.positionval));
		prorole.setText(UserProfileEdit.prorole.get(ViewProjectDetails.positionval));
	}
	else
	{
		projecttitle.setText("");
		projectsize.setText("");
		prostartdate.setText("");
		proenddate.setText("");
		prodes.setText("");
		prorole.setText("");
	}
	spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			spinnerval=position;
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	});
	
	 nextaddrs= (Button)v.findViewById(R.id.nextpro);
	prostartdateselect.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			 flag=1;
				DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener1, 
	                    year, month,day);
				dialog.show();
		}
	});
	proenddateselect.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			 flag=2;
				DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener1, 
						 year, month,day);
				dialog.show();
		}
	});
	delete.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			delete.setBackgroundResource(R.drawable.whitehigh);
			ViewProjectDetails.proval=2;
			nameValuePairs.clear();
			 nameValuePairs.add(new BasicNameValuePair("action","delete"));
		      nameValuePairs.add(new BasicNameValuePair("project_id",UserProfileEdit.proid.get(ViewProjectDetails.positionval)));
		      if( isNetworkConnected()==true)
		       {
		    	  
		    	   new GetContacts().execute();
		       }
		       else
		       {
		    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
		       }
		}
	});
	nextaddrs.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			if(projecttitle.getText().toString().equals(""))
			{
				projecttitle.setError("Mandatory");
			}
			else if(!projecttitle.getText().toString().matches("[a-z A-Z]+"))
			{
				projecttitle.setError("Invalid Character");
			}
			else if(projectsize.getText().toString().equals(""))
			{
				projectsize.setError("Mandatory");
			}
			else if(prostartdate.getText().toString().equals(""))
			{
				prostartdate.setError("Mandatory");
			}
			else if(proenddate.getText().toString().equals(""))
			{
				proenddate.setError("Mandatory");
			}
			else if(year1>year2)
			{
				prostartdate.setError("Please check selected date");
				Toast.makeText(getActivity(), "Please check date", 1).show();
			}
			else if((year1==year2)&&(month1>month2))
			{
				prostartdate.setError("Please check selected date");
				Toast.makeText(getActivity(), "Please check date", 1).show();
			}
			else if((year1==year2)&&(month1==month2)&&(date1>date2))
			{
				prostartdate.setError("Please check selected date");
				Toast.makeText(getActivity(), "Please check date", 1).show();
			}
			else if(prodes.getText().toString().equals(""))
			{
				prodes.setError("Mandatory");
			}
			else if(!prodes.getText().toString().matches("[a-z A-Z]+"))
			{
				prodes.setError("Invalid Character");
			}
			else if(prorole.getText().toString().equals(""))
			{
				prorole.setError("Mandatory");
			}
			else if(!prorole.getText().toString().matches("[a-z A-Z]+"))
			{
				prorole.setError("Invalid Character");
			}
			
			else
			{
				nextaddrs.setBackgroundResource(R.drawable.violetsmallhigh);
			try
			{
				 nameValuePairs.clear();
				if(ViewProjectDetails.proval==0)
				{
				
				 nameValuePairs.add(new BasicNameValuePair("action", "add"));
	    		 nameValuePairs.add(new BasicNameValuePair("project_title",projecttitle.getText().toString()));
	    		 nameValuePairs.add(new BasicNameValuePair("project_description", prodes.getText().toString()));
	    		 nameValuePairs.add(new BasicNameValuePair("project_start_date", prostartdate.getText().toString()));
	    		 nameValuePairs.add(new BasicNameValuePair("project_end_date", proenddate.getText().toString()));
	    		 nameValuePairs.add(new BasicNameValuePair("project_role_description", prorole.getText().toString()));	
	    		 nameValuePairs.add(new BasicNameValuePair("company_id", UserProfileEdit.companyid.get(spinnerval)));	
	    		 nameValuePairs.add(new BasicNameValuePair("project_size", projectsize.getText().toString()));	
	    		 if( isNetworkConnected()==true)
			       {
			    	  
			    	   new GetContacts().execute();
			       }
			       else
			       {
			    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
			       }
				}
				else if(ViewProjectDetails.proval==1)
				{
					 nameValuePairs.add(new BasicNameValuePair("action", "edit"));
					 nameValuePairs.add(new BasicNameValuePair("project_id", UserProfileEdit.proid.get(ViewProjectDetails.positionval)));
					 nameValuePairs.add(new BasicNameValuePair("project_title",projecttitle.getText().toString() ));
					 nameValuePairs.add(new BasicNameValuePair("project_description", prodes.getText().toString()));
					 nameValuePairs.add(new BasicNameValuePair("project_start_date", prostartdate.getText().toString()));
					 nameValuePairs.add(new BasicNameValuePair("project_end_date", proenddate.getText().toString()));
					 nameValuePairs.add(new BasicNameValuePair("project_role_description", prorole.getText().toString()));
					 nameValuePairs.add(new BasicNameValuePair("company_id", UserProfileEdit.companyid.get(spinnerval)));
					 nameValuePairs.add(new BasicNameValuePair("project_size", projectsize.getText().toString()));

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
				nextaddrs.setBackgroundResource(R.drawable.next_btn);
			}
			}
		}
	});
	return v;
}

private DatePickerDialog.OnDateSetListener datePickerListener1 
= new DatePickerDialog.OnDateSetListener() {

// when dialog box is closed, below method will be called.
public void onDateSet(DatePicker view, int selectedYear,
int selectedMonth, int selectedDay) {
//Do whatever you want
	 final Calendar c = Calendar.getInstance();
		int    yy = c.get(Calendar.YEAR);
		if(selectedYear>yy)
		{
			Toast.makeText(getActivity(), "Invalid date", 1).show();
		}
		else
		{
			
	if(flag==1)
	{
		date1=selectedDay;
		month1=selectedMonth+1;
		year1=selectedYear;
		prostartdate.setText(selectedYear+"-"+(selectedMonth+1)+"-"+selectedDay);
	}
	else if(flag==2)
	{
		date2=selectedDay;
		month2=selectedMonth+1;
		year2=selectedYear;
		proenddate.setText(selectedYear+"-"+(selectedMonth+1)+"-"+selectedDay);
	}
		}
	flag=0;
}
};
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
        	 jobj=JsonCall.postData(nameValuePairs, ImageSliderCarma.urlvalue+"users/project_api.json?");
        }
            catch (Exception e)
            {
            	e.printStackTrace();
            	delete.setBackgroundResource(R.drawable.upload_image_btn);
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
		 if(ViewProjectDetails.proval==1)
			{
				JSONObject jobj1=jobj.getJSONObject("result");
				String stringval=jobj1.getString("success");
				if(stringval.equals("Record has been updated"))
				{
					UserProfileEdit.proproject_title.remove(ViewProjectDetails.positionval);
					UserProfileEdit.prostart_date.remove(ViewProjectDetails.positionval);
					UserProfileEdit.proend_date.remove(ViewProjectDetails.positionval);
					UserProfileEdit.prodescription.remove(ViewProjectDetails.positionval);
					UserProfileEdit.prorole.remove(ViewProjectDetails.positionval);
					UserProfileEdit.prosize.remove(ViewProjectDetails.positionval);
					UserProfileEdit.procandidate_previous_detail_id.remove(ViewProjectDetails.positionval);
					
					UserProfileEdit.proproject_title.add(ViewProjectDetails.positionval,projecttitle.getText().toString());
					UserProfileEdit.prostart_date.add(ViewProjectDetails.positionval,prostartdate.getText().toString());
					UserProfileEdit.proend_date.add(ViewProjectDetails.positionval,proenddate.getText().toString());
					UserProfileEdit.prodescription.add(ViewProjectDetails.positionval,prodes.getText().toString());
					UserProfileEdit.prorole.add(ViewProjectDetails.positionval,prorole.getText().toString());
					UserProfileEdit.prosize.add(ViewProjectDetails.positionval,projectsize.getText().toString());
					UserProfileEdit.procandidate_previous_detail_id.add(ViewProjectDetails.positionval,UserProfileEdit.companyid.get(spinnerval));

					//					
					
					ViewProjectDetails.proval=3;
					Toast.makeText(getActivity(), "Record has been updated", 1).show();
//					finish();
					Fragment fragment = new ViewProjectDetails();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);
			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

				}
				else
				{
					Toast.makeText(getActivity(), "Failed to update", 1).show();
					nextaddrs.setBackgroundResource(R.drawable.next_btn);
				}
			}
			else if(ViewProjectDetails.proval==2)
			{
				String stringval=jobj.getString("result");
				if(stringval.equals("record has been removed"))
				{
					ViewProjectDetails.proval=3;
					UserProfileEdit.proid.remove(ViewProjectDetails.positionval);
					UserProfileEdit.proproject_title.remove(ViewProjectDetails.positionval);
					UserProfileEdit.prostart_date.remove(ViewProjectDetails.positionval);
					UserProfileEdit.proend_date.remove(ViewProjectDetails.positionval);
					UserProfileEdit.prodescription.remove(ViewProjectDetails.positionval);
					UserProfileEdit.prorole.remove(ViewProjectDetails.positionval);
					UserProfileEdit.procandidate_previous_detail_id.remove(ViewProjectDetails.positionval);
					UserProfileEdit.prosize.remove(ViewProjectDetails.positionval);
					if(UserProfileEdit.proproject_title.size()==0)
					{
						Toast.makeText(getActivity(), "Record has been removed", 1).show();
						Login.project="false";
						Fragment fragment = new UserProfileEdit();
				        Bundle args = new Bundle();
				        fragment.setArguments(args);
				        FragmentManager fragmentManager = getFragmentManager();
				        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

					}
					else
					{
//						finish();
						Toast.makeText(getActivity(), "Record has been removed", 1).show();
						Fragment fragment = new ViewProjectDetails();
				        Bundle args = new Bundle();
				        fragment.setArguments(args);
				        FragmentManager fragmentManager = getFragmentManager();
				        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

					}
				}
				else
				{
					Toast.makeText(getActivity(), "Failed to delete", 1).show();
					delete.setBackgroundResource(R.drawable.upload_image_btn);
				}
			}
			else if(ViewProjectDetails.proval==0)
			{
				JSONObject jobj1=jobj.getJSONObject("result");
				String stringval=jobj1.getString("success");
				if(stringval.equals("Record has been saved"))
				{
				
					JSONObject jobj3=jobj1.getJSONObject("CandidatePreviousProject");
					UserProfileEdit.proid.add(jobj3.getString("id"));
					UserProfileEdit.proproject_title.add(jobj3.getString("project_title"));
					UserProfileEdit.prostart_date.add(jobj3.getString("start_date"));
					UserProfileEdit.proend_date.add(jobj3.getString("end_date"));
					UserProfileEdit.prodescription.add(jobj3.getString("description"));
					UserProfileEdit.prorole.add(jobj3.getString("role"));
					UserProfileEdit.procandidate_previous_detail_id.add(jobj3.getString("candidate_previous_detail_id"));
					UserProfileEdit.prosize.add(jobj3.getString("size"));
					Login.project="true";
					Toast.makeText(getActivity(), "Success", 1).show();
					ViewProjectDetails.proval=3;
					Fragment fragment = new ViewProjectDetails();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);
			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

				Toast.makeText(getActivity(), "Record has been saved", 1).show();
				}
				else
				{
					nextaddrs.setBackgroundResource(R.drawable.next_btn);
					Toast.makeText(getActivity(), "Failed to Add", 1).show();
				}
			}
			

		}
		catch(Exception e)
		{
			Toast.makeText(getActivity(), "Failed Submit", 1).show();
			delete.setBackgroundResource(R.drawable.upload_image_btn);
			nextaddrs.setBackgroundResource(R.drawable.next_btn);
			e.printStackTrace();
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
