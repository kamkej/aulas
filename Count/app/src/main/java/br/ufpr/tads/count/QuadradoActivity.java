package br.ufpr.tads.count;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class QuadradoActivity extends Activity {

    int count;
    TextView contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadrado);
        Intent intent = getIntent();
        count = intent.getIntExtra("count", 0);
        contador = (TextView)findViewById(R.id.contadorq);
        contador.setText(String.valueOf(count));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quadrado, menu);
        return true;
    }

    public void plus(View view){
        count++;
        contador.setText(String.valueOf(count));

    }
    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("count",count);
        startActivity(intent);
        finish();
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
