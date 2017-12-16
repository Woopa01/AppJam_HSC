package com.example.dsm2017.appjam_hsc;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by dsm2017 on 2017-12-17.
 */

public class firstViewActivity extends AppCompatActivity{

    Button button;
    ImageView main_link;
    TextView count,count2;
    LinearLayout Main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_petition);

        count = (TextView) findViewById(R.id.Number_of_participants);
        button = (Button) findViewById(R.id.first_petition_button);
        main_link = (ImageView) findViewById(R.id.view_main_link2);

                main_link.setOnClickListener(new ImageView.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstViewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(firstViewActivity.this);
                View complete_dialog = getLayoutInflater().inflate(R.layout.dialog_petition, null);
                View main_link = complete_dialog.findViewById(R.id.complete_mainlink2);
                dialog.setContentView(complete_dialog);
                dialog.show();

                main_link.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(firstViewActivity.this,MainActivity.class);
                        startActivity(intent);
                        dialog.cancel();
                    }

                });

                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });

    }

}
