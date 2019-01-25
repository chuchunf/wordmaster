package me.wordmaster.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AllowedRoles {
    Role role() default Role.USER;

    enum Role {
        USER, ADMIN
    }
}
