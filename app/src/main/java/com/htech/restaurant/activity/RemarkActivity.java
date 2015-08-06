package com.htech.restaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.htech.restaurant.R;
import com.htech.restaurant.adapter.SubCategoryAdapter;

public class RemarkActivity extends ActionBarActivity {

    private Button mbtnDone;
    private EditText mRemarkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remark);
        mbtnDone = (Button) findViewById(R.id.activity_remark_btnDone);
        mRemarkText = (EditText) findViewById(R.id.activity_remark_etRemarkText);
        mbtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra(SubCategoryAdapter.REMARK_TEXT,mRemarkText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}
