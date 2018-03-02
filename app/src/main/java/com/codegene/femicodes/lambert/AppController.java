package com.codegene.femicodes.lambert;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by femicodes on 2/2/2018.
 */

public class AppController extends Application {

    private static final String TAG = AppController.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

         Toast.makeText(getBaseContext(), "App Controller started", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: App Controller Started");
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference Ref = FirebaseDatabase.getInstance().getReference();
        Ref.keepSynced(true);
    }
}
