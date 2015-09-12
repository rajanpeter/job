package com.Carmatec;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Learning extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView textview = new TextView(this);
        textview.setText("\nLearning : \n\nThis organization along with it’s friendly and motivating work environment and open culture also positions itself as a learning organization. Carmatec being positioned as a learning organization provides it’s employees with ample number of opportunities to learn and grow themselves and also develop the organization with their improving skill sets.");
        setContentView(textview);
    }
} 