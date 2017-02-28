package com.addressbook.analytics;

import com.addressbook.Contact;
import com.addressbook.Gender;
import com.addressbook.analytics.ContactAnalytics;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ContactAnalyticsTest {

    @Test
    public void contactStream_ProcessedByQuery() throws Exception {
        //given
        ContactAnalytics underTest = new ContactAnalytics(() -> Stream.of(new Contact("A", "B", LocalDate.now(), Gender.Male)));
        //when
        underTest.query(contacts -> /* then **/ assertTrue(contacts.count() == 1));
    }
}