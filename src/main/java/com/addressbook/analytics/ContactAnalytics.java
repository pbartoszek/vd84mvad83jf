package com.addressbook.analytics;

import com.addressbook.repository.ContactRepository;

public class ContactAnalytics {
    private final ContactRepository contactRepository;

    public ContactAnalytics(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void query(ContactQuery query){
        query.process(contactRepository.getContactStream());
    }
}
