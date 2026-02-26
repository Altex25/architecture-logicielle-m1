package org.example.util;

import org.example.dto.BookDTO;
import org.example.dto.LoanDTO;
import org.example.dto.ReviewDTO;
import org.example.dto.UserDTO;
import org.example.model.Book;
import org.example.model.Loan;
import org.example.model.Review;
import org.example.model.User;

public final class DTOMapper {

    private DTOMapper() {
    }

    public static BookDTO toBookDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.isAvailable());
    }

    public static Book toBook(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setAvailable(dto.isAvailable());
        return book;
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public static User toUser(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static LoanDTO toLoanDTO(Loan loan) {
        return new LoanDTO(
                loan.getId(),
                loan.getBook().getId(),
                loan.getUser().getId(),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getStatus().name()
        );
    }

    public static ReviewDTO toReviewDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getBook().getId(),
                review.getUser().getId(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }
}
