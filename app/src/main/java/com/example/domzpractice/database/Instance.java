package com.example.domzpractice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.domzpractice.model.Employee;
import com.example.domzpractice.model.Student;

import java.util.ArrayList;

public class Instance extends SQLiteOpenHelper {

    private static final String DB_NAME = "Student.db";
    private static final Integer VERSION = 1;

    private static Instance help = null;

    public Instance(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sql) {
        sql.execSQL(Schema.CREATE_EMPLOYEE_TBL);
        sql.execSQL(Schema.CREATE_STUDENT_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int i, int i1) {

        sql.execSQL(Schema.DROP_EMPLOYEE_TBL);
        sql.execSQL(Schema.DROP_STUDENT_TBL);

        onCreate(sql);
    }

    public static synchronized Instance getInstance(Context context){

        if(help == null){
            help = new Instance(context);
        }
        return help;
    }

    public void insertStudentData(Context context, Student student) {

        try (SQLiteDatabase sql = Instance.getInstance(context).getWritableDatabase()) {

            ContentValues cv = new ContentValues();

            cv.put(Student.CLM_STUDENT_ID, student.getStudentId());
            cv.put(Student.CLM_FIRSTNAME, student.getFirstName());
            cv.put(Student.CLM_MIDDLENAME, student.getMiddleName());
            cv.put(Student.CLM_LASTNAME, student.getLastName());
            cv.put(Student.CLM_GENDER, student.getGender());
            cv.put(Student.CLM_BIRTHDATE, student.getBirthDate());
            cv.put(Student.CLM_AGE, student.getAge());
            cv.put(Student.CLM_COURSE, student.getCourse());
            cv.put(Student.CLM_YEAR, student.getYear());
            cv.put(Student.CLM_TERM, student.getTerm());

            sql.insert(Table.STUDENT_TBL, null, cv);

        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
    }

    public void registerEmployee(Context context, Employee employee){
        try(SQLiteDatabase sql = Instance.getInstance(context).getWritableDatabase();){
            ContentValues values = new ContentValues();

            values.put(Employee.CLM_FIRSTNAME, employee.getFirstName());
            values.put(Employee.CLM_MIDDLENAME, employee.getMiddleName());
            values.put(Employee.CLM_LASTNAME, employee.getLastName());
            values.put(Employee.CLM_BIRTHDATE, employee.getBirthDate());
            values.put(Employee.CLM_AGE, employee.getAge());
            values.put(Employee.CLM_GENDER, employee.getGender());
            values.put(Employee.CLM_POSITION, employee.getPosition());
            values.put(Employee.CLM_USERNAME, employee.getUserName());
            values.put(Employee.CLM_PASSWORD, employee.getPassword());
            long newRowId = sql.insert(Table.EMPLOYEE_TBL, null, values);

            String message = -1 == newRowId ? "Registration Failed" : "Registration Successful";

            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }

    }

    public ArrayList<Student> getStudentData(Context context){

        ArrayList<Student> studentList = new ArrayList<>();

        try(SQLiteDatabase sql = Instance.getInstance(context).getReadableDatabase();
        Cursor cursor = sql.query(Table.STUDENT_TBL, null, null, null, null, null, null)){

            while(cursor.moveToNext()){
                Student student = new Student();
                student.setStudentId(cursor.getInt(0));
                student.setFirstName(cursor.getString(1));
                student.setMiddleName(cursor.getString(2));
                student.setLastName(cursor.getString(3));
                student.setGender(cursor.getString(4));
                student.setBirthDate(cursor.getString(5));
                student.setAge(cursor.getInt(6));
                student.setCourse(cursor.getString(7));
                student.setYear(cursor.getString(8));
                student.setTerm(cursor.getString(9));
                studentList.add(student);
            }
        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
        return studentList;
    }

    public boolean LoginUser(Context context, EditText username, EditText password) {
        String strUsername = username.getText().toString();
        String strPassword = password.getText().toString();

        try (SQLiteDatabase sql = Instance.getInstance(context).getReadableDatabase();
             Cursor cursor = sql.query(Table.EMPLOYEE_TBL, new String[]{Employee.CLM_PASSWORD}, Employee.CLM_USERNAME + " = ?", new String[]{strUsername}, null, null, null)) {

            if (cursor != null && cursor.moveToFirst() && strPassword.equals(cursor.getString(0))) {
                return true;
            }

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return false;
    }

    public String getUserId(Context context, String userName){

        try(SQLiteDatabase sql = Instance.getInstance(context).getReadableDatabase();
        Cursor cursor = sql.query(Table.EMPLOYEE_TBL, new String[]{Employee.CLM_EMPLOYEE_ID}, Employee.CLM_USERNAME + " = ?", new String[]{userName}, null, null, null)){
            if(cursor != null && cursor.moveToFirst() && userName.equals(cursor.getString(0))){
                return cursor.getString(0);
            }
        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
        return "";
    }
}
