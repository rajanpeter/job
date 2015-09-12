package com.Carmatec;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CultureValue extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        
        TextView textview = new TextView(this);
        textview.setText("\nCulture & Values : \n\nCarmatec’s friendly work environment also gives a way to an open culture which is also contributing a major share in making the organization one among the unique organizations in today’s world of high competition. The organization values it’s employees and also its clients equally. It strives to deliver the best offshore solutions for SMBs across the globe along with which it also takes care that the employees are giving their best to the organization.");
        setContentView(textview);
    }
} 