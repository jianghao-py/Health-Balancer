package com.example.comp3004project.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.comp3004project.LoginFunction.User;
import com.example.comp3004project.R;
import com.example.comp3004project.ui.home.EventAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CaloriesSuggestion extends AppCompatActivity {

    TextView calorieSuggestion,showBMR,finalSuggestion;
    RadioGroup genderSelection,exerciseSelection,keepOrLose;
    //Button saveData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_suggestion);


        calorieSuggestion = findViewById(R.id.CaloriesSuggestion);
        genderSelection  = findViewById(R.id.genderSelection);
        exerciseSelection = findViewById(R.id.exerciseSelection);
        showBMR = findViewById(R.id.BMR);
        keepOrLose = findViewById(R.id.keepOrLose);
        finalSuggestion = findViewById(R.id.textView37);



        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference personalReference = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Personal");


        personalReference.addValueEventListener(new ValueEventListener() {
            int age,height,weight;
            double BMR;
            int Calories;
            int finalCalories;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> userArrayList = new ArrayList<>();
                for (DataSnapshot personalSnapshot : dataSnapshot.getChildren()) {
                   String data = personalSnapshot.getValue(String.class);
                   userArrayList.add(data);
                }
                String ageString = userArrayList.get(0);
                String heightString = userArrayList.get(2);
                String weightString = userArrayList.get(3);
                age = Integer.parseInt(ageString);
                height = Integer.parseInt(heightString);
                weight = Integer.parseInt(weightString);


                genderSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.radioButton2){
                            BMR = 66.47 + (13.7 * weight) + (5* height) - (6.8 * age);
                            int BMRInt = (int) BMR;
                            String BMRString = String.valueOf(BMRInt);
                            showBMR.setText(BMRString);
                            exerciseSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                    if(checkedId == R.id.radioButton3){
                                        Calories = (int) (BMR * 1.2);
                                        String CaloriesString = String.valueOf(Calories);
                                        calorieSuggestion.setText(CaloriesString);
                                        keepOrLose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (checkedId == R.id.radioButton7){
                                                    finalCalories = Calories;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);


                                                }else {
                                                    finalCalories = Calories - 300;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }
                                            }
                                        });
                                    }else if(checkedId == R.id.radioButton4){
                                        Calories = (int) (BMR * 1.375);
                                        String CaloriesString = String.valueOf(Calories);
                                        calorieSuggestion.setText(CaloriesString);
                                        keepOrLose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (checkedId == R.id.radioButton7){
                                                    finalCalories = Calories;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }else {
                                                    finalCalories = Calories - 300;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }
                                            }
                                        });

                                    }else if(checkedId == R.id.radioButton5){
                                       Calories =(int) (BMR * 1.55);
                                        String CaloriesString = String.valueOf(Calories);
                                        calorieSuggestion.setText(CaloriesString);
                                        keepOrLose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (checkedId == R.id.radioButton7){
                                                    finalCalories = Calories;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }else {
                                                    finalCalories = Calories - 300;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }
                                            }
                                        });

                                    }else {
                                       Calories =(int) (BMR * 1.725);
                                        String CaloriesString = String.valueOf(Calories);
                                        calorieSuggestion.setText(CaloriesString);
                                        keepOrLose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (checkedId == R.id.radioButton7){
                                                    finalCalories = Calories;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }else {
                                                    finalCalories = Calories - 300;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }else {
                            BMR = 655.1 + (9.6 * weight) + (1.8 *height) - (4.7 * age);
                            int BMRInt = (int) BMR;
                            String BMRString = String.valueOf(BMRInt);
                            showBMR.setText(BMRString);
                            exerciseSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                    if(checkedId == R.id.radioButton3){
                                        Calories = (int) (BMR * 1.2);
                                        String CaloriesString = String.valueOf(Calories);
                                        calorieSuggestion.setText(CaloriesString);
                                        keepOrLose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (checkedId == R.id.radioButton7){
                                                    finalCalories = Calories;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }else {
                                                    finalCalories = Calories - 300;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }
                                            }
                                        });
                                    }else if(checkedId == R.id.radioButton4){
                                        Calories = (int) (BMR * 1.375);
                                        String CaloriesString = String.valueOf(Calories);
                                        calorieSuggestion.setText(CaloriesString);
                                        keepOrLose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (checkedId == R.id.radioButton7){
                                                    finalCalories = Calories;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }else {
                                                    finalCalories = Calories - 300;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }
                                            }
                                        });

                                    }else if(checkedId == R.id.radioButton5){
                                        Calories =(int) (BMR * 1.55);
                                        String CaloriesString = String.valueOf(Calories);
                                        calorieSuggestion.setText(CaloriesString);
                                        keepOrLose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (checkedId == R.id.radioButton7){
                                                    finalCalories = Calories;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }else {
                                                    finalCalories = Calories - 300;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }
                                            }
                                        });

                                    }else {
                                        Calories = (int) (BMR * 1.725);
                                        String CaloriesString = String.valueOf(Calories);
                                        calorieSuggestion.setText(CaloriesString);
                                        keepOrLose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (checkedId == R.id.radioButton7){
                                                    finalCalories = Calories;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }else {
                                                    finalCalories = Calories - 300;
                                                    finalSuggestion.setText("Calories Intake Suggestion: "+finalCalories);

                                                }
                                            }
                                        });
                                    }
                                }
                            });


                        }
                    }
                });



            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







      //  calorieSuggestion.setText(height);
        //height = userArrayList.get(2);
       // calorieSuggestion.setText(height);


        /*
        getDatabaseWeight();
        getDatabaseHeight();
        getDatabaseGender();
        getDatabaseAge();

         */


        /*
        height = showHeight.getText().toString();
        weight = showWeight.getText().toString();
        age = showAge.getText().toString();
        gender = showGender.getText().toString();

         */

        /*
        if(height.isEmpty()){
            calorieSuggestion.setText("Missing height, Please go to the Personal file to edit first");
        }else if(weight.isEmpty()){
            calorieSuggestion.setText("Missing height, Please go to the Personal file to edit first");
        }else if(age.isEmpty()){
            calorieSuggestion.setText("Missing height, Please go to the Personal file to edit first");
        }else if(gender.isEmpty()){
            calorieSuggestion.setText("Missing height, Please go to the Personal file to edit first");
        }else {
            calorieSuggestion.setText("All information is completed");
        }

         */



    }







/*
    public void getDatabaseHeight(){
        myRef = database.getReference("users").child(uid).child("Height");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                String height = value;
                showHeight.setText(value);
                calorieSuggestion.setText(height);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getDatabaseWeight(){
        myRef = database.getReference("users").child(uid).child("Weight");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                showWeight.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getDatabaseAge(){
        myRef = database.getReference("users").child(uid).child("Age");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
               showAge.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


*/
}