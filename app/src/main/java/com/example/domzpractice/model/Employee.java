package com.example.domzpractice.model;

public class Employee extends Person{

    public static final String CLM_EMPLOYEE_ID = "employee_id";
    public static final String CLM_POSITION = "position";
    public static final String CLM_USERNAME = "username";
    public static final String CLM_PASSWORD = "password";

    private Long employeeId;
    private String position;
    private String userName;
    private String password;

    public Employee() {
    }

    public Employee(String firstName,
                    String middleName,
                    String lastName,
                    String gender,
                    String birthDate,
                    Integer age,
                    Long employeeId,
                    String position,
                    String userName,
                    String password) {
        super(firstName, middleName, lastName, gender, birthDate, age);
        this.employeeId = employeeId;
        this.position = position;
        this.userName = userName;
        this.password = password;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
