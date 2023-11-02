package com.pubsub.demo;

import com.pubsub.demo.consumer.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class PubsubDemoApplication implements ApplicationRunner {
	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(PubsubDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ConsumerService consumerService = applicationContext.getBean(ConsumerService.class);

		consumerService.subscribeAsyncExample();
	}

}
