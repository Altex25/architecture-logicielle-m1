INSERT INTO books (id, title, author, isbn, available) VALUES (1, 'Book 1', 'Author 1', '99R8937R39784734', true);
INSERT INTO books (id, title, author, isbn, available) VALUES (2, 'Book 2', 'Author 2', '9829849742974', true);

INSERT INTO users (id, name, email) VALUES (1, 'Alice A', 'alice@example.com');
INSERT INTO users (id, name, email) VALUES (2, 'Bob B', 'bob@example.com');

INSERT INTO reviews (id, book_id, user_id, rating, comment, created_at)
VALUES (1, 1, 1, 5, 'A good book.', CURRENT_DATE);
