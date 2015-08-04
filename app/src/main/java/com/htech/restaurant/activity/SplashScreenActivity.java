package com.htech.restaurant.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.WindowManager;

import com.htech.restaurant.MyApplication;
import com.htech.restaurant.R;
import com.htech.restaurant.db.DatabaseService;

import java.io.IOException;

public class SplashScreenActivity extends ActionBarActivity {
    private DatabaseService mDatabaseService;
    private static int SPLASH_TIME_OUT = 1000;
    private String TAG = SplashScreenActivity.class.getSimpleName();
    private static final int REQUEST_CODE_ENABLE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set splash screen full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);
        // Load menu data from database
        new LoadMainMenuAsync().execute();
    }


    /**
     * Load data from server
     */
    private class LoadMainMenuAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {

            // Create database instance
            try {
                mDatabaseService = DatabaseService.getInstance(SplashScreenActivity.this);
                MyApplication.MAINMENU = mDatabaseService.getMainMenu();
                Log.d(TAG,"main menu size:"+MyApplication.MAINMENU .size());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    // Start home screen
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }, SPLASH_TIME_OUT);


        }
    }

}
