package com.test.greasyspoon;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener
{
   // ShareDialog shareDialog;
    TextView skip;

    Button signin,login1;

    LoginButton login;
    CallbackManager cbm;



    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "MainActivity";
    //TextView mStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skip=(TextView)findViewById(R.id.tvSkip);
        signin=(Button)findViewById(R.id.btnSignup);
        login1=(Button)findViewById(R.id.btnLogin);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);

            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);

            }
        });




        /*google code start inside oncreate*/
       // mStatusTextView = (TextView) findViewById(R.id.textView2);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(AppIndex.API).build();


        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;

                    // ...
                }
            }
        });
        /*findViewById(R.id.signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signout:
                        signOut();
                        break;

                    // ...
                }
            }
        });
        ends google oncreate*/

        FacebookSdk.sdkInitialize(getApplicationContext());


        login = (LoginButton) findViewById(R.id.login_button);
        //final TextView text = (TextView) findViewById(R.id.textView);
        cbm = CallbackManager.Factory.create();
        //shareDialog = new ShareDialog(MainActivity.this);

        login.registerCallback(cbm, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult) {
             //   text.setText("Login Done \n"+loginResult.getAccessToken().getUserId()+"\n"+loginResult.getAccessToken().getToken());
            }

            @Override
           public void onCancel() {
         //       text.setText("Login Cancel");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
       /* Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Hello Facebook")
                            .setContentDescription(
                                    "The 'Hello Facebook' sample  showcases simple Facebook integration")
                            .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                            .build();

                    shareDialog.show(linkContent);
                }

            }
        });
        // this part is optional
        shareDialog.registerCallback(cbm, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        }); */




        }

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

   /* private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        mStatusTextView.setText("SignOut Successfullllyy");
                    }
                });
    }
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /*google start*/

        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

          handleSignInResult(result);
        }

        /*google end*/

        cbm.onActivityResult(requestCode, resultCode, data);
    }



    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            SessionManager session=new SessionManager(MainActivity.this,"");
            session.createtitlesession(acct.getEmail());
            // updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            // updateUI(false);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
/*google end*/



}
