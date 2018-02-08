package io.public_library.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.public_library.person.Person;
import io.public_library.person.PersonRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PersonRepository personRepository;
    //@Autowired
    //private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Person user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        personRepository.save(user);
    }

    /*@Override
    public Person findByUsername(String username) {
        return personRepository.findOne(username);
    }*/

	@Override
	public Person findOne(String username) {
		return personRepository.findOne(username);
	}
}
