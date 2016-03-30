package br.com.wastenot.wastenot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class CardDetail extends AppCompatActivity {
    ImageView imgCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        Intent intent= getIntent();
        Cards cards = (Cards) intent.getSerializableExtra("cards");

        TextView title = (TextView)findViewById(R.id.txtTitle);
        TextView text = (TextView)findViewById(R.id.txtText);

        imgCard = (ImageView)findViewById(R.id.imgcard);


        title.setText(cards.getName());
        text.setText(cards.getText());

          new LoadImage().execute("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=75904&type=card");

    }

    public void setImage(Bitmap bitmap){
        imgCard.setImageBitmap(bitmap);
    }

}

