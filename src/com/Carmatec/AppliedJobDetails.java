package com.Carmatec;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AppliedJobDetails extends Fragment
{
	 public AppliedJobDetails() {
         // Empty constructor required for fragment subclasses
     }

	 TextView jobname,compname,braname,status;
	 TextView written,interviewsummery,communication,aptitude,interview1,interview2,interview3,interview4;
	 Button writtenbutton,communicationbutton,aptitudebutton,interview1button,interview2button,interview3button,interview4button;
@SuppressWarnings("deprecation")
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.demo, container, false);
		MainActivityfragment.actionname.setText("Applied Jobs");
		MainActivityfragment.actionname.setGravity(Gravity.CENTER);
		MainActivityfragment.pagevalue=15;
		this.setRetainInstance(true);
		written=(TextView)v.findViewById(R.id.written);
		interviewsummery=(TextView)v.findViewById(R.id.interviewsummery);
		communication=(TextView)v.findViewById(R.id.communication);
		interview1=(TextView)v.findViewById(R.id.interview1);
		interview2=(TextView)v.findViewById(R.id.interview2);
		interview3=(TextView)v.findViewById(R.id.interview3);
		interview4=(TextView)v.findViewById(R.id.interview4);
		aptitude=(TextView)v.findViewById(R.id.aptitude);
		writtenbutton=(Button)v.findViewById(R.id.writtenbutton);
		communicationbutton=(Button)v.findViewById(R.id.communicationbutton);
		aptitudebutton=(Button)v.findViewById(R.id.aptitudebutton);
		interview1button=(Button)v.findViewById(R.id.interview1button);
		interview2button=(Button)v.findViewById(R.id.interview2button);
		interview3button=(Button)v.findViewById(R.id.interview3button);
		interview4button=(Button)v.findViewById(R.id.interview4button);
		jobname=(TextView)v.findViewById(R.id.jobname);
		compname=(TextView)v.findViewById(R.id.compname);
		braname=(TextView)v.findViewById(R.id.braname);
		status=(TextView)v.findViewById(R.id.status);
		compname.setText(MainActivityfragment.Company.get(ViewAppliedJob.rowval));
		braname.setText(MainActivityfragment.Brand.get(ViewAppliedJob.rowval));
		jobname.setText(MainActivityfragment.Jobtitle.get(ViewAppliedJob.rowval));
		if(MainActivityfragment.aptitudeval.get(ViewAppliedJob.rowval).equals("Error"))
		{
			 written.setVisibility(View.GONE);
			 interviewsummery.setVisibility(View.GONE);
			 communication.setVisibility(View.GONE);
			 aptitude.setVisibility(View.GONE);
			 interview1.setVisibility(View.GONE);
			 interview2.setVisibility(View.GONE);
			
			 writtenbutton.setVisibility(View.GONE);
			 communicationbutton.setVisibility(View.GONE);
			 aptitudebutton.setVisibility(View.GONE);
			 interview1button.setVisibility(View.GONE);
			 interview2button.setVisibility(View.GONE); 
			
			 interview3.setVisibility(View.GONE);
			 interview3button.setVisibility(View.GONE); 
			 
			 interview4.setVisibility(View.GONE);
			 interview4button.setVisibility(View.GONE); 
		}
		else if(!MainActivityfragment.aptitudeval.get(ViewAppliedJob.rowval).equals("Error"))
		{
			
			String s=MainActivityfragment.aptitudeval.get(ViewAppliedJob.rowval).toString().replace("[", "");
			s=s.replace("]", "");
			s=s.trim();
			String[] separated =s.split(",");
			String ss=MainActivityfragment.aptitude.get(ViewAppliedJob.rowval).toString().replace("[", "");
			ss=ss.replace("]", "");
			ss=ss.trim();
			String[] separateds =ss.split(",");		
			try
				{
					
						if(separated.length==1)
						{
							written.setText(separated[0]);
							if(separateds[0].trim().equals("Y"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[0].trim().equals("S"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[0].trim().equals("O"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							 communication.setVisibility(View.GONE);
							 aptitude.setVisibility(View.GONE);
							 interview1.setVisibility(View.GONE);
							 interview2.setVisibility(View.GONE);
							 communicationbutton.setVisibility(View.GONE);
							 aptitudebutton.setVisibility(View.GONE);
							 interview1button.setVisibility(View.GONE);
							 interview2button.setVisibility(View.GONE); 
							 
							 interview3.setVisibility(View.GONE);
							 interview3button.setVisibility(View.GONE); 
							 interview4.setVisibility(View.GONE);
							 interview4button.setVisibility(View.GONE); 

						}
						else if(separated.length==2)
						{
							written.setText(separated[0]);
							if(separateds[0].trim().equals("Y"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[0].trim().equals("S"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[0].trim().equals("O"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							communication.setText(separated[1]);
							if(separateds[1].trim().equals("Y"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[1].trim().equals("S"))
							{
								communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[1].trim().equals("O"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							 aptitude.setVisibility(View.GONE);
							 interview1.setVisibility(View.GONE);
							 interview2.setVisibility(View.GONE);
							 aptitudebutton.setVisibility(View.GONE);
							 interview1button.setVisibility(View.GONE);
							 interview2button.setVisibility(View.GONE); 
							 interview3.setVisibility(View.GONE);
							 interview3button.setVisibility(View.GONE);
							 interview4.setVisibility(View.GONE);
							 interview4button.setVisibility(View.GONE); 
						}
						else if(separated.length==3)
						{
							written.setText(separated[0]);
							if(separateds[0].trim().equals("Y"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[0].trim().equals("O"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							else if(separateds[0].trim().equals("S"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							communication.setText(separated[1]);
							if(separateds[1].trim().equals("Y"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[1].trim().equals("S"))
							{
								communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[1].trim().equals("O"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							aptitude.setText(separated[2]);
							if(separateds[2].trim().equals("Y"))
							{
							aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[2].trim().equals("S"))
							{
								aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[2].trim().equals("O"))
							{
							aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							 interview1.setVisibility(View.GONE);
							 interview2.setVisibility(View.GONE);
							 interview1button.setVisibility(View.GONE);
							 interview2button.setVisibility(View.GONE); 
							 interview3.setVisibility(View.GONE);
							 interview3button.setVisibility(View.GONE); 
							 interview4.setVisibility(View.GONE);
							 interview4button.setVisibility(View.GONE); 
						}
						else if(separated.length==4)
						{
							written.setText(separated[0]);
							if(separateds[0].equals("Y"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[0].trim().equals("S"))
							{
								writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[0].trim().equals("O"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							communication.setText(separated[1]);
							if(separateds[1].trim().equals("Y"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[1].trim().equals("S"))
							{
								communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[1].trim().equals("O"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[2].trim().equals("Y"))
							{
								aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[2].trim().equals("S"))
							{
								aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[2].trim().equals("O"))
							{
							aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[3].trim().equals("Y"))
							{
							interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[3].trim().equals("S"))
							{
								interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[0].trim().equals("O"))
							{
							interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							communication.setText(separated[1]);
							aptitude.setText(separated[2]);
							interview1.setText(separated[3]);
							
							 interview2.setVisibility(View.GONE);
							 interview2button.setVisibility(View.GONE); 
							 interview3.setVisibility(View.GONE);
							 interview3button.setVisibility(View.GONE); 
							 interview4.setVisibility(View.GONE);
							 interview4button.setVisibility(View.GONE); 
						}
						else if(separated.length==5)
						{
							if(separateds[0].trim().equals("Y"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[0].trim().equals("S"))
							{
								writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[0].trim().equals("O"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							communication.setText(separated[1]);
							if(separateds[1].trim().equals("Y"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[1].trim().equals("S"))
							{
								communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[1].trim().equals("O"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[2].trim().equals("Y"))
							{
								aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[2].trim().equals("S"))
							{
								aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[2].trim().equals("O"))
							{
							aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[3].trim().equals("Y"))
							{
							interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[3].trim().equals("S"))
							{
								interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[3].trim().equals("O"))
							{
							interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[4].trim().equals("Y"))
							{
								interview2button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[4].trim().equals("S"))
							{
								interview2button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[4].trim().equals("O"))
							{
							interview2button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							written.setText(separated[0]);
							communication.setText(separated[1]);
							aptitude.setText(separated[2]);
							interview1.setText(separated[3]);
							interview2.setText(separated[4]);
							 interview3.setVisibility(View.GONE);
							 interview3button.setVisibility(View.GONE); 
							 interview4.setVisibility(View.GONE);
							 interview4button.setVisibility(View.GONE); 
	
						}
						/////////
						else if(separated.length==6)
						{
							if(separateds[0].trim().equals("Y"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[0].trim().equals("S"))
							{
								writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[0].trim().equals("O"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							communication.setText(separated[1]);
							if(separateds[1].trim().equals("Y"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[1].trim().equals("S"))
							{
								communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[1].trim().equals("O"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[2].trim().equals("Y"))
							{
								aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[2].trim().equals("S"))
							{
								aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[2].trim().equals("O"))
							{
							aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[3].trim().equals("Y"))
							{
							interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[3].trim().equals("S"))
							{
								interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[3].trim().equals("O"))
							{
							interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[4].trim().equals("Y"))
							{
								interview2button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[4].trim().equals("S"))
							{
								interview2button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[4].trim().equals("O"))
							{
							interview2button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[5].trim().equals("Y"))
							{
								interview3button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[5].trim().equals("S"))
							{
								interview3button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[5].trim().equals("O"))
							{
							interview3button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							written.setText(separated[0]);
							communication.setText(separated[1]);
							aptitude.setText(separated[2]);
							interview1.setText(separated[3]);
							interview2.setText(separated[4]);
							 interview3.setText(separated[5]);
							 interview4.setVisibility(View.GONE);
							 interview4button.setVisibility(View.GONE); 
	
						}
						//*************
						else if(separated.length==7)
						{
							if(separateds[0].trim().equals("Y"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[0].trim().equals("S"))
							{
								writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[0].trim().equals("O"))
							{
							writtenbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							communication.setText(separated[1]);
							if(separateds[1].trim().equals("Y"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[1].trim().equals("S"))
							{
								communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[1].trim().equals("O"))
							{
							communicationbutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[2].trim().equals("Y"))
							{
								aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[2].trim().equals("S"))
							{
								aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[2].trim().equals("O"))
							{
							aptitudebutton.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[3].trim().equals("Y"))
							{
							interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[3].trim().equals("S"))
							{
								interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[3].trim().equals("O"))
							{
							interview1button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[4].trim().equals("Y"))
							{
								interview2button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[4].trim().equals("S"))
							{
								interview2button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[4].trim().equals("O"))
							{
							interview2button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[5].trim().equals("Y"))
							{
								interview3button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[5].trim().equals("S"))
							{
								interview3button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[5].trim().equals("O"))
							{
							interview3button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							if(separateds[6].trim().equals("Y"))
							{
								interview4button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_tick));
							}
							else if(separateds[6].trim().equals("S"))
							{
								interview4button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_scheduled));
							}
							else if(separateds[6].trim().equals("O"))
							{
							interview4button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_hold));
							}
							written.setText(separated[0]);
							communication.setText(separated[1]);
							aptitude.setText(separated[2]);
							interview1.setText(separated[3]);
							interview2.setText(separated[4]);
							 interview3.setText(separated[5]);
							 interview4.setText(separated[6]);
//							 interview4button.setVisibility(View.GONE); 
	
						}
						//*************
						/////////
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		}
		if(MainActivityfragment.JobStatus.get(ViewAppliedJob.rowval).equals("Final Level"))
		{
			status.setTextColor(Color.parseColor("#399647"));
		}
		else if(MainActivityfragment.JobStatus.get(ViewAppliedJob.rowval).equals("Applied"))
		{
			status.setTextColor(Color.parseColor("#F8B15A"));
		}
		else if(MainActivityfragment.JobStatus.get(ViewAppliedJob.rowval).equals("Aptitude Tests Cleared"))
		{
			status.setTextColor(Color.parseColor("#DE5AF8"));
		}
		else if(MainActivityfragment.JobStatus.get(ViewAppliedJob.rowval).equals("Level 2 int"))
		{
			status.setTextColor(Color.parseColor("#4555EA"));
		}
		else if(MainActivityfragment.JobStatus.get(ViewAppliedJob.rowval).equals("Level 1 int"))
		{
			status.setTextColor(Color.parseColor("#64CBF3"));
		}
		else if(MainActivityfragment.JobStatus.get(ViewAppliedJob.rowval).equals("Accepted"))
		{
			status.setTextColor(Color.parseColor("#0B615E")); 
		}
		else if(MainActivityfragment.JobStatus.get(ViewAppliedJob.rowval).equals("Rejected"))
		{
			status.setTextColor(Color.parseColor("#FF0000")); 
		}
		status.setText(MainActivityfragment.JobStatus.get(ViewAppliedJob.rowval));
		return v;

	}
	
}
