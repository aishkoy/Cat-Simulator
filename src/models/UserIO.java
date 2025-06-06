package models;

import services.IOManager;
import services.JsonHandler;

import java.util.Comparator;
import java.util.List;

public class UserIO {
    private static final CatManager cm = new CatManager();
    private static boolean useDefaultSort = true;
    private static Comparator<Cat> currentComparator = Comparator.comparing(Cat::getAverage);
    private static boolean currentAscending = false;

    public UserIO() {
        JsonHandler.readJson(IOManager.getValidString("[a-zA-Z0-9._-]+", "Введите имя файла (пример: something.json): "));
        cm.setCats(JsonHandler.getCatList());
    }

    public void run() {
        System.out.println("\nДобро пожаловать в симулятор котов!");
        initializeCatsFields();
        displayCats();
        while (true) {
            showActionMenu();
            processMenuChoice();
            JsonHandler.writeJson(cm.getCats());
        }
    }

    private void initializeCatsFields(){
        if(!cm.getCats().isEmpty()){
            for (Cat cat : cm.getCats()) {
                cat.setActionPerformedToday(false);
                cat.setLifeStage(cat.determineCatLifeStage());
            }
        }
    }


    private void showActionMenu() {
        System.out.println("""
                
                            МЕНЮ ДЕЙСТВИЙ
                1. - Показать всех котов
                2. - Завести нового кота
                3. - Поиграть с котом
                4. - Покормить кота
                5. - Повести кота к ветеринару
                6. - Отсортировать список котов
                7. - Начать новый день
                
                0. - Выйти
                """);
    }

    private void processMenuChoice() {
        int choice = IOManager.getValidInt("^[0-7]$", "Введите число: ");
        switch (choice) {
            case 1 -> displayCats();
            case 2 -> cm.createCat();
            case 3 -> interactWithCat("поиграть");
            case 4 -> interactWithCat("покормить");
            case 5 -> interactWithCat("лечить");
            case 6 -> processSortMenu();
            case 7 -> {
                cm.startNextDay();
                displayCats();
            }
            case 0 -> {
                System.out.println("До свиданья");
                System.exit(0);
            }
        }
    }

    private void interactWithCat(String interaction){
        Cat cat = choiceCat();
        if (cat == null) {
            System.out.println("Список котов пуст, сначала добавьте кота!");
            return;
        }

        if(cat.isActionPerformedToday()){
            System.out.println("Вы уже взаимодействовали с этим котом!");
            return;
        }

        switch (interaction){
            case "поиграть" -> cm.playWithCat(cat);
            case "покормить" -> cm.feedCat(cat);
            case "лечить" -> cm.treatCat(cat);
            default -> System.out.println("выбрано неверное действие");
        }

    }

    private static Cat choiceCat() {
        List<Cat> cats = cm.getCats();
        if (cats.isEmpty()) return null;

        System.out.println("\nСписок котов: ");
        for (int i = 0; i < cats.size(); i++) {
            System.out.println(i + 1 + ". " + cats.get(i));
        }

        int catIndex;
        do {
            catIndex = IOManager.getValidInt("[1-9]\\d*", "Введите номер кота: ");
            if (catIndex < 1 || catIndex > cats.size()) {
                System.out.println("Неверный ввод! ");
            }
        } while (catIndex < 1 || catIndex > cats.size());

        return cats.get(catIndex - 1);
    }

    private void processSortMenu(){
        if(cm.getCats().isEmpty()) {
            System.out.println("Список котов пуст, сначала добавьте кота!");
            return;
        }

        System.out.println("""
                          МЕНЮ СОРТИРОВКИ
                1. - По имени
                2. - По возрасту
                3. - По уровню здоровья
                4. - По уровню настроения
                5. - По уровню сытости
                6. - По среднему уровню жизни
                """);

        int choice = IOManager.getValidInt("^[1-6]$", "Введите число: ");
        currentAscending = isAscendingSort();

        switch (choice) {
            case 1 -> currentComparator = Comparator.comparing(Cat::getName);
            case 2 -> currentComparator = Comparator.comparing(Cat::getAge);
            case 3 -> currentComparator = Comparator.comparing(Cat::getHealthLevel);
            case 4 -> currentComparator = Comparator.comparing(Cat::getMoodLevel);
            case 5 -> currentComparator = Comparator.comparing(Cat::getHungerLevel);
            case 6 -> currentComparator = Comparator.comparing(Cat::getAverage);
            default -> throw new IllegalStateException("Неверный выбор сортировки");
        }

        useDefaultSort = false;

        displayCats();
    }

    private boolean isAscendingSort(){
        System.out.println("""
                
                        ВЫБЕРИТЕ ВАРИАНТ СОРТИРОВКИ
                1. - По возрастанию
                2. - По убыванию
                """);
        return IOManager.getValidInt("^[1-2]$", "Введите число: ") == 1;
    }


    private void displayCats() {
        if (useDefaultSort) {
            cm.sortAndDisplayCats(Comparator.comparing(Cat::getAverage), false);
        } else {
            cm.sortAndDisplayCats(currentComparator, currentAscending);
        }
    }
}
