package com.example.domzpractice.database;

import com.example.domzpractice.model.Employee;
import com.example.domzpractice.model.Student;
import com.google.android.material.tabs.TabLayout;

public class Schema {

    public static final String DROP_STUDENT_TBL = "IF TABLE EXISTS DROP " + Table.STUDENT_TBL;
    public static final String DROP_EMPLOYEE_TBL = "IF TABLE EXIST DROP " + Table.EMPLOYEE_TBL;

    public static  final String CREATE_STUDENT_TBL = "CREATE TABLE " + Table.STUDENT_TBL +" (" +
            Student.CLM_STUDENT_ID  +" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Student.CLM_FIRSTNAME + " TEXT," +
            Student.CLM_MIDDLENAME + " TEXT," +
            Student.CLM_LASTNAME + " TEXT," +
            Student.CLM_GENDER + " TEXT," +
            Student.CLM_BIRTHDATE + " TEXT," +
            Student.CLM_AGE + " INT," +
            Student.CLM_COURSE + " TEXT," +
            Student.CLM_YEAR + " TEXT," +
            Student.CLM_TERM + " TEXT);";

    public static final String CREATE_EMPLOYEE_TBL = "CREATE TABLE " + Table.EMPLOYEE_TBL + "(" +
            Employee.CLM_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Employee.CLM_FIRSTNAME + " TEXT, " +
            Employee.CLM_MIDDLENAME + " TEXT, " +
            Employee.CLM_LASTNAME + " TEXT, " +
            Employee.CLM_BIRTHDATE + " TEXT, " +
            Employee.CLM_AGE + " INT, " +
            Employee.CLM_GENDER + " TEXT," +
            Employee.CLM_POSITION + " TEXT, " +
            Employee.CLM_USERNAME + " TEXT, " +
            Employee.CLM_PASSWORD + " TEXT);";

}
