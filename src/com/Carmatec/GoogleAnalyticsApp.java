package com.Carmatec;

import java.util.HashMap;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class GoogleAnalyticsApp extends Application {

	// change the following line 
	private static final String PROPERTY_ID = "UA-37781384-2";

	public static int GENERAL_TRACKER = 0;

	public enum TrackerName { 
		APP_TRACKER, GLOBAL_TRACKER, ECOMMERCE_TRACKER,
	}

	public HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

	public GoogleAnalyticsApp() {
		super();
	}

	public synchronized Tracker getTracker(TrackerName appTracker) {
		if (!mTrackers.containsKey(appTracker)) {
			try
			{
			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			Tracker t = (appTracker == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID) : (appTracker == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker) : analytics.newTracker(R.xml.ecommerce_tracker);
			mTrackers.put(appTracker, t);
			}
			catch(Exception e)
			{
				
			}
		}
		return mTrackers.get(appTracker);
	}
}
	
	
		// The following line should be changed to include the correct property id.
//		private static final String PROPERTY_ID = "UA-60186137-1";
//		
//		private static final String TAG = "MyApp";
//		 
//		public static int GENERAL_TRACKER = 0;
//		
//		public enum TrackerName {
//		APP_TRACKER, // Tracker used only in this app.
//		GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
//		ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
//		}
//		 
//		HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
//		public GoogleAnalyticsApp() {
//		super();
//		}
//		synchronized Tracker getTracker(TrackerName trackerId) {
//		if (!mTrackers.containsKey(trackerId)) {
//		GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//		Tracker t = (trackerId == TrackerName.APP_TRACKER) ? analytics.newTracker(R.xml.app_tracker)
//		: (trackerId == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(PROPERTY_ID)
//		: analytics.newTracker(R.xml.ecommerce_tracker);
//		mTrackers.put(trackerId, t);
//		 
//		}
//		return mTrackers.get(trackerId);
//		}
//		}