package br.com.tads.json.jsonteste;

import android.app.Activity;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {
    private  static String urlString;
    private ArrayAdapter arrayAdapter = null;
    ListView listView;
    String[] response ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview);

        final String url = "http://javatechig.com/api/get_category_posts/?dev=1&slug=android";

        new ListPost().execute(url);






//        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, response);

  //      listView.setAdapter(arrayAdapter);

    }
    private class ListPost extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... url) {
            webServiceURL ws = new webServiceURL();
            response =  ws.Connect(url[0]);
            return response;

        }


    public class AsyncHttpTask extends AsyncTask<String, Void ,Integer> {
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

                if (statusCode == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    String response = convertInputStreamToString(inputStream);
                    blogTitles = new String[1];
                    blogTitles[0] =response;
                   // parseResult(response);
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }


            } catch (Exception e) {
                Log.d("task", e.getLocalizedMessage());
            }

            return result;
        }

        private String convertInputStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            /* Close Stream */
            if (null != inputStream) {
                inputStream.close();
            }

            return result;

        }

        private void parseResult(String result) {
            try {
             //   JSONObject response = new JSONObject(result);
                JSONArray posts = new JSONArray(result);
              //  JSONArray posts = response.optJSONArray("");
                blogTitles = new String[posts.length()];

                for (int i = 0; i < posts.length(); i++) {
                    JSONObject post = posts.optJSONObject(i);
                    String title = post.optString("login");
                    blogTitles[i] = title;
                }
                Log.d("json",blogTitles[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

            if (result == 1) {

                arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, blogTitles);

                listView.setAdapter(arrayAdapter);
            } else {
                Log.e("fail", "Failed to fetch data!");
            }

        }
    }

}
