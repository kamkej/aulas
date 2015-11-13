package br.com.tads.co.cassorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class CadastroActivity extends AppCompatActivity {
    private  studentOperations studentop;
    EditText nome,nota;
    Spinner turma;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        nome = (EditText)findViewById(R.id.ediNome);
        nota = (EditText)findViewById(R.id.edtNota);
        status = (TextView)findViewById(R.id.status);
        turma = (Spinner)findViewById(R.id.spturma);


        studentop = new studentOperations(this);
        try {
            studentop.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0) {
                    float nota = Float.parseFloat(s.toString());
                    if (nota >= 7) {
                        status.setText("Aprovado");
                    } else if (nota < 4) {
                        status.setText("Reprovado");
                    } else {
                        status.setText("Final");
                    }
                }else{
                    status.setText("");
                }

            }
        });

    }



    public void addStudent(View view){
        Student student = new Student();

        student.setName(nome.getText().toString());
        student.setNota(Float.parseFloat(nota.getText().toString()));
        student.setStatus(status.getText().toString());
        student.setTurma(turma.getSelectedItem().toString());

        Student s = studentop.addStudent(student);

        if(s.getId()>0){
            Toast.makeText(this,"Estudante add com sucesso: "+s.getName(),Toast.LENGTH_LONG).show();
            nome.setText(null);
            nota.setText(null);
            status.setText(null);
        };


    }

    @Override
    protected void onResume(){
        try {
            studentop.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }
    @Override
    protected void onPause(){
        studentop.close();
        super.onPause();
    }
}
