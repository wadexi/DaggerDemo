package com.example.a073105.daggerdemo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.a073105.daggerdemo.R;
import com.example.a073105.daggerdemo.beans.Bean1;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = "SettingActivity";

    @Inject
    @Named("SettingActivity_title")
    String title;


    @Inject
    @Named("SettingActivity_Bean")
    Bean1 bean1;

    @Inject
    @Named("SettingActivity_Bean")
    Bean1 bean2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Log.d(TAG, "onCreate: bean1:" + bean1.toString() +  "  bean2：" + bean2.toString() );
    }

}
