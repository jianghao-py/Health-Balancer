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

public class SetGenderActivity extends AppCompatActivity {
    EditText getInputGender;
    Button setGender;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_gender);

        getInputGender = findViewById(R.id.editTextTextPersonName10);
        setGender = findViewById(R.id.button15);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String uid = user.getUid();

        setGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = getInputGender.getText().toString();

                mDatabase.child("users").child(uid).child("Gender").setValue(gender);
            }
        });


    }
}