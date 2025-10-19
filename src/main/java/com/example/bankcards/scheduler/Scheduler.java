package com.example.bankcards.scheduler;

import com.example.bankcards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@EnableScheduling
@EnableAsync
public class Scheduler {

    @Autowired
    private CardRepository cardRepository;

    @Scheduled(fixedDelay = 1000 * 60)
    @Async
    @Transactional
    public void setExpireStatusByScheduler() {
        cardRepository.setExpireStatusByScheduler();
    }
}
