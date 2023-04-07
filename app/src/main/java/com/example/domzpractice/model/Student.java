package com.example.domzpractice.model;

public class Student extends Person implements Comparable<Student> {

    public static final String CLM_STUDENT_ID = "student_id";
    public static final String CLM_COURSE = "course";
    public static final String CLM_YEAR = "year";
    public static final String CLM_TERM = "term";

    private Integer student_id;
    private String course;
    private String year;
    private String term;

    public Student() {
    }

    public Student(Integer student_id,
                   String firstName,
                   String middleName,
                   String lastName,
                   String gender,
                   String birthDate,
                   Integer age,
                   String course,
                   String year,
                   String term) {
        super(firstName, middleName, lastName, gender, birthDate, age);
        this.student_id = student_id;
        this.course = course;
        this.year = year;
        this.term = term;
    }

    public Integer getStudentId() {
        return student_id;
    }

    public void setStudentId(Integer student_id) {
        this.student_id = student_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public int compareTo(Student student) {
        return this.getLastName().compareTo(student.getLastName());
    }
}
