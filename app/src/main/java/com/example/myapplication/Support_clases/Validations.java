package com.example.myapplication.Support_clases;

import android.util.Patterns;

public class Validations {
    public static Boolean isemailvalid(String email){
        return  email!=null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static Boolean ispasswordvalid(String password){
        return password!=null && password.length() > 6
          && password.matches(".*[A-Z].*")
                && password.matches(".*\\W.*");
    }
}