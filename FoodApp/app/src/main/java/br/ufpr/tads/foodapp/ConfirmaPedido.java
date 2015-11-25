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
import android.widget.EditText;
import android.widget.TextView;
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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ConfirmaPedido extends AppCompatActivity {
    TextView nome;
    EditText qtd;
    Float valor;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_pedido);
        nome = (TextView)findViewById(R.id.txtItem);
        qtd = (EditText)findViewById(R.id.edtqtd);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nome.setText(bundle.getString("Item"));
        valor = bundle.getFloat("Valor");
        final UserAuth _userAuth=  (UserAuth)getApplicationContext();
        id = _userAuth.getId();

    }
    public void send(View view)  {

        String url = "http://192.168.56.1:8080/FoodService/webresources/payorder";


        try {
        JSONObject jsonParam = new JSONObject();
            jsonParam.put("item",nome.getText().toString());
            jsonParam.put("satus",0);
            //jsonParam.put("tipo","dinheiro");
            jsonParam.put("userid", id);
            jsonParam.put("qtd", Integer.parseInt(qtd.getText().toString()));
            jsonParam.put("valor", valor);
        String message = jsonParam.toString();

            new AsyncHttpTask().execute(url,message);

        } catch (JSONException e) {
            e.printStackTrace();
        }



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
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                os = new BufferedOutputStream(urlConnection.getOutputStream());
                os.write(params[1].getBytes());
                //clean up
                os.flush();

                int HttpResult =urlConnection.getResponseCode();
                if(HttpResult ==HttpURLConnection.HTTP_NO_CONTENT){

                    result = 1;
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(),"UTF-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    System.out.println(""+sb.toString());

                }else{
                    result=0;
                    System.out.println("task1"+urlConnection.getResponseMessage()+HttpResult);
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
                Toast.makeText(ConfirmaPedido.this,"Dados Inserido com sucesso!",Toast.LENGTH_LONG).show();
            finish();



            } else {
                Log.e("fail", "Failed to fetch data!");
            }

        }
    }

}
