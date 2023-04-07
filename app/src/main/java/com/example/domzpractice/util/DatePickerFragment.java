package com.example.domzpractice.util;

import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;
import java.time.Period;

public class DatePickerFragment implements DatePickerDialog.OnDateSetListener{

    private EditText etAge;
    private Button btnDatePicker;

    public DatePickerFragment(EditText etAge, Button btnDatePicker){
       this.etAge = etAge;
       this.btnDatePicker = btnDatePicker;
   }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        String strBirthDate = formatDate(year, (month + 1), day);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate dateSelected = LocalDate.parse(strBirthDate);
            LocalDate currentDate = LocalDate.now();

            int ageCalculated = Period.between(dateSelected, currentDate).getYears();
            String strAge = ageCalculated +"";
            btnDatePicker.setText(strBirthDate);
            etAge.setText(strAge);
        }
    }

    private String formatDate(int year, int month, int day){
        String strYear = String.valueOf(year);
        String strMonth = month < 10 ? "0" + month : String.valueOf(month);
        String strDay = day < 10 ? "0" + day : String.valueOf(day);
        return strYear + "-" + strMonth +"-"+ strDay;
    }
}
