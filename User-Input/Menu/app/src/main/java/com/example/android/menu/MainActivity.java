package com.example.android.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void printToLogs(View view) {
        TextView menuItem = (TextView) findViewById(R.id.menu_item_1);
        Log.v("MainActivity", menuItem.getText().toString());
        menuItem = (TextView) findViewById(R.id.menu_item_2);
        Log.v("MainActivity", menuItem.getText().toString());
        menuItem = (TextView) findViewById(R.id.menu_item_3);
        Log.v("MainActivity", menuItem.getText().toString());
    }
}
