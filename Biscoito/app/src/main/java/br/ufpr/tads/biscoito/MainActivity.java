package br.ufpr.tads.biscoito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int sorte;
    TextView result;
    Random r;
    String frase[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frase = new String[]{"A vida trará coisas boas se tiveres paciência","Demonstre amor e alegria em todas as oportunidades e verás que a paz nasce dentro de você.","Não compense na ira o que lhe falta na razão.","Defeitos e virtudes são apenas dois lados da mesma moeda","A maior de todas as torres começa no solo.","Não há que ser forte. Há que ser flexível.","Gente todo dia arruma os cabelos, por que não o coração?","Há três coisas que jamais voltam; a flecha lançada, a palavra dita e a oportunidade perdida.","A juventude não é uma época da vida, é um estado de espírito.","Podemos escolher o que semear, mas somos obrigados a\n" +
                "colher o que plantamos."};

        r = new Random();
        sorte = r.nextInt(10);
        result = (TextView)findViewById(R.id.result);
        result.setText(frase[sorte]);

    }
    public void sortear(View view){
        sorte = r.nextInt(10);
        result.setText(frase[sorte]);
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
