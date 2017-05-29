package edu.hm.cs.swa.authorization;

import edu.hm.cs.swa.controller.MediaServiceResult;
import edu.hm.cs.swa.model.Token;
import edu.hm.cs.swa.model.User;

import java.util.HashSet;

/**
 * Created by CaptainEinsicht on 23.05.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */
public class AuthServiceImpl {

    private HashSet<User> userHashSet = new HashSet<>();


    /**
     * Constructor.
     */
    public AuthServiceImpl() {
    }


    /**
     * Login and authenticate.
     *
     * @param user User that requests something.
     * @return Status code.
     */
    public MediaServiceResult login(final User user) {
        final MediaServiceResult result;

        System.out.println("Login requested.");

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


    /**
     * Generates a token for a User.
     *
     * @param user User that is supposed to get a token.
     * @return Token for user.
     */
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


    /**
     * adds a new User to the set.
     *
     * @param user the new user
     */
    public void addUser(User user) {
        this.userHashSet.add(user);
    }

}
