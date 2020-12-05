package com.example.comp3004project.ui.dashboard;

import com.google.firebase.database.Exclude;

public class HelpWorkOut {

    private  String type, workOut;
    long date;
    int workOutCalories;

    @Exclude
    String recordId;

    public  HelpWorkOut(){

    }

    public  HelpWorkOut(String type,long date,String workOut, int workOutCalories){
        this.type = type;
        this.date = date;
        this.workOut = workOut;
        this.workOutCalories = workOutCalories;

    }

    @Exclude
    public String getRecordId() {
        return recordId;
    }
    @Exclude
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkOut() {
        return workOut;
    }

    public void setWorkOut(String workOut) {
        this.workOut = workOut;
    }

    public int getWorkOutCalories() {
        return workOutCalories;
    }

    public void setWorkOutCalories(int workOutCalories) {
        this.workOutCalories = workOutCalories;
    }
}
