package com.tp.complet.rating.strategy;

public class ScienceRatingStrategy implements RatingStrategy {
    @Override
    public double calculate(int reviewCount) {
        return Math.min(5, reviewCount * 0.15 + 2.5);
    }
}
