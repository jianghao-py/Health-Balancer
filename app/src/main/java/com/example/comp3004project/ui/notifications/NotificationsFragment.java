package com.example.comp3004project.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.comp3004project.LoginFunction.Resgister;
import com.example.comp3004project.MainActivity;
import com.example.comp3004project.R;
import com.google.firebase.auth.FirebaseAuth;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    Button logoutButton;
    TextView textViewTitle;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        //初始化控件
        textViewTitle = root.findViewById(R.id.textView);
        logoutButton = root.findViewById(R.id.button6);

        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewTitle.setText(s);
            }
        });

         return root;
          */

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        textViewTitle = root.findViewById(R.id.textView);

        logoutButton = root.findViewById(R.id.logout);


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), Resgister.class));
            }
        });
    }
}