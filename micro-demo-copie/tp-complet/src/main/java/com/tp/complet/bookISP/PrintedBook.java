package com.tp.complet.bookISP;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PrintedBook implements Book, PrintableBook, LoanableBook {

    private final String title;
    private final String author;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void registerLoan() {
        System.out.println("Registering eBook loan...");
    }

    @Override
    public void calculateFine() {
        System.out.println("Calculating eBook fine...");
    }

    @Override
    public void printBook() {
        System.out.println("Printing physical book...");
    }

    @Override
    public void bindBook() {
        System.out.println("Binding physical book...");
    }
}
