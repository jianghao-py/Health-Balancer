package com.example.comp3004project.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.comp3004project.R;
import com.example.comp3004project.ui.dashboard.HelperNewEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkOutRecordActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<HelperNewEvent> eventArrayList = new ArrayList<>();
    WorkOutAdapter workOutAdapter;
    Context myContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out_record);

        myContext = this;
        recyclerView = findViewById(R.id.RecyclerView2);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        readEventFromFireBase();
    }

    private  void readEventFromFireBase(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        //DatabaseReference eventReferenceFood = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Events").child("Food");
        //DatabaseReference eventReferenceWorkOut = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Events").child("WorkOut");

        DatabaseReference eventReference = firebaseDatabase.getReference().child("users").child(currentUser.getUid()).child("Events").child("WorkOut");

        eventReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HelperNewEvent events;
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    events = eventSnapshot.getValue(HelperNewEvent.class);

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


}