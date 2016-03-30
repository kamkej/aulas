package br.com.wastenot.wastenot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by julio on 3/29/16.
 */
public class LoadImage extends AsyncTask<String, String,Bitmap> {
    Bitmap bitmap;
    private  CardDetail cardDetail;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
     /*   super.onPreExecute();
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading Image ....");
        pDialog.show();*/
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
            cardDetail.setImage(bitmap);
        }else {
            Log.d("bit", "ok");
            //Toast.makeText(.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

        }

    }

}
