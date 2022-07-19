package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.entity.TransactionEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.TransactionRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
    public TransactionResponse transact(Integer userID, TransactionRequest request) throws Exception {

        if (request.getAmount() <= 0)
            return TransactionResponse.builder()
                    .message("Invalid Transaction amount")
                    .build();

        Optional<UserEntity> optionalUser = userRepository.findById(userID);
        if (optionalUser.isEmpty())
            return TransactionResponse.builder()
                    .message("User not found. Please use a registered user")
                    .build();

        UserEntity user = optionalUser.get();

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
                    return TransactionResponse.builder()
                            .message(String.format("Insufficient balance to withdraw. Current Balance: %.2f", getUpdatedBalance(user)))
                            .build();
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
                return TransactionResponse.builder()
                        .message(String.format("Unhandled transaction type %s", request.getTransactionType()))
                        .build();
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
        Optional<TransactionEntity> Transaction = transactionRepository.findById(transactionID);
        if (Transaction.isPresent()) {
            transactionRepository.deleteById(transactionID);
            return "Your TransactionID " + transactionID + " delete successfully.";
        }
        return "Entered Transaction ID does not Exists";
    }

    /**
     * This updateTransaction method is used to update the existing transaction in the repository.
     * @param transactionID is to specify which transaction the user wants to update.
     * @param request is for transaction details to update their transaction.
     * @return the transaction response whether transaction is updated or is empty.
     */
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