package com.addressbook.analytics;

import com.addressbook.Contact;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class OldestPersonQuery implements ContactQuery {

    private Optional<Contact> oldestPerson;

    @Override
    public void process(Stream<Contact> contacts) {
        oldestPerson = contacts.sorted(Comparator.comparing(Contact::getDob)).findFirst();
    }

    public Optional<Contact> getOldestPerson() {
        return oldestPerson;
    }
}
