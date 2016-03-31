package br.com.wastenot.wastenot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.InputStream;
import java.net.URL;

import static br.com.wastenot.wastenot.R.color.red;

public class CardDetail extends AppCompatActivity {
    ImageView imgCard;
    TextView textViewLoad;
    private ProgressBar mProgress;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        Intent intent = getIntent();
        Cards cards = (Cards) intent.getSerializableExtra("cards");

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layoutid);
        layout.setBackgroundResource(R.drawable.roundeditext);

        LinearLayout border = (LinearLayout) findViewById(R.id.layoutBorder);
        border.setBackgroundColor(getResources().getColor(R.color.red));
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        textViewLoad = (TextView) findViewById(R.id.txtLoading);

        TextView title = (TextView) findViewById(R.id.txtTitle);
        TextView text = (TextView) findViewById(R.id.txtText);

        imgCard = (ImageView) findViewById(R.id.imgcard);


        title.setText(cards.getName());
        text.setText(cards.getText());

        new LoadImage().execute("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + cards.getMultiverseid() + "&type=card");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CardDetail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://br.com.wastenot.wastenot/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CardDetail Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://br.com.wastenot.wastenot/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {

        Bitmap bitmap;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            super.onPreExecute();

            ;
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
            if (bitmap != null) {
                imgCard.setImageBitmap(bitmap);
                mProgress.setVisibility(View.INVISIBLE);
                textViewLoad.setVisibility(View.INVISIBLE);
            } else {
                Log.d("bit", "ok");
                Toast.makeText(CardDetail.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
                mProgress.setVisibility(View.INVISIBLE);
                textViewLoad.setVisibility(View.INVISIBLE);

            }

        }
    }

}


