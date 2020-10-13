package com.example.comp3004project.ui.dashboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.comp3004project.R;

public class CreateEventActivity extends AppCompatActivity {

    private Button create_your_own;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        create_your_own = (Button) findViewById(R.id.create_event);

        create_your_own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(CreateEventActivity.this, NewEventActivity.class);
            startActivity(intent);
            }
        });


    }

}