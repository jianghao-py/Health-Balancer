package com.example.comp3004project.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.comp3004project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetWeightAndHeight extends AppCompatActivity {
    EditText getInputHeight,getInputWeight;
    Button setHeight,setWeight;
    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_weight_and_height);

        getInputHeight = findViewById(R.id.editTextNumber);
        getInputWeight = findViewById(R.id.editTextNumber2);

        setHeight = findViewById(R.id.button12);
        setWeight = findViewById(R.id.button13);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String uid = user.getUid();



        setHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = getInputHeight.getText().toString();

                mDatabase.child("users").child(uid).child("Height").setValue(height);

            }
        });

        setWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = getInputWeight.getText().toString();

                mDatabase.child("users").child(uid).child("Weight").setValue(weight);
            }
        });



    }
}