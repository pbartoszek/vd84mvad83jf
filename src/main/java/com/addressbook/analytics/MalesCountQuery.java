package com.addressbook.analytics;

import com.addressbook.Contact;
import com.addressbook.Gender;

import java.util.stream.Stream;

public class MalesCountQuery implements ContactQuery {

    private long malesCount;

    @Override
    public void process(Stream<Contact> contacts) {
        malesCount = contacts.filter(c->c.getGender() == Gender.Male).count();
    }

    public long getMalesCount() {
        return malesCount;
    }
}
