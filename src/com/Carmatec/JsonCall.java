package com.Carmatec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;
import android.util.Log;

public class JsonCall 
{
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";	  
	public JsonCall() { 
 
	} 
	public static JSONObject postData( List<NameValuePair> nameValuePairs,String s) {
  HttpClient httpclient = new DefaultHttpClient();
  HttpPost httppost = new HttpPost(s);
  try {
      httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));     
      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
      StrictMode.setThreadPolicy(policy);
      HttpResponse response = httpclient.execute(httppost);
		HttpEntity httpEntity = response.getEntity();
		is = httpEntity.getContent();	
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				json = sb.toString();
			} catch (Exception e) {
				Log.e("Buffer Error", "Error converting result " + e.toString());
			}
			try {
				jObj = new JSONObject(json);
				System.out.println(jObj+"output");
			} catch (JSONException e) {
				e.printStackTrace();
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}	
  } catch (ClientProtocolException e) {
      // TODO Auto-generated catch block
  } catch (IOException e) {
      // TODO Auto-generated catch block
  }
  return jObj;
} 
}
