package com.Carmatec;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CarmatecAddress  extends Fragment{
	 public CarmatecAddress() {
	    }
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
				View v = inflater.inflate(R.layout.carma_address, container, false);
				MainActivityfragment.actionname.setText("Reach Us");
				MainActivityfragment.actionname.setGravity(Gravity.CENTER);
				this. setRetainInstance(true);
				MainActivityfragment.pagevalue=20; 
				ImageView imageView1= (ImageView)v.findViewById(R.id.imageView1);
				Button button1= (Button)v.findViewById(R.id.button1);
				Button button2= (Button)v.findViewById(R.id.button2);
				Button button33= (Button)v.findViewById(R.id.button33);
				TextView textView3= (TextView)v.findViewById(R.id.textView3);
				textView3.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Uri uri = Uri.parse("http://carmatec.com/");
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					}
				});
				button33.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Uri uri = Uri.parse("http://carmatec.com/");
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					}
				});
				imageView1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						startActivity(new Intent(getActivity(),Mapviewnew.class));
					}
				});
				button1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						startActivity(new Intent(getActivity(),Mapviewnew.class));
					}
				});
				button2.setOnClickListener(new OnClickListener() {
	
					@Override
					public void onClick(View v) {
						// calling  
						Intent callIntent = new Intent(Intent.ACTION_CALL);
						callIntent.setData(Uri.parse("tel:+91 8041317700"));
						startActivity(callIntent);
//						startActivity(new Intent(getActivity(),Mapviewnew.class));
					}
				});
				return v;
		}
}