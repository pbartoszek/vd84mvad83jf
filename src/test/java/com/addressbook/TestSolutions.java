package com.addressbook;

import com.addressbook.analytics.BillPaulQuery;
import com.addressbook.analytics.ContactAnalytics;
import com.addressbook.analytics.MalesCountQuery;
import com.addressbook.analytics.OldestPersonQuery;
import com.addressbook.repository.ContactFileRepository;
import com.addressbook.repository.ContactParser;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSolutions {

    private ContactAnalytics analytics;

    @Before
    public void setUp() throws URISyntaxException {
        analytics = createAnalytics();
    }

    @Test
    public void howMoneyAreThereMale()  {
        //given
        MalesCountQuery query = new MalesCountQuery();
        //when
        analytics.query(query);
        //then
        assertThat(query.getMalesCount()).isEqualTo(3);
    }

    @Test
    public void howManyDaysBillIsOlderThenPaul()  {
        //given
        BillPaulQuery query = new BillPaulQuery();
        //when
        analytics.query(query);
        //then
        assertThat(query.getDaysDifference()).isEqualTo(2862);
    }

    @Test
    public void whoIsTheOldestContact()  {
        //given
        OldestPersonQuery query = new OldestPersonQuery();
        //when
        analytics.query(query);
        //then
        Optional<Contact> oldestPerson = query.getOldestPerson();
        assertThat(oldestPerson.isPresent()).isEqualTo(true);
        assertThat(oldestPerson.get().getFirstName()).isEqualTo("Wes");
    }

    private ContactAnalytics createAnalytics() throws URISyntaxException {
        Path addressBookPath = Paths.get(this.getClass().getResource("/AddressBook").toURI());

        ContactFileRepository contactRepository = new ContactFileRepository(addressBookPath, new ContactParser());
        return new ContactAnalytics(contactRepository);
    }
}
