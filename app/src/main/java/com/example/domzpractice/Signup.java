package com.example.domzpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.domzpractice.database.Instance;
import com.example.domzpractice.model.Employee;
import com.example.domzpractice.util.DatePickerFragment;
import com.example.domzpractice.util.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class Signup extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etMiddleName;
    private EditText etLastName;
    private EditText etAge;
    private EditText etPosition;
    private EditText etUsername;
    private EditText etPassword;

    private Spinner spinnerGender;
    private Button btnDatePicker;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etFirstName = findViewById(R.id.etFirstName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etLastName = findViewById(R.id.etLastName);
        etAge = findViewById(R.id.etAge);
        etPosition = findViewById(R.id.etPosition);
        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

        spinnerGender = findViewById(R.id.spinnerGender);

        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        initSpinner(this);

        btnDatePicker.setOnClickListener(e ->{
            showCalendarDialog();
        });

        btnLogin.setOnClickListener(e -> {
            Intent intent = new Intent(Signup.this, MainActivity.class);
            startActivity(intent);
        });


        btnSignUp.setOnClickListener(e ->{
            saveEmployee();
        });
    }

    private void saveEmployee(){
        if(!Validator.isEmpty(etFirstName, etMiddleName, etLastName, etAge, etPosition, etUsername, etPassword)){
            return;
        }

        String genderChoice []= getResources().getStringArray(R.array.gender);

        Instance instance = new Instance(this);

        Employee employee = new Employee();

        employee.setFirstName(etFirstName.getText().toString());
        employee.setMiddleName(etMiddleName.getText().toString());
        employee.setLastName(etLastName.getText().toString());
        employee.setAge(Integer.valueOf(etAge.getText().toString()));
        employee.setGender(genderChoice[spinnerGender.getSelectedItemPosition()]);
        employee.setBirthDate(btnDatePicker.getText().toString());
        employee.setPosition(etPosition.getText().toString());
        employee.setUserName(etUsername.getText().toString());
        employee.setPassword(etPassword.getText().toString());

        instance.registerEmployee(this, employee);
    }

    private void initSpinner(Activity activity){
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(
                activity.getApplicationContext(),
                R.array.gender,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
        );
        spinnerGender.setAdapter(genderAdapter);
    }
    private void showCalendarDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.JANUARY) +1;
        int day = 1;
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerFragment(etAge, btnDatePicker), year, month, day);
        datePickerDialog.show();
    }

}