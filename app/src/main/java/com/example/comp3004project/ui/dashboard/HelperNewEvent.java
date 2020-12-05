package com.example.comp3004project.ui.dashboard;

import com.google.firebase.database.Exclude;

public class HelperNewEvent {
   private  String type,mainFood,drink,workOut,workOutCalorie;
   int mainFoodCaloriesInt,drinkCaloriesInt;
   long date;

 @Exclude
 String recordId;

    public HelperNewEvent() {
    }

    public HelperNewEvent(String type,long date,String mainFood,String drink,int mainFoodCaloriesInt,int drinkCaloriesInt) {
        this.date = date;
        this.type = type;
        this.mainFood = mainFood;
        this.drink = drink;
        this.mainFoodCaloriesInt = mainFoodCaloriesInt;
        this.drinkCaloriesInt = drinkCaloriesInt;
    }

    /*
    public HelperNewEvent(String type, long date, String workOut, String workOutCalorie){
        this.date =date;
        this.type = type;
        this.workOut = workOut;
        this.workOutCalorie = workOutCalorie;
    }

     */

    @Exclude
    public String getRecordId() {
        return recordId;
    }
    @Exclude
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public int getDrinkCaloriesInt() {
        return drinkCaloriesInt;
    }

    public void setDrinkCaloriesInt(int drinkCaloriesInt) {
        this.drinkCaloriesInt = drinkCaloriesInt;
    }

    public int getMainFoodCaloriesInt() {
        return mainFoodCaloriesInt;
    }

    public void setMainFoodCaloriesInt(int mainFoodCaloriesInt) {
        this.mainFoodCaloriesInt = mainFoodCaloriesInt;
    }

    /*
    public String getWorkOut() {
        return workOut;
    }

    public String getWorkOutCalorie() {
        return workOutCalorie;
    }

    public void setWorkOutCalorie(String workOutCalorie) {
        this.workOutCalorie = workOutCalorie;
    }

    public void setWorkOut(String workOut) {
        this.workOut = workOut;
    }

     */


/*
    public String getMainFoodCalorie() {
        return mainFoodCalorie;
    }

    public void setMainFoodCalorie(String mainFoodCalorie) {
        this.mainFoodCalorie = mainFoodCalorie;
    }

    public String getDrinkCalorie() {
        return drinkCalorie;
    }

    public void setDrinkCalorie(String drinkCalorie) {
        this.drinkCalorie = drinkCalorie;
    }

 */

    public String getMainFood() {
        return mainFood;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setMainFood(String mainFood) {
        this.mainFood = mainFood;
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


}
