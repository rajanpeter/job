package com.Carmatec;
//
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//
public class UserProfileEdit extends Fragment
{
	 public UserProfileEdit() {
         // Empty constructor required for fragment subclasses
     }
	static	Bitmap bitmap,oldbitmap;
	String imagedupl;
	 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		static ArrayList<String> companyid= new ArrayList<String>();
	 static ArrayList<String> companyname= new ArrayList<String>();
	 private ProgressDialog pDialog;
	 static ArrayList<String> issued_date= new ArrayList<String>();
	 static ArrayList<String> certiid= new ArrayList<String>();
	 static ArrayList<String> certifications= new ArrayList<String>();
	 static ArrayList<String> candidate_id= new ArrayList<String>();
	 static ArrayList<String> issued_by= new ArrayList<String>();
	 static ArrayList<String> issued_upto= new ArrayList<String>();
	 
	 static ArrayList<String> eduid= new ArrayList<String>();
	 static ArrayList<String> eduinstitute= new ArrayList<String>();
	 static ArrayList<String> eduscore= new ArrayList<String>();
	 static ArrayList<String> eduyear= new ArrayList<String>();
	 static ArrayList<String> educandidate_id= new ArrayList<String>();
	 static ArrayList<String> eduspecialization= new ArrayList<String>();
	 static ArrayList<String> edugraduation= new ArrayList<String>();
	 
	 static ArrayList<String> compto= new ArrayList<String>();
	 static ArrayList<String> compid= new ArrayList<String>();
	 static ArrayList<String> complast_salary_drawn= new ArrayList<String>();
	 static ArrayList<String> compcompany_name= new ArrayList<String>();
	 static ArrayList<String> compfunctioning= new ArrayList<String>();
	 static ArrayList<String> comprole= new ArrayList<String>();
	 static ArrayList<String> compfrom= new ArrayList<String>();
	 static ArrayList<String> compcandidate_id= new ArrayList<String>();
	 
	 static ArrayList<String> proid= new ArrayList<String>();
	 static ArrayList<String> proend_date= new ArrayList<String>();
	 static ArrayList<String> procandidate_previous_detail_id= new ArrayList<String>();
	 static ArrayList<String> proproject_title= new ArrayList<String>();
	 static ArrayList<String> prodescription= new ArrayList<String>();
	 static ArrayList<String> prostart_date= new ArrayList<String>();
	 static ArrayList<String> prosize= new ArrayList<String>();
	 static ArrayList<String> prorole= new ArrayList<String>();
	 String s;
		static String image,zip,reason_for_change,resume_base64,current_role,alt_contact,alt_email,
		avatar,contact,city,relevant_exp,id,first_name,address_line1,address_line2,email,
		current_ctc,last_name,updated_on,active,expected_ctc,gender,experience,image_base64,
		notice_period,resume_id;
	 int userprofileval=0;
	 JSONObject jobj; 
	Button personalprofile,Educational,certification,companyprofile,projectprofile;
//	static int expfresh=0;
	TextView textView6,textView5;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.copydemo, container, false);
		MainActivityfragment.actionname.setText("My Profile");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		MainActivityfragment.pagevalue=8;
		 this. setRetainInstance(true);
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	     .detectAll()
	     .penaltyLog()
	     .build();
	 StrictMode.setThreadPolicy(policy);

		personalprofile=(Button)v.findViewById(R.id.personalprofile);
		Educational=(Button)v.findViewById(R.id.Educational);
		certification=(Button)v.findViewById(R.id.certification);
		companyprofile=(Button)v.findViewById(R.id.companyprofile);
		projectprofile=(Button)v.findViewById(R.id.projectprofile);
		 textView5=(TextView)v.findViewById(R.id.textView5);
		 textView6=(TextView)v.findViewById(R.id.textView6);

			if(Login.company.equals("false"))
			{
				projectprofile.setVisibility(View.GONE);
				textView6.setVisibility(View.GONE);
			}
			else
			{
				projectprofile.setVisibility(View.VISIBLE);
				textView6.setVisibility(View.VISIBLE);
			}
