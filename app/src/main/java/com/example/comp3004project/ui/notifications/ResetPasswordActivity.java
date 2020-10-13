package com.example.comp3004project.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class ResetPasswordActivity extends AppCompatActivity {
    EditText inputPassword;
    Button resetPassword;
    ImageButton returnPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputPassword = findViewById(R.id.editTextTextPersonName6);
        resetPassword = findViewById(R.id.button7);
        returnPage = findViewById(R.id.imageButton2);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String password = inputPassword.getText().toString();


                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Password is Empty!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() < 6){
                    Toast.makeText(getApplicationContext(),"Password has to be greater than 6 characters!",Toast.LENGTH_SHORT).show();
                    return;
                }



                user.updatePassword(password)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Reset Password Succeeded",Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });

        returnPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPasswordActivity.this, PersonalFileActivity.class));
            }
        });

    }


}