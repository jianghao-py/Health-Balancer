package com.example.comp3004project.ui.dashboard;

public class HelpWorkOut {

    private  String type, workOut;
    long date;
    int workOutCalories;

    public  HelpWorkOut(){

    }

    public  HelpWorkOut(String type,long date,String workOut, int workOutCalories){
        this.type = type;
        this.date = date;
        this.workOut = workOut;
        this.workOutCalories = workOutCalories;

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
