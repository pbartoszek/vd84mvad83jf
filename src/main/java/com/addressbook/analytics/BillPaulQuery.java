package com.addressbook.analytics;

import com.addressbook.Contact;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

public class BillPaulQuery implements ContactQuery {

    private long daysDifference;

    @Override
    public void process(Stream<Contact> contacts) {

        List<Contact> foundContacts = contacts
                .filter(c -> c.getFirstName().equals("Paul") || c.getFirstName().equals("Bill"))
                .collect(Collectors.toList());

        List<Contact> billList = foundContacts.stream().filter(c -> c.getFirstName().equals("Bill")).collect(Collectors.toList());
        List<Contact> paulList = foundContacts.stream().filter(c -> c.getFirstName().equals("Paul")).collect(Collectors.toList());

        if(billList.size() != 1){
            throw new IllegalStateException("Expected 1 contact with name Bill");
        }
        if(paulList.size() != 1){
            throw new IllegalStateException("Expected 1 contact with name Paul");
        }

        Contact billContact = billList.get(0);
        Contact paulContact = paulList.get(0);

        daysDifference = DAYS.between(billContact.getDob(), paulContact.getDob());
    }

    public long getDaysDifference() {
        return daysDifference;
    }
}
