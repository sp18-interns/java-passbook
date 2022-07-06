package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.PaginatedResponse;
import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.entity.TransactionEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.TransactionRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Transaction service helps to provide service to perform transactions
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create method to do the actual transaction contaning the business logic to add deposit and withdraw and return response according
     * @param userID  to make transaction for specific user.
     * @param request all fields required in transaction
     * @return the transaction response. (CREDIT OR DEBIT)
     * @throws Exception shows error messages.
     */
    public TransactionResponse transact(Integer userID, TransactionRequest request) throws Exception {

        Optional<UserEntity> optionalUser = userRepository.findById(userID);
        if (optionalUser.isEmpty())
            return TransactionResponse.builder().message("User not found. Please use a registered user").build();

        // TODO: Add deposit/withdraw logic and change below return accordingly

        return TransactionResponse.builder().build();
    }

    /**
     * This method(getZeroOrLastBalance) is to get balance as zero if its user first transaction or the closing balance of the latest transaction.
     * first assign the balance 0.
     * @param user to get the balance of that specific user.
     * @return updated balance after transaction done.
     */
    private Float getUpdatedBalance(UserEntity user) {
        Float balance = 0f;
        // TODO: Add getting the latest closing balance of the user
        return balance;
    }

    public PaginatedResponse getTransaction(String pageNo, String pageSize, @NotNull Integer userID) {

        final Optional<UserEntity> optionalUser = userRepository.findById(userID);
        if (optionalUser.isEmpty())
            return PaginatedResponse.builder()
                    .message("user not found")
                    .build();

        Pageable paging =
                PageRequest.of(
                        Integer.parseInt(pageNo),
                        Integer.parseInt(pageSize),
                        Sort.by("time").ascending());
        Page<TransactionEntity> transactions =
                transactionRepository.findByUser(paging, optionalUser.get());

        if (transactions.isEmpty())
            return PaginatedResponse.builder()
                    .message("No transactions for this user.")
                    .build();

        List<TransactionResponse> response =
                transactions.getContent().stream()
                        .map(this::toTransactionResponse)
                        .collect(Collectors.toList());
        return PaginatedResponse.builder()
                .content(Collections.singletonList(response))
                .totalPages(transactions.getTotalPages())
                .currentPage(Integer.parseInt(pageNo))
                .pageSize(Integer.parseInt(pageSize))
                .message("return list of transaction")
                .totalElements(transactions.getTotalElements())
                .build();

    }

    private TransactionResponse toTransactionResponse(TransactionEntity transaction) {

        return TransactionResponse.builder()
                .amount(transaction.getAmount())
                .time(transaction.getTime())
                .txnID(transaction.getId())
                .note(transaction.getNote())
                .closingBalance(transaction.getClosingBalance())
                .transactionType(transaction.getTransactionType())
                .build();
    }

}