package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {

    /*будет генерировать случайную строку*/
    public static String generateRandomString(){
        SecureRandom random = new SecureRandom();
        BigInteger bigInteger = new BigInteger(130, random); //Строка может состоять из цифр и любой из 26 маленьких букв английского алфавита
        return bigInteger.toString(36);
    }
    /*должен выводить переданный текст в консоль*/
    /*Весь дальнейший вывод в программе должен быть реализован через этот метод!*/
    public static void printMessage(String message){
        System.out.println(message);
    }
}
