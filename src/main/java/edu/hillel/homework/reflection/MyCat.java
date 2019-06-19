package edu.hillel.homework.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.lang.reflect.Method;

public class MyCat implements ICodeAnalyzer {

    @Analyzable(
            author = "Victoria Bagriy",
            name = "light scan"
    )
    private String name;

    @Analyzable(
            author = "Victoria Bagriy",
            name = "light scan"
    )
    private int age;
    private String favoriteGames;

    public MyCat() {
    }

    @Test(
            author = "Cat"
    )
    @Analyzable(
            author = "Dmitriy Bagriy"
    )
    public MyCat(String name, int age, String favoriteGames) {
        this.name = name;
        this.age = age;
        this.favoriteGames = favoriteGames;
    }

    @Test(
            author = "Dog"
    )
    @Analyzable(
            author = "Victoria Bagriy"
    )
    private String whatCatDo(int time, String action) {
        String catAction;
        if (time >= 0 && time < 12 && action.equals("eat")) {
            catAction = "I am eating";
        } else if (time >= 12 && time < 24 && action.equals("play")) {
            catAction = "I want play with you!";
        } else {
            catAction = "I am playing";
        }
        return catAction;
    }

    @Override
    public void analyzeClass(Class<?> clazz) {
        analyzeMethods(clazz);
        analyzeConstructors(clazz);
        analyzeFields(clazz);
    }

    private void analyzeMethods(Class<?> clazz) {
        System.out.println("======= STARTS ANALYZE METHODS =======");
        analyseExecutable(clazz.getDeclaredMethods());
        System.out.println("======= FINISH ANALYZE METHODS =======");
    }

    private void analyzeConstructors(Class<?> clazz) {
        System.out.println("======= STARTS ANALYZE CONSTRUCTORS =======");
        analyseExecutable(clazz.getConstructors());
        System.out.println("======= FINISH ANALYZE CONSTRUCTORS =======");
    }

    private void analyzeFields(Class<?> clazz) {
        System.out.println("======= STARTS ANALYZE FIELDS =======");
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            if (field.isAnnotationPresent(Analyzable.class)) {
                System.out.println("Field of class " + field.getName());
                System.out.println("Field modifier " + Modifier.toString(field.getModifiers()));
                analyseAnnotations(field.getDeclaredAnnotations());
                System.out.println("-----------------------------------");
            }
        });
        System.out.println("======= FINISH ANALYZE FIELDS =======");

    }

    private void analyseAnnotations(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            System.out.println("There is such an annotation with such parameters: " + annotation);
        }
    }

    private void analyseExecutable(Executable[] executables) {
        for (Executable executable : executables) {
            if (!executable.isAnnotationPresent(Analyzable.class)) {
                continue;
            }

            String executableType = "Constructor";

            if (executable instanceof Method) {
                Method method = (Method) executable;
                System.out.println("This is type of return parameter: " + method.getReturnType().getName());
                executableType = "Method";
            }

            System.out.println(executableType + " name is: " + executable.getName());
            int count = executable.getParameterCount();
            System.out.println("Number of incoming parameters is: " + count + "\r\nName of parameter method " +
                    executable.getName() + " is " +
                    Arrays.toString(executable.getParameters()));
            System.out.println();
            System.out.println("This is " + executableType + " with annotation Analyzable: " + executable.getName() +
                    "\r\nAccess modifier for this method: " + Modifier.toString(executable.getModifiers()));

            System.out.println("-----------------------------------");
            analyseAnnotations(executable.getDeclaredAnnotations());

        }
    }
}

