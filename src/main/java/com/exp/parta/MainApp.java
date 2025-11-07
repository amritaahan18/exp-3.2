package com.exp.parta;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        // Initialize Spring context using Java-based configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the Student bean
        Student student = context.getBean(Student.class);

        // Display student information demonstrating successful dependency injection
        System.out.println("====== Part A: Dependency Injection Demo ======");
        student.displayStudentInfo();
        System.out.println("\nDependency Injection successful!");
        System.out.println("Course object was automatically injected into Student.");
    }
}
