package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class Functions extends AppCompatActivity {

    Button allPeople, byPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);

        allPeople = findViewById(R.id.buttonAll);
        byPosition = findViewById(R.id.buttonPosition);

        allPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "allpeople";
                Intent i = new Intent(Functions.this,RVActivity.class);
                i.putExtra("getallpeople", str);
                startActivity(i);
            }
        });
        byPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "position";
                Intent i = new Intent(Functions.this,RVActivity.class);
                i.putExtra("getallpeople", str);
                startActivity(i);
            }
        });
    }

}