package com.tp.complet.library;

import lombok.Getter;

@Getter
public enum LibraryConfig {
    INSTANCE;

    private final String libraryName;
    private final int maxLoanDays;

    LibraryConfig() {
        this.libraryName = "Library";
        this.maxLoanDays = 30;
    }
}
