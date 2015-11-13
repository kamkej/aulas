package br.com.tads.co.cassorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Cadastrar(View view){
        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);
    }
    public void Relatorio(View view){
        Intent intent = new Intent(this,RelatorioActivity.class);
        startActivity(intent);
    }
}
