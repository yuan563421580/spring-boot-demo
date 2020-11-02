package com.yuansb.demo.mongodb.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Author: cupid
 * Created by Cupid520 on 2019/1/16.
 * @Description:
 */
//@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("139.129.100.87", 27017);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "demo_db");
    }

}
