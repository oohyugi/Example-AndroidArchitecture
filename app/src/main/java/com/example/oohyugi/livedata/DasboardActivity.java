package com.example.oohyugi.livedata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DasboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        Button biasa = findViewById(R.id.biasa);
        biasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DasboardActivity.this,MainActivity1.class);
                startActivity(intent);
            }
        });

        Button livedata = findViewById(R.id.livedata);
        livedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DasboardActivity.this,MainLiveDataActivity.class);
                startActivity(intent);
            }
        });
    }
}
