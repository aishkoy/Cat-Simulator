package models;

import enums.CatLifeStage;

public class Cat {
    private final String name;
    private final int age;
    private int hungerLevel;
    private int moodLevel;
    private int healthLevel;
    private int averageLivingStandard;

    private final transient CatLifeStage lifeStage;

    public Cat(String name, int age, int hungerLevel, int moodLevel, int healthLevel) {
        this.name = name;
        this.age = age;
        this.hungerLevel = hungerLevel;
        this.moodLevel = moodLevel;
        this.healthLevel = healthLevel;
        calculateAverageLivingStandard();
        this.lifeStage = determineCatLifeStage();
    }

    public void calculateAverageLivingStandard() {
        this.averageLivingStandard = (int) Math.round((moodLevel + healthLevel + hungerLevel) / 3.0);
    }

    private CatLifeStage determineCatLifeStage () {
        if( age < 6) return CatLifeStage.YOUNG;
        else if (age < 11) return CatLifeStage.ADULT;
        else return CatLifeStage.ELDER;
    }

    public void changeHungerLevel(int amount) {
        hungerLevel = Math.max(Math.min(hungerLevel + amount, 100), 0);
    }

    public void changeMoodLevel(int amount) {
        moodLevel = Math.max(Math.min(moodLevel + amount, 100), 0);
    }

    public void changeHealthLevel(int amount) {
        healthLevel = Math.max(Math.min(healthLevel + amount, 100), 0);
    }

    public CatLifeStage getLifeStage() {
        return lifeStage;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public int getMoodLevel() {
        return moodLevel;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public int getAverage() {
        return averageLivingStandard;
    }

    @Override
    public String toString() {
        String ageString = switch (age) {
            case 1 -> "год";
            case 2,3,4 -> "года";
            default -> "лет";
        };
        return name + " : " + age + ageString;
    }
}
