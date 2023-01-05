package com.passbook.sparkeighteen.service.producer;

import com.passbook.sparkeighteen.peristence.POJO.KafkaTransactionRequest;
import com.passbook.sparkeighteen.utils.Constants;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public TransactionProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(KafkaTransactionRequest txnRequest) {
        System.out.println("Producer: txnRequest: " + txnRequest);
        this.kafkaTemplate.send(Constants.TRANSACTION_REQUESTS_TOPIC, txnRequest.toString());
    }
}
