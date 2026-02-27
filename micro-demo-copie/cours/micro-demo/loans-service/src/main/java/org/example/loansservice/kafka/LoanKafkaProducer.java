package org.example.loansservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoanKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public LoanKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.kafka.topic.loan}")
    private String topic;

    public void sendLoanCreated(Long accountId, Long loanId) {
        String event = String.format(
                "{\"event\":\"LOAN_CREATED\",\"accountId\":%d,\"loanId\":%d}",
                accountId,
                loanId
        );
        log.info("Producing loan created event: {}", event);
        kafkaTemplate.send(topic, event);
    }

    public void sendLoanDeleted(Long accountId, Long loanId) {
        String event = String.format(
                "{\"event\":\"LOAN_DELETED\",\"accountId\":%d,\"loanId\":%d}",
                accountId,
                loanId
        );
        log.info("Producing loan deleted event: {}", event);
        kafkaTemplate.send(topic, event);
    }
}
