package com.example.comp3004project.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp3004project.R;
import com.example.comp3004project.ui.dashboard.HelperNewEvent;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private final ArrayList<HelperNewEvent> eventsList;
    private final Context myContext;

    public EventAdapter(ArrayList<HelperNewEvent> eventsList, Context myContext){
        this.eventsList = eventsList;
        this.myContext = myContext;

    }



    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView date,type,mainFood,mainFoodCalories,drink,drinkCalories;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            date = itemView.findViewById(R.id.ShowDateTextView);
            type = itemView.findViewById(R.id.ShowTypeTextView);
            mainFood = itemView.findViewById(R.id.ShowMainFood);
            mainFoodCalories = itemView.findViewById(R.id.ShowMainFoodCalories);
            drink = itemView.findViewById(R.id.ShowDrink);
            drinkCalories = itemView.findViewById(R.id.ShowDrinkCalories);
        }
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card,parent,false);
        //View eventView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_workout_card,parent,false);

        ViewHolder viewHolder = new ViewHolder(eventView);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {

        holder.type.setText(eventsList.get(position).getType());
        holder.date.setText(eventsList.get(position).getDate());
        holder.mainFood.setText(eventsList.get(position).getMainFood());
        holder.mainFoodCalories.setText(eventsList.get(position).getMainFoodCalorie());
        holder.drink.setText(eventsList.get(position).getDrink());
        holder.drinkCalories.setText(eventsList.get(position).getDrinkCalorie());


    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
