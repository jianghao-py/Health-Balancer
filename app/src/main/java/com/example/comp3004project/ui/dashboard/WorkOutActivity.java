package com.example.comp3004project.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comp3004project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkOutActivity extends AppCompatActivity {
    EditText inputWorkOut,inputWorkOutCalories;
    Button saveEvent;
    String getDate,getSelect,workOutString,workOutCalorieString;
    private WorkOutActivity myContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);

        inputWorkOut = findViewById(R.id.editTextWork);
        inputWorkOutCalories = findViewById(R.id.editTextWorkCalories);
        saveEvent = findViewById(R.id.button16);

        Intent intent = getIntent();
        getDate = intent.getStringExtra("Date");
        getSelect = intent.getStringExtra("Select");

        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workOutString = inputWorkOut.getText().toString();
                workOutCalorieString = inputWorkOutCalories.getText().toString();
                saveDataInFirebase();
            }
        });

        myContext = this;
    }

    private  void saveDataInFirebase(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference rootReference = firebaseDatabase.getReference();


        DatabaseReference eventReference = rootReference.child("users").child(currentUser.getUid()).child("Events").child("WorkOut");

        DatabaseReference newEventReference = eventReference.push();

        HelperNewEvent newEvent = new HelperNewEvent(getSelect,getDate,workOutString,workOutCalorieString);

        newEventReference.setValue(newEvent).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(WorkOutActivity.this,"Event submitted in Firebase",Toast.LENGTH_SHORT).show();
                    myContext.finish();
                }
                else {
                    Toast.makeText(WorkOutActivity.this,"Event could not submit in Firebase",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}