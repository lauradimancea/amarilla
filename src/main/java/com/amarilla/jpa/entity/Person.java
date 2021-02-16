package com.amarilla.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "people")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    private String name;

    private String phone;

    private String email;

    private String address;

    private String primarySkill;

    private String skills;

    private String salary;

    private String legalForm;

    private String mobility;

    private String notes;

    private String source;
}
