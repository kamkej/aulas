package kamke.com.applogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    private LoginButton loginButton;
    private TextView id,email,nome;
    private CallbackManager  callbackManager;
    private ProfilePictureView profilePictureView;
    private AccessToken Session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        id = (TextView)findViewById(R.id.id);
        email= (TextView)findViewById(R.id.email);
        nome = (TextView)findViewById(R.id.nome);
        loginButton =(LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends","email","public_profile");
        profilePictureView = (ProfilePictureView) findViewById(R.id.profileId);

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    id.setText(loginResult.getAccessToken().getUserId());
                    perfil(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                    Toast.makeText(MainActivity.this, "Cancelado ", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onError(FacebookException e) {
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();


                }



            });
    }

    private  void login(){
        if(AccessToken.getCurrentAccessToken() != null){
            Toast.makeText(MainActivity.this, "Logado ", Toast.LENGTH_SHORT).show();
            Session = AccessToken.getCurrentAccessToken();
            perfil(Session);
        }else {
            id.setText(null);
            profilePictureView.setProfileId(null);
            nome.setText(null);
            email.setText(null);
        }

    }

    private void perfil(AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        Log.v("LoginActivity", response.toString());
                        JSONObject json = response.getJSONObject();
                        try {
                            profilePictureView.setCropped(true);
                            profilePictureView.setProfileId(json.getString("id"));
                            email.setText(json.getString("email"));
                            nome.setText(json.getString("name"));
                            id.setText(json.getString("id"));
                        } catch (JSONException e) {
                            Log.v("json", e.getMessage());
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }


}
