package com.htech.restaurant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.htech.restaurant.MyApplication;
import com.htech.restaurant.R;
import com.htech.restaurant.adapter.SubCategoryAdapter;
import com.htech.restaurant.db.DatabaseService;
import com.htech.restaurant.vos.SubMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuDetailActivity extends AppCompatActivity implements SubCategoryAdapter.OnItemClickListener {

    private String TAG = MenuDetailActivity.class.getSimpleName();
    private SubCategoryAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private DatabaseService mDatabaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.menu_detail_toolbar);
        // setSupportActionBar(mToolbar);

        int menuId = getIntent().getIntExtra("MenuId", 0);
        Log.d(TAG, "selected menu id:" + menuId);

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_menu_detail_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSubMenuData(menuId);

    }

    /**
     * Fetch table list
     */
    private void getSubMenuList() {
        List<SubMenu> subCategories = new ArrayList<SubMenu>();

        for (int i = 0; i < 12; i++) {
            int id = (i + 1);
            SubMenu category = new SubMenu();
            category.setId(id);
            category.setSubCatName("" + id);
            subCategories.add(category);
        }
        mAdapter = new SubCategoryAdapter(this, subCategories, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClickListener(int position) {
        Log.d(TAG, "add item:" + position);
    }

    public void getSubMenuData(int id) {
        try {
            mDatabaseService = DatabaseService.getInstance(MenuDetailActivity.this);
            MyApplication.SUBMENU = mDatabaseService.getSubMenu(id);
            Log.d(TAG, "sub menu size:" + MyApplication.SUBMENU.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (MyApplication.SUBMENU.size() > 0) {
            mAdapter = new SubCategoryAdapter(this, MyApplication.SUBMENU, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            Toast.makeText(MenuDetailActivity.this, "NO DATA", Toast.LENGTH_SHORT).show();
        }


    }
}
