package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.entity.TransactionEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.TransactionRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

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
     *
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
     *
     * @param user to get the balance of that specific user.
     * @return updated balance after transaction done.
     */
    private Float getUpdatedBalance(UserEntity user) {
        Float balance = 0f;
        // TODO: Add getting the latest closing balance of the user
        return balance;
    }

    public TransactionResponse updateTransaction(Integer transactionID, @Valid TransactionRequest request) {
        Optional<TransactionEntity> Transaction = transactionRepository.findById(transactionID);
        if (Transaction.isEmpty()) {
            return TransactionResponse.builder()
                    .message("No records have been found.")
                    .txnID(transactionID)
                    .build();
        }
        final TransactionEntity transaction = Transaction.get();

        transaction.setAmount(request.getAmount());
        transaction.setNote(request.getNote());
        transaction.setTransactionType(request.getTransactionType());
        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .txnID(transactionID)
                .time(transaction.getTime())
                .amount(request.getAmount())
                .note(request.getNote())
                .transactionType(transaction.getTransactionType())
                .closingBalance(transaction.getClosingBalance())
                .message("Transaction updated")
                .build();

    }
}