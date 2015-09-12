package com.Carmatec;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import android.app.ActionBar;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast; 

public class MapView extends FragmentActivity implements android.location.LocationListener
{
	int locationval=0;
	 ArrayList<LatLng> points = null;
	  PolylineOptions lineOptions = null;
    TextToSpeech tts;
GoogleMap googleMap;
LocationManager locationManager;
PendingIntent pendingIntent;
SharedPreferences sharedPreferences;
Location location;
double longitude,latitude;     	
Button start,stop;
View view;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.mapview);
    setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
	getActionBar().setCustomView(R.layout.actionbar);

view=	getActionBar().getCustomView();
    TextView actionname=(TextView)view.findViewById(R.id.textView1);
    actionname.setText("Reach Us"); 
    tts=new TextToSpeech(MapView.this, new TextToSpeech.OnInitListener() {

        @Override
        public void onInit(int status) {
            // TODO Auto-generated method stub
            if(status == TextToSpeech.SUCCESS){
                int result=tts.setLanguage(Locale.US); 
                if(result==TextToSpeech.LANG_MISSING_DATA ||
                        result==TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("error", "This Language is not supported");
                }
                else{ 
//                    ConvertTextToSpeech();
                }
            }
            else
                Log.e("error", "Initilization Failed!");
        }
    });
    int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

    // Showing status
    if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
        int requestCode = 10;
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
        dialog.show();

    }else { // Google Play Services are available

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = fm.getMap();
        googleMap.setMyLocationEnabled(true);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            drawCircle(new LatLng(12.920508, 77.670514));
            drawMarker(new LatLng(12.920508, 77.670514),"Carmatec IT Solution");
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(12.920508, 77.670514)));
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        locationManager.requestLocationUpdates(provider, 20000, 0, MapView.this);
     	 LatLng latLng = new LatLng(12.920508, 77.670514);
	       googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
  		 googleMap.animateCamera(CameraUpdateFactory.zoomTo(18));
        if(location!=null){ 
        	System.out.println("LOCATION HAS VALUE");
        	 try
             {
             longitude = location.getLongitude(); 
     		 latitude = location.getLatitude();
     		LatLng origin= new LatLng(latitude, longitude);
     		LatLng dest= new LatLng(12.920508, 77.670514);
     		  String url = getDirectionsUrl(origin, dest);
     	        DownloadTask downloadTask = new DownloadTask();
     	        downloadTask.execute(url);
             }
             catch(Exception e)
             {
             }
       
        }
        else {
  
        	System.out.println("NO LOCATION VALUE");
//        	 onLocationChanged(location);
		}
       
	        start=(Button)findViewById(R.id.start);
	        stop=(Button)findViewById(R.id.stop);
	        start.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					start.setEnabled(false);
					locationval=1;
					    LatLng latLng = new LatLng(latitude, longitude);
					    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
					    googleMap.animateCamera(CameraUpdateFactory.zoomTo(18));
					  
				        Intent proximityIntent = new Intent("com.Carmatec.proximity");
				        pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, proximityIntent,Intent.FLAG_ACTIVITY_NEW_TASK);
				        locationManager.addProximityAlert(12.920508, 77.670514, 20, -1, pendingIntent);
				}
			});
	        stop.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 Intent proximityIntent = new Intent("com.Carmatec.proximity");
					 
				     pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, proximityIntent,Intent.FLAG_ACTIVITY_NEW_TASK);

				     // Removing the proximity alert
				     locationManager.removeProximityAlert(pendingIntent);
				     locationManager.removeUpdates(MapView.this);

				     googleMap.setMyLocationEnabled(false);
				     // Removing the marker and circle from the Google Map
				     googleMap.clear();
//					locationManager.removeUpdates(this);
					
					locationManager = null;
					googleMap.clear();
					finish();
				}
			});
    }
}

@Override
protected void onPause() {
    // TODO Auto-generated method stub

    if(tts != null){

        tts.stop();
        tts.shutdown();
    }
    super.onPause();
}

private void ConvertTextToSpeech() {
    // TODO Auto-generated method stub
  String  text = "You reached carmatec i t solution";
    if(text==null||"".equals(text))
    { 
        text = "Content not available";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }else
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null); 
}
private void drawMarker(LatLng point,String title){
    // Creating an instance of MarkerOptions
    MarkerOptions markerOptions = new MarkerOptions();

    // Setting latitude and longitude for the marker
    markerOptions.position(point);
    markerOptions.title(title);
    // Adding marker on the Google Map
    googleMap.addMarker(markerOptions);
}

private void drawCircle(LatLng point){
    CircleOptions circleOptions = new CircleOptions();
    circleOptions.center(point);
    circleOptions.radius(20);
    circleOptions.strokeColor(Color.BLACK);
    circleOptions.fillColor(0x30ff0000);
    circleOptions.strokeWidth(2);
    googleMap.addCircle(circleOptions);

}

