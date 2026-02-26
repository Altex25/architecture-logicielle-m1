package com.tp.complet.library;

import com.tp.complet.configuration.DatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class LibrarySingletonTest {
    @Autowired
    private DatabaseConfiguration databaseConfiguration1;

    @Autowired
    private DatabaseConfiguration databaseConfiguration2;

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldDbConfigReturnSameInstance() {
        assertSame(databaseConfiguration1, databaseConfiguration2);

        DatabaseConfiguration fromContext1 = context.getBean(DatabaseConfiguration.class);
        DatabaseConfiguration fromContext2 = context.getBean(DatabaseConfiguration.class);
        assertSame(fromContext1, fromContext2);
    }

    @Test
    void shouldLibraryConfigReturnSameInstance() {
        LibraryConfig first = LibraryConfig.INSTANCE;
        LibraryConfig second = LibraryConfig.INSTANCE;

        assertSame(first, second);
    }
}
