package com.amarilla.service;

import com.amarilla.jpa.entity.Person;
import com.amarilla.jpa.repository.PersonRepository;
import com.amarilla.model.NewPersonRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final CacheService cacheService;

    public PersonService(PersonRepository personRepository, CacheService cacheService) {
        this.personRepository = personRepository;
        this.cacheService = cacheService;
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Optional<Person> getPerson(int id) {
        return personRepository.findById(id);
    }

    public List<Person> findPeopleByAddressAndSkillAndLanguage(String address, String skill, String language, int experience) {
        return cacheService.findAllPeople().stream()
                .filter(person -> person.getYearsExperience() >= experience)
                .filter(person -> hasAddress(person, address))
                .filter(person -> hasLanguage(person, language))
                .filter(person -> hasSkill(person, skill))
                .collect(Collectors.toList());
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
                .language(newPersonRequest.getLanguage())
                .yearsExperience(newPersonRequest.getYearsExperience())
                .build();
        return personRepository.save(personToAdd);
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }

    private boolean hasSkill(Person person, String skill) {

        if (skill == null) return true;

        return person.getPrimarySkill() != null && person.getPrimarySkill().equalsIgnoreCase(skill)
                || person.getSkills() != null && person.getSkills().toLowerCase().contains(skill.toLowerCase());
    }

    private boolean hasAddress(Person person, String address) {

        if (address == null) return true;

        return person.getAddress() != null && person.getAddress().equalsIgnoreCase(address);
    }

    private boolean hasLanguage(Person person, String language) {

        if (language == null) return true;

        return person.getLanguage() != null && person.getLanguage().toLowerCase().contains(language.toLowerCase());
    }
}
