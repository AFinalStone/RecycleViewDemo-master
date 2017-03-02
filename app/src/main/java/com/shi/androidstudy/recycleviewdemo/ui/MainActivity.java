package com.shi.androidstudy.recycleviewdemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shi.androidstudy.recycleviewdemo.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_listView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FragmentActivity.class);
                intent.putExtra(FragmentActivity.TypeRecycler,FragmentActivity.TypeRecycler_ListView);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_gridView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FragmentActivity.class);
                intent.putExtra(FragmentActivity.TypeRecycler,FragmentActivity.TypeRecycler_GridView);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_staggered).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FragmentActivity.class);
                intent.putExtra(FragmentActivity.TypeRecycler,FragmentActivity.TypeRecycler_Staggered);
                startActivity(intent);
            }
        });

    }


}
