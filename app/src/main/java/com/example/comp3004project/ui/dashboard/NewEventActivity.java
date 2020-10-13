package com.example.comp3004project.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.comp3004project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NewEventActivity extends AppCompatActivity {

    private Button submit_button;
    private TextView food_name, calories, description;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    DatabaseReference test = database.getReference("test");
    private static final String TAG = "NewEventActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        myRef.setValue("Hello, World!");
        submit_button = (Button) findViewById(R.id.submit_event);
        food_name = (TextView) findViewById(R.id.input_foodName);
        calories = (TextView) findViewById(R.id.input_carlories);
        description = (TextView) findViewById(R.id.input_description);
//        rootNode = FirebaseDatabase.getInstance();
//        reference = rootNode.getReference().child("hello");
//
//        reference.setValue("dum");

//        submit_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                Date date = new Date();
//                String time = formatter.format(date);
//                System.out.println(food_name.getText().toString() + "\n" + calories.getText().toString() + "\n" + description.getText().toString());
//                HelperNewEvent new_event = new HelperNewEvent(food_name.toString(), calories.toString(), description.toString());
////                reference.setValue("dum");
//
//
//            }
//        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}