package br.com.wastenot.wastenot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

        try {
            InputStream file = this.getAssets().open("allsets.json");
            byte[] fileArray = new byte[file.available()];
            file.read(fileArray);
            file.close();

            JSONObject sets = new JSONObject(String.valueOf(fileArray));

            JSONArray jsonArray = sets.optJSONArray("cards");

            Log.d("json", String.valueOf(jsonArray.length()));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
