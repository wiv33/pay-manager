package org.psawesome.payserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class PayServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(PayServerApplication.class, args);
  }

}
