package com.example.domzpractice.util;

import android.widget.EditText;

public class Validator {

    public static boolean isEmpty(EditText ... field){
        for(EditText e: field){
            if(e.getText().toString().isEmpty()){
                e.setError("Field is empty");
                return false;
            }
        }
        return true;
    }

}
