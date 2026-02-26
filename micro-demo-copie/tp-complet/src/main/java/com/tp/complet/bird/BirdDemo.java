package com.tp.complet.bird;

import java.util.List;

public class BirdDemo {
    public static void main(String[] args) {
        List<Bird> birds = List.of(new Sparrow(), new Ostrich());
        // OK pour tous les Bird
        birds.forEach(Bird::eat);

        List<FlyingBird> flyingBirds = List.of(new Sparrow());
        // OK pour tous les FlyingBird
        flyingBirds.forEach(FlyingBird::fly);
    }
}
