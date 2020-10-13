package com.example.comp3004project.ui.dashboard;

public class HelperNewEvent {
    private String name, calories, description;

    public HelperNewEvent() {
    }

    public HelperNewEvent(String name, String calories, String description) {
        this.name = name;
        this.calories = calories;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
