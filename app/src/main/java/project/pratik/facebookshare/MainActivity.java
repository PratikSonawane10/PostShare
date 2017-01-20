package project.pratik.facebookshare;


import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.google.android.gms.plus.PlusShare;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private static final int REQ_SELECT_PHOTO = 1;
    private static final int REQ_START_SHARE = 2;

    private CallbackManager callbackManager;
    private LoginManager loginManager;
    String Description ="Arriving in #Rotterdam #today! 25th November (2PM - 8PM) Hilton Rotterdam Weena 10, Rotterdam (+31107108005) Mobile: +44-7816-107-472 #tailored #bespoke #suit #men #menswear #formal #formalwear #tailor #customsuit #tailormade #fashion #suits";
    ImageButton shareImageButtonFacebook;
    ImageButton shareImageButtonGoogle;
    @Override
    protected void onCreate(Bundle savedInstanceState){

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            shareImageButtonFacebook = (ImageButton) findViewById(R.id.shareImageButtonFacebook);
            shareImageButtonGoogle = (ImageButton) findViewById(R.id.shareImageButtonGoogle);
            shareImageButtonFacebook.setOnClickListener(this);
            shareImageButtonGoogle.setOnClickListener(this);

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("project.pratik.facebookshare", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    private void sharePhotoToFacebook(){
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.ashsuit);
        //Uri.parse("https://www.facebook.com/<user_name_here>");
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .setCaption(Description)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        ShareApi.share(content, null);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.shareImageButtonFacebook){

            FacebookSdk.sdkInitialize(getApplicationContext());
            callbackManager = CallbackManager.Factory.create();
            List<String> permissionNeeds = Arrays.asList("publish_actions");

            //this loginManager helps you eliminate adding a LoginButton to your UI
            loginManager = LoginManager.getInstance();

            loginManager.logInWithPublishPermissions(this, permissionNeeds);

            loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
            {
                @Override
                public void onSuccess(LoginResult loginResult)
                {
                    sharePhotoToFacebook();
                }
                @Override
                public void onCancel()
                {
                    System.out.println("onCancel");
                }
                @Override
                public void onError(FacebookException exception)
                {
                    System.out.println("onError");
                }
            });

        }if(view.getId() == R.id.shareImageButtonGoogle){

            Intent shareIntent = new PlusShare.Builder(this)
                    .setType("text/plain")
                    .setText(Description)
                    .addStream(Uri.parse("android.resource://project.pratik.facebookshare/drawable/ashsuit"))
                    .getIntent();
            startActivityForResult(shareIntent, 0);

//            Intent photoPicker = new Intent(Intent.ACTION_PICK);
//            photoPicker.setType("video/*, image/*");
//            startActivityForResult(photoPicker, REQ_SELECT_PHOTO);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        callbackManager.onActivityResult(requestCode, resultCode, intent);

//        if(requestCode == REQ_SELECT_PHOTO) {
//            if(resultCode == RESULT_OK) {
//                Uri selectedImage = intent.getData();
//                ContentResolver cr = this.getContentResolver();
//                String mime = cr.getType(selectedImage);
//
//                PlusShare.Builder share = new PlusShare.Builder(this);
//                share.setText(Description);
//                share.addStream(selectedImage);
//                share.setType(mime);
//                startActivityForResult(share.getIntent(), REQ_START_SHARE);
//            }
//        }
    }
}
