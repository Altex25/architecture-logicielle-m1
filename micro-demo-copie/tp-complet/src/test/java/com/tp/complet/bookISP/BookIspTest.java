package com.tp.complet.bookISP;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class BookIspTest {
    @Test
    void ebookShouldExposeOnlyRelevantContracts() {
        EBook ebook = new EBook("Clean Code", "Robert C. Martin");

        assertInstanceOf(Book.class, ebook);
        assertInstanceOf(DigitalBook.class, ebook);
        assertInstanceOf(LoanableBook.class, ebook);
    }

    @Test
    void printedBookShouldExposeOnlyRelevantContracts() {
        PrintedBook printedBook = new PrintedBook("DDD", "Eric Evans");

        assertInstanceOf(Book.class, printedBook);
        assertInstanceOf(PrintableBook.class, printedBook);
        assertInstanceOf(LoanableBook.class, printedBook);
    }
}
