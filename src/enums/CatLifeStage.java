package enums;

import models.Cat;

public enum CatLifeStage {
    YOUNG {
        @Override
        public void feedCat(Cat cat) {
            cat.changeHungerLevel(7);
            cat.changeMoodLevel(7);
            printAction("покормили кота", cat);
        }

        @Override
        public void treatCat(Cat cat) {
            cat.changeHealthLevel(7);
            cat.changeMoodLevel(-3);
            cat.changeHungerLevel(-3);
            printAction("оказали лечение", cat);
        }

        @Override
        public void playWithCat(Cat cat) {
            cat.changeHealthLevel(7);
            cat.changeMoodLevel(7);
            cat.changeHungerLevel(-3);
            printAction("поиграли с котом", cat);
        }
    },

    ADULT {
        @Override
        public void feedCat(Cat cat) {
            cat.changeHungerLevel(5);
            cat.changeMoodLevel(5);
            printAction("покормили кота", cat);
        }

        @Override
        public void treatCat(Cat cat) {
            cat.changeHealthLevel(5);
            cat.changeMoodLevel(-5);
            cat.changeHungerLevel(-5);
            printAction("оказали лечение", cat);
        }

        @Override
        public void playWithCat(Cat cat) {
            cat.changeHealthLevel(5);
            cat.changeMoodLevel(5);
            cat.changeHungerLevel(-5);
            printAction("поиграли с котом", cat);
        }
    },

    ELDER {
        @Override
        public void feedCat(Cat cat) {
            cat.changeHungerLevel(4);
            cat.changeMoodLevel(4);
            printAction("покормили кота", cat);
        }

        @Override
        public void treatCat(Cat cat) {
            cat.changeHealthLevel(4);
            cat.changeMoodLevel(-6);
            cat.changeHungerLevel(-6);
            printAction("оказали лечение", cat);
        }

        @Override
        public void playWithCat(Cat cat) {
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
        System.out.println("Вы " + message + "Кот: " + cat );
    }
}
