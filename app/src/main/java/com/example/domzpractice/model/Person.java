package com.example.domzpractice.model;

public abstract class Person {

    public static final String CLM_FIRSTNAME = "first_name";
    public static final String CLM_MIDDLENAME = "middle_name";
    public static final String CLM_LASTNAME = "last_name";
    public static final String CLM_GENDER = "gender";
    public static final String CLM_BIRTHDATE = "birth_date";
    public static final String CLM_AGE = "age";

    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String birthDate;
    private Integer age;

    public Person() {}

    public Person(String firstName,
                  String middleName,
                  String lastName,
                  String gender,
                  String birthDate,
                  Integer age) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = Integer.valueOf(age);
    }
}
