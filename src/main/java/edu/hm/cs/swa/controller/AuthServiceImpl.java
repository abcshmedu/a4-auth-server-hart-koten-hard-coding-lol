package edu.hm.cs.swa.controller;

import edu.hm.cs.swa.model.Token;
import edu.hm.cs.swa.model.User;

import java.util.HashSet;

/**
 * Created by CaptainEinsicht on 23.05.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */
public class AuthServiceImpl {

    private HashSet<User> userHashSet = new HashSet<>();


    public AuthServiceImpl() {
    }


    public MediaServiceResult login(final User user) {
        final MediaServiceResult result;

        System.out.println("alter ich bin hier also ich nich alles so schlimm wie du dachtest");

        if (user == null) {
            result = MediaServiceResult.AUTHORIZATION;
        } else if (!userHashSet.contains(user)) {
            result = MediaServiceResult.AUTHORIZATION;
        } else if (user.getUserToken() != null) {
            result = MediaServiceResult.OK;
        } else {
            user.setUserToken(generateToken(user));
            result = MediaServiceResult.OK;
        }
        return result;
    }


    private Token generateToken(User user) {
        String userName = user.getUserName();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        int age = user.getAge();
        String time = System.currentTimeMillis() + "";
        String timeandPassword = time + password;
        String passAndTimeHash = Math.abs((timeandPassword.hashCode())) + "";
        String tokenstr = userName + "-" + passAndTimeHash + "-" + firstName + "-" + lastName + "-" + age;
        return new Token(tokenstr);
    }

}
