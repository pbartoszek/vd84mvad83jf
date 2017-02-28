package com.addressbook.analytics;

import com.addressbook.Contact;
import com.addressbook.Gender;
import com.addressbook.analytics.OldestPersonQuery;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class OldestPersonQueryTest {

    @Test
    public void emptyContactList_emptyOptionalOldestContact() throws Exception {
        //given
        OldestPersonQuery query = new OldestPersonQuery();
        //when
        query.process(Stream.empty());
        //then
        assertFalse(query.getOldestPerson().isPresent());
    }

    @Test
    public void returnsOldestContact() throws Exception {
        //given
        OldestPersonQuery query = new OldestPersonQuery();

        Contact male1 = new Contact("M1", "M1", LocalDate.of(1990,1,1), Gender.Male);
        Contact male2 = new Contact("M2", "M2", LocalDate.of(2000,1,1), Gender.Male);
        Contact female1 = new Contact("F1", "F2", LocalDate.of(1980,1,1), Gender.Female);
        //when
        query.process(Stream.of(male1,male2,female1));
        //then
        assertTrue(query.getOldestPerson().isPresent());
        assertEquals("F1", query.getOldestPerson().get().getFirstName());
    }

    @Test
    public void thereAre2OldestContacts_returnFirstFromStream() throws Exception {
        //given
        OldestPersonQuery query = new OldestPersonQuery();

        Contact male1 = new Contact("M1", "M1", LocalDate.of(1993,1,1), Gender.Male);
        Contact male2 = new Contact("M2", "M1", LocalDate.of(1990,1,1), Gender.Male);
        Contact male3 = new Contact("M3", "M2", LocalDate.of(1990,1,1), Gender.Male);
        //when
        query.process(Stream.of(male1,male2,male3));
        //then
        assertTrue(query.getOldestPerson().isPresent());
        assertEquals("M2", query.getOldestPerson().get().getFirstName());
    }

}