package com.Carmatec;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WorkEnvironment extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        TextView textview = new TextView(this);
        textview.setText("\nWork Environment : \n\nCarmatec is an organization which is spreading out its roots in different parts of the globe in a journey towards its vision to be “ The world’s best IT solutions partner through Technology Leadership, innovation and world class workforce. Any organization with the best work environment, culture and learning nature can climb the success peak very quickly and efficiently.");
        setContentView(textview); 
    }
}   