//		}
		
		personalprofile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				personalprofile.setBackgroundResource(R.drawable.profilehigh);
				try
				{
					userprofileval=1;
					nameValuePairs.clear();
					s=ImageSliderCarma.urlvalue+"users/candidate_edit_api.json?";
					nameValuePairs.add(new BasicNameValuePair("action", "view"));
	 	        	nameValuePairs.add(new BasicNameValuePair("candidate_id",Login.candidate_id));
	 	        	if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    		personalprofile.setBackgroundResource(R.drawable.fresher);
				    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
				       }
				}
				catch(Exception eex)
				{
					personalprofile.setBackgroundResource(R.drawable.fresher);
					
					Toast.makeText(getActivity(), "Error", 1).show();
				}
				}
			});
		Educational.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Educational.setBackgroundResource(R.drawable.educationhigh);
				try
				{
					userprofileval=3;
					nameValuePairs.clear();
					s=ImageSliderCarma.urlvalue+"users/education_api.json?";
					nameValuePairs.add(new BasicNameValuePair("action", "view"));
	 	        	nameValuePairs.add(new BasicNameValuePair("candidate_id",Login.candidate_id));
	 	        	if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   Educational.setBackgroundResource(R.drawable.profile2);
				    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
				       }
				}
				catch(Exception eex)
				{
					Educational.setBackgroundResource(R.drawable.profile2);
					Toast.makeText(getActivity(), "Error", 1).show();
				}
				
				
		}
		});
		certification.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			certification.setBackgroundResource(R.drawable.certificationhigh);
			try
			{
				userprofileval=2;
				nameValuePairs.clear();
				s=ImageSliderCarma.urlvalue+"users/certification_api.json?";
				nameValuePairs.add(new BasicNameValuePair("action", "view"));
 	        	nameValuePairs.add(new BasicNameValuePair("candidate_id",Login.candidate_id));
 	        	if( isNetworkConnected()==true)
			       {
			    	  
			    	   new GetContacts().execute();
			       }
			       else
			       {
			    		certification.setBackgroundResource(R.drawable.profile5);
			    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
			       }
			}
			catch(Exception eex)
			{
				certification.setBackgroundResource(R.drawable.profile5);
				
				Toast.makeText(getActivity(), "Error", 1).show();
			}
		}
	});
		companyprofile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				companyprofile.setBackgroundResource(R.drawable.experiencehigh);
				try
				{
					ViewCompanyDetails.addval=1;
					userprofileval=4;
					nameValuePairs.clear();
					s=ImageSliderCarma.urlvalue+"users/experience_api.json?";
					nameValuePairs.add(new BasicNameValuePair("action", "view"));
	 	        	nameValuePairs.add(new BasicNameValuePair("candidate_id",Login.candidate_id));
	 	        	if( isNetworkConnected()==true)
				       {
				    	  
				    	   new GetContacts().execute();
				       }
				       else
				       {
				    	   companyprofile.setBackgroundResource(R.drawable.profile3);
				    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
				       }
				}
				catch(Exception eex)
				{
				
					Toast.makeText(getActivity(), "Error", 1).show();
				}		
				
			}
		});
		projectprofile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				projectprofile.setBackgroundResource(R.drawable.projecthigh);
				userprofileval=5;
				nameValuePairs.clear();
				s=ImageSliderCarma.urlvalue+"users/project_api.json?";
				nameValuePairs.add(new BasicNameValuePair("action", "view"));
 	        	nameValuePairs.add(new BasicNameValuePair("candidate_id",Login.candidate_id));
 	        	if( isNetworkConnected()==true)
			       {
			    	  
			    	   new GetContacts().execute();
			       }
			       else
			       {
			    	   projectprofile.setBackgroundResource(R.drawable.profile4);
			    	   Toast.makeText(getActivity(), "Please check your internet connection", 1).show();
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
		        	 	jobj=JsonCall.postData(nameValuePairs, s);
		        	 if(userprofileval==1)
		        	 {
		        			JSONObject candidatedetails=jobj.getJSONObject("candidatedetails");
							JSONObject Candidate=candidatedetails.getJSONObject("Candidate");
							reason_for_change=Candidate.getString("reason_for_change");
							zip=Candidate.getString("zip");
							current_role=Candidate.getString("current_role");
							alt_contact=Candidate.getString("alt_contact");
							alt_email=Candidate.getString("alt_email");
							contact=Candidate.getString("contact");
							city=Candidate.getString("city");
							relevant_exp=Candidate.getString("relevant_exp");
							id=Candidate.getString("id");
							first_name=Candidate.getString("first_name");
							address_line1=Candidate.getString("address_line1");
							address_line2=Candidate.getString("address_line2");
							email=Candidate.getString("email");
							current_ctc=Candidate.getString("current_ctc");
							last_name=Candidate.getString("last_name");
							active=Candidate.getString("active");
							expected_ctc=Candidate.getString("expected_ctc");
							gender=Candidate.getString("gender");
							experience=Candidate.getString("experience");
							 image=Candidate.getString("profile_pic_url");
							 image = image.replaceAll("\\/", "/");
							 notice_period=Candidate.getString("notice_period");
							 if(image.equals(imagedupl))
							 {
								 imagedupl=image; 
							 }
							 else
							 {
								 imagedupl=image;
							
							bitmap=	downloadBitmap("http://"+UserProfileEdit.image);
							
							
							 }
		        	 }
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
						
	        	 if(userprofileval==1)
					{

	        		 userprofileval=0;
					 startActivity(new Intent(getActivity(),Register.class));
					}
					else if(userprofileval==2)
					{
						try
						{
						issued_date.clear();
						certiid.clear();
						certifications.clear();
						candidate_id.clear();
						issued_by.clear();
						issued_upto.clear();
						JSONArray results=jobj.getJSONArray("result");
						for(int i=0;i<results.length();i++)
						{
							JSONObject Certification=results.getJSONObject(i);
							JSONObject cerjobj=Certification.getJSONObject("Certification");
							issued_date.add(cerjobj.getString("issued_date"));
							certiid.add(cerjobj.getString("id"));
							certifications.add(cerjobj.getString("certification"));
							candidate_id.add(cerjobj.getString("candidate_id"));
							issued_by.add(cerjobj.getString("issued_by"));
							issued_upto.add(cerjobj.getString("issued_upto"));
						}
						if(certiid.size()==0)
						{
							Login.certifications="false";
						}
						if(Login.certifications.equals("true"))
						{
							Fragment fragment = new ViewCertificationDetails();
					        Bundle args = new Bundle();
					        fragment.setArguments(args);
					        FragmentManager fragmentManager = getFragmentManager();
					        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			
			
						}
						else
						{
							ViewCertificationDetails.certval=0;
							Fragment fragment = new CertificationRegister();
					        Bundle args = new Bundle();
					        fragment.setArguments(args);
					        FragmentManager fragmentManager = getFragmentManager();
					        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			
						}
						userprofileval=0;
						}
						catch(Exception e)
						{
							userprofileval=0;
							certification.setBackgroundResource(R.drawable.profile5);
						}
					}
					else if(userprofileval==3)
					{
						try
						{
						JSONArray results=jobj.getJSONArray("result");
						 eduid.clear();
						 eduinstitute.clear();
						 eduscore.clear();
						 eduyear.clear();
						 educandidate_id.clear();
						 eduspecialization.clear();
						 edugraduation.clear();
						for(int i=0;i<results.length();i++)
						{
							JSONObject education=results.getJSONObject(i);
							JSONObject CandidateEducationDetail=education.getJSONObject("CandidateEducationDetail");
							eduid.add(CandidateEducationDetail.getString("id"));
							eduinstitute.add(CandidateEducationDetail.getString("institute"));
							eduscore.add(CandidateEducationDetail.getString("score"));
							eduyear.add(CandidateEducationDetail.getString("year"));
							educandidate_id.add(CandidateEducationDetail.getString("candidate_id"));
							eduspecialization.add(CandidateEducationDetail.getString("specialization"));
							edugraduation.add(CandidateEducationDetail.getString("graduation"));
						}
						if(eduid.size()==0)
						{
							Login.education="false";
						}
						if(Login.education.equals("true"))
						{
							Fragment fragment = new ViewEducationDetails();
					        Bundle args = new Bundle();
					        fragment.setArguments(args);
					        FragmentManager fragmentManager = getFragmentManager();
					        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


						}
						else
						{
							Fragment fragment = new EductnRegister();
					        Bundle args = new Bundle();
					        fragment.setArguments(args);
					        FragmentManager fragmentManager = getFragmentManager();
					        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

						}
						userprofileval=0;
						}
						catch(Exception e)
						{
							Educational.setBackgroundResource(R.drawable.profile2);
							userprofileval=0;
						}
					}
					else if(userprofileval==4)
					{
						try
						{
						JSONArray results=jobj.getJSONArray("result");						
						compto.clear();
						compid.clear();
						complast_salary_drawn.clear();
						compcompany_name.clear();
						compfunctioning.clear();
						comprole.clear();
						compfrom.clear();
						compcandidate_id.clear();
						for(int i=0;i<results.length();i++)
						{
							JSONObject education=results.getJSONObject(i);
							JSONObject CandidateEducationDetail=education.getJSONObject("CandidatePreviousDetail");

							compto.add(CandidateEducationDetail.getString("to"));
							compid.add(CandidateEducationDetail.getString("id"));
							complast_salary_drawn.add(CandidateEducationDetail.getString("last_salary_drawn"));
							compcompany_name.add(CandidateEducationDetail.getString("company_name"));
							compfunctioning.add(CandidateEducationDetail.getString("functioning"));
							comprole.add(CandidateEducationDetail.getString("role"));
							compfrom.add(CandidateEducationDetail.getString("from"));
							compcandidate_id.add(CandidateEducationDetail.getString("candidate_id"));
						}
						
						if(compid.size()>0)
						{
							Login.company="true";
						}
						else
						{
							Login.company="false";
						}
						if(Login.company.equals("true"))
						{
							
							Fragment fragment = new ViewCompanyDetails();
					        Bundle args = new Bundle();
					        fragment.setArguments(args);
					        FragmentManager fragmentManager = getFragmentManager();
					        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
						}
						else
						{
							ViewCompanyDetails.addval=2;
							Fragment fragment = new CompanyRegister();
					        Bundle args = new Bundle();
					        fragment.setArguments(args);
					        FragmentManager fragmentManager = getFragmentManager();
					        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

						}
						}
						catch(Exception e)
						{
							companyprofile.setBackgroundResource(R.drawable.profile3);
						}
					}
					else if(userprofileval==5)
					{ 
						try
						{
						proid.clear();
						proend_date.clear();
						procandidate_previous_detail_id.clear();
						proproject_title.clear();
						prodescription.clear();
						prostart_date.clear();
						prosize.clear();
						prorole.clear();
						companyid.clear();
						companyname.clear();
						JSONObject resultz=jobj.getJSONObject("result");
						JSONArray all_projects=resultz.getJSONArray("all_projects");
						JSONObject all_companies=resultz.getJSONObject("all_companies");
						for(int i=0;i<all_projects.length();i++)
						{
						JSONObject CandidatePreviousProjectz=all_projects.getJSONObject(i);
						JSONObject CandidatePreviousProject=CandidatePreviousProjectz.getJSONObject("CandidatePreviousProject");
						prorole.add(CandidatePreviousProject.getString("role"));
						proid.add(CandidatePreviousProject.getString("id"));
						proend_date.add(CandidatePreviousProject.getString("end_date"));
						procandidate_previous_detail_id.add(CandidatePreviousProject.getString("candidate_previous_detail_id"));
						proproject_title.add(CandidatePreviousProject.getString("project_title"));
						prodescription.add(CandidatePreviousProject.getString("description"));
						prostart_date.add(CandidatePreviousProject.getString("start_date"));
						prosize.add(CandidatePreviousProject.getString("size"));
						}
						  Iterator<?> keys = all_companies.keys();
				           while( keys.hasNext() ){
				           String key = (String)keys.next();
			               if( all_companies.get(key) instanceof JSONObject ){
			           	   JSONObject c = (JSONObject) all_companies.get(key);
			           	companyid.add(c.getString("id"));
						companyname.add(c.getString("company_name"));
			               }
				           }

						if(companyid.size()<=0)
						{
							Toast.makeText(getActivity(), "Add Company first", 1).show();
						}
						else
						{
							if(proid.size()>0)
							{
							Login.project="true";
							}
							else
							{
								Login.project="false";	
							}
						if(Login.project.equals("true"))
						{
							Fragment fragment = new ViewProjectDetails();
					        Bundle args = new Bundle();
					        fragment.setArguments(args);
					        FragmentManager fragmentManager = getFragmentManager();
					        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
							}
						else 
						{
							ViewProjectDetails.proval=0;
							Fragment fragment = new ProjectRegister();
					        Bundle args = new Bundle();
					        fragment.setArguments(args);
					        FragmentManager fragmentManager = getFragmentManager();
					        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
}
						}
						}
			        	 catch (Exception e) {
							// TODO: handle exception
			        		 ViewProjectDetails.proval=0;
			        		 Fragment fragment = new ProjectRegister();
						        Bundle args = new Bundle();
						        fragment.setArguments(args);
						        FragmentManager fragmentManager = getFragmentManager();
						        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	
						}
					}
					
					}
			catch(Exception ee)
			{
				ee.printStackTrace();
				Toast.makeText(getActivity(), "Invalid", 1).show();
			}
			}
	 }
	 private Bitmap downloadBitmap(String url) {
			final DefaultHttpClient client = new DefaultHttpClient();
			final HttpGet getRequest = new HttpGet(url);
			try {

				HttpResponse response = client.execute(getRequest);
				final int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != HttpStatus.SC_OK) {
					Log.w("ImageDownloader", "Error " + statusCode + 
							" while retrieving bitmap from " + url);
					return null;
				}

				final HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream inputStream = null;
					try {
						inputStream = entity.getContent();
						final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
						return bitmap;
					} finally {
						if (inputStream != null) {
							inputStream.close();
						}
						entity.consumeContent();
					}
				}
			} catch (Exception e) {
				getRequest.abort();
				Log.e("ImageDownloader", "Something went wrong while" +
						" retrieving bitmap from " + url + e.toString());
			} 

			return null;
		}
@Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	personalprofile.setBackgroundResource(R.drawable.fresher);
	companyprofile.setBackgroundResource(R.drawable.profile3);
	Educational.setBackgroundResource(R.drawable.profile2);
	certification.setBackgroundResource(R.drawable.profile5);
	projectprofile.setBackgroundResource(R.drawable.profile4);
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
