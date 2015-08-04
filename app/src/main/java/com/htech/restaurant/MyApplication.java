package com.htech.restaurant;

import android.app.Application;

import com.htech.restaurant.vos.MainMenu;
import com.htech.restaurant.vos.SubMenu;

import java.util.ArrayList;

/**
 * Created by software on 8/4/15.
 */
public class MyApplication extends Application {

    public static ArrayList<MainMenu> MAINMENU = new ArrayList<MainMenu>();
    public static ArrayList<SubMenu> SUBMENU = new ArrayList<SubMenu>();
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