private String getDirectionsUrl(LatLng origin,LatLng dest){
	
	String str_origin = "origin="+origin.latitude+","+origin.longitude;
    String str_dest = "destination="+dest.latitude+","+dest.longitude;
    String sensor = "sensor=false";
    String parameters = str_origin+"&"+str_dest+"&"+sensor;
    String output = "json";
    String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
    return url;
}
private String downloadUrl(String strUrl) throws IOException{
String data = "";
InputStream iStream = null;
HttpURLConnection urlConnection = null;
try{
  URL url = new URL(strUrl);
  urlConnection = (HttpURLConnection) url.openConnection();
  urlConnection.connect();
  iStream = urlConnection.getInputStream();
  BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
  StringBuffer sb = new StringBuffer();
  String line = "";
  while( ( line = br.readLine()) != null){
      sb.append(line);
  }
  data = sb.toString();
  br.close();
}catch(Exception e){
  Log.d("Exception while downloading url", e.toString());
}finally{
  iStream.close();
  urlConnection.disconnect();
}
return data;
}
private class DownloadTask extends AsyncTask<String, Void, String>{
@Override
protected String doInBackground(String... url) {
  String data = "";

  try{
      // Fetching the data from web service
      data = downloadUrl(url[0]);
  }catch(Exception e){
      Log.d("Background Task",e.toString());
  }
  return data;
}

// Executes in UI thread, after the execution of
// doInBackground()
@Override
protected void onPostExecute(String result) {
  super.onPostExecute(result);

  ParserTask parserTask = new ParserTask();

  // Invokes the thread for parsing the JSON data
  parserTask.execute(result);
}
}

/** A class to parse the Google Places in JSON format */
private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

// Parsing the data in non-ui thread
@Override
protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

  JSONObject jObject;
  List<List<HashMap<String, String>>> routes = null;

  try{
      jObject = new JSONObject(jsonData[0]);
      DirectionsJSONParser parser = new DirectionsJSONParser();

      // Starts parsing data
      routes = parser.parse(jObject);
  }catch(Exception e){
      e.printStackTrace();
  }
  return routes;
}

// Executes in UI thread, after the parsing process
@Override
protected void onPostExecute(List<List<HashMap<String, String>>> result) {
 

  @SuppressWarnings("unused")
MarkerOptions markerOptions = new MarkerOptions();

  // Traversing through all the routes
  for(int i=0;i<result.size();i++){
      points = new ArrayList<LatLng>();
      lineOptions = new PolylineOptions();

      // Fetching i-th route
      List<HashMap<String, String>> path = result.get(i);

      // Fetching all the points in i-th route
      for(int j=0;j<path.size();j++){
          HashMap<String,String> point = path.get(j);

          double lat = Double.parseDouble(point.get("lat"));
          double lng = Double.parseDouble(point.get("lng"));
          LatLng position = new LatLng(lat, lng);

          points.add(position);
      }

      // Adding all the points in the route to LineOptions
      lineOptions.addAll(points);
      lineOptions.width(10);
      lineOptions.color(Color.BLUE);
  }

  // Drawing polyline in the Google Map for the i-th route
  googleMap.addPolyline(lineOptions);
  lineOptions=null;
 
}
}
@Override
public void onLocationChanged(Location location) {
	try
	{
		try
		{
		longitude = location.getLongitude();
		latitude = location.getLatitude();
//		points.clear();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(locationval==1)
		{
		
    LatLng latLng = new LatLng(latitude, longitude);
    
    
    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    googleMap.animateCamera(CameraUpdateFactory.zoomTo(18));
    
    
    boolean proximity_entering = getIntent().getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true);
    if(proximity_entering){
        Toast.makeText(getBaseContext(),"Entering the region" +proximity_entering ,Toast.LENGTH_LONG).show();
        ConvertTextToSpeech();
    }else{
        Toast.makeText(getBaseContext(),"Exiting the region"  ,Toast.LENGTH_LONG).show();
    }
		}
		else
		{
			 try
             {     		 
     		LatLng origin= new LatLng(latitude, longitude);
     		LatLng dest= new LatLng(12.920508, 77.670514);
     		  String url = getDirectionsUrl(origin, dest);
     	        DownloadTask downloadTask = new DownloadTask();
     	        downloadTask.execute(url);

             }
             catch(Exception e)
             {
             }
		}
	}
	catch(Exception e)
	{
	}

}
@Override
public void onProviderDisabled(String provider) {
  // TODO Auto-generated method stub
}

@Override
public void onProviderEnabled(String provider) {
  // TODO Auto-generated method stub
}

@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
  // TODO Auto-generated method stub
}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stubzzz
//	super.onBackPressed(); 
	 Intent proximityIntent = new Intent("com.Carmatec.proximity");
	 
     pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, proximityIntent,Intent.FLAG_ACTIVITY_NEW_TASK);

     // Removing the proximity alert
     locationManager.removeProximityAlert(pendingIntent);
     locationManager.removeUpdates(MapView.this);

     googleMap.setMyLocationEnabled(false);
     // Removing the marker and circle from the Google Map
     googleMap.clear();
//	locationManager.removeUpdates(this);
	
	locationManager = null;
	googleMap.clear();
	finish();
}
}

