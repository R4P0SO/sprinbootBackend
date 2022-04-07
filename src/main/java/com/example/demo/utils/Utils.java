package com.example.demo.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*public class Utils {
    public static boolean isPalindrome(String value){
        StringBuilder builder = new  StringBuilder(value);
        String reversedName=builder.reverse().toString();
        return value.equals(reversedName);
    }
}*/
public class Utils {
    public static boolean isPalindrome(String value){
        StringBuilder builder = new StringBuilder(value);
        String reversedName = builder.reverse().toString();
        return value.equals(reversedName);
    }
    public static void save(String fileName, String text) throws IOException{
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(fileName, true);
            pw = new PrintWriter(fw);
            pw.print(text);
        
        }finally{
            if(pw!=null) pw.close();
        }
    }
    public static boolean remove(String fileName){
        File f = new File(fileName);
        boolean result = f.delete();
        return result;
    }
}

