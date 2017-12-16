package com.example.dsm2017.appjam_hsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dsm2017 on 2017-12-16.
 */

public class MainActivity extends AppCompatActivity {

    FloatingActionButton write_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write_button = (FloatingActionButton) findViewById(R.id.write_button);

        write_button.setOnClickListener(new FloatingActionButton.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WriteActivity.class);
                startActivity(intent);
            }
        });

    }
}
