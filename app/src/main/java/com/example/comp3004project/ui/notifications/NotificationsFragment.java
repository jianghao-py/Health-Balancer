package com.example.comp3004project.ui.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.comp3004project.LoginFunction.Resgister;
import com.example.comp3004project.R;
import com.example.comp3004project.ui.AlarmReceiver;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    Button logoutButton,goPersonalFileButton;
    Switch notify;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        logoutButton = root.findViewById(R.id.notif_logout);
        goPersonalFileButton = root.findViewById(R.id.person_info);
        notify = root.findViewById(R.id.notify);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);

        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        //Logout Function
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), Resgister.class));
            }
        });

        goPersonalFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PersonalFileActivity.class));
            }
        });

        notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(getActivity(), AlarmReceiver.class);
                intent.putExtra("NotificationText", "some text");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                if (isChecked) {
                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY, 13);
                    startTime.set(Calendar.MINUTE,0);
                    startTime.set(Calendar.SECOND, 0);
                    long alarmStartTime = startTime.getTimeInMillis();
                    alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                    Toast.makeText(getActivity(), "time alarm set", Toast.LENGTH_SHORT).show();
                } else {
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(getActivity(), "time alarm cancel", Toast.LENGTH_SHORT).show();

                }


            }

        });
    }

}