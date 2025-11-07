package com.exp.parta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Course course() {
        return new Course("Advanced Java Programming", "CSE-501");
    }

    @Bean
    public Student student() {
        return new Student("John Doe", 101, course());
    }
}
