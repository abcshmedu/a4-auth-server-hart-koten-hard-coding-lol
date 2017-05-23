package edu.hm.cs.swa.model;

/**
 * Created by CaptainEinsicht on 23.05.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */
public class User {

    private String firstName;

    private String lastName;

    private String password;

    private int age;


    public User(final String firstName, final String lastName, final String password, final int age) {
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


    public int getAge() {
        return age;
    }
}
