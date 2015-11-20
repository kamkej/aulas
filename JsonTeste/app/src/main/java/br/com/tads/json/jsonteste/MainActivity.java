package br.com.tads.json.jsonteste;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        @Override
        protected void onPostExecute(String[] strings) {

            arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, response);
            listView.setAdapter(arrayAdapter);
        }
    }

}
