package br.ufpr.tads.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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

public class NovoPedido extends Activity {
    ListView listView;
    String[] nome;
    Float[] valor;
    Bitmap[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);
        listView = (ListView)findViewById(R.id.listview);

        final String url = "http://192.168.56.1:8080/FoodService/webresources/neworder";

        new AsyncHttpTask().execute(url);


    }
    public class AsyncHttpTask extends AsyncTask<String, Void ,Integer> {
       // String[] foods;

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
                nome = new String[posts.length()];
                image = new Bitmap[posts.length()];
                valor = new Float[posts.length()];

                for (int i = 0; i < posts.length(); i++) {
                    JSONObject post = posts.optJSONObject(i);
                    nome[i] = post.optString("nome");
                    valor[i] = Float.parseFloat(post.optString("valor"));
                    String imageUrl = "http://192.168.56.1:8080/FoodService/images/"+post.optString("img");

                    image[i] = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());


                }
              //  Log.d("json",foods[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

            if (result == 1) {
                final ListCell listCell = new ListCell(NovoPedido.this,nome,image,valor);
                listView.setAdapter(listCell);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        intent.setClass(NovoPedido.this, ConfirmaPedido.class);
                        intent.putExtra("Item", nome[position]);
                        startActivity(intent);

                    }
                });




            } else {
                Log.e("fail", "Failed to fetch data!");
            }

        }
    }






}
