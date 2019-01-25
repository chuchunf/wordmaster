package me.wordmaster.resource;

import me.wordmaster.security.AllowedRoles;
import me.wordmaster.service.UserService;
import me.wordmaster.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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
        UserVO user = service.login(username, password);
        if ( user==null ) {
            return Response.status(403).entity(null).build();
        } else {
            return Response.ok(user).build();
        }
    }

    @GET
    @Path("last7days")
    @AllowedRoles
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLast7days(@Context HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("user");
        return Response.ok(service.getLast7DayProgress(username)).build();
    }

    @GET
    @Path("topuser")
    @AllowedRoles
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopUser() {
        return Response.ok(service.getTopUser()).build();
    }
}
