package com.addressbook.analytics;

import com.addressbook.Contact;
import com.addressbook.Gender;
import com.addressbook.analytics.MalesCountQuery;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MalesCountQueryTest {

    @Test
    public void noMaleContacts_countIsZero() throws Exception {
        //given
        MalesCountQuery query = new MalesCountQuery();
        Contact female1 = new Contact("F1", "F2", LocalDate.now(), Gender.Female);
        //when
        query.process(Stream.of(female1));
        //then
        assertEquals(0,query.getMalesCount());
    }

    @Test
    public void countsMaleContacts() throws Exception {
        //given
        MalesCountQuery query = new MalesCountQuery();

        Contact male1 = new Contact("M1", "M1", LocalDate.now(), Gender.Male);
        Contact male2 = new Contact("M2", "M2", LocalDate.now(), Gender.Male);
        Contact female1 = new Contact("F1", "F2", LocalDate.now(), Gender.Female);
        //when
        query.process(Stream.of(male1,male2,female1));
        //then
        assertEquals(2,query.getMalesCount());
    }

}