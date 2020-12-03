package com.example.comp3004project.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp3004project.LoginFunction.Login;
import com.example.comp3004project.MainActivity;
import com.example.comp3004project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FoodEventActivity extends AppCompatActivity {
    EditText inputMainFood, inputMainFoodCalorie, inputDrink,inputDrinkCalorie;
    Button saveEvent,setDate,goSearchFood;
    TextView showDate;
    String getSelect,mainFoodString,drinkString,mainFoodCalorieString,drinkCalorieString;
    private FoodEventActivity myContext;



    Calendar calendar = Calendar.getInstance();
    Locale ca = new Locale("en","CA");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY",ca);
    Date date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_event);

        inputMainFood = findViewById(R.id.editTextMainFood);
        inputMainFoodCalorie = findViewById(R.id.editTextMainFoodCalorie);
        inputDrink = findViewById(R.id.editTextDrink);
        inputDrinkCalorie = findViewById(R.id.editTextDrinkCalorie);
        saveEvent = findViewById(R.id.SaveEventButton);

        setDate = findViewById(R.id.button18);
        showDate = findViewById(R.id.textView2);
        goSearchFood = findViewById(R.id.button24);


        Intent intent = getIntent();
        getSelect = intent.getStringExtra("Select");




        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        showDate.setText(simpleDateFormat.format(calendar.getTime()));
                        date = calendar.getTime();
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        goSearchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodEventActivity.this, SearchFood.class));
            }
        });


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



        HelperNewEvent newEvent = new HelperNewEvent(getSelect,date.getTime(),mainFoodString,drinkString,Integer.parseInt(mainFoodCalorieString),Integer.parseInt(drinkCalorieString));


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