package com.tp.complet.rating;

import com.tp.complet.rating.strategy.RatingStrategy;

import java.util.HashMap;
import java.util.Map;

public class BookRater {
    private final Map<String, RatingStrategy> strategies;

    public BookRater(Map<String, RatingStrategy> strategies) {
        this.strategies = new HashMap<>(strategies);
    }

    public double calculateRating(String bookType, int reviewCount) {
        RatingStrategy strategy = strategies.get(bookType);
        if (strategy == null) {
            return 0;
        }
        return strategy.calculate(reviewCount);
    }

    public void registerStrategy(String bookType, RatingStrategy strategy) {
        strategies.put(bookType, strategy);
    }
}
