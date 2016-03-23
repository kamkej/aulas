package br.com.wastenot.wastenot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CardDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        Intent intent= getIntent();
        Cards cards = (Cards) intent.getSerializableExtra("cards");

        TextView title = (TextView)findViewById(R.id.txtTitle);
        TextView text = (TextView)findViewById(R.id.txtText);

        title.setText(cards.getName());
        text.setText(cards.getText());



    }
}
