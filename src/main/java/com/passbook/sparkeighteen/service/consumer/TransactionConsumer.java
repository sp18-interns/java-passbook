package com.passbook.sparkeighteen.service.consumer;

import com.passbook.sparkeighteen.service.TransactionService;
import com.passbook.sparkeighteen.utils.Constants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TransactionConsumer {

    private final TransactionService transactionService;

    public TransactionConsumer(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = Constants.TRANSACTION_REQUESTS_TOPIC, groupId = Constants.GROUP_ID)
    public void consume(String txnRequest) throws IOException {
        System.out.println("Consumer txnRequest: " + txnRequest);
    }
}