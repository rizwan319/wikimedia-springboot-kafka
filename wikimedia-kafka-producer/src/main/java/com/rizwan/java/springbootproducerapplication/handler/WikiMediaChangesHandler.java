package com.rizwan.java.springbootproducerapplication.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

//trigger when new event in wikimedia
//when new event arrives(/message in wiki) this handler will be triggered
//within handler onMessage() method will be triggered to send msg to topic using kafka provided send method
public class WikiMediaChangesHandler implements EventHandler {

    private static final Logger log = LoggerFactory.getLogger(WikiMediaChangesHandler.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    public WikiMediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        log.info(String.format("Event data -> {%s}", messageEvent.getData()));
        kafkaTemplate.send("wiki_topic", messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
