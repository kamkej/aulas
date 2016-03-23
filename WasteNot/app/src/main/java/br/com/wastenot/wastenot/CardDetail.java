package br.com.wastenot.wastenot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class CardDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        TextView title = (TextView) findViewById(R.id.textTitle);
        TextView text = (TextView) findViewById(R.id.texttext);

        Intent intent = getIntent();
        Cards cards = (Cards) intent.getSerializableExtra("cards");

        title.setText(cards.getName());
        text.setText(cards.getText());

    }
}
