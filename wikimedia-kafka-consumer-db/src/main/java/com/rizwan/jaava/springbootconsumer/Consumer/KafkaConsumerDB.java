package com.rizwan.jaava.springbootconsumer.Consumer;

import com.rizwan.jaava.springbootconsumer.entity.WikiMediaData;
import com.rizwan.jaava.springbootconsumer.repository.WikiMediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerDB {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerDB.class);

    public KafkaConsumerDB(WikiMediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    private WikiMediaDataRepository dataRepository;

    @KafkaListener(topics = "wiki_topic", groupId = "myWikiGroup")
    private void consume(String eventMsg){
        log.info(String.format("Event Message received from {myWikiGroup} Topic -> {%s}", eventMsg));
        WikiMediaData wikiMediaData = new WikiMediaData();
        wikiMediaData.setWikiEventData(eventMsg);
        dataRepository.save(wikiMediaData);
    }
}
