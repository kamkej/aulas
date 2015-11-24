package br.ufpr.tads.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends Activity {

     EditText login,senha;
    TextView alerta;

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



        @Override
        protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

            if (result == 1) {
                if(Integer.parseInt(response)==1){
                    finish();

                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, Dashboard.class);
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
