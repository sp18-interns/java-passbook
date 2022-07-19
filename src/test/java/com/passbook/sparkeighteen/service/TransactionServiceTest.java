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
import static org.mockito.Mockito.when;

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
     * If transaction id is right and exist then delete transaction is successful.
     */
    @Test
    public void deleteTransaction_transactionIdExist_deleteTransactionSuccessful() throws Exception {
        Integer transactionID = 1;

        TransactionEntity user = TransactionEntity.builder()
                .amount(100f)
                .note("Coffee")
                .transactionType(TransactionType.CREDIT)
                .closingBalance(100f)
                .build();

        when(transactionRepository.findById(transactionID)).thenReturn(Optional.ofNullable(user));

        String response = transactionService.deleteTransaction(transactionID);
        assertEquals("Your TransactionID " + transactionID + " delete successfully.", response);

    }

    /**
     * If transaction id does not exist then delete transaction is unsuccessful.
     * @throws Exception of entered transaction id does not exist.
     */
    @Test
    public void deleteTransaction_transactionIdNotExist_deleteTransactionUnSuccessful() throws Exception {
        Integer transactionID = 1;

        when(transactionRepository.findById(transactionID)).thenReturn(Optional.empty());

        String response = transactionService.deleteTransaction(transactionID);
        assertEquals("Entered Transaction ID does not Exists", response);

    }

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
