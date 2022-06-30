package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.POJO.TransactionType;
import com.passbook.sparkeighteen.peristence.entity.TransactionEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.TransactionRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public TransactionResponse transact(Integer userID, TransactionRequest request, TransactionType txnType) throws Exception {

        Optional<UserEntity> optionalUser = userRepository.findById(userID);
        if (optionalUser.isEmpty())
            return TransactionResponse.builder()
                    .message("User not found. Please use a registered user")
                    .build();

        UserEntity user = optionalUser.get();

        Optional<TransactionEntity> optionalTxn = deposit(user, request);

        if (optionalTxn.isEmpty())
            return TransactionResponse.builder()
                    .message("Deposit amount cannot be 0 or less.")
                    .build();

        TransactionEntity transaction = optionalTxn.get();

        return TransactionResponse.builder()
                .amount(transaction.getAmount())
                .id(transaction.getId())
                .note(transaction.getNote())
                .time(transaction.getTime())
                .closingBalance(transaction.getClosingBalance())
                .transactionType(transaction.getTransactionType())
                .message(String.format("Your A/C XXXXX has a %s by Rs %.2f on %s ", transaction.getTransactionType(), transaction.getAmount(), transaction.getTime()))
                .build();
    }

    private Optional<TransactionEntity> deposit(UserEntity user, TransactionRequest request) {
        if (request.getAmount() <= 0) {
            return Optional.empty();
        }

        TransactionEntity transaction = transactionRepository.save(TransactionEntity.builder().amount(request.getAmount()).note(request.getNote()).user(user).closingBalance(request.getAmount() + getZeroOrLastBalance(user)).time(LocalDateTime.now()).transactionType(TransactionType.CREDIT).build());

        return Optional.of(transaction);
    }

    private Float getZeroOrLastBalance(UserEntity user) {
        Float balance = 0f;

        Optional<List<TransactionEntity>> optionalTxns = transactionRepository.findByUser(user);
        if (optionalTxns.isPresent()) {
            List<TransactionEntity> txns = optionalTxns.get();
            if (txns.size() > 0) balance = txns.get(txns.size() - 1).getClosingBalance();
        }

        return balance;
    }
}
