package com.filos.port;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ METHOD, CONSTRUCTOR,PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface ValidUser {
    String message() default "The user is not valid";

    Class<?>[] groups() default {};
}
