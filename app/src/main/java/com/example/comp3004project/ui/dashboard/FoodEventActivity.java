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

public class FoodEventActivity extends AppCompatActivity {
    EditText inputMainFood, inputMainFoodCalorie, inputDrink,inputDrinkCalorie;
    Button saveEvent;
    String getDate,getSelect,mainFoodString,drinkString,mainFoodCalorieString,drinkCalorieString;
    private FoodEventActivity myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_event);

        inputMainFood = findViewById(R.id.editTextMainFood);
        inputMainFoodCalorie = findViewById(R.id.editTextMainFoodCalorie);
        inputDrink = findViewById(R.id.editTextDrink);
        inputDrinkCalorie = findViewById(R.id.editTextDrinkCalorie);
        saveEvent = findViewById(R.id.SaveEventButton);

        Intent intent = getIntent();
        getDate = intent.getStringExtra("Date");
        getSelect = intent.getStringExtra("Select");


        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveDataInFirebase();
                mainFoodString = inputMainFood.getText().toString();
                drinkString = inputDrink.getText().toString();
                mainFoodCalorieString = inputMainFoodCalorie.getText().toString();
                drinkCalorieString = inputDrinkCalorie.getText().toString();


                saveDataInFirebase();
            }
        });

        myContext = this;

    }

    private  void saveDataInFirebase(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference rootReference = firebaseDatabase.getReference();


        DatabaseReference eventReference = rootReference.child("users").child(currentUser.getUid()).child("Events").child("Food");

        DatabaseReference newEventReference = eventReference.push();

        HelperNewEvent newEvent = new HelperNewEvent(getSelect,getDate,mainFoodString,drinkString,mainFoodCalorieString,drinkCalorieString);

        newEventReference.setValue(newEvent).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(FoodEventActivity.this,"Event submitted in Firebase",Toast.LENGTH_SHORT).show();
                    myContext.finish();
                }
                else {
                    Toast.makeText(FoodEventActivity.this,"Event could not submit in Firebase",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}