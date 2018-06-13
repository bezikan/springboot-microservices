package com.oceanboa.stock.db2service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.oceanboa.stock.db2service.model.Quote;
import com.oceanboa.stock.db2service.repository.QuotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private QuotesRepository quotesRepository;


    //    @Scheduled(fixedRate = 5000)
    @Scheduled(cron="0 0 9-17 * * *")
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));

        List<Quote> quotes = quotesRepository.findByUserName("Bob");
        quotesRepository.deleteAll(quotes);

        quotesRepository.save(new Quote("Bob", "GOOG"));
        quotesRepository.save(new Quote("Bob", "MSFT"));
        quotesRepository.save(new Quote("Bob", "TSLA"));

        List<Quote> quotesSal = quotesRepository.findByUserName("Sally");
        quotesRepository.deleteAll(quotesSal);

        quotesRepository.save(new Quote("Sally", "GE"));
        quotesRepository.save(new Quote("Sally", "GS"));
        quotesRepository.save(new Quote("Sally", "HD"));

    }
}
