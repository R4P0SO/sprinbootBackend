package com.example.demo.controllers;

import com.example.demo.utils.Utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ejercicio{
    @GetMapping("/")
    public String greet(){
        return "bienvenido al servidor";
    }
    @GetMapping("/randoom")
    public String numberRandom(){
        long r = Math.round(Math.random()*100);
        return r + "";
    }
    @GetMapping("/palindromo/{value}")
    public String palindrome(@PathVariable String value){
        boolean palindrome = Utils.isPalindrome(value);
        return palindrome ? "Si es palindromo": "No es palindromo";
       
        
    }
}