package me.wordmaster.resource;

import me.wordmaster.security.AllowedRoles;
import me.wordmaster.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("search")
public class SearchResource {
    @Autowired
    private SearchService service;

    @GET
    @Path("title/{title}")
    @AllowedRoles
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWord(@PathParam("title") String title) {
        return Response.ok(service.searchByList(title)).build();
    }
}
