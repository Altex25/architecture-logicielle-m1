package org.example.loan.client;

import org.example.loan.exception.RemoteServiceException;
import org.example.loan.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class BookClient {

    private final RestTemplate restTemplate;
    private final String bookServiceUrl;

    public BookClient(RestTemplate restTemplate, @Value("${book.service.url}") String bookServiceUrl) {
        this.restTemplate = restTemplate;
        this.bookServiceUrl = bookServiceUrl;
    }

    public BookPayload findById(Long bookId) {
        String url = bookServiceUrl + "/api/books/{id}";
        try {
            BookPayload payload = restTemplate.getForObject(url, BookPayload.class, bookId);
            if (payload == null) {
                throw new RemoteServiceException("Book service returned an empty response", null);
            }
            return payload;
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("Book not found with id: " + bookId);
        } catch (RestClientException ex) {
            throw new RemoteServiceException("Cannot reach book-service", ex);
        }
    }

    public void updateAvailability(Long bookId, boolean available) {
        String url = bookServiceUrl + "/api/books/{id}/availability?available={available}";
        try {
            ResponseEntity<BookPayload> response = restTemplate.exchange(url, HttpMethod.PUT, null, BookPayload.class, bookId, available);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RemoteServiceException("book-service availability update failed", null);
            }
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("Book not found with id: " + bookId);
        } catch (RestClientException ex) {
            throw new RemoteServiceException("Cannot reach book-service", ex);
        }
    }

    public static class BookPayload {

        private Long id;
        private String title;
        private String author;
        private String isbn;
        private boolean available;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }
}
