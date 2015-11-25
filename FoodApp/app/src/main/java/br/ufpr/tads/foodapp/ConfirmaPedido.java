package br.ufpr.tads.foodapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ConfirmaPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_pedido);
        TextView nome = (TextView)findViewById(R.id.txtItem);
        Intent intent = getIntent();
        nome.setText(intent.getStringExtra("Item"));

    }
    public void send(View view){

        //finish();
       // Intent intent = new Intent(this,NovoPedido.class);
       // startActivity(intent);
       // String url = "http://192.168.56.1:8080/FoodService/webresources/payorder";

        new AsyncHttpTask().execute();

    }
    public class AsyncHttpTask extends AsyncTask<String, Void ,Integer> {
        // String[] foods;

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            OutputStream os = null;
            HttpURLConnection urlConnection = null;
            StringBuilder sb = new StringBuilder();
            Integer result = 0;
            try {
                URL url = new URL("http://192.168.56.1:8080/FoodService/webresources/payorder");
                urlConnection = (HttpURLConnection) url.openConnection();
             //   urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
              //  urlConnection.setUseCaches(false);
              //  urlConnection.setConnectTimeout(10000);
              //  urlConnection.setReadTimeout(10000);
                urlConnection.setRequestProperty("Content-Type","application/json");

                //urlConnection.setRequestProperty("Host", "android.schoolportal.gr");
                urlConnection.connect();

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("item","cocacola");
                jsonParam.put("satus",0);
                jsonParam.put("tipo","dinheiro");
                jsonParam.put("userid", 1);
                jsonParam.put("qtd", 1);
                jsonParam.put("valor", 23.5);
                String message = jsonParam.toString();
                System.out.println(jsonParam.toString());
                os = new BufferedOutputStream(urlConnection.getOutputStream());
                os.write(message.getBytes());
                //clean up
                os.flush();

                int HttpResult =urlConnection.getResponseCode();
                if(HttpResult ==HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(),"UTF-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    System.out.println(""+sb.toString());

                }else{
                    System.out.println("task1"+urlConnection.getResponseMessage()+HttpResult);
                }


              //  if (statusCode == 200) {
                    //inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    //String response = convertInputStreamToString(inputStream);

                  //  parseResult(response);
               //     result = 1; // Successful
             //   } else {
            //        result = 0; //"Failed to fetch data!";
            //    }


            } catch (Exception e) {
                Log.d("tasks", e.getLocalizedMessage());
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

     /*   private void parseResult(String result) {
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

                    image[i] = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());


                }
                //  Log.d("json",foods[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        @Override
        protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

            if (result == 1) {





            } else {
                Log.e("fail", "Failed to fetch data!");
            }

        }
    }

}
