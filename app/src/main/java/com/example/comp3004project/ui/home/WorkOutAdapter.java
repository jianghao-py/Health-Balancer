package com.example.comp3004project.ui.home;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp3004project.R;
import com.example.comp3004project.ui.dashboard.HelpWorkOut;
import com.example.comp3004project.ui.dashboard.HelperNewEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class WorkOutAdapter extends RecyclerView.Adapter<WorkOutAdapter.ViewHolder> {

    //private final ArrayList<HelperNewEvent> eventsList;
    private  final  ArrayList<HelpWorkOut> eventsList;
    private final Context myContext;
    Locale ca = new Locale("en","CA");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY",ca);

    public WorkOutAdapter(ArrayList<HelpWorkOut> eventsList,Context myContext){
        this.eventsList = eventsList;
        this.myContext = myContext;

    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView date,type,workOut,workOutCalories,delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.ShowDateInWorkOut);
            type = itemView.findViewById(R.id.ShowTypeInWorkOut);
            workOut = itemView.findViewById(R.id.ShowWorkOut);
            workOutCalories = itemView.findViewById(R.id.ShowWorkOutCalories);
            delete = itemView.findViewById(R.id.textView40);


        }
    }

    @NonNull
    @Override
    public WorkOutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_workout_card,parent,false);

        WorkOutAdapter.ViewHolder viewHolder = new WorkOutAdapter.ViewHolder(eventView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkOutAdapter.ViewHolder holder, int position) {
        final HelpWorkOut helpWorkOut = eventsList.get(position);
        holder.type.setText(eventsList.get(position).getType());
        holder.date.setText(simpleDateFormat.format(eventsList.get(position).getDate()));
        holder.workOut.setText(eventsList.get(position).getWorkOut());
        holder.workOutCalories.setText(String.valueOf(eventsList.get(position).getWorkOutCalories()));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteWarning(helpWorkOut.getRecordId());
            }
        });


    }

    private void showDeleteWarning(final String recordID){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(myContext);
        builder.setMessage("Are you sure you want to delete this record?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((WorkOutRecordActivity)myContext).deleteFromFirebase(recordID);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
