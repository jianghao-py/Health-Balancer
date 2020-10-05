package com.example.comp3004project.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> firebaseUserName;
    String name;

    public HomeViewModel() {

    }

    public LiveData<String> getUserName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!= null){
            for(UserInfo profile:user.getProviderData()){
                name = profile.getDisplayName();
            }
        }
        if(firebaseUserName ==null){
            firebaseUserName = new MutableLiveData<>();
            firebaseUserName.setValue("Welcome "+name);
        }

        return firebaseUserName;
    }
}