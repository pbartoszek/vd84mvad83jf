package com.addressbook.repository;

import com.addressbook.Contact;

import java.util.stream.Stream;

public interface ContactRepository {
    Stream<Contact> getContactStream();
}
