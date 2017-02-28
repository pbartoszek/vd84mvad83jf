package com.addressbook.repository;

import com.addressbook.Contact;
import com.addressbook.Gender;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class ContactParser {

    public static final DateTimeFormatter DOB_DF = new DateTimeFormatterBuilder().appendPattern("dd/MM/")
            .appendValueReduced(
                    ChronoField.YEAR, 2, 2, Year.now().getValue() - 90
            ).toFormatter();

    public Contact parseContact(String line){
        String[] parts = line.split(",");
        if(parts.length!=3){
            throw new IllegalArgumentException("Expected 3 parts in contact[" + line + "]");
        }
        String fullName = parts[0];

        String[] fullNameSplit = fullName.split("\\s", 2);
        if(fullNameSplit.length!=2){
            throw new IllegalArgumentException("First name and last name required");
        }
        String firstName = fullNameSplit[0];
        String lastName = fullNameSplit[1];

        String genderStr = parts[1].trim();
        Gender gender = parseGender(genderStr);

        String dobString = parts[2].trim();
        LocalDate dob = parseDob(dobString);

        return new Contact(firstName, lastName,dob,gender);
    }

    private LocalDate parseDob(String dobString) {
        return LocalDate.parse(dobString, DOB_DF);
    }

    private Gender parseGender(String genderStr) {
        Gender gender;
        if ("Male".equalsIgnoreCase(genderStr)) {
            gender = Gender.Male;
        }else if("Female".equalsIgnoreCase(genderStr)){
            gender = Gender.Female;
        }else{
            throw new IllegalArgumentException("Unknown gender[" + genderStr + "]");
        }
        return gender;
    }
}