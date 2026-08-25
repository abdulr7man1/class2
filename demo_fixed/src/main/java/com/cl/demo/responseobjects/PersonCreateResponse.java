package com.cl.demo.responseobjects;

import com.cl.demo.entities.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonCreateResponse {
    String personId;
    String fullName;
    String userName;
    String email;
    String phoneNumber;

    public static PersonCreateResponse convert(Person person) {
        PersonCreateResponse response = new PersonCreateResponse();

















