package com.addressbook.analytics;

import com.addressbook.Contact;
import com.addressbook.Gender;
import com.addressbook.analytics.BillPaulQuery;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class BillPaulQueryTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void paulIsOlderThenBill_calculateDayDifference() throws Exception {
        //given
        BillPaulQuery query = new BillPaulQuery();
        Contact bill = new Contact("Bill", "M1", LocalDate.of(1970,1,1), Gender.Male);
        Contact paul = new Contact("Paul", "M2", LocalDate.of(1970,1,15), Gender.Male);
        //when
        query.process(Stream.of(bill,paul));
        //them
        assertEquals(14,query.getDaysDifference());
    }

    @Test
    public void billIsOlderThenPaul_calculateNegativeDayDifference() throws Exception {
        //given
        BillPaulQuery query = new BillPaulQuery();
        Contact bill = new Contact("Bill", "M1", LocalDate.of(1970,1,15), Gender.Male);
        Contact paul = new Contact("Paul", "M2", LocalDate.of(1970,1,1), Gender.Male);
        //when
        query.process(Stream.of(bill,paul));
        //then
        assertEquals(-14,query.getDaysDifference());
    }

    @Test
    public void thereIsNotPaulContact_throwException() throws Exception {
        //expect
        thrown.expect(IllegalStateException.class);

        //given
        BillPaulQuery query = new BillPaulQuery();
        Contact bill = new Contact("Bill", "M1", LocalDate.of(1970,1,1), Gender.Male);
        //when
        query.process(Stream.of(bill));
    }

    @Test
    public void thereIsNotBillContact_throwException() throws Exception {
        //expect
        thrown.expect(IllegalStateException.class);

        //given
        BillPaulQuery query = new BillPaulQuery();
        Contact paul = new Contact("Paul", "M1", LocalDate.of(1970,1,1), Gender.Male);
        //when
        query.process(Stream.of(paul));
    }

    @Test
    public void thereAreMultipleBillAndPaulContacts_throwException() throws Exception {
        //expect
        thrown.expect(IllegalStateException.class);

        //given
        BillPaulQuery query = new BillPaulQuery();
        Contact bill = new Contact("Bill", "M1", LocalDate.of(1970,1,1), Gender.Male);
        Contact bill2 = new Contact("Bill", "M2", LocalDate.of(1970,10,1), Gender.Male);

        Contact paul1 = new Contact("Paul", "M1", LocalDate.of(1970,4,1), Gender.Male);
        Contact paul2 = new Contact("Paul", "M2", LocalDate.of(1970,6,1), Gender.Male);
        //when
        query.process(Stream.of(bill, bill2,paul1, paul2));
    }

}