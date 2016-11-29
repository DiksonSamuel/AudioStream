package com.example.dellpc.streamaudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Loggin extends AppCompatActivity {

    private Toolbar toolbar;
    EditText etmail,etpassword;
    Button bloggin,bregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        toolbar= (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etmail = (EditText)findViewById(R.id.etmail);
        etpassword = (EditText)findViewById(R.id.etpassword);
        bloggin = (Button)findViewById(R.id.bloggin);
        bregister = (Button)findViewById(R.id.bregister);

        bregister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ireg = new Intent(Loggin.this,Register.class);
                Loggin.this.startActivity(ireg);
            }
        });

        assert bloggin != null;
        if (bloggin != null) {
            bloggin.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    final String username = etmail.getText().toString();
                    final String password = etpassword.getText().toString();
                    Response.Listener<String> response = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonobj = new JSONObject(response);
                                Boolean success = jsonobj.getBoolean("success");

                                if (success) {
                                    String name = jsonobj.getString("username");
                                    Intent lo = new Intent(Loggin.this, MainActivity.class);
                                    lo.putExtra("name", name);

                                    Loggin.this.startActivity(lo);

                                } else {
                                    Toast.makeText(getApplicationContext(), "FAILED", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LogginRequest logginrequest = new LogginRequest(username, password, response);
                    RequestQueue queue = Volley.newRequestQueue(Loggin.this);
                    queue.add(logginrequest);

                }
            });
        }


    }
}
