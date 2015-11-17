package br.com.tads.json.jsonteste;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by julio on 11/17/15.
 */
public class AsyncHttpTask extends AsyncTask<String, Void ,Integer>{
    String[] blogTitles;

    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        Integer result = 0;
        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                String response = convertInputStreamToString(inputStream);
                parseResult(response);
                result = 1; // Successful
            }else{
                result = 0; //"Failed to fetch data!";
            }


        } catch (Exception e ) {
            Log.d("task", e.getLocalizedMessage());
        }

        return result;
    }
    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;

    }
    private void parseResult(String result) {
        try{
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("posts");
            blogTitles = new String[posts.length()];

            for(int i=0; i< posts.length();i++ ){
                JSONObject post = posts.optJSONObject(i);
                String title = post.optString("title");
                blogTitles[i] = title;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
