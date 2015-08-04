package com.htech.restaurant.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.htech.restaurant.R;
import com.htech.restaurant.fragment.FragmentIndex;
import com.htech.restaurant.fragment.SelectTableFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;


public class MainActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle bundle) {
        // set header data
        setDrawerHeaderImage(R.drawable.mat2);
        setUsername("My App Name");
        setUserEmail("My version build");
        //setFirstAccountPhoto(getResources().getDrawable(R.drawable.photo));

        // create sections
        this.addSection(newSection("Select Table", new SelectTableFragment()));
        this.addSection(newSection("Section 2", new FragmentIndex()));
        this.addSection(newSection("Section 3",R.drawable.ic_mic_white_24dp,new SelectTableFragment()).setSectionColor(Color.parseColor("#9c27b0")));
        this.addSection(newSection("Section", R.drawable.ic_hotel_grey600_24dp, new SelectTableFragment()).setSectionColor(Color.parseColor("#03a9f4")));

        // create bottom section
        this.addBottomSection(newSection("Bottom Section", R.drawable.ic_settings_black_24dp, new Intent(this, Settings.class)));
        // add pattern
       // this.setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();


        // set the indicator for child fragments
        // N.B. call this method AFTER the init() to leave the time to instantiate the ActionBarDrawerToggle
        this.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
    }


    @Override
    public void onHomeAsUpSelected() {
        // when the back arrow is selected this method is called
    }
}
