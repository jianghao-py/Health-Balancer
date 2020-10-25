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

public class SetAgeActivity extends AppCompatActivity {
    EditText getInputAge;
    Button setAge;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_age);

        getInputAge = findViewById(R.id.editTextTextPersonName9);
        setAge = findViewById(R.id.button14);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String uid = user.getUid();


        setAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = getInputAge.getText().toString();
                mDatabase.child("users").child(uid).child("Age").setValue(age);
            }
        });


    }
}