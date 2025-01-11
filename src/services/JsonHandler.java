package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Cat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private static final Type TYPE = new TypeToken<List<Cat>>(){}.getType();
    private static final String DIRECTORY = "src/data/";
    private static Path PATH;

    private static List<Cat> catList;

    private JsonHandler(){}

    public static void readJson(String fileName){
        try {
            PATH = getPath(fileName);
            String json = Files.readString(PATH);
            catList = GSON.fromJson(json, TYPE);
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            offerFileAction();
        } catch (IOException e) {
            System.out.println("Произошла ошибка при чтении файла: " + e.getMessage());
        }

    }

    public static void writeJson(){
        try{
            String newJson = GSON.toJson(catList, TYPE);
            Files.write(PATH, newJson.getBytes());
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи файла: " + e.getMessage());
        }

    }

    private static Path getPath(String fileName) throws FileNotFoundException {
        File file = new File(DIRECTORY + fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Такого файла не существует!");
        }
        return file.toPath();
    }

    private static void offerFileAction(){
        System.out.println("""
                
                Выберите одно из следующих действий:
                1. - Ввести другое название файла
                2. - Создать файл""");
        int choice = IOManager.getValidInt("[1-2]", "Введите число: ");
        switch (choice){
            case 1 -> readJson(IOManager.getValidString("[a-zA-Z0-9._-]+", "Введите имя файла: "));
            case 2 -> createFile();
        }
    }

    private static void createFile(){
        String fileName = IOManager.getValidString("[a-zA-Z0-9._-]+", "Введите имя файла: ");
        String fullPath = DIRECTORY + fileName;
        try(FileWriter _ = new FileWriter(fullPath)){
            System.out.println("Пустой файл " + fileName + " был успешно создан!");
            PATH = Paths.get(fullPath);
        } catch (IOException e){
            System.out.println("Произошла ошибка при создании файла: " + e.getMessage());
        }
    }

    public static List<Cat> getCatList() {
        return catList;
    }
}
