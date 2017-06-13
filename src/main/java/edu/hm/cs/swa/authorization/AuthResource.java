package edu.hm.cs.swa.authorization;

import edu.hm.cs.swa.controller.MediaServiceResult;
import edu.hm.cs.swa.model.User;

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


@Path("/authentication")
public class AuthResource {

    private AuthServiceImpl as = new AuthServiceImpl();


    /**
     * Default c'tor.
     */
    public AuthResource() {
        User kevin = new User("Chicksterminator", "Kevin", "penopt", "20quicksniperlord05", 12);
    }


    /**
     * login the user.
     *
     * @param user the user who wants to login.
     * @return a Response.
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        AuthServiceResult msr = as.login(user);
        return Response.status(msr.getCode()).build();
    }
}
