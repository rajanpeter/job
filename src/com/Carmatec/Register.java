package com.Carmatec;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.Carmatec.GoogleAnalyticsApp.TrackerName;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Register extends Activity
{
	static String encoded="",image64;
    private Bitmap bitmap;
	 private static final int REQUEST_CODE = 1;	
	  ImageView image;
	String picturePath="";
	private RadioButton radioSexButton; 
	static List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	static ArrayList<String> registerdatas= new ArrayList<String>();
	 EditText fname,lname;
	 RadioGroup sex;
	 String sexgroup="";
	 Button next1,buttonLoadImage;
	 View view;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.register);
	 this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	 getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.actionbar);

	view=	getActionBar().getCustomView();
    TextView actionname=(TextView)view.findViewById(R.id.textView1);
    actionname.setText("Edit Profile");
    if( isNetworkConnected()==true)
    {
 	  
    	try
    	{
    	Tracker t = ((GoogleAnalyticsApp) getApplication()).getTracker(TrackerName.APP_TRACKER);
    	t.setScreenName("Profile Edit");
    	t.send(new HitBuilders.AppViewBuilder().build());
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
	try
	{
	Tracker t = ((GoogleAnalyticsApp) getApplication()).getTracker(TrackerName.APP_TRACKER);
	t.setScreenName("Profile Edit");
	t.send(new HitBuilders.AppViewBuilder().build());
	}
	catch(Exception e)
	{
		System.out.println(e);
		e.printStackTrace();
	}
		

		     
		    image = (ImageView) findViewById(R.id.image);
		    try
		    {
		    	if(!(UserProfileEdit.bitmap==null))
		    	{
		    		image.setImageBitmap(UserProfileEdit.bitmap);
		    	}
		    	else
		    	{
		    		encoded="";
		    	}
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
		 next1 = (Button) findViewById(R.id.next1);
	     buttonLoadImage = (Button) findViewById(R.id.imageselectz);
	     fname = (EditText) findViewById(R.id.fname);
	     lname = (EditText) findViewById(R.id.lname);
	     fname.setText(UserProfileEdit.first_name);
	     lname.setText(UserProfileEdit.last_name);
	     sex=(RadioGroup)findViewById(R.id.radioGroup1);
	     RadioButton rb1 = (RadioButton) findViewById(R.id.radiomale);
	     RadioButton rb2 = (RadioButton) findViewById(R.id.radiofemale);
	     System.out.println("GENDER....."+UserProfileEdit.gender);
	     try
	     {
	     if(UserProfileEdit.gender.equals("M"))
	     	{
	    	 sexgroup="Male";
	    	 	rb1.setChecked(true);
	    	 	rb2.setChecked(false);
	     	}
	     else if(UserProfileEdit.gender.equals("F"))
	     	{
	    	 sexgroup="Female";
	    	 rb2.setChecked(true);
	    	 rb1.setChecked(false);
	     	}
	     }
	     catch(Exception e)
	     {
	    	
	    	 rb1.setChecked(true);
	    	 	rb2.setChecked(false); 
	     }
	     sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	
	    	 @Override
	    	 public void onCheckedChanged(RadioGroup group, int checkedId) {
	    		 // TODO Auto-generated method stub
	    		 int selectedId = sex.getCheckedRadioButtonId();
	    		 radioSexButton = (RadioButton) findViewById(selectedId);
	    		 sexgroup=radioSexButton.getText().toString();
	    	 }
	     });

	  
	 next1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			if(fname.getText().toString().equals(""))
			{
				fname.setError("Mandatory");
			}
			else if(!fname.getText().toString().matches("[a-zA-Z]+"))
			{
				fname.setError("Invalid Character");
			}
			else if(lname.getText().toString().equals(""))
			{
				lname.setError("Mandatory");
			}
			else if(!lname.getText().toString().matches("[a-zA-Z]+"))
			{
				lname.setError("Invalid Character");
			}
			else
			{
				nameValuePairs.clear();
				 next1.setBackgroundResource(R.drawable.violetsmallhigh);
				if(!(UserProfileEdit.bitmap==null))
				{
				try
	    		{
	    	    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	    	    UserProfileEdit.bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
	    	    byte[] b = baos.toByteArray();
	    	   encoded = Base64.encodeToString(b,Base64.DEFAULT);
	    	   Register.nameValuePairs.add(new BasicNameValuePair("img_name", Login.user_id+".jpg"));
				Register.nameValuePairs.add(new BasicNameValuePair("base_64", Register.encoded));
	    		
	        	}
	    		catch(Exception ee)
	    		{
	    			ee.printStackTrace();
	    			encoded="";
	    		}
				}
//				}
				
		        try {
		        	registerdatas.clear();
		        	registerdatas.add(fname.getText().toString());
			        registerdatas.add(lname.getText().toString());
				    nameValuePairs.add(new BasicNameValuePair("action","edit"));
			        nameValuePairs.add(new BasicNameValuePair("candidate_id", UserProfileEdit.id));
			        nameValuePairs.add(new BasicNameValuePair("first_name", fname.getText().toString()));
			        nameValuePairs.add(new BasicNameValuePair("last_name", lname.getText().toString()));
			        if(sexgroup.equalsIgnoreCase("Male"))
		        	{
			        	 nameValuePairs.add(new BasicNameValuePair("gender", "M"));
			        	 registerdatas.add("M"); 
		        	}
		        	else if(sexgroup.equalsIgnoreCase("Female"))
		        	{
		        		 nameValuePairs.add(new BasicNameValuePair("gender", "F"));
		        		 registerdatas.add("F");
		        	}
		        	startActivity(new Intent(getApplicationContext(),AddrsRegister.class));
					} catch (Exception e) {
						e.printStackTrace();
						 next1.setBackgroundResource(R.drawable.next_btn);
					}
				}
		
			}
	 });
	 buttonLoadImage.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			  Intent intent = new Intent();
		        intent.setType("image/*");
		        intent.setAction(Intent.ACTION_GET_CONTENT);
		        intent.addCategory(Intent.CATEGORY_OPENABLE);
		        startActivityForResult(intent, REQUEST_CODE);

		}
	});
}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
        try {
            if (bitmap != null) {
                bitmap.recycle();
            }
            InputStream stream = getContentResolver().openInputStream(
                    data.getData());
            bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
            image.setImageBitmap(bitmap);
            UserProfileEdit.bitmap=bitmap;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) { 
            e.printStackTrace();
        }
    super.onActivityResult(requestCode, resultCode, data);
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
@Override
public void onBackPressed() {
	super.onBackPressed();
	finish();
}
@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
//	GoogleAnalytics.getInstance(this).reportActivityStart(this);
	GoogleAnalytics.getInstance(Register.this).reportActivityStart(this);
}


@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
//	GoogleAnalytics.getInstance(this).reportActivityStop(this);
	GoogleAnalytics.getInstance(Register.this).reportActivityStop(this);
}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	 next1.setBackgroundResource(R.drawable.next_btn);	
	 AddrsRegister.s2="";
		Register.registerdatas.clear();
		Register.nameValuePairs.clear();
}

}
