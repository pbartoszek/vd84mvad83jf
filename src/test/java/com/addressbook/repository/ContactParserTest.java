package com.addressbook.repository;

import com.addressbook.Contact;
import com.addressbook.Gender;
import com.addressbook.RequiredKeywordsMatcher;
import com.addressbook.repository.ContactParser;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ContactParserTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void emptyLine_throwException() throws Exception {
        //expect
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(new RequiredKeywordsMatcher("Expected 3 parts"));

        //given
        ContactParser underTest = new ContactParser();
        //then
        underTest.parseContact("");
    }

    @Test
    public void lineHasOnlyFirstAndLastName_throwException() throws Exception {
        //expect
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(new RequiredKeywordsMatcher("Expected 3 parts", "Bill McKnight"));

        //given
        ContactParser underTest = new ContactParser();
        //when
        Contact contact = underTest.parseContact("Bill McKnight");
    }

    @Test
    public void lineHasOnlyFirstOrLastName_throwException() throws Exception {
        //expect
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(new RequiredKeywordsMatcher("first name", "last name"));

        //given
        ContactParser underTest = new ContactParser();
        //when
        underTest.parseContact("Bill, Male, 16/03/77");
    }


    @Test
    public void correctFormatLine_parsedContact() throws Exception {
        //given
        ContactParser underTest = new ContactParser();
        //when
        Contact contact = underTest.parseContact("Bill McKnight, Male, 16/03/77");
        //then
        Assertions.assertThat(contact.getFirstName()).isEqualTo("Bill");
        Assertions.assertThat(contact.getLastName()).isEqualTo("McKnight");
    }

    @Test
    public void maleGender_parse() throws Exception {
        //given
        ContactParser underTest = new ContactParser();
        //when
        Contact contact = underTest.parseContact("Bill McKnight, Male, 16/03/77");
        //then
        Assertions.assertThat(contact.getGender()).isEqualTo(Gender.Male);
    }

    @Test
    public void femaleGender_parse() throws Exception {
        //given
        ContactParser underTest = new ContactParser();
        //when
        Contact contact = underTest.parseContact("Bill McKnight, Female, 16/03/77");
        //then
        Assertions.assertThat(contact.getGender()).isEqualTo(Gender.Female);
    }

    @Test
    public void unknownGender_throwException() throws Exception {
        //expect
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("XXX");

        //given
        ContactParser underTest = new ContactParser();
        //when
        Contact contact = underTest.parseContact("Bill McKnight, XXX, 16/03/77");
    }

    @Test
    public void correctFormatLine_successfullyParseContact() throws Exception {
        //given
        ContactParser underTest = new ContactParser();
        //when
        Contact contact = underTest.parseContact("Bill McKnight, Female, 16/03/77");
        //then
        Assertions.assertThat(contact.getDob()).isEqualTo(LocalDate.of(1977,3,16));
    }

    @Test
    public void incorrectDateFormat_throwException() throws Exception {
        //expect
        thrown.expect(DateTimeParseException.class);

        //given
        ContactParser underTest = new ContactParser();
        //when
        Contact contact = underTest.parseContact("Bill McKnight, Female, 16/dfa03/7347");
    }
}