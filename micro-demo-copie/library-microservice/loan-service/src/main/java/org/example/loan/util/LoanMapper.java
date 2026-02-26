package org.example.loan.util;

import org.example.loan.dto.LoanDTO;
import org.example.loan.model.Loan;

public final class LoanMapper {

    private LoanMapper() {
    }

    public static LoanDTO toDto(Loan loan) {
        return new LoanDTO(
                loan.getId(),
                loan.getBookId(),
                loan.getUserId(),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getStatus().name()
        );
    }
}
