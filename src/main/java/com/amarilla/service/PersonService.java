package com.amarilla.service;

import com.amarilla.jpa.entity.Person;
import com.amarilla.jpa.repository.PersonRepository;
import com.amarilla.model.NewPersonRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Optional<Person> getPerson(int id) {
        return personRepository.findById(id);
    }

    public Person addPerson(NewPersonRequest newPersonRequest) {
        Person personToAdd = Person.builder()
                .name(newPersonRequest.getName())
                .address(newPersonRequest.getAddress())
                .email(newPersonRequest.getEmail())
                .phone(newPersonRequest.getPhone())
                .salary(newPersonRequest.getSalary())
                .legalForm(newPersonRequest.getLegalForm())
                .mobility(newPersonRequest.getMobility())
                .primarySkill(newPersonRequest.getPrimarySkill())
                .skills(newPersonRequest.getSkills())
                .notes(newPersonRequest.getNotes())
                .source(newPersonRequest.getSource())
                .build();
        return personRepository.save(personToAdd);
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }
}
