package org.psawesome.payclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class PayClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(PayClientApplication.class, args);
  }

}

