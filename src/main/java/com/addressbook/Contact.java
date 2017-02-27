package com.addressbook;

import java.time.LocalDate;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final LocalDate dob;
    private final Gender gender;


    public Contact(String firstName, String lastName, LocalDate dob, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Gender getGender() {
        return gender;
    }
}
