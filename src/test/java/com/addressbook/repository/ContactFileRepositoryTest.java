package com.addressbook.repository;

import com.addressbook.Contact;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ContactFileRepositoryTest {

    @Test
    public void forGivenAddressBookFile_parseContacts() throws Exception {
        //given
        Path addressBookPath = Paths.get(this.getClass().getResource("/AddressBook").toURI());
        ContactRepository underTest = new ContactFileRepository(addressBookPath, new ContactParser());
        //when
        Stream<Contact> contactStream = underTest.getContactStream();
        //then
        assertEquals(5,contactStream.count());
    }
}