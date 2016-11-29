package com.example.dellpc.streamaudio;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Play extends AppCompatActivity {

    int pos;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        t = (TextView) findViewById(R.id.textView);

        Intent in = getIntent();
        pos= in.getIntExtra("pos", -1);


    }

}
