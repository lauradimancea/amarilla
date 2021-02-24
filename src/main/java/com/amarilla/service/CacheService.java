package com.amarilla.service;

import com.amarilla.jpa.entity.Person;
import com.amarilla.jpa.repository.PersonRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CacheService {

    private static final String MILLIS_INTERVAL = "60000";

    private final PersonRepository personRepository;

    private List<Person> allPeople = new ArrayList<>();

    public CacheService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Scheduled(fixedDelayString = MILLIS_INTERVAL)
    public void run() {
        allPeople = load();
    }

    public List<Person> findAllPeople() {
        return new ArrayList<>(allPeople);
    }

    private List<Person> load() {
        return personRepository.findAll();
    }
}
