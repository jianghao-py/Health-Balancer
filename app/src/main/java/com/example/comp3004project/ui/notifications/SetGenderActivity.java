package com.example.comp3004project.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comp3004project.LoginFunction.User;
import com.example.comp3004project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetGenderActivity extends AppCompatActivity {
    Button saveData;
    CheckBox maleCheckBox,femaleCheckBox;
    EditText getAge,getWeight,getHeight;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_gender);

        saveData = findViewById(R.id.button15);
        maleCheckBox =findViewById(R.id.checkBox2);
        femaleCheckBox = findViewById(R.id.checkBox3);
        getAge = findViewById(R.id.getAgeEditText);
        getWeight = findViewById(R.id.getWeightEditText);
        getHeight = findViewById(R.id.getHeightEditText);

        /*
        setGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String gender = getInputGender.getText().toString();
                if(maleCheckBox.isChecked()){
                    gender = "Male";
                }
                if(femaleCheckBox.isChecked()){
                    gender = "Female";
                }

                mDatabase.child("users").child(uid).child("Gender").setValue(gender);
            }
        });
         */

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataInFirebase();
            }
        });


    }

    private  void saveDataInFirebase() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference rootReference = firebaseDatabase.getReference();
        DatabaseReference personalReference = rootReference.child("users").child(currentUser.getUid()).child("Personal");

        if(maleCheckBox.isChecked()){
            gender = "Male";
        }
        if(femaleCheckBox.isChecked()){
            gender = "Female";
        }

        User user = new User(getAge.getText().toString(),gender,getWeight.getText().toString(),getHeight.getText().toString());

        personalReference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SetGenderActivity.this, "Event submitted in Firebase", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SetGenderActivity.this, "Event could not submit in Firebase", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}