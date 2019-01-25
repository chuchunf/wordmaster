package me.wordmaster.resource;

import me.wordmaster.security.AllowedRoles;
import me.wordmaster.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("word")
public class WordResource {
    @Autowired
    private WordService service;

    @GET
    @Path("next10words")
    @AllowedRoles
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopUser(@Context HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("user");
        return Response.ok(service.getNext10Words(username)).build();
    }
}
