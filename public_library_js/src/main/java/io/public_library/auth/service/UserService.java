package io.public_library.auth.service;

import io.public_library.person.Person;

public interface UserService {
	
    void save(Person person);
    Person findOne(String username);
}
