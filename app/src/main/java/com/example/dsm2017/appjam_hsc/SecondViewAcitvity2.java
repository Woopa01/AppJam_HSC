package com.example.dsm2017.appjam_hsc;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by dsm2017 on 2017-12-17.
 */

public class SecondViewAcitvity2 extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_petition);

        button = (Button) findViewById(R.id.second_petition_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SecondViewAcitvity2.this);
                View complete_dialog = getLayoutInflater().inflate(R.layout.dialog_petition, null);
                View main_link = complete_dialog.findViewById(R.id.complete_mainlink2);
                dialog.setContentView(complete_dialog);
                dialog.show();

                main_link.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }

                });

                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });

    }
}
