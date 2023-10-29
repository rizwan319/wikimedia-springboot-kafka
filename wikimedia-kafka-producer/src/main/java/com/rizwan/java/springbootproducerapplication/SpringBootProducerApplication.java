package com.rizwan.java.springbootproducerapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProducerApplication implements CommandLineRunner {

    public static void main( String[] args){
        SpringApplication.run(SpringBootProducerApplication.class);
    }
    @Autowired
    private WikiMediaChangesProducer wikiMediaChangesProducer;

    //whenever we run springbootapp using main entrypt class then this obj will be
    //instantiated and its mes=thod sendMessageTo Topic will be called.
    @Override
    public void run(String... args) throws Exception {
        wikiMediaChangesProducer.sendMessageToTopic();
    }
}
