package models;

import services.IOManager;
import services.JsonHandler;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class UserIO {
    private static final CatManager cm = new CatManager();

    public UserIO() {
        JsonHandler.readJson(IOManager.getValidString("[a-zA-Z0-9._-]+", "Введите имя файла (пример: something.json): "));
        cm.setCats(JsonHandler.getCatList());
    }

    public void run() {
        System.out.println("Добро пожаловать в симулятор котов!");
        while (true) {
            showActionMenu();
            processMenuChoice();
        }
    }

    private void showActionMenu() {
        System.out.println("""
                            СИМУЛЯТОР КОТОВ
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
        int choice = IOManager.getValidInt("[1-2]", "Введите число: ");
        switch (choice) {
            case 1 -> cm.sortAndDisplayCats(Comparator.comparing(Cat::getAverage).reversed(), false);
            case 2 -> cm.createCat();
            case 3 -> {
                if (choiceCat() == null) {
                    System.out.println("Список котов пуст, сначала добавьте кота!");
                    return;
                }
                cm.playWithCat(Objects.requireNonNull(choiceCat()));
            }
            case 4 -> {
                if (choiceCat() == null) {
                    System.out.println("Список котов пуст, сначала добавьте кота!");
                    return;
                }
                cm.feedCat(Objects.requireNonNull(choiceCat()));
            }
            case 5 -> {
                if (choiceCat() == null) {
                    System.out.println("Список котов пуст, сначала добавьте кота!");
                    return;
                }
                cm.treatCat(Objects.requireNonNull(choiceCat()));
            }
            case 6 -> {
            }
            case 7 -> {
            }
            case 0 -> {
            }
        }
    }

    private static Cat choiceCat() {
        List<Cat> cats = cm.getCats();
        if (cats.isEmpty()) return null;

        System.out.println("Список котов: ");
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
}
