package com.example.messagingproducer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MessagingProducerApplication.class)
@DirtiesContext
public class MessagingProducerApplicationTests {

	@Autowired
	private Source source;

	@Autowired
	private MessageCollector messageCollector;

	@Autowired
	private MessagingProducerApplication application;

	@Test
	public void whenSendMessage_thenReceiveMessage() {
		application.logMessage();
		Object payload = messageCollector.forChannel(source.output()).poll().getPayload();
		assertThat(payload.toString()).isEqualTo("Dummy String");
	}
}
