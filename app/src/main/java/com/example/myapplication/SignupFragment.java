package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.ModelClass.UserModel;
import com.example.myapplication.Support_clases.Authentication;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.databinding.FragmentSignupBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignupFragment extends Fragment {
    FragmentSignupBinding binding;
    private Authentication authentication;

    public SignupFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        if (getActivity() != null) {
            authentication = new Authentication(auth, getActivity());
        } else {
            // Log or handle the case where activity is null.
            Log.e("SignupFragment", "getActivity() is null. Authentication not initialized.");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false);


        binding.signupbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username= binding.username.getText().toString().trim();
                String email= binding.Email.getText().toString().trim();
                String phoneno= binding.num.getText().toString().trim();
                String password= binding.pass.getText().toString().trim();

                UserModel userModel=new UserModel(username,email,phoneno,password);
                Log.d("Before",binding.username.getText().toString().trim());
                Log.d("After",username);
                if (authentication != null) {
                    // Now `authentication` should be properly initialized.
                    authentication.Signup(userModel);
                } else {
                    Log.e("SignupFragment", "Authentication is not initialized");
                    // Optionally show a toast or handle the case gracefully
                }




            }
        });
        binding.loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft=requireActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragContainer,new LoginFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        return binding.getRoot();

    }
}