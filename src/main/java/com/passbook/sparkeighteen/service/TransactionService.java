package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.constant.PassbookApplicationConstant;
import com.passbook.sparkeighteen.exception.PassbookException;
import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.entity.TransactionEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.TransactionRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Transaction service helps to provide service to perform transactions.
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    /**
     * Instantiates a new Transaction service.
     * @param transactionRepository the transaction repository
     * @param userRepository the user repository
     */
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create method to do the actual transaction containing the business logic to add deposit and withdraw and return response according.
     * @param userID  to make transaction for specific user.
     * @param request all fields required in transaction
     * @return the transaction response. (CREDIT OR DEBIT)
     * @throws Exception shows error messages.
     */
    public TransactionResponse transact(Integer userID, TransactionRequest request) {

        if (request.getAmount() <= 0)
            throw new PassbookException(PassbookApplicationConstant.INVALID_TRANSACTION_AMOUNT, HttpStatus.BAD_REQUEST);

        final UserEntity optionalUser = userRepository.findById(userID)
                .orElseThrow(
                        () ->
                                new PassbookException(PassbookApplicationConstant.USER_ID_NOT_FOUND, HttpStatus.BAD_REQUEST));

        UserEntity user = optionalUser;

        TransactionEntity transaction;
        switch (request.getTransactionType()) {
            case CREDIT:
                transaction = transactionRepository.save(TransactionEntity.builder()
                        .amount(request.getAmount())
                        .note(request.getNote())
                        .user(user)
                        .closingBalance(request.getAmount() + getUpdatedBalance(user))
                        .time(LocalDateTime.now())
                        .transactionType(request.getTransactionType())
                        .build());
                break;
            case DEBIT:
                if (getUpdatedBalance(user) < request.getAmount()) {
                    throw new PassbookException(PassbookApplicationConstant.INSUFFICIENT_BALANCE, HttpStatus.BAD_REQUEST);
                }

                transaction = transactionRepository.save(TransactionEntity.builder()
                        .amount(request.getAmount())
                        .user(user)
                        .note(request.getNote())
                        .closingBalance(getUpdatedBalance(user) - request.getAmount())
                        .time(LocalDateTime.now())
                        .transactionType(request.getTransactionType())
                        .build());
                break;
            default:
                throw new PassbookException(PassbookApplicationConstant.UNHANDLED_TRANSACTION_TYPE, HttpStatus.BAD_REQUEST);
        }

        return TransactionResponse.builder()
                .txnID(transaction.getId())
                .amount(transaction.getAmount())
                .note(transaction.getNote())
                .time(transaction.getTime())
                .closingBalance(transaction.getClosingBalance())
                .transactionType(transaction.getTransactionType())
                .message(String.format("Your A/C XXXXX has a %s by Rs %.2f on %s ", transaction.getTransactionType(), transaction.getAmount(), transaction.getTime()))
                .build();
    }

    /**
     * This method(getZeroOrLastBalance) is to get balance as zero if its user first transaction or the closing balance of the latest transaction.
     * first assign the balance 0.
     * @param user to get the balance of that specific user.
     * @return updated balance after transaction done.
     */
    private Float getUpdatedBalance(UserEntity user) {
        Float balance = 0f;
        Optional<List<TransactionEntity>> optionalTxns = transactionRepository.findByUser(user);
        if (optionalTxns.isPresent()) {
            List<TransactionEntity> txns = optionalTxns.get();
            if (txns.size() > 0) balance = txns.get(txns.size() - 1).getClosingBalance();
        }
        return balance;
    }

    /**
     * Delete a specific transaction.
     * @param transactionID To delete specific transaction.
     * @return whether the transaction is deleted successfully or not.
     */
    public String deleteTransaction(Integer transactionID) {
        final TransactionEntity Transaction = transactionRepository.findById(transactionID).orElseThrow(
                () ->
                        new PassbookException(
                                PassbookApplicationConstant.INVALID_TRANSACTION_ID, HttpStatus.BAD_REQUEST));
        transactionRepository.deleteById(transactionID);
        return "Your TransactionID " + transactionID + " delete successfully.";
    }
}