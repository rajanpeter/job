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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class CompanyRegister extends Fragment
{
	 private ProgressDialog pDialog;
	 JSONObject jobj;
	 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	 private Calendar cal;
	 private int day;
	 private int month;
	 private int year;
	 int flag=0;
	 int date1,month1,year1, date2,month2,year2;
	 Button startdateselect,enddateselect,delete,nextaddrs;
	 EditText compname,startdate,Enddate,role,roledescr,lastsalary;
	 public CompanyRegister() {
	    }
	 @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
				View v = inflater.inflate(R.layout.companyregister, container, false);
				MainActivityfragment.pagevalue=19;
				MainActivityfragment.actionname.setText("Corporate Experience");
				MainActivityfragment.actionname.setGravity(Gravity.CENTER);
				this. setRetainInstance(true);
				startdateselect=(Button)v.findViewById(R.id.startdateselect);
				enddateselect=(Button)v.findViewById(R.id.enddateselect);
				delete=(Button)v.findViewById(R.id.delete);
				nextaddrs= (Button)v.findViewById(R.id.nextcomp);
				compname=(EditText)v.findViewById(R.id.compname);
				startdate=(EditText)v.findViewById(R.id.startdate);
				Enddate=(EditText)v.findViewById(R.id.Enddate);	
				role=(EditText)v.findViewById(R.id.role);
				roledescr=(EditText)v.findViewById(R.id.roledescr);
				lastsalary=(EditText)v.findViewById(R.id.lastsalary);
			
				if(ViewCompanyDetails.addval==1)
				{
					delete.setVisibility(View.VISIBLE);
					
				}
				else
				{
					delete.setVisibility(View.GONE);
				}

			  cal = Calendar.getInstance();
			  day = cal.get(Calendar.DAY_OF_MONTH);
			  month = cal.get(Calendar.MONTH);
			  year = cal.get(Calendar.YEAR);
			  if(ViewCompanyDetails.addval==1)
			  {
				  	compname.setText(UserProfileEdit.compcompany_name.get(ViewCompanyDetails.positionval));
					startdate.setText(UserProfileEdit.compfrom.get(ViewCompanyDetails.positionval));
					Enddate.setText(UserProfileEdit.compto.get(ViewCompanyDetails.positionval));
					role.setText(UserProfileEdit.comprole.get(ViewCompanyDetails.positionval));
					roledescr.setText(UserProfileEdit.compfunctioning.get(ViewCompanyDetails.positionval));
					lastsalary.setText(UserProfileEdit.complast_salary_drawn.get(ViewCompanyDetails.positionval));
			  }
			  else
			  {
				 	compname.setText("");
					startdate.setText("");
					Enddate.setText("");
					role.setText("");
					roledescr.setText("");
					lastsalary.setText("");
					delete.setVisibility(View.GONE);
			   
			  }
			  delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					    delete.setBackgroundResource(R.drawable.whitehigh);
						nameValuePairs.clear();
						nameValuePairs.add(new BasicNameValuePair("action","delete"));
						nameValuePairs.add(new BasicNameValuePair("company_id", UserProfileEdit.compid.get(ViewCompanyDetails.positionval)));
						nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
						ViewCompanyDetails.addval=3;
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
			startdateselect.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					 flag=1;
						DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener1, 
								 year, month,day);
						dialog.show();
				}
			});
			enddateselect.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					flag=2;
					DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener1, 
							 year, month,day);
					dialog.show();	}
			});
			nextaddrs.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				
					if(compname.getText().toString().equals(""))
					{
						compname.setError("Mandatory");
					}
					else if(!compname.getText().toString().matches("[a-z A-Z]+"))
					{
						compname.setError("Invalid Character");
					}
					else if(startdate.getText().toString().equals(""))
					{
						startdate.setError("Mandatory");
					}
					else if(Enddate.getText().toString().equals(""))
					{
						Enddate.setError("Mandatory");
					}
					else if(Enddate.getText().toString().equals(startdate.getText().toString()))
					{
						Enddate.setError("Start and End dates are same");
						startdate.setError("Start and End dates are same");
						
					}
					else if(year1>year2)
					{
						startdate.setError("Please check selected date");
						Toast.makeText(getActivity(), "Please check date", 1).show();
					}
					else if((year1==year2)&&(month1>month2))
					{
						startdate.setError("Please check selected date");
						Toast.makeText(getActivity(), "Please check date", 1).show();
					}
					else if((year1==year2)&&(month1==month2)&&(date1>date2))
					{
						startdate.setError("Please check selected date");
						Toast.makeText(getActivity(), "Please check date", 1).show();
					}
					else if(role.getText().toString().equals(""))
					{
						role.setError("Mandatory");
					}
					else if(!role.getText().toString().matches("[a-z A-Z]+"))
					{
						role.setError("Invalid Character");
					}
					else if(roledescr.getText().toString().equals(""))
					{
						roledescr.setError("Mandatory");
					}
					else if(!roledescr.getText().toString().matches("[a-z A-Z]+"))
					{
						roledescr.setError("Invalid Character");
					}
					else if(lastsalary.getText().toString().equals(""))
					{
						lastsalary.setError("Mandatory");
					}
					
					else
					{
						nextaddrs.setBackgroundResource(R.drawable.violetsmallhigh);
						if(ViewCompanyDetails.addval==2)
						{
					try
					{
						
						nameValuePairs.clear();
						 nameValuePairs.add(new BasicNameValuePair("action", "add"));
			    		 nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
			    		 nameValuePairs.add(new BasicNameValuePair("company_name", compname.getText().toString()));
			    		 nameValuePairs.add(new BasicNameValuePair("start_date", startdate.getText().toString()));
			    		 nameValuePairs.add(new BasicNameValuePair("end_date", Enddate.getText().toString()));
			    		 nameValuePairs.add(new BasicNameValuePair("role", role.getText().toString()));	
			    		 nameValuePairs.add(new BasicNameValuePair("role_description", roledescr.getText().toString()));	
			    		 nameValuePairs.add(new BasicNameValuePair("last_salary", lastsalary.getText().toString()));	
				
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
						else if(ViewCompanyDetails.addval==1)
						{
							nameValuePairs.clear();
							 nameValuePairs.add(new BasicNameValuePair("action", "edit"));
				    		 nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
				    		 nameValuePairs.add(new BasicNameValuePair("company_id", UserProfileEdit.compid.get(ViewCompanyDetails.positionval)));
				    		 nameValuePairs.add(new BasicNameValuePair("company_name", compname.getText().toString()));
				    		 nameValuePairs.add(new BasicNameValuePair("start_date", startdate.getText().toString()));
				    		 nameValuePairs.add(new BasicNameValuePair("end_date", Enddate.getText().toString()));
				    		 nameValuePairs.add(new BasicNameValuePair("role", role.getText().toString()));	
				    		 nameValuePairs.add(new BasicNameValuePair("role_description", roledescr.getText().toString()));	
				    		 nameValuePairs.add(new BasicNameValuePair("last_salary", lastsalary.getText().toString()));	
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
				}
			});
			return v;
}
	 private DatePickerDialog.OnDateSetListener datePickerListener1 
	 = new DatePickerDialog.OnDateSetListener() {
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
	 		startdate.setText(selectedYear+"-"+(selectedMonth+1)+"-"+selectedDay);
	 	}
	 	else if(flag==2)
	 	{
	 		date2=selectedDay;
			month2=selectedMonth+1;
			year2=selectedYear;
	 		Enddate.setText(selectedYear+"-"+(selectedMonth+1)+"-"+selectedDay);
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
	        	 jobj=JsonCall.postData(nameValuePairs, ImageSliderCarma.urlvalue+"users/experience_api.json?");
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
				if(ViewCompanyDetails.addval==1)
				{
					JSONObject jobj1=jobj.getJSONObject("result");
					String stringval=jobj1.getString("success");
					if(stringval.equals("Record has been updated"))
					{
					UserProfileEdit.compcompany_name.remove(ViewCompanyDetails.positionval);
					UserProfileEdit.compfrom.remove(ViewCompanyDetails.positionval);
					UserProfileEdit.compto.remove(ViewCompanyDetails.positionval);
					UserProfileEdit.comprole.remove(ViewCompanyDetails.positionval);
					UserProfileEdit.compfunctioning.remove(ViewCompanyDetails.positionval);
					UserProfileEdit.complast_salary_drawn.remove(ViewCompanyDetails.positionval);
					
					UserProfileEdit.compcompany_name.add(ViewCompanyDetails.positionval,compname.getText().toString());
					UserProfileEdit.compfrom.add(ViewCompanyDetails.positionval,startdate.getText().toString());
					UserProfileEdit.compto.add(ViewCompanyDetails.positionval,Enddate.getText().toString());
					UserProfileEdit.comprole.add(ViewCompanyDetails.positionval,role.getText().toString());
					UserProfileEdit.compfunctioning.add(ViewCompanyDetails.positionval,roledescr.getText().toString());
					UserProfileEdit.complast_salary_drawn.add(ViewCompanyDetails.positionval,lastsalary.getText().toString());
					
					ViewCompanyDetails.addval=0;
//					finish();
					Fragment fragment = new ViewCompanyDetails();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);
			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

					Toast.makeText(getActivity(), "Updated", 1).show();
					}
					else
					{
						Toast.makeText(getActivity(), "Failed to update", 1).show();
						nextaddrs.setBackgroundResource(R.drawable.next_btn);
					}
				}
				else if(ViewCompanyDetails.addval==2)
				{
					JSONObject jobj1=jobj.getJSONObject("result");
					String stringval=jobj1.getString("success");
					if(stringval.equals("Record has been saved"))
					{
						Login.company="true";
					JSONObject jobj3=jobj1.getJSONObject("CandidatePreviousDetail");
					UserProfileEdit.compid.add(jobj3.getString("id"));
					UserProfileEdit.compcompany_name.add(jobj3.getString("company_name"));
					UserProfileEdit.compfrom.add(jobj3.getString("from"));
					UserProfileEdit.compto.add(jobj3.getString("to"));
					UserProfileEdit.comprole.add(jobj3.getString("role"));
					UserProfileEdit.compfunctioning.add(jobj3.getString("functioning"));
					UserProfileEdit.compcandidate_id.add(jobj3.getString("candidate_id"));
					UserProfileEdit.complast_salary_drawn.add(jobj3.getString("last_salary_drawn"));
					ViewCompanyDetails.addval=0;
//					finish();
					Fragment fragment = new ViewCompanyDetails();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);
			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

					}
					else
					{
						Toast.makeText(getActivity(), "Failed to add", 1).show();
						nextaddrs.setBackgroundResource(R.drawable.next_btn);
					}
				}	
			else if(ViewCompanyDetails.addval==3)
			{
				String stringval=jobj.getString("result");
				if(stringval.equals("record has been removed"))
				{
				UserProfileEdit.compid.remove(ViewCompanyDetails.positionval);
				UserProfileEdit.compcompany_name.remove(ViewCompanyDetails.positionval);
				UserProfileEdit.compfrom.remove(ViewCompanyDetails.positionval);
				UserProfileEdit.compto.remove(ViewCompanyDetails.positionval);
				UserProfileEdit.comprole.remove(ViewCompanyDetails.positionval);
				UserProfileEdit.compfunctioning.remove(ViewCompanyDetails.positionval);
				UserProfileEdit.compcandidate_id.remove(ViewCompanyDetails.positionval);
				UserProfileEdit.complast_salary_drawn.remove(ViewCompanyDetails.positionval);
				Toast.makeText(getActivity(), "Success", 1).show();
				if(UserProfileEdit.compid.size()==0)
				{
					Login.company="false";
					Login.project="false";
//					finish();
					startActivity(new Intent(getActivity(),MainActivityfragment.class));
				}
				else
				{
//					finish();
					Fragment fragment = new ViewCompanyDetails();
			        Bundle args = new Bundle();
			        fragment.setArguments(args);
			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

				}
				
				}
				else
				{
					Toast.makeText(getActivity(), "Failed to remove", 1).show();
					delete.setBackgroundResource(R.drawable.upload_image_btn);
				}
			}
			else
			{
				Toast.makeText(getActivity(), "Failed", 1).show();
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
