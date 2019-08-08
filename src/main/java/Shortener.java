package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {


    private StorageStrategy storageStrategy; //будет храниться стратегия хранения данных
    private Long lastId = 0L; //Это поле будет отвечать за последнее значение идентификатора, которое было использовано при добавлении новой строки в хранилище.

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    /*будет возвращать идентификатор id для заданной строки.*/
    public synchronized Long getId(String string){
        if (storageStrategy.containsValue(string))
            return storageStrategy.getKey(string);
        else {
            lastId++;
            storageStrategy.put(lastId, string);
        }
        return lastId;
    }

    /*будет возвращать строку для заданного идентификатора или null, если передан неверный идентификатор.*/
    public synchronized String getString(Long id){
        return storageStrategy.getValue(id);
    }

}