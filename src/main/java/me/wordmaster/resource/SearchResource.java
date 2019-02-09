package me.wordmaster.resource;

import me.wordmaster.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

@Component
@Path("report")
public class SearchResource {
    @Autowired
    private SearchService service;
}
