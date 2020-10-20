package com.azoft.energosbyt.prebilling.converter.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class InputMessageProcessor {

  protected String outputQueueName = "g_eremeev_out";

  @Autowired
  protected ObjectMapper mapper;

  public abstract void process (Message message);

  public abstract boolean appliesTo (String messageType);
}
