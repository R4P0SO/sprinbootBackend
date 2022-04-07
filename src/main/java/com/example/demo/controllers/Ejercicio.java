package com.example.demo.controllers;



import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;

import com.example.demo.models.Person;
import com.example.demo.services.RickAndMortyService;
import com.example.demo.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ejercicio{
    @Autowired
    RickAndMortyService rickAndMortyService;

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
        /*
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("datos.txt");
            pw = new PrintWriter(fw);
            pw.print(articleValue+","+priceValue+"\n");
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al escribir en disco";
        } finally{
            pw.close();
        }*/
        try {
            Utils.save("datos.txt", articleValue+","+priceValue+"\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error al guardar en disco";
        }
        
        return "produc guardado correct";
    }
    @DeleteMapping("/removeFile")
    public String removeFile(){
        boolean result = Utils.remove("datos.txt");
        return result ? "borrado correcto" : "no se puede borrar";
    } 
    @GetMapping("/rickandmorty/random")
    public String getRickAndMortyRandomCharacter(){
        Person c = rickAndMortyService.getCharacterFromAPI();
        return "<img src='"+c.image+"'/>"+"<h2> "+c.name+"</h2>";
        //return MessageFormat.format("<img src='{0}'/>", c.image);


    }
} 