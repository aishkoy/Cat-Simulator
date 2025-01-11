package models;

import services.IOManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class CatManager {
    private List<Cat> cats;
    private final static Random rand = new Random();

    public CatManager() {
        cats = new ArrayList<>();
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        if(cats == null || cats.isEmpty()){
            this.cats = new ArrayList<>();
            return;
        }
        this.cats.addAll(cats);
    }

    public void createCat() {
        String name = IOManager.getValidString("[a-zA-Zа-яА-Я]+", "Введите имя кота: ");
        int age = IOManager.getValidInt("^(1[0-8]|[1-9])$", "Введите возраст кота (1-18): ");
        int hungerLevel = rand.nextInt(61) + 20;
        int moodLevel = rand.nextInt(61) + 20;
        int healthLevel = rand.nextInt(61) + 20;

        cats.add(new Cat(name, age, hungerLevel, moodLevel, healthLevel));
    }

    public void feedCat(Cat cat) {
        cat.getLifeStage().feedCat(cat);
        cat.calculateAverageLivingStandard();
        cat.setActionPerformedToday(true);
    }

    public void treatCat(Cat cat) {
        cat.getLifeStage().treatCat(cat);
        cat.calculateAverageLivingStandard();
        cat.setActionPerformedToday(true);
    }

    public void playWithCat(Cat cat) {
        cat.getLifeStage().playWithCat(cat);
        cat.calculateAverageLivingStandard();
        cat.setActionPerformedToday(true);
    }

    public void startNextDay(){
        if(cats.isEmpty()){
            System.out.println("Список котов пуст! Добавьте котов!");
            return;
        }

        for(Cat cat : cats){
            cat.changeHungerLevel(-5 + (int)(Math.random() * (5)));
            cat.changeMoodLevel( -3 + (int)(Math.random() * 7));
            cat.changeHealthLevel( -3 + (int)(Math.random() * 7));
            cat.calculateAverageLivingStandard();
            cat.setActionPerformedToday(false);
        }

        System.out.println("""
                Настал новый день!
                Характеристики котов были изменены""");
    }

    public void deleteCat(Cat cat) {
        cats.remove(cat);
    }

    public void sortAndDisplayCats(Comparator<Cat> comparator, boolean isAscending) {
        if(cats.isEmpty()){
            System.out.println("Список котов пуст! Добавьте котов!");
            return;
        }

        if(!isAscending){
            comparator = comparator.reversed();
        }

        cats.sort(comparator);

        System.out.println("╔══════╦═════════════════╦═════════╦══════════╦════════════╦═════════╦═════════════════╗");
        System.out.println("║  №   ║       имя       ║ возраст ║ здоровье ║ настроение ║ сытость ║ средний уровень ║");
        System.out.println("╠══════╬═════════════════╬═════════╬══════════╬════════════╬═════════╬═════════════════╣");
        int catIndex = 0;
        for(Cat cat : cats){
            System.out.printf("║  %-3s ║ %-15s ║   %-3s   ║    %-3s   ║    %-3s     ║    %-3s  ║       %-8s  ║%n",
                    (++catIndex),  cat.getName(), cat.getAge(), cat.getHealthLevel(), cat.getMoodLevel(), cat.getHungerLevel(), cat.getAverage());
        }
        System.out.println("╚══════╩═════════════════╩═════════╩══════════╩════════════╩═════════╩═════════════════╝");
    }
}
