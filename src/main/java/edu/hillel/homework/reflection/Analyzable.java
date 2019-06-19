package edu.hillel.homework.reflection;


import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface Analyzable {

    String author();

    String name() default "deep scan";

}
