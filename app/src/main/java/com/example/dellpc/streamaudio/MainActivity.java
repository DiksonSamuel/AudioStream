package com.example.dellpc.streamaudio;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<SongDetails> sl = new ArrayList<>();
    String[] sn,sa;
    TextView tv1;
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> artist = new ArrayList<>();
    ArrayList<String>  urs = new ArrayList<>();
    ArrayList<String>  urs1 = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 =(TextView)findViewById(R.id.tv1);

        toolbar= (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layoutManager=  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        new JsonL().execute("http://192.168.43.125/MusicApp/songs.php");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.log) {
            Intent intent = new Intent(MainActivity.this,Loggin.class);
            startActivity(intent);
        }
        if(id== R.id.reg){
            Intent intent = new Intent(MainActivity.this,Register.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    class JsonL extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;
            BufferedReader br = null;

            try {
                URL url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();

                br = new BufferedReader(new InputStreamReader(is));
                StringBuffer sb = new StringBuffer();
                String lin = "";
                while ((lin = br.readLine()) != null) {
                    sb.append(lin);
                }
                Log.d("CUSTOM MESSAGE", "json");
                String finalans = sb.toString();
                StringBuffer final1 = new StringBuffer();
                JSONArray ja = new JSONArray(finalans);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jsonChildNode = ja.getJSONObject(i);
                    int songid = Integer.parseInt(jsonChildNode.optString("id"));
                    String songname = jsonChildNode.optString("title").toString();
                    String songartist = jsonChildNode.optString("artist").toString();
                    String ur = jsonChildNode.optString("url").toString();
                    id.add(songid);
                    title.add(songname);
                    artist.add(songartist);
                    urs.add(ur);
                    final1.append(songname + "/" + songartist + "\n");
                }

                return final1.toString();

            } catch (MalformedURLException e) {
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {

            } finally {
                if (conn != null) {
                    conn.disconnect();
                }

                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("CUSTOM MESSAGE", s);
            Log.d("CUSTOM MESSAGE", s);

            for(int i=0;i<title.size();i++){
                SongDetails sd = new SongDetails(title.get(i),artist.get(i));
                sl.add(sd);
            }
            adapter = new SongAdapter(sl,MainActivity.this);
            recyclerView.setAdapter(adapter);




        }
    }

}
