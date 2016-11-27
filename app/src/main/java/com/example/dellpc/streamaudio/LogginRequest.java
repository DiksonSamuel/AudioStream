package com.example.dellpc.streamaudio;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell Pc on 27-11-2016.
 */
public class LogginRequest extends StringRequest {

    private static final String LOG_URL= "http://192.168.0.107/MusicApp/login.php";
    private Map<String,String> params;

    public LogginRequest(String email,String password,Response.Listener<String> listener){
        super(Method.POST,LOG_URL,listener,null);
        params= new HashMap<>();
        params.put("email",email);
        params.put("password",password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
