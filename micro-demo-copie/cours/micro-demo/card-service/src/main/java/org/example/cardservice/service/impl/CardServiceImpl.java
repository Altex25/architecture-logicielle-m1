package org.example.cardservice.service.impl;

import org.example.cardservice.entity.Card;
import org.example.cardservice.repository.CardRepository;
import org.example.cardservice.service.AccountService;
import org.example.cardservice.service.CardService;
import org.example.cardservice.usable.AccountUsable;
import org.example.cardservice.kafka.CardKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CardKafkaProducer cardKafkaProducer;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public List<Card> getCardsByAccountId(Long accountId) {
        if (isAccountExisting(accountId)) {
            return cardRepository.findByAccountId(accountId);
        }
        return null;
    }

    public Card saveCard(Card card) {
        if (isAccountExisting(card.getAccountId())) {
            Card saved = cardRepository.save(card);
            cardKafkaProducer.sendCardCreated(saved.getAccountId(), saved.getId());
            return saved;
        }
        return null;
    }

    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
        cardRepository.deleteById(id);
        cardKafkaProducer.sendCardDeleted(card.getAccountId(), card.getId());
    }

    private boolean isAccountExisting(Long accountId) {
        AccountUsable accountUsable = null;
        accountUsable = accountService.getAccountById(accountId);
        return accountUsable != null;
    }
}
