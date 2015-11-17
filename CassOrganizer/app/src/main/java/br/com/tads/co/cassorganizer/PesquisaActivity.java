package br.com.tads.co.cassorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;

import static android.view.View.*;

public class PesquisaActivity extends AppCompatActivity {
    ListView alunos;
    Spinner turmas;
    studentOperations studentop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
        turmas = (Spinner) findViewById(R.id.spturmapesq);

        studentop = new studentOperations(this);

        try {
            studentop.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        



    }
}
