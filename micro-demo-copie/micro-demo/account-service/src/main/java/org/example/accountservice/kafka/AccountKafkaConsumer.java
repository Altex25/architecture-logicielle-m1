package org.example.accountservice.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.accountservice.service.AccountService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountKafkaConsumer {
    private final ObjectMapper objectMapper;
    private final AccountService accountService;

    public AccountKafkaConsumer(ObjectMapper objectMapper, AccountService accountService) {
        this.objectMapper = objectMapper;
        this.accountService = accountService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.card}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleCardEvent(String message) {
        handleEvent(message, true);
    }

    @KafkaListener(topics = "${spring.kafka.topic.loan}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleLoanEvent(String message) {
        handleEvent(message, false);
    }

    private void handleEvent(String message, boolean isCardEvent) {
        try {
            JsonNode node = objectMapper.readTree(message);
            String event = node.path("event").asText(null);
            JsonNode accountIdNode = node.path("accountId");
            if (event == null || accountIdNode.isMissingNode()) {
                log.warn("Ignoring invalid event: {}", message);
                return;
            }
            long accountId = accountIdNode.asLong();
            if (isCardEvent) {
                if ("CARD_CREATED".equals(event)) {
                    accountService.updateCardCount(accountId, 1);
                } else if ("CARD_DELETED".equals(event)) {
                    accountService.updateCardCount(accountId, -1);
                } else {
                    log.warn("Unknown card event: {}", message);
                }
            } else {
                if ("LOAN_CREATED".equals(event)) {
                    accountService.updateLoanCount(accountId, 1);
                } else if ("LOAN_DELETED".equals(event)) {
                    accountService.updateLoanCount(accountId, -1);
                } else {
                    log.warn("Unknown loan event: {}", message);
                }
            }
        } catch (Exception e) {
            log.warn("Failed to process event: {}", message, e);
        }
    }
}
