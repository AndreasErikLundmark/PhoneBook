package phonebookpackages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import phonebookpackages.repository.PhoneBookRepository;

@RestController
@RequestMapping("/phonebook")
public class PhoneBookController {

    private final PhoneBookRepository repository;

    @Autowired
    public PhoneBookController(PhoneBookRepository repository) {
        this.repository = repository;
    }
    //Get the whole phonebook
    @GetMapping
    public String findAll() {
        return repository.toString();
    }
    //Find friend by name
    @GetMapping("/{name}")
    public String findByName(@PathVariable String name) {
        name=name.toLowerCase();
        return repository.findByName(name);
    }

    //Add a friend to the phonebook
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{name}/{phoneNumber}")
    public void add(@PathVariable String name, @PathVariable long phoneNumber) {
        repository.add(name, phoneNumber);
    }
//Update a friend in the phonebook
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{name}/{phoneNumber}")
    public void update(@PathVariable String name, @PathVariable long phoneNumber) {
        repository.add(name, phoneNumber);
    }
//Delete a friend from the phonebook
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        repository.remove(name);
    }

}
