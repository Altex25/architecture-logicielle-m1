package com.tp.complet.rating.strategy;

public class FictionRatingStrategy implements RatingStrategy {
    @Override
    public double calculate(int reviewCount) {
        return Math.min(5, reviewCount * 0.1 + 2.0);
    }
}
