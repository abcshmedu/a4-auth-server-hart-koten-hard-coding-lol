package edu.hm.cs.swa.authorization;

import edu.hm.cs.swa.model.Token;
import edu.hm.cs.swa.model.User;

import javax.ws.rs.core.Response;
import java.util.HashSet;

/**
 * Created by CaptainEinsicht on 23.05.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */
public class AuthServiceImpl {

    private HashSet<User> userHashSet = new HashSet<>();

    private static HashSet<Token> tokenHashSet = new HashSet<>();

    private static final int FIFTEENMIN = 90000;


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
    public AuthServiceResult login(final User user) {
        final AuthServiceResult result;

        System.out.println("Login requested.");

        if (user == null) {
            result = AuthServiceResult.ERROR;
        } else if (!userHashSet.contains(user)) {
            result = AuthServiceResult.NAME_PASSWORD;
        } else if (user.getUserToken() != null) {
            result = AuthServiceResult.OK;
        } else {
            user.setUserToken(generateToken(user));
            result = AuthServiceResult.OK;
        }
        return result;
    }


    /**
     * Generates a token for a User.
     *
     * @param user User that is supposed to get a token.
     * @return Token for user.
     */
    public Token generateToken(User user) {
        String userName = user.getUserName();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        int age = user.getAge();
        String passAndTimeHash = Math.abs((password.hashCode())) + "";
        String tokenstr = userName + "-" + passAndTimeHash + "-" + firstName + "-" + lastName + "-" + age;
        Token token = new Token(tokenstr);
        user.setUserToken(token);
        tokenHashSet.add(token);
        return token;
    }


    /**
     * check token.
     *
     * @param token some token.
     * @return
     */
    public static boolean tokenIsValid(Token token) {
        boolean firstcheck = tokenHashSet.contains(token);
        boolean secondCheck = false;
        if (System.currentTimeMillis() - token.getTimestamp() < FIFTEENMIN) {
            secondCheck = true;
        }
        return firstcheck && secondCheck;
    }


    /**
     * adds a new User to the set.
     *
     * @param user the new user
     */
    public void addUser(User user) {
        this.userHashSet.add(user);
    }


    /**
     * checks whether the given token is valid ot not.
     *
     * @param token the token to check.
     * @return good or bad response.
     */
    public Response validToken(final Token token) {
        Response.accepted();
    }
}
