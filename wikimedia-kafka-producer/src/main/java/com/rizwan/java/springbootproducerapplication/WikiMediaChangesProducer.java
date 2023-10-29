package com.rizwan.java.springbootproducerapplication;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.rizwan.java.springbootproducerapplication.handler.WikiMediaChangesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikiMediaChangesProducer {

    private static final Logger log = LoggerFactory.getLogger(WikiMediaChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    //when spring finds a single constructor for spring bean then spring will inject
    //the dependency we dont have to add @Autowired anno
    public WikiMediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToTopic() throws InterruptedException {

        String topic = "wiki_topic";
        //in order to read realtime wikimedia stream data we will use event source
        EventHandler eventHandler = new WikiMediaChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        //Now we pass it to event source
        //create event source which will connect to wikimedia source and read all event data
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();
        //EventSource use executor-service to create threads
        //We need to start event source in seperate thread
        TimeUnit.MINUTES.sleep(10);
    }
}
