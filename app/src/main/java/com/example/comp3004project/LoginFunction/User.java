package com.example.comp3004project.LoginFunction;

public class User {
    public String uid;
    public String emailAddress;
    private String age,gender,weight,height;

    public User(String age,String gender,String weight,String height){
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public User(String uid,String emailAddress){
        this.emailAddress = emailAddress;
        this.uid =uid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
