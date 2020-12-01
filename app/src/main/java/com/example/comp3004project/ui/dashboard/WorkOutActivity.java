package com.example.comp3004project.ui.dashboard;

import androidx.annotation.NonNull;
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

public class WorkOutActivity extends AppCompatActivity {
    EditText inputWorkOut,inputWorkOutCalories;
    Button saveEvent,setDate;
    String getSelect,workOutString,workOutCalorieString;
    private WorkOutActivity myContext;
    TextView showDate;


    Calendar calendar = Calendar.getInstance();
    Locale ca = new Locale("en","CA");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY",ca);
    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);

        inputWorkOut = findViewById(R.id.editTextWork);
        inputWorkOutCalories = findViewById(R.id.editTextWorkCalories);
        saveEvent = findViewById(R.id.button16);
        setDate = findViewById(R.id.button19);
        showDate = findViewById(R.id.textView21);

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

        //HelperNewEvent newEvent = new HelperNewEvent(getSelect,date.getTime(),workOutString,workOutCalorieString);
        HelpWorkOut helpWorkOut = new HelpWorkOut(getSelect,date.getTime(),workOutString,Integer.parseInt(workOutCalorieString));

        newEventReference.setValue(helpWorkOut).addOnCompleteListener(new OnCompleteListener<Void>() {
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