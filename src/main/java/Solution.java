package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10_000);
    }

    /**
     * Этот метод должен для переданного множества строк
     * возвращать множество идентификаторов
     */
    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> idSet = new HashSet<>();
        for (String s : strings) {
            idSet.add(shortener.getId(s));
        }
        return idSet;
    }

    /**
     * Метод будет возвращать множество строк,
     * которое соответствует переданному множеству идентификаторов.
     */
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> stringSet = new HashSet<>();
        for (Long key : keys) {
            stringSet.add(shortener.getString(key));
        }
        return stringSet;
    }

    /**
     * Метод будет тестировать работу переданной стратегии
     * на определенном количестве элементов elementsNumber
     */
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        System.out.println(strategy.getClass().getSimpleName());
        Set<String> stringsPoolSrc = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            stringsPoolSrc.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date date1 = new Date();
        Set<Long> idPool = getIds(shortener, stringsPoolSrc);
        Date date2 = new Date();
        System.out.println(date2.getTime() - date1.getTime());

        Date date3 = new Date();
        Set<String> stringsPoolDst = getStrings(shortener, idPool);
        Date date4 = new Date();
        System.out.println(date4.getTime() - date3.getTime());

        if(stringsPoolSrc.containsAll(stringsPoolDst)){
            System.out.println("Тест пройден.");
        } else {
            System.out.println("Тест не пройден.");
        }
    }
}