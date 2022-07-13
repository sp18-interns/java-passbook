package com.passbook.sparkeighteen.controller;

import com.passbook.sparkeighteen.peristence.POJO.TransactionRequest;
import com.passbook.sparkeighteen.peristence.POJO.TransactionResponse;
import com.passbook.sparkeighteen.peristence.POJO.TransactionType;
import com.passbook.sparkeighteen.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
public class TransactionControllerTest {
    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    public void validRequest_successfulResponse_CreditTransactionSuccessfull() throws Exception {

        Integer txnID = 1;

        TransactionRequest transactionRequest = TransactionRequest.builder()
                .amount(100F)
                .name("ketan")
                .note("Mobile Recharge")
                .transactionType(TransactionType.CREDIT)
                .build();

        Mockito.when(transactionService.transact(txnID,transactionRequest)).thenReturn(TransactionResponse.builder()
                .message("Credited")
                .amount(100f)
                .note("tea")
                .transactionType(TransactionType.CREDIT)
                .closingBalance(100f)
                .build());

        ResponseEntity<TransactionResponse> response = transactionController.transact(txnID,transactionRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void InvalidRequest_errorResponse_CreditTransactionUnSuccessfull() throws Exception {

        Integer txnID = 1;

        TransactionRequest transactionRequest = TransactionRequest.builder()
                .amount(-100f)
                .transactionType(TransactionType.CREDIT)
                .build();

        Mockito.when(transactionService.transact(txnID,transactionRequest)).thenReturn(TransactionResponse.builder()
                .message("amount cannot be less than zero")
                .amount(null)
                .note(null)
                .transactionType(TransactionType.CREDIT)
                .closingBalance(null)
                .build());

        ResponseEntity<TransactionResponse> response = transactionController.transact(txnID,transactionRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void validRequest_successfulResponse_DebitTransactionSuccessfull() throws Exception {

        Integer txnID = 1;

        TransactionRequest transactionRequest = TransactionRequest.builder()
                .amount(100F)
                .name("Ashish")
                .note("Wifi Recharge")
                .transactionType(TransactionType.DEBIT)
                .build();

        Mockito.when(transactionService.transact(txnID,transactionRequest)).thenReturn(TransactionResponse.builder()
                .message("Debited")
                .amount(100f)
                .note("coffee")
                .transactionType(TransactionType.DEBIT)
                .closingBalance(0f)
                .build());

        ResponseEntity<TransactionResponse> response = transactionController.transact(txnID,transactionRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void InvalidRequest_errorResponse_DebitTransactionUnSuccessfull() throws Exception {

        Integer txnID = 1;

        TransactionRequest transactionRequest = TransactionRequest.builder()
                .amount(200f)
                .transactionType(TransactionType.DEBIT)
                .build();

        Mockito.when(transactionService.transact(txnID,transactionRequest)).thenReturn(TransactionResponse.builder()
                .message("Insufficient Balance")
                .amount(200f)
                .note(null)
                .transactionType(TransactionType.DEBIT)
                .closingBalance(10f)
                .build());

        ResponseEntity<TransactionResponse> response = transactionController.transact(txnID,transactionRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

}