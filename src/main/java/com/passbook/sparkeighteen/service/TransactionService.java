package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.TransactionRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Transaction service.
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    /**
     * Instantiates a new Transaction service.
     *
     * @param transactionRepository to perform operation related to transaction repository like finding user from transaction repository
     * @param userRepository to perform operation related to user repository like finding user id from user repository
     */
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Transact transaction response.
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
     * This method(getZeroOrLastBalance) is basically used for showing user closingBalance.
     * first assign the balance 0.
     * @param user to get the balance of that specific user.
     * @return updated balance after transaction done.
     */
    private Float getZeroOrLastBalance(UserEntity user) {
        Float balance = 0f;
        // TODO: Add getting the latest closing balance of the user
        return balance;
    }
}