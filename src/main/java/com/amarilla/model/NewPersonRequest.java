package com.amarilla.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NewPersonRequest {

    @NotBlank
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
