package com.example.comp3004project.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.comp3004project.MainActivity;
import com.example.comp3004project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SetNameActivity extends AppCompatActivity {
    EditText inputName;
    Button setNameButton;
    ImageButton goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name);

        inputName = findViewById(R.id.editTextTextPersonName);
        setNameButton = findViewById(R.id.button9);
        goBackButton = findViewById(R.id.imageButton);

        setNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(inputName.getText().toString())
                        .build();

                user.updateProfile(profileChangeRequest)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Change Successful",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SetNameActivity.this, PersonalFileActivity.class));
                                }
                            }
                        });
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetNameActivity.this, PersonalFileActivity.class));
            }
        });

    }
}