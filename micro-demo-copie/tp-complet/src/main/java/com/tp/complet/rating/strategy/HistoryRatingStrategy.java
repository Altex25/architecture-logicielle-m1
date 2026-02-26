package com.tp.complet.rating.strategy;

public class HistoryRatingStrategy implements RatingStrategy {
    @Override
    public double calculate(int reviewCount) {
        return Math.min(5, reviewCount * 0.08 + 3.0);
    }
}
