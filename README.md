# PhoneBook-fullstack

[![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![JSON](https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white)](https://www.json.org/json-en.html)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![HTML](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/HTML)
[![CSS](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)](https://developer.mozilla.org/en-US/docs/Web/CSS)
[![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)](https://developer.mozilla.org/en-US/docs/Web/JavaScript)


# PhoneBook API

This project is a simple PhoneBook backend API built with Java and Spring Boot. It allows you to manage a phonebook with functionalities to add, update, find, and delete friends. The data is stored locally using a JSON file, acting as a simple database.
Note: A "friend" represents a "phonebook entry" ... which might be a bit misleading or impractical, 
Note: Maven required

## Structure

- **PhoneBookController**: Handles the API requests and maps them to the corresponding methods in the `PhoneBookRepository`.
- **PhoneBookRepository**: Manages the storage and retrieval of friends' data.
- **Friend**: Represents a friend with attributes like `name` and `phoneNumber`.

## API Endpoints

- `GET /phonebook`  
  Retrieves the entire list of friends. ( Phone book )

- `GET /phonebook/{name}`  
  Searches for a friend by name. Returns the friend's details if found.

- `POST /phonebook/{name}/{phoneNumber}`  
  Adds a new friend to the phonebook. The friend's name and phone number are provided in the URL path.

- `PUT /phonebook/{name}/{phoneNumber}`  
  Updates the phone number of an existing friend. If the friend doesn't exist, it will be added to the phonebook.

- `DELETE /phonebook/{name}`  
  Removes a friend from the phonebook based on their name.






## Screenshots frontend
run index.html for frontend demo. 
<img width="200" alt="phonebookprint" src="https://github.com/user-attachments/assets/0e75ea2a-4304-4a4a-8a3b-1ed190b1e546">
