package com.tp.complet.userpreferences;

import com.tp.complet.prototype.UserPreferences;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class UserPreferencesTest {

    @Test
    void cloneShouldCreateIndependentObjectWithSameValues() throws Exception {
        UserPreferences original = new UserPreferences("DARK", "FR");
        UserPreferences cloned = original.clone();

        assertNotSame(original, cloned);

        Field pref1 = UserPreferences.class.getDeclaredField("preference1");
        Field pref2 = UserPreferences.class.getDeclaredField("preference2");
        pref1.setAccessible(true);
        pref2.setAccessible(true);

        assertEquals(pref1.get(original), pref1.get(cloned));
        assertEquals(pref2.get(original), pref2.get(cloned));
    }
}
