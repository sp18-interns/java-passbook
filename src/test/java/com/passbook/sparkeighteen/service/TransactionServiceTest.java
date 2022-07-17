package com.passbook.sparkeighteen.service;

import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.POJO.TransactionType;
import com.passbook.sparkeighteen.peristence.entity.TransactionEntity;
import com.passbook.sparkeighteen.peristence.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

/**
 * The Transaction service test.
 */
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    /**
     * if the transaction id exist then update the transaction of the particular transaction.
     */
    @Test
    public void updateTransaction_transactionIdExists_transactionUpdateSuccessful() throws Exception {
        TransactionRequest request = TransactionRequest.builder()
                .amount(200f)
                .name("ketan Shinde")
                .note("200 paid")
                .transactionType(TransactionType.DEBIT)
                .build();

        TransactionEntity ketansTransaction = TransactionEntity.builder()
                .id(1)
                .note("200 paid 100 remaining")
                .build();


        Mockito.when(transactionRepository.findById(1)).thenReturn(Optional.ofNullable(ketansTransaction));

        TransactionResponse transactionResponse = transactionService.updateTransaction(1, request);
        assertEquals("Transaction updated", transactionResponse.getMessage());
    }

    /**
     * if the transaction id does not exist then the transaction update is unsuccessful.
     */
    @Test
    public void updateTransaction_transactionIdNotExists_transactionUpdateUnSuccessful() throws Exception {
        TransactionRequest request = TransactionRequest.builder()
                .amount(200f)
                .name("ketan Shinde")
                .note("200 paid")
                .transactionType(TransactionType.DEBIT)
                .build();

        Mockito.when(transactionRepository.findById(any())).thenReturn(Optional.empty());

        TransactionResponse transactionResponse = transactionService.updateTransaction(1, request);
        assertEquals("No records have been found.", transactionResponse.getMessage());
    }
}
