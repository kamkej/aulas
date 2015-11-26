package br.ufpr.tads.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PayOrder extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;
    String json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);
        listView = (ListView) findViewById(R.id.listview);
        final UserAuth _userAuth = (UserAuth) getApplicationContext();
        int id = _userAuth.getId();
        String url = "http://192.168.56.1:8080/FoodService/webresources/payorder/" + id;

        new AsyncHttpTask().execute(url, "GET");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static final int REQUEST_EDICAO = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.dinheiro) {
           makeJson("dinheiro");
            return true;
        } else if (item.getItemId() == R.id.debito) {

         makeJson("debito");


            return true;
        } else if (item.getItemId() == R.id.credito) {
            makeJson("credito");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void makeJson(String tipo) {
        String url2 = "http://192.168.56.1:8080/FoodService/webresources/payorder/";
        try {
            JSONArray posts = new JSONArray(json);
            JSONObject jsonObject = new JSONObject();

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);

                post.put("status", 1);
                post.put("tipo", tipo);
                int id = post.getInt("payid");
                url2 = url2+id;

                new AsyncHttpTaskPut().execute(url2, post.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {
        String[] conta;

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
                urlConnection.setRequestMethod(params[1].toString());
                int statusCode = urlConnection.getResponseCode();

                if (statusCode == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    String response = convertInputStreamToString(inputStream);
                    json = response;
                    parseResult(response);
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
                conta = new String[posts.length()];

                for (int i = 0; i < posts.length(); i++) {
                    JSONObject post = posts.optJSONObject(i);
                    String s = post.optString("item");
                    float valor = Float.parseFloat(post.optString("valor"));
                    int qtd = post.optInt("qtd");
                    String title = s + "      " + valor * qtd;
                    conta[i] = title;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

            if (result == 1) {

                arrayAdapter = new ArrayAdapter(PayOrder.this, android.R.layout.simple_list_item_1, conta);

                listView.setAdapter(arrayAdapter);
            } else {
                Log.e("fail", "Failed to fetch data!");
            }

        }
    }

    public class AsyncHttpTaskPut extends AsyncTask<String, Void, Integer> {
        // String[] foods;

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            OutputStream os = null;
            HttpURLConnection urlConnection = null;
            StringBuilder sb = new StringBuilder();
            Integer result = 0;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("PUT");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                os = new BufferedOutputStream(urlConnection.getOutputStream());
                os.write(params[1].getBytes());
                //clean up
                os.flush();

                int HttpResult = urlConnection.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_NO_CONTENT) {

                    result = 1;
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(), "UTF-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    System.out.println("" + sb.toString());

                } else {
                    result = 0;
                    System.out.println("task1" + urlConnection.getResponseMessage() + HttpResult);
                }


            } catch (Exception e) {
                Log.d("tasks", e.getLocalizedMessage());
            }

            return result;
        }


        @Override
        protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

            if (result == 1) {
                Toast.makeText(PayOrder.this, "Conta Paga Consucesso!", Toast.LENGTH_LONG).show();
               // finish();


            } else {
                Log.e("fail", "Failed to fetch data!");
            }

        }

    }
}
