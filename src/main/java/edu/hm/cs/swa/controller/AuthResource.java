package edu.hm.cs.swa.controller;

import edu.hm.cs.swa.model.User;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by CaptainEinsicht on 23.05.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */

@Singleton
@Path("/user")
public class AuthResource {

    /**
     * Defualt con.
     */
    public AuthResource() {
    }


    /**
     * login the user.
     *
     * @param user the userwho wants to login.
     * @return a Response.
     */
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        MediaServiceResult msr =

    }
}
