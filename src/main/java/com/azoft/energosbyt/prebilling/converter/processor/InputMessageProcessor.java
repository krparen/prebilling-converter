package com.azoft.energosbyt.prebilling.converter.processor;

import com.azoft.energosbyt.prebilling.converter.converter.Converter;
import com.azoft.energosbyt.prebilling.converter.service.RabbitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Slf4j
public abstract class InputMessageProcessor<I, O> {

  @Value("${energosbyt.rabbit.output-queue}")
  protected String outputQueueName;

  protected final Converter<I, O> converter;
  protected final ObjectMapper mapper;
  private final RabbitService rabbitService;


  protected InputMessageProcessor(Converter<I, O> converter, ObjectMapper mapper, RabbitService rabbitService) {
    this.converter = converter;
    this.mapper = mapper;
    this.rabbitService = rabbitService;
  }

  protected abstract Class<I> getInputClass();

  public void process(Message inputMessage) {

    I input = rabbitService.deserializeBodyAsType(inputMessage, getInputClass());

    MessageProperties outputProperties = new MessageProperties();
    for (Map.Entry<String, Object> entry : inputMessage.getMessageProperties().getHeaders().entrySet()) {
      outputProperties.setHeader(entry.getKey(), entry.getValue());
    }

    O output = converter.convert(input);
    rabbitService.send(outputQueueName, outputProperties, output);
  }

  public abstract boolean appliesTo (String messageType);
}
