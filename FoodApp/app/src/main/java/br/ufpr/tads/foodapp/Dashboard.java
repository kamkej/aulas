package br.ufpr.tads.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
    public void pedidos(View view){
        Intent intent = new Intent();
        intent.setClass(Dashboard.this, NovoPedido.class);
        startActivity(intent);
    }
    public void pagamento(View view){
        Intent intent = new Intent();
        intent.setClass(Dashboard.this, Pagamento.class);
        startActivity(intent);
    }

}
