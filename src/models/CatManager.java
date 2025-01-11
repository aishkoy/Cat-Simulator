package models;

import services.IOManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CatManager {
    private final List<Cat> cats;
    private final static Random rand = new Random();

    public CatManager() {
        cats = new ArrayList<>();
    }

    public CatManager(List<Cat> cats) {
        this.cats = cats;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void createCat() {
        String name = IOManager.getValidString("[a-zA-Zа-яА-Я]+", "Введите имя для кота: ");
        int age = IOManager.getValidInt("1?[1-9]|1[0-8]", "Введите возраст для кота (1-18): ");
        int hungerLevel = rand.nextInt(61) + 20;
        int moodLevel = rand.nextInt(61) + 20;
        int healthLevel = rand.nextInt(61) + 20;

        cats.add(new Cat(name, age, hungerLevel, moodLevel, healthLevel));
    }

    public void feedCat(Cat cat) {
        cat.getLifeStage().feedCat(cat);
        cat.calculateAverageLivingStandard();
    }

    public void treatCat(Cat cat) {
        cat.getLifeStage().treatCat(cat);
        cat.calculateAverageLivingStandard();
    }

    public void playWithCat(Cat cat) {
        cat.getLifeStage().playWithCat(cat);
        cat.calculateAverageLivingStandard();
    }

    public void startNextDay(){
        for(Cat cat : cats){
            cat.changeHungerLevel(-5 + (int)(Math.random() * (5)));
            cat.changeMoodLevel( -3 + (int)(Math.random() * 7));
            cat.changeHealthLevel( -3 + (int)(Math.random() * 7));
            cat.calculateAverageLivingStandard();
        }

        System.out.println("""
                Настал новый день!
                Характеристики котов были изменены""");
    }
}
