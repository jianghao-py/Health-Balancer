package com.example.comp3004project.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp3004project.R;
import com.example.comp3004project.ui.dashboard.HelpWorkOut;
import com.example.comp3004project.ui.dashboard.HelperNewEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class WorkOutRecordActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<HelpWorkOut> eventArrayList = new ArrayList<>();
    WorkOutAdapter workOutAdapter;
    Context myContext;
    TextView showStartDate,showEndDate;
    Button setStartDate,setEndDate,searchDate,showAll,refreshPage;

    Date startDate,endDate;
    Calendar calendar = Calendar.getInstance();
    Locale ca = new Locale("en","CA");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY",ca);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out_record);

        myContext = this;
        recyclerView = findViewById(R.id.RecyclerView2);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        showStartDate = findViewById(R.id.showStartDateWorkOut);
        showEndDate = findViewById(R.id.ShowEndDateWorkOut);
        setStartDate = findViewById(R.id.setStartDateWorkOut);
        setEndDate = findViewById(R.id.setEndDateWorkOut);
        searchDate = findViewById(R.id.searchDateWorkOut);
        showAll = findViewById(R.id.button20);
        refreshPage = findViewById(R.id.button26);

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


                DatabaseReference eventReference = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Events").child("WorkOut");

                Query query = eventReference.orderByChild("date").startAt(startDate.getTime()).endAt(endDate.getTime());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        eventArrayList.clear();
                        for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                            HelpWorkOut events = eventSnapshot.getValue(HelpWorkOut.class);
                            events.setRecordId(eventSnapshot.getKey());

                            eventArrayList.add(events);

                        }
                        workOutAdapter = new WorkOutAdapter(eventArrayList,myContext);
                        recyclerView.setAdapter(workOutAdapter);
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

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readEventFromFireBase();
            }
        });

        refreshPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRefreshPage();
            }
        });



    }

    private void setRefreshPage(){
        finish();
        startActivity( new Intent(this, RecordActivity.class));

    }




    private  void readEventFromFireBase(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


        DatabaseReference eventReference = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Events").child("WorkOut");

        eventReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HelpWorkOut events;
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    events = eventSnapshot.getValue(HelpWorkOut.class);
                    events.setRecordId(eventSnapshot.getKey());

                    eventArrayList.add(events);

                }
               workOutAdapter = new WorkOutAdapter(eventArrayList,myContext);
                recyclerView.setAdapter(workOutAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void  deleteFromFirebase(String recordID){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference eventReference = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Events").child("WorkOut");
        DatabaseReference findRecord = eventReference.child(recordID);

        findRecord.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(myContext,"Delete Successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(myContext,"Delete Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}