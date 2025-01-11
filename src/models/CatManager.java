package models;

import java.util.ArrayList;
import java.util.List;

public class CatManager {
    private final List<Cat> cats;

    public CatManager() {
        cats = new ArrayList<>();
    }

    public CatManager(List<Cat> cats) {
        this.cats = cats;
    }

    public List<Cat> getCats() {
        return cats;
    }
}
