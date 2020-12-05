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
import com.example.comp3004project.ui.dashboard.HelperNewEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private final ArrayList<HelperNewEvent> eventsList;
    private final Context myContext;
    Locale ca = new Locale("en","CA");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY",ca);

    public EventAdapter(ArrayList<HelperNewEvent> eventsList, Context myContext){
        this.eventsList = eventsList;
        this.myContext = myContext;

    }



    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView date,type,mainFood,mainFoodCalories,drink,drinkCalories,delete;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            date = itemView.findViewById(R.id.ShowDateTextView);
            type = itemView.findViewById(R.id.ShowTypeTextView);
            mainFood = itemView.findViewById(R.id.ShowMainFood);
            mainFoodCalories = itemView.findViewById(R.id.ShowMainFoodCalories);
            drink = itemView.findViewById(R.id.ShowDrink);
            drinkCalories = itemView.findViewById(R.id.ShowDrinkCalories);
            delete = itemView.findViewById(R.id.textView39);
        }
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card,parent,false);

        ViewHolder viewHolder = new ViewHolder(eventView);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        final HelperNewEvent helperNewEvent = eventsList.get(position);

        holder.type.setText(eventsList.get(position).getType());
        holder.date.setText(simpleDateFormat.format(eventsList.get(position).getDate()));
        //holder.date.setText(eventsList.get(position).getDate());
        holder.mainFood.setText(eventsList.get(position).getMainFood());
        holder.mainFoodCalories.setText(String.valueOf(eventsList.get(position).getMainFoodCaloriesInt()));
        holder.drink.setText(eventsList.get(position).getDrink());
        holder.drinkCalories.setText(String.valueOf(eventsList.get(position).getDrinkCaloriesInt()));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteWarning(helperNewEvent.getRecordId());
            }
        });

    }

    private void showDeleteWarning(final String recordID){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(myContext);
        builder.setMessage("Are you sure you want to delete this record?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((RecordActivity)myContext).deleteFromFirebase(recordID);
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
