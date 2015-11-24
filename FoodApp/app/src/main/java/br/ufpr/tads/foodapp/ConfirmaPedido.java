package br.ufpr.tads.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmaPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_pedido);
        TextView nome = (TextView)findViewById(R.id.txtItem);
        Intent intent = getIntent();
        nome.setText(intent.getStringExtra("Item"));

    }
    public void send(View view){
        finish();
        Intent intent = new Intent(this,NovoPedido.class);
        startActivity(intent);
    }
}
