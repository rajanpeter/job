package com.Carmatec;


import android.app.ActionBar;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapviewnew  extends FragmentActivity 
{

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
    setContentView(R.layout.newmapview);
    setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
	getActionBar().setCustomView(R.layout.actionbar);

view=	getActionBar().getCustomView();
    TextView actionname=(TextView)view.findViewById(R.id.textView1);
    actionname.setText("Reach Us"); 
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
//        Criteria criteria = new Criteria();
//        String provider = locationManager.getBestProvider(criteria, true);
     	 LatLng latLng = new LatLng(12.920508, 77.670514);
	       googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
  		 googleMap.animateCamera(CameraUpdateFactory.zoomTo(18));
       
    }
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



}

