package com.example.demo.controllers;

import java.text.MessageFormat;

import com.example.demo.utils.Utils;

import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/sumar")
    public String add(@RequestParam String num1, @RequestParam String num2){
        int resultado = Integer.parseInt(num1);
        Object params[] = {num1, num2, resultado};
      return MessageFormat.format("la suma de {0} y {1} es {2}", params);
    }

}