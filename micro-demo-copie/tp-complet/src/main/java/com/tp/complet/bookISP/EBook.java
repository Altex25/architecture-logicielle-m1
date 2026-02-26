package com.tp.complet.bookISP;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EBook implements Book, DigitalBook, LoanableBook {

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
    public void downloadPDF() {
        System.out.println("Downloading PDF...");
    }

    @Override
    public void openEReader() {
        System.out.println("Open in E-Reader...");
    }

    @Override
    public void registerLoan() {
        System.out.println("Registering eBook loan...");
    }

    @Override
    public void calculateFine() {
        System.out.println("Calculating eBook fine...");
    }
}
