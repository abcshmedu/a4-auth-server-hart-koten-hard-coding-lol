package edu.hm.cs.swa.Tests;

import edu.hm.cs.swa.authorization.AuthServiceImpl;
import edu.hm.cs.swa.controller.MediaServiceResult;
import edu.hm.cs.swa.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by CaptainEinsicht on 29.05.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */
public class AuthServiceImplTest {

    private User kevin = new User("Chicksterminator", "kevin", "Penopt", "20QuickSniperLord05", 12);

    private final AuthServiceImpl asi = new AuthServiceImpl();


    /**
     * should work.
     */
    @Test
    public void loginTestSuccess() {
        asi.addUser(kevin);
        MediaServiceResult ms = asi.login(kevin);
        assertEquals(ms, MediaServiceResult.OK);
    }


    /**
     * should not work.
     */
    @Test
    public void loginTestFail() {
        final User someUser = new User("someUser", "some", "User", "", 50);
        MediaServiceResult ms = asi.login(someUser);
        assertEquals(ms, MediaServiceResult.AUTHORIZATION);
    }
}
