package com.tp.complet.bird;

public class Sparrow implements FlyingBird {
    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }

    @Override
    public void eat() {
        System.out.println("Sparrow is eating seeds");
    }
}
