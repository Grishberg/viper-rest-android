package com.grishberg.viper_rest_android.presentation.main;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.grishberg.viper_rest_android.R;
import com.grishberg.viper_rest_android.presentation.common.Layout;

import butterknife.Bind;

@Layout(id = R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
