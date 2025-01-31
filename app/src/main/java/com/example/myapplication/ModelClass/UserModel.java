package com.example.myapplication.ModelClass;

import java.io.Serializable;

public class UserModel implements Serializable{
        private String username;
        private String email;
        private String phoneNo;
        private String password;

        public UserModel(String username, String email, String phoneno, String password) {
                this.username = username;
                this.email = email;
                this.phoneNo = phoneno;
                this.password = password;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoneNo() {
                return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
                this.phoneNo = phoneNo;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
}