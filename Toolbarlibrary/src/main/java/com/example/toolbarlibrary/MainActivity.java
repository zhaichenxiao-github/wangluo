package com.example.toolbarlibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.jdsjlzx.recyclerview.LRecyclerView;

public class MainActivity extends AppCompatActivity {

    private LRecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rv = (LRecyclerView) findViewById(R.id.rv);

    }
}