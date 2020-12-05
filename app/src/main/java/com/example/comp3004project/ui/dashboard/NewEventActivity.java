package com.example.comp3004project.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp3004project.LoginFunction.Resgister;
import com.example.comp3004project.MainActivity;
import com.example.comp3004project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NewEventActivity extends AppCompatActivity {

    List<String>data = null;
    ArrayAdapter<String> adapter;

    Button goNextPage;
    Spinner spinner;

    String getSpinner;
   // String saveSelect;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);


        spinner = findViewById(R.id.spinner);
        goNextPage = findViewById(R.id.GoNextPage);


        spinner.setPrompt("Select:");
        data = new ArrayList<String>();
        data.add("Breakfast");
        data.add("Lunch");
        data.add("Dinner");
        data.add("WorkOut");

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,this.data);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] types = data.toArray(new String[data.size()]);
              //  Toast.makeText(NewEventActivity.this,types[position],Toast.LENGTH_LONG).show();
                getSpinner = types[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        goNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getSpinner == "WorkOut"){
                    Intent i = new Intent(NewEventActivity.this, WorkOutActivity.class);
                    i.putExtra("Select",getSpinner);
                    startActivity(i);

                }
                else {
                    Intent i = new Intent(NewEventActivity.this, FoodEventActivity.class);
                    i.putExtra("Select",getSpinner);
                    startActivity(i);

                }
            }

        });




    }

    /*
    private  void saveDataInFirebase(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference rootReference = firebaseDatabase.getReference();

       // DatabaseReference uidReference = rootReference.child("users").child(currentUser.getUid());
       // uidReference.setValue("Events");

        DatabaseReference eventReference = rootReference.child("users").child(currentUser.getUid()).child("Events");

       DatabaseReference newEventReference = eventReference.push();

        HelperNewEvent newEvent = new HelperNewEvent(getSpinner);

      newEventReference.setValue(newEvent).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(NewEventActivity.this,"Event submitted in Firebase",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NewEventActivity.this,"Event could not submit in Firebase",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

     */

}


