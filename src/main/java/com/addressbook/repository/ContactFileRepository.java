package com.addressbook.repository;

import com.addressbook.Contact;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ContactFileRepository implements ContactRepository {
    private final Path filePath;
    private final ContactParser contactParser;

    public ContactFileRepository(Path filePath, ContactParser contactParser) {
        this.filePath = filePath;
        this.contactParser = contactParser;
    }

    @Override
    public Stream<Contact> getContactStream(){
        try {
            return Files.lines(filePath).map(l -> {
                try {
                    return contactParser.parseContact(l);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getFilePath() {
        try {
            return Paths.get(this.getClass().getResource("/AddressBook").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
