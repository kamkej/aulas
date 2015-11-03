package br.com.tads.artman;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.SQLException;

public class NovoEdicaoActivity extends Activity {

    private EditText edtNome;
    private EditText edtRevista;
    private EditText edtEdicacao;
    private Spinner spEstado;
    private CheckBox cbPago;
    private Artigo artigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_edicao);
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtRevista = (EditText)findViewById(R.id.edtRevista);
        edtEdicacao = (EditText)findViewById(R.id.edtEdicao);
        spEstado = (Spinner)findViewById(R.id.spEstado);
        cbPago = (CheckBox)findViewById(R.id.cbPago);
        Intent intent = getIntent();
        if(intent != null){
            artigo = (Artigo)intent.getSerializableExtra("artigo");
            if(artigo !=null){
                edtNome.setText(artigo.nome);
                edtRevista.setText(artigo.revista);
                edtEdicacao.setText(artigo.edicao);
                spEstado.setSelection(artigo.status);
                cbPago.setChecked(artigo.pago==1?true:false);
            }
        }

    }
    public  void salvar(View view) throws SQLException {
        BancoDeDados db = new BancoDeDados(this);
        db.abrir();
        if(artigo != null){
            db.atualizarArtigo(artigo.id,edtNome.getText().toString(),
                    edtRevista.getText().toString(),edtEdicacao.getText().toString(),
                    spEstado.getSelectedItemPosition(),cbPago.isChecked()?1:0);
        }else {
            db.insereArtigo(edtNome.getText().toString(),edtRevista.getText().toString(),
                    edtEdicacao.getText().toString(),spEstado.getSelectedItemPosition(),cbPago.isChecked()?1:0);
        }
        db.fechar();
        setResult(ArtigosActivity.REQUEST_SALVOU);
        finish();

    }
}
