//https://www.youtube.com/watch?v=q_RLfOB7axQ

package phonebookpackages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import phonebookpackages.repository.PhoneBookRepository;

@SpringBootApplication
public class PhoneBookApplication {


    public static void main(String[]args){
        SpringApplication.run(PhoneBookApplication.class,args);


        PhoneBookRepository phoneBookRepository = new PhoneBookRepository();

        phoneBookRepository.add("Peter","0737124443");
        System.out.println(phoneBookRepository.getFriends());
    }
}

