package com.example.myapplication.Support_clases;

import android.content.Context;
import android.util.Patterns;

import com.example.myapplication.ModelClass.ItemModel;

import java.lang.reflect.Field;

public class Validations {


    public static Boolean isemailvalid(String email){
        return  email!=null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static Boolean ispasswordvalid(String password){
        return password!=null && password.length() > 6
          && password.matches(".*[A-Z].*")
                && password.matches(".*\\W.*");
    }
    public  static  boolean isempty(String password,String email, String username, String phoneNo){

        return  password==null || password.isEmpty() || email==null || email.isEmpty()|| username==null || username.isEmpty()|| phoneNo==null ||phoneNo.isEmpty();
    }
    public static boolean isItemEmpty(ItemModel itemModel) {
        if (itemModel == null) {
            return true; // If the object itself is null, return true
        }

        for (Field field : itemModel.getClass().getDeclaredFields()) {
            field.setAccessible(true); // Allow access to private fields
            try {
                Object value = field.get(itemModel);
                if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                    return true; // Return true if any field is null or an empty string
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return false; // Return false if all fields are non-null and non-empty
    }
}
