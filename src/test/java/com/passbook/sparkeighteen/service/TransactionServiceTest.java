package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.Gender;
import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.POJO.TransactionType;
import com.passbook.sparkeighteen.peristence.entity.TransactionEntity;
import com.passbook.sparkeighteen.peristence.entity.UserEntity;
import com.passbook.sparkeighteen.peristence.repository.TransactionRepository;
import com.passbook.sparkeighteen.peristence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void InvalidPayload_userIdNotFound_transactUnsuccessfull() throws Exception {

        Integer userID = 1;

        TransactionRequest request = TransactionRequest.builder()
                .amount(250f)
                .note("Coldrinks")
                .name("Ketan")
                .transactionType(TransactionType.CREDIT)
                .build();

        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.empty());

        TransactionResponse response = transactionService.transact(userID, request);
        assertEquals("User not found. Please use a registered user", response.getMessage());

    }

    @Test
    public void transact_userIdExist_transactSuccessful() throws Exception {
        Integer userID = 1;

        TransactionRequest request = TransactionRequest.builder()
                .amount(250f)
                .note("Coldrinks")
                .name("Ketan")
                .transactionType(TransactionType.CREDIT)
                .build();

        UserEntity user = UserEntity.builder()
                .email("ketan@gmail.com")
                .dob(LocalDate.of(2000, 2, 25))
                .password("qwerty")
                .gender(Gender.MALE)
                .lastname("Shinde")
                .firstname("ketan")
                .build();

        TransactionEntity transaction = TransactionEntity.builder()
                .amount(request.getAmount())
                .id(1)
                .note(request.getNote())
                .user(user)
                .time(LocalDateTime.now())
                .transactionType(request.getTransactionType())
                .build();

        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.ofNullable(user));
        Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(transaction);

        TransactionResponse response = transactionService.transact(userID, request);
        assertEquals(String.format("Your A/C XXXXX has a %s by Rs %.2f on %s ", transaction.getTransactionType(), transaction.getAmount(), transaction.getTime()), response.getMessage());

    }

    @Test
    public void transact_amountLessThanOne_txnTypeCredit_transactUnsuccessful() throws Exception {
        Integer userID = 1;

        TransactionRequest request = TransactionRequest.builder()
                .amount(0f)
                .note("Coldrinks")
                .name("Ketan")
                .transactionType(TransactionType.CREDIT)
                .build();

        TransactionEntity txn = TransactionEntity.builder()
                .amount(request.getAmount())
                .build();

        TransactionResponse response = transactionService.transact(userID, request);
        assertEquals("Invalid Transaction amount", response.getMessage());
    }

    @Test
    public void transact_Credit_TransactionSuccessful() throws Exception {

        Integer userID = 1;

        TransactionRequest request = TransactionRequest.builder()
                .amount(250f)
                .note("Coldrinks")
                .name("Ketan")
                .transactionType(TransactionType.CREDIT)
                .build();

        UserEntity user = UserEntity.builder()
                .email("ketan@gmail.com")
                .dob(LocalDate.of(2000, 2, 25))
                .password("qwerty")
                .gender(Gender.MALE)
                .lastname("Shinde")
                .firstname("ketan")
                .build();

        TransactionEntity transaction = TransactionEntity.builder()
                .amount(request.getAmount())
                .id(1)
                .note(request.getNote())
                .user(user)
                .time(LocalDateTime.now())
                .transactionType(request.getTransactionType())
                .build();

        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.ofNullable(user));
        Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(transaction);

        TransactionResponse response = transactionService.transact(userID, request);
        assertEquals(String.format("Your A/C XXXXX has a %s by Rs %.2f on %s ", transaction.getTransactionType(), transaction.getAmount(), transaction.getTime()), response.getMessage());

    }

    @Test
    public void transact_Debit_TransactionUnSuccessful() throws Exception {

        Integer userID = 1;
        Float balance = 0f;

        TransactionRequest request = TransactionRequest.builder()
                .amount(25f)
                .note("Chocolate")
                .name("Ketan")
                .transactionType(TransactionType.DEBIT)
                .build();

        UserEntity user = UserEntity.builder()
                .email("ketan@gmail.com")
                .dob(LocalDate.of(2000, 2, 25))
                .password("qwerty")
                .gender(Gender.MALE)
                .lastname("Shinde")
                .firstname("ketan")
                .build();

        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.ofNullable(user));
        Mockito.when(transactionRepository.findByUser(user)).thenReturn(Optional.empty());

        TransactionResponse response = transactionService.transact(userID, request);
        assertEquals(String.format("Insufficient balance to withdraw. Current Balance: %.2f", balance), response.getMessage());
    }

    @Test
    public void transact_Debit_TransactionSuccessful() throws Exception {

        Integer userID = 1;

        TransactionRequest request = TransactionRequest.builder()
                .amount(25f)
                .note("Coldrinks")
                .name("Ketan")
                .transactionType(TransactionType.DEBIT)
                .build();

        UserEntity user = UserEntity.builder()
                .email("ketan@gmail.com")
                .dob(LocalDate.of(2000, 2, 25))
                .password("qwerty")
                .gender(Gender.MALE)
                .lastname("Shinde")
                .firstname("ketan")
                .build();

        TransactionEntity transaction = TransactionEntity.builder()
                .amount(request.getAmount())
                .id(1)
                .note(request.getNote())
                .user(user)
                .time(LocalDateTime.now())
                .closingBalance(200f)
                .transactionType(request.getTransactionType())
                .build();

        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.ofNullable(user));
        Mockito.when(transactionRepository.findByUser(user)).thenReturn(Optional.of(List.of(transaction)));
        Mockito.when(transactionRepository.save(ArgumentMatchers.any())).thenReturn(transaction);

        TransactionResponse response = transactionService.transact(userID, request);
        assertEquals(String.format("Your A/C XXXXX has a %s by Rs %.2f on %s ", transaction.getTransactionType(), transaction.getAmount(), transaction.getTime()), response.getMessage());

    }

}