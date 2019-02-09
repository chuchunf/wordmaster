package me.wordmaster;

import me.wordmaster.resource.AdminResource;
import me.wordmaster.resource.ReportResource;
import me.wordmaster.resource.UserResource;
import me.wordmaster.resource.WordResource;
import me.wordmaster.security.JWTFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(UserResource.class);
        register(WordResource.class);
        register(AdminResource.class);
        register(ReportResource.class);

        register(JWTFilter.class);
    }
}
