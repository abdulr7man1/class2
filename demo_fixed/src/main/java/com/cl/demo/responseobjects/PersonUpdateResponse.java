package com.cl.demo.responseobjects;

import com.cl.demo.entities.Person;
import com.cl.demo.requestobjects.PersonUpdateRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonUpdateResponse {

    String personId;
    String userName;
    String email;


    public static PersonUpdateResponse convert(Person person) {
        PersonUpdateResponse response = new PersonUpdateResponse();
        response.setPersonId(person.getId().toString());
        response.setEmail(person.getEmail());
        response.setUserName(person.getUserName().getActiveUserName());
        return response;








