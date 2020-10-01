package com.example.comp3004project.LoginFunction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comp3004project.MainActivity;
import com.example.comp3004project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Resgister extends AppCompatActivity {
    private EditText inputEmail,inputPassword,inputUsername;
    private Button signUpButton,goLoginButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);

        inputUsername = (EditText) findViewById(R.id.editTextTextPersonName);
        inputEmail = (EditText) findViewById(R.id.editTextTextPersonName2);
        inputPassword = (EditText) findViewById(R.id.editTextTextPersonName3);
        signUpButton = (Button) findViewById(R.id.button2);
        goLoginButton = (Button) findViewById(R.id.button3);

        mAuth = FirebaseAuth.getInstance();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String username= inputUsername.getText().toString().trim();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(),"Username is Empty!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(emailInput)){
                    Toast.makeText(getApplicationContext(),"Email is Empty!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Password is Empty!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() < 6){
                    Toast.makeText(getApplicationContext(),"Password has to be greater than 6 characters!",Toast.LENGTH_SHORT).show();
                    return;
                }

                createAccount();

            }
        });

        goLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Resgister.this,Login.class));
                finish();
            }
        });
    }


    public void createAccount(){
        mAuth.createUserWithEmailAndPassword(inputEmail.getText().toString(), inputPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Resgister.this,"createUserWithEmail:onComplete:"+ task.isSuccessful(),Toast.LENGTH_SHORT).show();

                        if (! task.isSuccessful()){
                            Toast.makeText(Resgister.this,"SignUp Fail"+task.getException(),Toast.LENGTH_LONG).show();
                            Log.e("MyTag",task.getException().toString());
                        }else {
                            startActivity(new Intent(Resgister.this,MainActivity.class));
                            finish();
                        }
                    }


                });
    }
}