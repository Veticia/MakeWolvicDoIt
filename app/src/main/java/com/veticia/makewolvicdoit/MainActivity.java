package com.veticia.makewolvicdoit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity {
    public void closeApp() {
        finishAffinity();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String packageName = "com.igalia.wolvic";
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            Intent intentGet = getIntent();
            Uri requested = intentGet.getData();
            if (requested != null) {
                Log.e("data", requested.toString());
                Uri uri = Uri.parse("wolvic://com.igalia.wolvic/?url=" + requested);
                Intent intentOpenLink = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentOpenLink);
                closeApp();
            }else{
                //it shouldn't be normally possible to start this app but if you manage let's just open Wolvic
                startActivity(launchIntent);
                closeApp();
            }
        }
        // Wolvic is not installed on the device
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.main_view);
        ImageView close = findViewById(R.id.close);
        close.setOnClickListener(v -> closeApp());
    }
}