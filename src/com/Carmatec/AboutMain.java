package com.Carmatec;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class AboutMain extends TabActivity {
View view;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutmain);
try
{  
		Resources ressources = getResources();     
		TabHost tabHost = getTabHost(); 
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.actionbar);

	view=	getActionBar().getCustomView(); 
//		final ActionBar ab = getActionBar();    
//	    ab.setDisplayShowHomeEnabled(false); 
//	    ab.setDisplayShowTitleEnabled(false);     
//	    final LayoutInflater inflater = (LayoutInflater)getSystemService("layout_inflater");
//	    View view = inflater.inflate(R.layout.actio_bar,null); 
//	    ab.setCustomView(view);
//	    ab.setDisplayShowCustomEnabled(true);
	    TextView actionname=(TextView)view.findViewById(R.id.textView1);
	    actionname.setText("About Us");
		// Android tab
		Intent intentAndroid = new Intent().setClass(this, WorkEnvironment.class);
		TabSpec tabSpecAndroid = tabHost
			.newTabSpec("Work Environment")
			.setIndicator("", ressources.getDrawable(R.drawable.work_environment))
			.setContent(intentAndroid);

		// Apple tab
		Intent intentApple = new Intent().setClass(this, CultureValue.class);
		TabSpec tabSpecApple = tabHost
			.newTabSpec("Culture & Values")
			.setIndicator("", ressources.getDrawable(R.drawable.culture_values))
			.setContent(intentApple);
		 
		// Windows tab
		Intent intentWindows = new Intent().setClass(this, Learning.class);
		TabSpec tabSpecWindows = tabHost
			.newTabSpec("Learning")
			.setIndicator("", ressources.getDrawable(R.drawable.learning))
			.setContent(intentWindows);
		
		// Blackberry tab
//		Intent intentBerry = new Intent().setClass(this, BlackBerryActivity.class);
//		TabSpec tabSpecBerry = tabHost
//			.newTabSpec("Berry")
//			.setIndicator("", ressources.getDrawable(R.drawable.icon_blackberry_config))
//			.setContent(intentBerry);
	
		// add all tabs 
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		tabHost.addTab(tabSpecWindows);
//		tabHost.addTab(tabSpecBerry);
		
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(2);
}
catch(Exception e)
{
	Toast.makeText(getApplicationContext(), "No Internet Connection", 1).show();
}
	}

}