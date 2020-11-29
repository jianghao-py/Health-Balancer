package com.example.comp3004project.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp3004project.LoginFunction.Resgister;
import com.example.comp3004project.LoginFunction.User;
import com.example.comp3004project.MainActivity;
import com.example.comp3004project.R;
import com.example.comp3004project.ui.dashboard.HelperNewEvent;
import com.example.comp3004project.ui.home.WorkOutAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PersonalFileActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    String uid = user.getUid();
    DatabaseReference myRef;

    Button setNameButton, changePasswordButton, returnButton, setAgeButton, setGenderButton, setWeightAndHeightButton;
    TextView userNameTextView, genderTextView, ageTextView, heightTextView, weightTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);

        //Buttons
        setNameButton = findViewById(R.id.button10);
        changePasswordButton = findViewById(R.id.button11);
        returnButton = findViewById(R.id.button);
        setGenderButton = findViewById(R.id.button4);

        //TextViews
        userNameTextView = findViewById(R.id.textView5);
        genderTextView = findViewById(R.id.textView3);
        ageTextView = findViewById(R.id.textView6);
        heightTextView = findViewById(R.id.textView8);
        weightTextView = findViewById(R.id.textView7);

        //Set Name Button (Go to Change Name page)
        setNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalFileActivity.this, SetNameActivity.class));

            }
        });

        //back to previous page
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalFileActivity.this, MainActivity.class));

            }
        });

        //get User name
        getFireBaseUserName();


        //go ResetPasswordActivity
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalFileActivity.this, ResetPasswordActivity.class));
            }
        });


        //go setGender
        setGenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalFileActivity.this, SetGenderActivity.class));

            }
        });



        getDatabaseWeight();
        getDatabaseGender();
        getDatabaseAge();
        getDatabaseHeight();


    }


    public void getFireBaseUserName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        for (UserInfo profile : user.getProviderData()) {
            String name = profile.getDisplayName();
            userNameTextView.setText("Name：" + name);

        }
    }

    public void getDatabaseHeight() {

        myRef = database.getReference("users").child(uid).child("Personal").child("height");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                heightTextView.setText("Height: " + value + " CM");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getDatabaseWeight() {
        myRef = database.getReference("users").child(uid).child("Personal").child("weight");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                weightTextView.setText("Weight: " + value + " KG");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getDatabaseAge() {
        myRef = database.getReference("users").child(uid).child("Personal").child("age");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                ageTextView.setText("Age: " + value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getDatabaseGender() {
        myRef = database.getReference("users").child(uid).child("Personal").child("gender");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                genderTextView.setText("Gender: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





}