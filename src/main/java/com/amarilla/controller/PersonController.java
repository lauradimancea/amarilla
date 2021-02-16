package com.amarilla.controller;

import com.amarilla.jpa.entity.Person;
import com.amarilla.model.NewPersonRequest;
import com.amarilla.service.PersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin")
public class PersonController {

    private final PersonService personService;
    private final String baseUrl;

    public PersonController(PersonService personService,
                            @Value("${base.url}") String baseUrl) {
        this.personService = personService;
        this.baseUrl = baseUrl;
    }

    @GetMapping(path = "/people", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<List<Person>> getAllPeople() {

        return ResponseEntity.ok(personService.getAllPeople());
    }

    @GetMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Person> getPerson(@PathVariable int id) {

        Optional<Person> personOptional = personService.getPerson(id);
        return personOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Person> addPerson(@RequestBody @Valid NewPersonRequest newPersonRequest) throws URISyntaxException {

        Person person = personService.addPerson(newPersonRequest);
        String url = baseUrl + "/admin/person/" + person.getId();
        return ResponseEntity.created(new URI(url)).build();
    }

    @DeleteMapping(path = "/person/{id}")
    public HttpEntity deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return ResponseEntity.accepted().build();
    }
}
