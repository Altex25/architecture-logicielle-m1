package com.tp.complet.book;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookBuilder {
    private final String title;
    private final String author;
    private final String publicationYear;
    private final String publisher;
    private final String isbn;
    private final List<String> tags;
}
