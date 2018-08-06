package com.example.root.databaselogin;

public class UserProfile {
    public String Name;
    public String Age;
    public String Email;


    public UserProfile() {
    }

    public UserProfile(String userName, String userAge, String userEmail) {
        this.Age = userAge;
        this.Email = userEmail;
        this.Name = userName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String userAge) {
        this.Age = userAge;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String userEmail) {
        this.Email = userEmail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String userName) {
        this.Name = userName;
    }
}
