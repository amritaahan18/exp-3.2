package com.exp.parta;

public class Student {
    private String name;
    private int rollNumber;
    private Course course;

    public Student() {
    }

    public Student(String name, int rollNumber, Course course) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void displayStudentInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Course Details: " + course);
    }
}
