package phonebookpackages.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookRepositoryTest {

    @Test
    void remove() {

        PhoneBookRepository phoneBookRepository = new PhoneBookRepository();
        phoneBookRepository.add("Peter","0737124443");
        phoneBookRepository.remove("Peter");
        assertNull(phoneBookRepository.findByName("Peter"));

    }
}