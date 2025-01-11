package models;

import services.IOManager;
import services.JsonHandler;

public class UserIO {
    private final CatManager cm;

    public UserIO() {
        this.cm = new CatManager();
    }

    public void run(){
        System.out.println("Добро пожаловать в симулятор котов!");
        initializeFields();
    }

    private void initializeFields(){
        JsonHandler.readJson(IOManager.getValidString("[a-zA-Z0-9._-]+", "Введите имя файла (пример: something.json): "));
        cm.setCats(JsonHandler.getCatList());
    }
}
