package br.ufpr.tads.count;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {


    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] itens = new String[]{"Circulo","Quadrado","Sair"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itens);
        setListAdapter(arrayAdapter);

        Intent intent = getIntent();
        count = intent.getIntExtra("count",0);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Long object = this.getListAdapter().getItemId(position);
        if(object==0){
            Intent intent = new Intent(this,CirculoActivity.class);
            intent.putExtra("count", count);
            startActivity(intent);
            finish();
        }else if(object==1){
            Intent intent = new Intent(this,QuadradoActivity.class);
            intent.putExtra("count",count);
            startActivity(intent);
            finish();
        }else{
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
