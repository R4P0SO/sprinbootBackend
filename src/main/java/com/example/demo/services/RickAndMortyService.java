package com.example.demo.services;

import com.example.demo.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RickAndMortyService {
    @Autowired
    RestTemplate restTemplate;

    public Person getCharacterFromAPI(){
        long randomNumber = Math.round(Math.random()*826)+1;
        String url = "https://rickandmortyapi.com/api/character/"+randomNumber;
        Person person = restTemplate.getForObject(url, Person.class);
        return person;
    }
}
