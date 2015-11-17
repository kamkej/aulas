package br.com.tads.json.jsonteste;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private  static String urlString;
    private ArrayAdapter arrayAdapter = null;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview);
        final String url = "http://javatechig.com/api/get_category_posts/?dev=1&slug=android";
        new AsyncHttpTask().execute(url);
    }
    @Override
    protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

        if(result == 1){

            arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, blogTitles);

            listView.setAdapter(arrayAdapter);
        }else{
            Log.e("fail", "Failed to fetch data!");
        }
    }
}
