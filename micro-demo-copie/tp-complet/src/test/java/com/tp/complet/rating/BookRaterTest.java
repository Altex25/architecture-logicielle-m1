package com.tp.complet.rating;

import com.tp.complet.rating.strategy.FictionRatingStrategy;
import com.tp.complet.rating.strategy.HistoryRatingStrategy;
import com.tp.complet.rating.strategy.ScienceRatingStrategy;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRaterTest {
    @Test
    void shouldCalculateExistingTypes() {
        BookRater rater = new BookRater(Map.of(
                "fiction", new FictionRatingStrategy(),
                "science", new ScienceRatingStrategy(),
                "history", new HistoryRatingStrategy()
        ));

        assertEquals(3.0, rater.calculateRating("fiction", 10), 0.0001);
        assertEquals(4.0, rater.calculateRating("science", 10), 0.0001);
        assertEquals(3.8, rater.calculateRating("history", 10), 0.0001);
    }

    @Test
    void shouldAddNewTypeWithoutModifyingBookRater() {
        BookRater rater = new BookRater(Map.of(
                "fiction", new FictionRatingStrategy()
        ));

        rater.registerStrategy("poetry", reviewCount -> Math.min(5, 1.5 + reviewCount * 0.2));

        assertEquals(3.5, rater.calculateRating("poetry", 10), 0.0001);
    }
}
