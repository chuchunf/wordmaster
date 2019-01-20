package me.wordmaster.resource;

import me.wordmaster.model.AppUser;
import me.wordmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("user")
public class UserResource {
    @Autowired
    private UserService service;

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        AppUser user = service.login(username, password);
        if ( user==null ) {
            return Response.status(403).entity(null).build();
        } else {
            return Response.ok(user).build();
        }
    }
}
