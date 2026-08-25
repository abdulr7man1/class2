package com.cl.demo.services;

import com.cl.demo.DemoApplication;
import com.cl.demo.entities.Person;
import com.cl.demo.entities.UserName;
import com.cl.demo.requestobjects.PersonCreateRequest;
import com.cl.demo.requestobjects.PersonUpdateRequest;
import com.cl.demo.utils.HelperUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {

    public static final String PERSON_USERNAME_OR_EMAIL_ALREADY_TAKEN = "Given username or email is already taken";
    public static final String PERSON_SAVED = "Person saved";

    public Map<String, String> addPerson(PersonCreateRequest requestObj) {

        Map<String, String> response = new HashMap<>();
        Person person = new Person();

        if (!verifyUserNameAndEmail(requestObj.getPersonUserName(), requestObj.getPersonEmail())) {
            response.put("error", PERSON_USERNAME_OR_EMAIL_ALREADY_TAKEN);
            return response;
        }

        person.setId(UUID.randomUUID());
        person.setIsActive(Boolean.TRUE);
        person.setCreatedDate(new Date());

        UserName userName = new UserName();
        userName.setActiveUserName(requestObj.getPersonUserName());

        person.setUserName(userName);
        person.setName(getFullName(requestObj));
        person.setEmail(requestObj.getPersonEmail());

        //TODO: Add Phone Number Logic in PhoneNumber Service
        Boolean result = DemoApplication.Person_List.add(person);

        if (result) {
            response.put("response", PERSON_SAVED);
        }
        return response;
    }

    public Person getPersonById(String uuid) {
        for (Person p : DemoApplication.Person_List) {
            if (p.getId().toString().equals(uuid) && p.getIsActive() != false) {
                return p;
            }
        }
        return new Person();
    }

    public Person updatePerson(PersonUpdateRequest updateObj) {
        Person person = getPersonById(updateObj.getUuid());
        if (person == null || person.getId() == null || !person.getIsActive()) {
            return person;
        }
        DemoApplication.Person_List.remove(person);

        person.setUserName(getUserNameByCompare(person.getUserName(), updateObj));
        person.setEmail(HelperUtils.compare(person.getEmail(), updateObj.getEmailToUpdate()));

        DemoApplication.Person_List.add(person);
        return person;
    }

    public List<Person> getAllPersons() {


















































