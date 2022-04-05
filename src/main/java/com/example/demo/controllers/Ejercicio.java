package com.example.demo.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Map;

import com.example.demo.utils.Utils;

//import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

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
    /*@GetMapping("/palindromo/{value}")
    public String palindrome(@PathVariable String value){
        boolean palindrome = Utils.isPalindrome(value);
        return palindrome ? "Si es palindromo": "No es palindromo";
       
        
    }*/
    // http://localhost:8080/palindromo/ana
    @GetMapping("/palindromo/{name}")
    public String palindrome(@PathVariable String name){
        boolean palindrome = Utils.isPalindrome(name);
        return palindrome ? "Si es palindromo" : "No es palindromo";
    }
    @GetMapping("/sumar")
    public String add(@RequestParam String num1, @RequestParam String num2){
        int resultado = Integer.parseInt(num1);
        Object params[] = {num1, num2, resultado};
      return MessageFormat.format("la suma de {0} y {1} es {2}", params);
    }
    @PostMapping("/saveProductOnDisk")
    public String saveProductOnDisk(@RequestParam Map<String,String> body){
        //obtener los datos un producto {articulo, precio}
        String articleValue = body.get("article");
        String priceValue = body.get("price");
        //valido que el articulo precio no sean vacios
        if(articleValue.equals("")  || priceValue.equals("") ){
            return "Error datos incorrectos";
        }
            if(Integer.parseInt(priceValue) < 0){
                return "Error el precio es negativo";
        }
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("datos.txt");
            pw = new PrintWriter(fw);
            pw.print(articleValue);
            pw.print(",");
            pw.print(priceValue);
            pw.print("\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error al escribir en disco";
        }finally{
            pw.close();
        }
        return "produc guardado correct";
    }
} 