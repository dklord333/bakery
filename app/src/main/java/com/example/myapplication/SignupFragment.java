package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment {
    FragmentSignupBinding binding;

    public SignupFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false);

        binding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), customer_dashboard.class);
                startActivity(intent);
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