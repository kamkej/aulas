package br.com.wastenot.wastenot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Log.d("JS", "LOAD...");
        String json = null;
        TextView  log = (TextView) findViewById(R.id.log);
        try {
            Log.d("JS","file...");
            InputStream file = this.getAssets().open("allsets.json");
            byte[] fileArray = new byte[file.available()];
            file.read(fileArray);
            file.close();

            json = new String(fileArray,"UTF-8");

            JSONObject sets = new JSONObject(String.valueOf(json));

            JSONArray jsonArray = sets.optJSONArray("cards");


           // Log.d("json", String.valueOf(jsonArray.length()));
            log.setText(String.valueOf(jsonArray.length()));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
