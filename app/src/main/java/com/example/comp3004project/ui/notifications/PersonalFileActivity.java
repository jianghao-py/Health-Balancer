package com.example.comp3004project.ui.notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp3004project.LoginFunction.Resgister;
import com.example.comp3004project.MainActivity;
import com.example.comp3004project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class PersonalFileActivity extends AppCompatActivity {

    Button setNameButton,changePasswordButton,returnButton;
    TextView userNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);


        setNameButton = findViewById(R.id.button10);
        changePasswordButton = findViewById(R.id.button11);
       // deleteAccountButton = findViewById(R.id.button12);
        userNameTextView = findViewById(R.id.textView5);
        returnButton = findViewById(R.id.button);

        //Set Name Button (Go to Change Name page)
        setNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalFileActivity.this, SetNameActivity.class));

            }
        });

        //back to previous page
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalFileActivity.this, MainActivity.class));

            }
        });

        //get User name
        getFireBaseUserName();

        /*
        //DeleteAccount
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"This Account Deleted",Toast.LENGTH_LONG).show();
                                    FirebaseAuth.getInstance().signOut();
                                }
                            }
                        });
            }
        });

         */

        //go ResetPasswordActivity
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalFileActivity.this, ResetPasswordActivity.class));
            }
        });


}


    public void getFireBaseUserName(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        for(UserInfo profile : user.getProviderData()) {
            String name = profile.getDisplayName();
            userNameTextView.setText(name);

        }
    }
}