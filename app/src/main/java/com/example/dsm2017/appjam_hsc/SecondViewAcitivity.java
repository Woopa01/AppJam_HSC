package com.example.dsm2017.appjam_hsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by dsm2017 on 2017-12-17.
 */

public class SecondViewAcitivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondview);

        linearLayout = (LinearLayout) findViewById(R.id.second_petition2);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondViewAcitivity.this,SecondViewAcitvity2.class);
                startActivity(intent);
            }
        });

    }

}
