package project.pratik.facebookshare;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import project.pratik.facebookshare.Adapter.ShowMenuAdapter;
import project.pratik.facebookshare.Connectivity.FetchMenu;
import project.pratik.facebookshare.InternetConnectivity.NetworkChangeReceiver;
import project.pratik.facebookshare.Model.ShowMenuItems;

import static android.R.attr.path;

public class MainActivity2 extends BaseActivity implements View.OnClickListener {

    public List<ShowMenuItems> showMenuItems = new ArrayList<ShowMenuItems>();
    RecyclerView recyclerView;
    RecyclerView.Adapter reviewAdapter;
    LinearLayoutManager linearLayoutManager;
    boolean doubleBackToExitPressedOnce = false;
    private int current_page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list);

        recyclerView = (RecyclerView) findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.smoothScrollToPosition(0);
        reviewAdapter = new ShowMenuAdapter(showMenuItems);
        recyclerView.setAdapter(reviewAdapter);

        try {

            FetchMenu fetchMenu = new FetchMenu(MainActivity2.this);
            fetchMenu.fetchTodaysMenu(showMenuItems,recyclerView, reviewAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onBackPressed() {
        MainActivity2.this.finish();
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        PackageManager pm = MainActivity2.this.getPackageManager();
        ComponentName component = new ComponentName(MainActivity2.this, NetworkChangeReceiver.class);
        pm.setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
    @Override
    protected void onResume() {
        super.onResume();
        PackageManager pm = MainActivity2.this.getPackageManager();
        ComponentName component = new ComponentName(MainActivity2.this, NetworkChangeReceiver.class);
        pm.setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.GET_ACTIVITIES);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public void onClick(View view) {
//        if(view.getId() == R.id.shareImageButton){
//            Intent sendIntent = new Intent();
//            sendIntent.setAction(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_TEXT,content);
//            sendIntent.putExtra(Intent.EXTRA_STREAM, path);
//            sendIntent.setType("text/plain");
//            Intent.createChooser(sendIntent,"Share via");
//            startActivity(sendIntent);
//        }
    }
}
