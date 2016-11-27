package com.example.dellpc.streamaudio;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell Pc on 27-11-2016.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_URL= "http://192.168.0.107/MusicApp/register.php";
    private Map<String,String> params;

    public RegisterRequest(String username, int age,String email,String password,Response.Listener<String> listener){
        super(Method.POST,REGISTER_URL,listener,null);
        params= new HashMap<>();
        params.put("username", username);
        params.put("age",String.valueOf(age));
        params.put("email",email);
        params.put("password",password);
        Log.d("CUSTOM MESSAGE", "CAME INSIDE LISENTER");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
