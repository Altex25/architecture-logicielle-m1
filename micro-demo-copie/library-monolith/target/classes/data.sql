INSERT INTO books (id, title, author, isbn, available) VALUES (1, 'Clean Code', 'Robert C. Martin', '9780132350884', true);
INSERT INTO books (id, title, author, isbn, available) VALUES (2, 'Effective Java', 'Joshua Bloch', '9780134685991', true);

INSERT INTO users (id, name, email) VALUES (1, 'Alice Martin', 'alice@example.com');
INSERT INTO users (id, name, email) VALUES (2, 'Bob Dupont', 'bob@example.com');

INSERT INTO reviews (id, book_id, user_id, rating, comment, created_at)
VALUES (1, 1, 1, 5, 'Excellent reference for clean architecture practices.', CURRENT_DATE);
