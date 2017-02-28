package com.addressbook.analytics;

import com.addressbook.Contact;

import java.util.stream.Stream;

public interface ContactQuery {
    void process(Stream<Contact> contacts);
}
