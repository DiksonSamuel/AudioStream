package com.example.dellpc.streamaudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    private Toolbar toolbar;
    EditText etusername,etage,etmail,etpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar= (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etusername = (EditText)findViewById(R.id.etusername);
        etage = (EditText)findViewById(R.id.etage);
        etmail = (EditText)findViewById(R.id.etmail);
        etpassword = (EditText)findViewById(R.id.etpassword);
        final Button   bregister1= (Button)findViewById(R.id.bregister);
        bregister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etusername.getText().toString();
                final int age = Integer.parseInt(etage.getText().toString());
                final String email = etmail.getText().toString();
                final String password = etpassword.getText().toString();
                Log.d("CUSTOM MESSAGE","CLICKED");

                Response.Listener<String> responseListener= new Response.Listener<String>() {

                    @Override
                        public void onResponse(String response) {
                        Log.d("CUSTOM MESSAGE","CAMEINSIDE ONRESPONSE");
                        try {
                            JSONObject jsonobj= new JSONObject(response);
                            Boolean success = jsonobj.getBoolean("success");
                            Log.d("CUSTOM MESSAGE",success.toString());
                            if(success) {
                                Intent re = new Intent(Register.this, MainActivity.class);
                                Register.this.startActivity(re);
                            } else {
                                Toast.makeText(getApplicationContext(), "FAILED", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                Log.d("CUSTOM MESSAGE","CAMEINSIDE OUTISE");


                RegisterRequest registerrequest = new RegisterRequest(username,age,email,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerrequest);
            }
        });
    }
}
