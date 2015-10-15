package br.ufpr.tads.drinkmixer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Detail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        TextView nome = (TextView)findViewById(R.id.name);
        TextView ingredientes = (TextView)findViewById(R.id.ingredientes);
        TextView preparo = (TextView)findViewById(R.id.preparo);
        int id = Integer.parseInt(intent.getStringExtra("drinkId"));

        if(id==0){
            nome.setText(R.string.n0);
            ingredientes.setText(R.string.i0);
            preparo.setText(R.string.p0);
        }else if(id==1){
            nome.setText(R.string.n1);
            ingredientes.setText(R.string.i1);
            preparo.setText(R.string.p1);
        }else if(id==2){
            nome.setText(R.string.n2);
            ingredientes.setText(R.string.i2);
            preparo.setText(R.string.p2);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
