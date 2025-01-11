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
        this.averageLivingStandard = calculateAverageLivingStandard();
        this.lifeStage = determineCatLifeStage();
    }

    private int calculateAverageLivingStandard() {
        return (int) Math.round((moodLevel + healthLevel + hungerLevel) / 3.0);
    }

    private CatLifeStage determineCatLifeStage () {
        if( age < 6) return CatLifeStage.YOUNG;
        else if (age < 11) return CatLifeStage.ADULT;
        else return CatLifeStage.ELDER;
    }
}
