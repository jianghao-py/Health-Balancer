package com.example.comp3004project.ui.dashboard;

public class HelperNewEvent {
   private  String type,date,mainFood,drink,mainFoodCalorie,drinkCalorie,workOut,workOutCalorie;


    public HelperNewEvent() {
    }

    public HelperNewEvent(String type,String date,String mainFood,String drink,String mainFoodCalorie,String drinkCalorie ) {
        this.date = date;
        this.type = type;
        this.mainFood = mainFood;
        this.drink = drink;
        this.drinkCalorie =drinkCalorie;
        this.mainFoodCalorie = mainFoodCalorie;
    }

    public HelperNewEvent(String type, String date, String workOut, String workOutCalorie){
        this.date =date;
        this.type = type;
        this.workOut = workOut;
        this.workOutCalorie = workOutCalorie;
    }

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

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
