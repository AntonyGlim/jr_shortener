package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** должен возвращать размер файла на который указывает path */
    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** должен сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry. */
    public void putEntry(Entry entry){
        try (OutputStream out = Files.newOutputStream(path)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
                if (entry.next != null) {
                    putEntry(entry.next);
                }
                oos.writeObject(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null */
    public Entry getEntry(){
        if (getFileSize() == 0) return null;
        Entry entry = null;
        try (InputStream in = Files.newInputStream(path)) {
            try (ObjectInputStream ois = new ObjectInputStream(in)) {
                entry = (Entry) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entry;
    }

    /** удалять файл на который указывает path */
    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
