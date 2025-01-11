package enums;

import models.Cat;

public enum CatLifeStage {
    YOUNG {
        @Override
        public void feedCat(Cat cat) {

        }

        @Override
        public void treatCat(Cat cat) {

        }

        @Override
        public void playWithCat(Cat cat) {

        }
    },
    ADULT {
        @Override
        public void feedCat(Cat cat) {

        }

        @Override
        public void treatCat(Cat cat) {

        }

        @Override
        public void playWithCat(Cat cat) {

        }
    },
    ELDER {
        @Override
        public void feedCat(Cat cat) {

        }

        @Override
        public void treatCat(Cat cat) {

        }

        @Override
        public void playWithCat(Cat cat) {

        }
    };

    public abstract void feedCat(Cat cat);
    public abstract void treatCat(Cat cat);
    public abstract void playWithCat(Cat cat);

}
