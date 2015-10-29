package br.com.canastraville;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import br.com.canastraville.Login;

/**
 * Created by julio on 10/16/15.
 */
public class JSInterface {
    Context context;

    JSInterface(Context c){
        context = c;
    }
    @JavascriptInterface
    public  void showToast(String toast){
        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
    }
    @JavascriptInterface
    public void goback(){
        Intent intent = new Intent(context,Login.class);
        context.startActivity(intent);
    }
}
