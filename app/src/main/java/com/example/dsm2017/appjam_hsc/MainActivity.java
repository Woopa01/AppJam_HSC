package com.example.dsm2017.appjam_hsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dsm2017.appjam_hsc.ListView.ListViewAdapter;

import java.util.List;

/**
 * Created by dsm2017 on 2017-12-16.
 */

public class MainActivity extends AppCompatActivity {

    int flag = 0;
    FloatingActionButton write_button;
    LinearLayout first_petition,second_petition_string,second_petition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write_button = (FloatingActionButton) findViewById(R.id.write_button);
        first_petition = (LinearLayout) findViewById(R.id.first_petition);
        second_petition = (LinearLayout) findViewById(R.id.second_petition);
        second_petition_string = (LinearLayout) findViewById(R.id.second_petition_string);

        if(flag==0){
        second_petition.setVisibility(View.INVISIBLE);
        second_petition_string.setVisibility(View.INVISIBLE);
            }

        write_button.setOnClickListener(new FloatingActionButton.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WriteActivity.class);
                startActivity(intent);
            }
        });

        first_petition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, firstViewActivity.class);
                startActivity(intent);
            }
        });

        second_petition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondViewAcitivity.class);
                startActivity(intent);
                second_petition.setVisibility(View.VISIBLE);
                second_petition_string.setVisibility(View.VISIBLE);
            }
        });



    }

}
