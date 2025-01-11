package enums;

import models.Cat;

import java.util.Random;

public enum CatLifeStage {
    YOUNG {
        @Override
        public void feedCat(Cat cat) {
            if(isRiskPresent()){
                System.out.println("При кормлении что-то пошло не так, и кот отравился!");
                cat.changeHealthLevel(-5);
                cat.changeMoodLevel(-5);
                return;
            }
            cat.changeHungerLevel(7);
            cat.changeMoodLevel(7);
            printAction("покормили кота", cat);
        }

        @Override
        public void treatCat(Cat cat) {
            if(isRiskPresent()){
                System.out.println("Вы дали коту не то лекарство. Ему стало только хуже!");
                cat.changeHealthLevel(-5);
                cat.changeMoodLevel(-5);
                return;
            }
            cat.changeHealthLevel(7);
            cat.changeMoodLevel(-3);
            cat.changeHungerLevel(-3);
            printAction("оказали лечение", cat);
        }

        @Override
        public void playWithCat(Cat cat) {
            if(isRiskPresent()){
                System.out.println("При игре кот случайно травмировался!");
                cat.changeHealthLevel(-5);
                cat.changeMoodLevel(-5);
            }
            cat.changeHealthLevel(7);
            cat.changeMoodLevel(7);
            cat.changeHungerLevel(-3);
            printAction("поиграли с котом", cat);
        }
    },

    ADULT {
        @Override
        public void feedCat(Cat cat) {
            if(isRiskPresent()){
                System.out.println("При кормлении что-то пошло не так, и кот отравился!");
                cat.changeHealthLevel(-7);
                cat.changeMoodLevel(-7);
                return;
            }
            cat.changeHungerLevel(5);
            cat.changeMoodLevel(5);
            printAction("покормили кота", cat);
        }

        @Override
        public void treatCat(Cat cat) {
            if(isRiskPresent()){
                System.out.println("Вы дали коту не то лекарство. Ему стало только хуже!");
                cat.changeHealthLevel(-7);
                cat.changeMoodLevel(-7);
                return;
            }
            cat.changeHealthLevel(5);
            cat.changeMoodLevel(-5);
            cat.changeHungerLevel(-5);
            printAction("оказали лечение", cat);
        }

        @Override
        public void playWithCat(Cat cat) {
            if(isRiskPresent()){
                System.out.println("При игре кот случайно травмировался!");
                cat.changeHealthLevel(-7);
                cat.changeMoodLevel(-7);
            }
            cat.changeHealthLevel(5);
            cat.changeMoodLevel(5);
            cat.changeHungerLevel(-5);
            printAction("поиграли с котом", cat);
        }
    },

    ELDER {
        @Override
        public void feedCat(Cat cat) {
            if(isRiskPresent()){
                System.out.println("При кормлении что-то пошло не так, и кот отравился!");
                cat.changeHealthLevel(-9);
                cat.changeMoodLevel(-9);
                return;
            }
            cat.changeHungerLevel(4);
            cat.changeMoodLevel(4);
            printAction("покормили кота", cat);
        }

        @Override
        public void treatCat(Cat cat) {
            if(isRiskPresent()){
                System.out.println("Вы дали коту не то лекарство. Ему стало только хуже!");
                cat.changeHealthLevel(-9);
                cat.changeMoodLevel(-9);
                return;
            }
            cat.changeHealthLevel(4);
            cat.changeMoodLevel(-6);
            cat.changeHungerLevel(-6);
            printAction("оказали лечение", cat);
        }

        @Override
        public void playWithCat(Cat cat) {
            if(isRiskPresent()){
                System.out.println("При игре кот случайно травмировался!");
                cat.changeHealthLevel(-9);
                cat.changeMoodLevel(-9);
            }
            cat.changeHealthLevel(4);
            cat.changeMoodLevel(4);
            cat.changeHungerLevel(-6);
            printAction("поиграли с котом", cat);
        }
    };

    public abstract void feedCat(Cat cat);
    public abstract void treatCat(Cat cat);
    public abstract void playWithCat(Cat cat);

    void  printAction(String message, Cat cat) {
        System.out.println("Вы " + message + " (" + cat + ")");
    }

    boolean isRiskPresent(){
        Random rand = new Random();
        return rand.nextDouble() <= 0.20;
    }
}
