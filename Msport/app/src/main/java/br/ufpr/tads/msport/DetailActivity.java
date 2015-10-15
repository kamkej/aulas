package br.ufpr.tads.msport;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {

    String[] itens = {"Bola","Barraca","Raquete","Luva"};
    Integer[]imageID = {R.drawable.bola,R.drawable.barraca,R.drawable.raquete,R.drawable.luva};
    String[] valor = {"R$ 25,8","R$ 26,8","R$ 27,8","R$ 28,8"};
    String[]  descricao={"Bola","Barraca","Raquete","Luva"};
    TextView title,preco,desc;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = (TextView)findViewById(R.id.titulo);
        preco = (TextView)findViewById(R.id.preco);
        desc = (TextView)findViewById(R.id.desc);
        img = (ImageView)findViewById(R.id.imgdetail);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        title.setText(itens[id]);
        img.setImageResource(imageID[id]);
        preco.setText(valor[id]);
        desc.setText(descricao[id]);
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
