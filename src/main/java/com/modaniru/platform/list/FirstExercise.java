package com.modaniru.platform.list;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FirstExercise {
    private static String word = null;
    private static String attempt = null;
    private static StringBuilder wordChecker = null;
    private static StringBuilder result = null;

    public static void main(String[] args) throws FileNotFoundException {
        init();

        getPresentLetter(result);
        getPresentLetter(wordChecker);
        getAnotherStatusLetter();

        for(int i = 0; i < result.length(); i++){
            switch (result.charAt(i)){
                case ('p'):
                    System.out.println("present");
                    break;
                case ('a'):
                    System.out.println("absent");
                    break;
                case ('c'):
                    System.out.println("correct");
                    break;
            }
        }
    }

    public static void init(){
        Scanner scanner = new Scanner(System.in);
        word = scanner.next();
        attempt = scanner.next();
        result = new StringBuilder("");
        wordChecker = new StringBuilder("");
    }
    public static void getPresentLetter(StringBuilder list){
        for(int i = 0; i < word.length();i++){
            if(word.charAt(i)==attempt.charAt(i)){
                list.append('c');
            }
            else{
                list.append('a');
            }
        }
    }

    public static void getAnotherStatusLetter(){
        for(int i = 0; i < word.length();i++){
            if(result.charAt(i)=='a' && findLetter(attempt.charAt(i))){
                result.replace(i, i+1, "p");
            }
        }
    }
    public static boolean findLetter(char letter){
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == letter && wordChecker.charAt(i)=='a'){
                wordChecker.replace(i,i+1,"p");
                return true;
            }
        }
        return false;
    }
}
