package com.example.domzpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.domzpractice.database.Instance;
import com.example.domzpractice.database.SessionManager;
import com.example.domzpractice.model.Student;
import com.example.domzpractice.util.Validator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;

    private Button btnLogin;
    private Button btnSignUp;
    private Instance instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        SessionManager sessionManager = new SessionManager(this);

        instance = new Instance(this);

        for(Student e: getStudent()){
            instance.insertStudentData(this, e);
        }

        if(sessionManager.isLoggedIn()){
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
        }else {

            btnSignUp.setOnClickListener(e -> {
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            });

            btnLogin.setOnClickListener(e -> {

                if (!Validator.isEmpty(etUserName, etPassword)) {
                    return;
                }

                if (!instance.LoginUser(this, etUserName, etPassword)) {
                    Toast.makeText(this, "Incorrect username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                sessionManager.createSession(instance.getUserId(this, etUserName.getText().toString()));
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);

            });
        }

    }


    public ArrayList<Student> getStudent() {
        ArrayList<Student> list = new ArrayList<>();

        list.add(new Student(1, "John", "Wayne", "Doe Smith", "Male", "01-01-1990", 31, "BSIT", "4th", "1st"));
        list.add(new Student(2, "Jane", "Wayne", "Doe Garcia", "Female", "10-02-1995", 26, "BSCS", "2nd", "2nd"));
        list.add(new Student(3, "Juan", "Wayne", "Dela Cruz Santos", "Male", "20-05-1998", 23, "BBA", "1st", "1st"));
        list.add(new Student(4, "Maria", "Wayne", "Dela Cruz Gomez", "Female", "05-09-1999", 22, "BSME", "3rd", "2nd"));
        list.add(new Student(5, "James", "Wayne", "Bond Gonzales", "Male", "07-07-1997", 24, "BSECE", "2nd", "1st"));
        list.add(new Student(6, "Mickey", "Wayne", "Mouse Tan", "Male", "25-12-2000", 21, "BSBA", "1st", "2nd"));
        list.add(new Student(7, "Donald", "Wayne", "Duck Lee", "Male", "15-06-1996", 25, "BSP", "3rd", "1st"));
        list.add(new Student(8, "Peter", "Wayne", "Parker Ramos", "Male", "15-03-1994", 27, "BFA", "4th", "2nd"));
        list.add(new Student(9, "Clark", "Wayne", "Kent Co", "Male", "25-11-1993", 28, "BSED", "5th", "1st"));
        list.add(new Student(10, "Bruce", "Wayne", "Wayne Sy", "Male", "13-08-1991", 30, "BSCrim", "2nd", "2nd"));

        return list;
    }

}