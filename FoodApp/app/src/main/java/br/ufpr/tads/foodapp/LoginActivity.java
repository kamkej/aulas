package br.ufpr.tads.foodapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class LoginActivity extends Activity {

    EditText login,senha;
    TextView alerta;
    String nome;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (EditText)findViewById(R.id.edtLogin);
        senha = (EditText)findViewById(R.id.edtpwd);
        alerta = (TextView)findViewById(R.id.txtalert);

    }
    public void login(View view){
        String loginid=login.getText().toString();
        String password=senha.getText().toString();

        String url = "http://192.168.56.1:8080/FoodService/webresources/user/"+loginid+"/"+password;

        new AsyncHttpTask().execute(url);
    }

    public class AsyncHttpTask extends AsyncTask<String, Void ,Integer> {
        String response;

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
                    response = convertInputStreamToString(inputStream);
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
                JSONObject res = new JSONObject(result);
                nome = res.optString("login");
                id = res.optInt("userID");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



        @Override
        protected void onPostExecute(Integer result) {


            if (result == 1) {
                if(!nome.equalsIgnoreCase("Erro")){
                    finish();

                    final UserAuth _userAuth=  (UserAuth)getApplicationContext();
                    _userAuth.setNome(nome);
                    _userAuth.setId(id);

                    Intent intent = new Intent();

                    intent.setClass(getBaseContext(), Dashboard.class);

                    startActivity(intent);


                }else{
                    alerta.setText("Erro no login ou senha!");
                }

            } else {
                alerta.setText("Erro ou se conectar!");
            }
        }
    }






}
