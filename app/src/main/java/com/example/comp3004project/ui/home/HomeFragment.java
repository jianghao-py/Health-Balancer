package com.example.comp3004project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.comp3004project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView textViewTitle;
    FirebaseUser user;
    String FireBaseUserName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textViewTitle = root.findViewById(R.id.textView2);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        /*
        homeViewModel.getUserName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textViewTitle.setText(s);
            }
        });
         */


        user = FirebaseAuth.getInstance().getCurrentUser();
        for(UserInfo profile : user.getProviderData()){
            FireBaseUserName = profile.getUid();
            textViewTitle.setText("Welcome "+FireBaseUserName);
        }




    }





}