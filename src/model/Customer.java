package model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        this.firstName = firstName;
        this.lastName = lastName;
        Pattern pattern = Pattern.compile(emailRegex);
        if (pattern.matcher(email).matches()){
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Error, invalid email.");
        }
    }

    public String getEmail() {
        return email;
    }

    public String toString(){
        return "First Name: " + this.firstName + " Last Name: " + this.lastName + " Email: " + this.email;
    }
}
