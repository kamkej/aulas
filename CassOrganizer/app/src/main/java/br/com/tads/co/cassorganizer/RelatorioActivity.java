package br.com.tads.co.cassorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.List;

public class RelatorioActivity extends AppCompatActivity {

    studentOperations studentop;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        studentop = new studentOperations(this);
        try {
            studentop.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List values = studentop.getAllStudents();

        listView = (ListView)findViewById(R.id.list);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,values);
        listView.setAdapter(adapter);

    }
}
