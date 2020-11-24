package com.example.comp3004project.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp3004project.R;
import com.example.comp3004project.ui.dashboard.HelperNewEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RecordActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<HelperNewEvent> eventArrayList = new ArrayList<>();
    EventAdapter eventAdapter;
    Context myContext;
    TextView showStartDate,showEndDate;
    Button setStartDate,setEndDate,searchDate;

    Date startDate,endDate;
    Calendar calendar = Calendar.getInstance();
    Locale ca = new Locale("en","CA");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY",ca);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        myContext = this;
        recyclerView = findViewById(R.id.RecyclerView1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        showStartDate = findViewById(R.id.seclectStartDateFood);
        showEndDate = findViewById(R.id.selectEndDateFood);
        setStartDate = findViewById(R.id.foodStartDateButton);
        setEndDate = findViewById(R.id.foodEndDateButton);
        searchDate = findViewById(R.id.foodSearchButton);

        searchDate.setEnabled(false);
        setStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                       showStartDate.setText(simpleDateFormat.format(calendar.getTime()));
                        startDate = calendar.getTime();

                        if(showEndDate.getText().toString().isEmpty()){
                            searchDate.setEnabled(false);
                        }else {
                            searchDate.setEnabled(true);
                        }

                        }

                    },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        setEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        showEndDate.setText(simpleDateFormat.format(calendar.getTime()));
                        endDate = calendar.getTime();

                        if(showStartDate.getText().toString().isEmpty()){
                            searchDate.setEnabled(false);
                        }else {
                            searchDate.setEnabled(true);
                        }


                    }

                    },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        searchDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


                DatabaseReference eventReference = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Events").child("Food");

                Query query = eventReference.orderByChild("date").startAt(startDate.getTime()).endAt(endDate.getTime());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        eventArrayList.clear();
                        for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                            HelperNewEvent events = eventSnapshot.getValue(HelperNewEvent.class);

                            eventArrayList.add(events);

                        }
                        eventAdapter = new EventAdapter(eventArrayList,myContext);
                        recyclerView.setAdapter(eventAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                if(startDate.getTime() > endDate.getTime() || endDate.getTime() < startDate.getTime()){
                    Toast.makeText(myContext,"Date Selected Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

       // readEventFromFireBase();



    }

    private  void readEventFromFireBase(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


        DatabaseReference eventReference = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Events").child("Food");

        eventReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    HelperNewEvent events = eventSnapshot.getValue(HelperNewEvent.class);

                    eventArrayList.add(events);

                }
                eventAdapter = new EventAdapter(eventArrayList,myContext);
                recyclerView.setAdapter(eventAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}