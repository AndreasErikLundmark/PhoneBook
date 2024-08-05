package org.example;

import phonebookpackages.model.Friend;
import org.junit.jupiter.api.Test;
import phonebookpackages.repository.PhoneBookRepository;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    PhoneBookRepository phoneBookRepository = new PhoneBookRepository();

    @Test
    void returnTrueIfRemove() {

        // Add a friend
        phoneBookRepository.add("Test", 705575538L);

        // Remove the friend
        phoneBookRepository.remove("Test");

        // Check if the friend is still in the list
        boolean found = false;
        for (Friend f : phoneBookRepository.getFriends()) {
            if (f.getName().equals("Test")) {
                found = true;
                break;
            }
        }

        // Assert that the friend is not found
        assertFalse(found, "Friend 'Test' should not be found after removal.");
    }

    @Test
    void checkingName(){
        boolean found = false;
        // Add a friend
        phoneBookRepository.add("Test", 705575538L);

        for(Friend f: phoneBookRepository.getFriends()){
            if(f.getName().equals("Test")){
                found = true;
                phoneBookRepository.remove("Test");
                break;
            }
        }

        assertTrue(found, "Friend could not be added to the phonebook");

    }



}