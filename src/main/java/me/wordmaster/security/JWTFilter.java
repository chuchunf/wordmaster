package me.wordmaster.security;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTFilter implements ContainerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private HttpServletRequest request;

    @Autowired
    private JWTService jwtservice;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        if ( !method.isAnnotationPresent(AllowedRoles.class) ) return;

        String authheader = requestContext.getHeaders().getFirst(AUTH_HEADER);
        if ( authheader==null ) {
            fail(requestContext);
            return;
        }

        Optional<UserToken> usertoken = jwtservice.getUserToken(authheader);
        if ( !usertoken.isPresent() ) {
            fail(requestContext);
            return;
        } else {
            request.getSession().setAttribute("user", usertoken.get().getUsername());
        }

        AllowedRoles roles = method.getAnnotation(AllowedRoles.class);
        if ( roles.role() == AllowedRoles.Role.ADMIN ) {
            checkAdmin(usertoken.get());
        }
    }

    private void checkAdmin(UserToken userToken) {
    }

    private void fail(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(403).entity("Invalid Token").build());
    }
}
