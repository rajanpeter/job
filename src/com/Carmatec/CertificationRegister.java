package com.Carmatec;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import android.app.DatePickerDialog;
import android.app.Fragment;
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

public class CertificationRegister extends Fragment
{
	Button date,dates,nextaddrs ,   delete;
	 private Calendar cal;
	 private int day;
	 private int month;
	 private int year;
	 private ProgressDialog pDialog;
	 String s;
	 JSONObject jobj;
	 int i;
	 int date1,month1,year1, date2,month2,year2;
	 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	EditText Certification,Issuingorganization,Issueddate,tilldate;
	 public CertificationRegister() {
	    }
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
				View v = inflater.inflate(R.layout.certificationregister, container, false);
				MainActivityfragment.actionname.setText("Awarded");
				MainActivityfragment.actionname.setGravity(Gravity.CENTER);
				this. setRetainInstance(true);
				MainActivityfragment.pagevalue=17;
				delete=(Button)v.findViewById(R.id.delete);
				cal = Calendar.getInstance();
				day = cal.get(Calendar.DAY_OF_MONTH);
				month = cal.get(Calendar.MONTH);
				year = cal.get(Calendar.YEAR);
				Certification=(EditText)v.findViewById(R.id.Certification);
				Issuingorganization=(EditText)v.findViewById(R.id.Issuingorganization);
				Issueddate=(EditText)v.findViewById(R.id.Issueddate);
				tilldate=(EditText)v.findViewById(R.id.tilldate);
				if(ViewCertificationDetails.certval==1)
				{
					 delete.setVisibility(View.VISIBLE);
					 Certification.setText(UserProfileEdit.certifications.get(ViewCertificationDetails.positionval));
					 Issuingorganization.setText(UserProfileEdit.issued_by.get(ViewCertificationDetails.positionval));
					 Issueddate.setText(UserProfileEdit.issued_date.get(ViewCertificationDetails.positionval));
					 tilldate.setText(UserProfileEdit.issued_upto.get(ViewCertificationDetails.positionval));
				}
				else
				{
					 delete.setVisibility(View.GONE);
					 Certification.setText("");
					 Issuingorganization.setText("");
					 Issueddate.setText("");
					 tilldate.setText("");
				
				}
			delete.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						    delete.setBackgroundResource(R.drawable.whitehigh);
							ViewCertificationDetails.certval=2;
							nameValuePairs.clear();
							nameValuePairs.add(new BasicNameValuePair("action","delete"));
							nameValuePairs.add(new BasicNameValuePair("certificate_id",UserProfileEdit.certiid.get(ViewCertificationDetails.positionval)));
							nameValuePairs.add(new BasicNameValuePair("candidate_id",Login.candidate_id));
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
				 date=(Button)v.findViewById(R.id.date);
				 date.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
						i=1;
						DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener1, 
			                    year, month,day);
						dialog.show();
					}
				});
				 dates=(Button)v.findViewById(R.id.dates);
				 dates.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
						i=2;
						DatePickerDialog dialog = new DatePickerDialog(getActivity(), datePickerListener1, 
								 year, month,day);
						dialog.show();
					}
				});
			 nextaddrs= (Button)v.findViewById(R.id.nextcert);
			 nextaddrs.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) { 
					// TODO Auto-generated method stub
					
					if(Certification.getText().toString().equals(""))
					{
						Certification.setError("Mandatory");
					}
					else if(!Certification.getText().toString().matches("[a-z A-Z]+"))
					{
						Certification.setError("Invalid Character");
					}
					else if(Issuingorganization.getText().toString().equals(""))
					{
						Issuingorganization.setError("Mandatory");
					}
					else if(!Issuingorganization.getText().toString().matches("[a-z A-Z]+"))
					{
						Issuingorganization.setError("Invalid Character");
					}
					else if(Issueddate.getText().toString().equals(""))
					{
						Issueddate.setError("Mandatory");
					}
					else if(tilldate.getText().toString().equals(""))
					{
						tilldate.setError("Mandatory");
					}
					else if(Issueddate.getText().toString().equals(tilldate.getText().toString()))
					{
						tilldate.setError("Same date");
						Toast.makeText(getActivity(), "Same date", 1).show();
					}
					else if(year1>year2)
					{
						Issueddate.setError("Please check selected date");
						Toast.makeText(getActivity(), "Please check date", 1).show();
					}
					else if((year1==year2)&&(month1>month2))
					{
						Issueddate.setError("Please check selected date");
						Toast.makeText(getActivity(), "Please check date", 1).show();
					}
					else if((year1==year2)&&(month1==month2)&&(date1>date2))
					{
						Issueddate.setError("Please check selected date");
						Toast.makeText(getActivity(), "Please check date", 1).show();
					}
					else
					{
						nextaddrs.setBackgroundResource(R.drawable.violetsmallhigh);
					try
					{
						nextaddrs.setBackgroundResource(R.drawable.violetsmallhigh);
						nameValuePairs.clear();
				    	if(ViewCertificationDetails.certval==1)
						{
				    		 nameValuePairs.add(new BasicNameValuePair("action", "edit"));
				    		 nameValuePairs.add(new BasicNameValuePair("certificate_id", UserProfileEdit.certiid.get(ViewCertificationDetails.positionval)));
				    		 nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
				    		 nameValuePairs.add(new BasicNameValuePair("certificate_name", Certification.getText().toString()));
				    		 nameValuePairs.add(new BasicNameValuePair("issuing_organization", Issuingorganization.getText().toString()));
				    		 nameValuePairs.add(new BasicNameValuePair("issue_date", Issueddate.getText().toString()));	
				    		 nameValuePairs.add(new BasicNameValuePair("issued_upto", tilldate.getText().toString()));	
				    		 
						}
						else if(ViewCertificationDetails.certval==0)
						{
							 nameValuePairs.add(new BasicNameValuePair("action", "add"));
							 nameValuePairs.add(new BasicNameValuePair("candidate_id", Login.candidate_id));
							 nameValuePairs.add(new BasicNameValuePair("certificate_name", Certification.getText().toString()));
							 nameValuePairs.add(new BasicNameValuePair("issuing_organization", Issuingorganization.getText().toString()));
							 nameValuePairs.add(new BasicNameValuePair("issue_date", Issueddate.getText().toString()));
							 nameValuePairs.add(new BasicNameValuePair("issued_upto", tilldate.getText().toString()));	
						}
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
	        	 jobj=JsonCall.postData(nameValuePairs, ImageSliderCarma.urlvalue+"users/certification_api.json?");
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
	//			JSONObject  jobj= new JSONObject(s);
				
				
					Login.certifications="true";
					
					if(ViewCertificationDetails.certval==1)
					{
						JSONObject jobj1=jobj.getJSONObject("result");
						String stringval=jobj1.getString("success");
						if(stringval.equals("Record has been updated"))
						{
							UserProfileEdit.issued_date.remove(ViewCertificationDetails.positionval);
							UserProfileEdit.issued_upto.remove(ViewCertificationDetails.positionval);
							UserProfileEdit.issued_by.remove(ViewCertificationDetails.positionval);
							UserProfileEdit.certifications.remove(ViewCertificationDetails.positionval);
							
							UserProfileEdit.issued_date.add(ViewCertificationDetails.positionval,Issueddate.getText().toString());
							UserProfileEdit.issued_upto.add(ViewCertificationDetails.positionval,tilldate.getText().toString());
							UserProfileEdit.issued_by.add(ViewCertificationDetails.positionval,Issuingorganization.getText().toString());
							UserProfileEdit.certifications.add(ViewCertificationDetails.positionval,Certification.getText().toString());
							ViewCertificationDetails.certval=0;
							Toast.makeText(getActivity(), "Edit Success", 1).show();
							Fragment fragment = new ViewCertificationDetails();
					        Bundle args = new Bundle();
					        fragment.setArguments(args);
					        android.app.FragmentManager fragmentManager = getFragmentManager();
					        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
					
							}
							else
							{
								Toast.makeText(getActivity(), "Edit Failed", 1).show();
								nextaddrs.setBackgroundResource(R.drawable.next_btn);
							}
						}
						else if(ViewCertificationDetails.certval==2)
						{
		
						
							if(jobj.getString("result").equals("record has been removed"))
							{
							UserProfileEdit.issued_date.remove(ViewCertificationDetails.positionval);
							UserProfileEdit.issued_upto.remove(ViewCertificationDetails.positionval);
							UserProfileEdit.certiid.remove(ViewCertificationDetails.positionval);
							UserProfileEdit.certifications.remove(ViewCertificationDetails.positionval);
							UserProfileEdit.candidate_id.remove(ViewCertificationDetails.positionval);
							UserProfileEdit.issued_by.remove(ViewCertificationDetails.positionval);
						
							Toast.makeText(getActivity(), "Item Removed", 1).show();
							if(UserProfileEdit.certifications.size()==0)
							{
								Login.certifications="false";
								startActivity(new Intent(getActivity(),MainActivityfragment.class));
							}
							else
							{
								Login.certifications="true";
								Fragment fragment = new ViewCertificationDetails();
						        Bundle args = new Bundle();
						        fragment.setArguments(args);
						        android.app.FragmentManager fragmentManager = getFragmentManager();
						        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
							
							}
							}
							else
							{
								Toast.makeText(getActivity(), "Failed to remove", 1).show();
								delete.setBackgroundResource(R.drawable.upload_image_btn);
						}
						}
						else if(ViewCertificationDetails.certval==0)
						{
						JSONObject jobj1=jobj.getJSONObject("result");
						String stringval=jobj1.getString("success");
						if(stringval.equals("Record has been saved"))
						{
				
						JSONObject jobj3=jobj1.getJSONObject("Certification");
						UserProfileEdit.certiid.add(jobj3.getString("id"));
						UserProfileEdit.certifications.add(jobj3.getString("certification"));
						UserProfileEdit.issued_by.add(jobj3.getString("issued_by"));
						UserProfileEdit.issued_date.add(jobj3.getString("issued_date"));
						UserProfileEdit.issued_upto.add(jobj3.getString("issued_upto"));
						UserProfileEdit.candidate_id.add(jobj3.getString("candidate_id"));
						Login.certifications="true";
						Toast.makeText(getActivity(), " Success", 1).show();
						Fragment fragment = new ViewCertificationDetails();
				        Bundle args = new Bundle();
				        fragment.setArguments(args);
				        android.app.FragmentManager fragmentManager = getFragmentManager();
				        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
					
						}
						else
						{
							Toast.makeText(getActivity(), " Failed", 1).show();
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

	private DatePickerDialog.OnDateSetListener datePickerListener1 
	= new DatePickerDialog.OnDateSetListener() {
	public void onDateSet(DatePicker view, int selectedYear,
	int selectedMonth, int selectedDay) {
		
		if(i==1)
		{
			date1=selectedDay;
			month1=selectedMonth+1;
			year1=selectedYear;
			Issueddate.setText(selectedYear+"-"+(selectedMonth+1)+"-"+selectedDay);
		}
		else if(i==2)
		{
			date2=selectedDay;
			month2=selectedMonth+1;
			year2=selectedYear;
			tilldate.setText(selectedYear+"-"+(selectedMonth+1)+"-"+selectedDay);
		}
			i=0;
	}
	};
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
