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

public class WorkOutAdapter extends RecyclerView.Adapter<WorkOutAdapter.ViewHolder> {

    private final ArrayList<HelperNewEvent> eventsList;
    private final Context myContext;

    public WorkOutAdapter(ArrayList<HelperNewEvent> eventsList,Context myContext){
        this.eventsList = eventsList;
        this.myContext = myContext;

    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView date,type,workOut,workOutCalories;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.ShowDateInWorkOut);
            type = itemView.findViewById(R.id.ShowTypeInWorkOut);
            workOut = itemView.findViewById(R.id.ShowWorkOut);
            workOutCalories = itemView.findViewById(R.id.ShowWorkOutCalories);


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

        holder.type.setText(eventsList.get(position).getType());
        holder.date.setText(eventsList.get(position).getDate());
        holder.workOut.setText(eventsList.get(position).getWorkOut());
        holder.workOutCalories.setText(eventsList.get(position).getWorkOutCalorie());

    }



    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
