package br.com.wastenot.wastenot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class CardDetail extends AppCompatActivity {
    ImageView imgCard;
    ProgressDialog pDialog;
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

          new LoadImage().execute("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid="+cards.getMultiverseid()+"&type=card");

    }

   private class LoadImage extends AsyncTask<String, String,Bitmap> {

        Bitmap bitmap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
      super.onPreExecute();
        pDialog = new ProgressDialog(CardDetail.this);
        pDialog.setMessage("Loading Image ....");
        pDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                imgCard.setImageBitmap(bitmap);
            }else {
                Log.d("bit", "ok");
              //  Toast.makeText(this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }

        }
    }

}


