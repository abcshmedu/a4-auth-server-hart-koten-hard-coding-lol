package edu.hm.cs.swa.model;

/**
 * Created by CaptainEinsicht on 23.05.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */
public class User {

    private String userName;

    private String firstName;

    private String lastName;

    private String password;

    private int age;

    private Token userToken;


    public User(final String userName, final String firstName, final String lastName, final String password, final int age) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
    }


    public boolean isOVer18() {
        return age >= 18;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getPassword() {
        return password;
    }


    public String getUserName() {
        return userName;
    }


    public int getAge() {
        return age;
    }


    public Token getUserToken() {
        return userToken;
    }


    public void setUserToken(final Token userToken) {
        this.userToken = userToken;
    }
}
