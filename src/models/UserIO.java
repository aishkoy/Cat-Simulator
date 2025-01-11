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
        while(true){
            showActionMenu();
        }
    }

    private void initializeFields(){
        JsonHandler.readJson(IOManager.getValidString("[a-zA-Z0-9._-]+", "Введите имя файла (пример: something.json): "));
        cm.setCats(JsonHandler.getCatList());
    }

    private void showActionMenu(){
        System.out.println("""
                            СИМУЛЯТОР КОТОВ
                1. - Показать всех котов
                2. - Завести нового кота
                3. - Поиграть с котом
                4. - Покормить кота
                5. - Повести кота к ветеринару
                6. - Отсортировать список котов
                
                0. - Выйти
                """);
    }
}
