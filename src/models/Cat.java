package models;

public class Cat {
    private final String name;
    private final int age;
    private int hungerLevel;
    private int moodLevel;
    private int healthLevel;
    private int averageLivingStandard;

    public Cat(String name, int age, int hungerLevel, int moodLevel, int healthLevel) {
        this.name = name;
        this.age = age;
        this.hungerLevel = hungerLevel;
        this.moodLevel = moodLevel;
        this.healthLevel = healthLevel;
        this.averageLivingStandard = calculateAverageLivingStandard();
    }

    private int calculateAverageLivingStandard() {
        return (int) Math.round((moodLevel + healthLevel + hungerLevel) / 3.0);
    }
}
