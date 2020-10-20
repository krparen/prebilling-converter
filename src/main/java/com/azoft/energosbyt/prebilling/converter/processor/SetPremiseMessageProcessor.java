package com.azoft.energosbyt.prebilling.converter.processor;

import com.azoft.energosbyt.prebilling.converter.converter.BaseCcbPremiseToImportAddressConverter;
import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbPremise;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportAddress;
import com.azoft.energosbyt.prebilling.converter.service.RabbitService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SetPremiseMessageProcessor extends InputMessageProcessor {

  @Autowired
  private BaseCcbPremiseToImportAddressConverter baseCcbPremiseToImportAddressConverter;
  @Autowired
  private RabbitService rabbitService;

  @Override
  public void process(Message inputMessage) {
    BaseCcbPremise input = rabbitService.deserializeBodyAsType(inputMessage, BaseCcbPremise.class);

    MessageProperties outputProperties = new MessageProperties();
    for (Map.Entry<String, Object> entry : inputMessage.getMessageProperties().getHeaders().entrySet()) {
      outputProperties.setHeader(entry.getKey(), entry.getValue());
    }

    ImportAddress output = baseCcbPremiseToImportAddressConverter.convert(input);
    rabbitService.send(outputQueueName, outputProperties, output);
  }

  @Override
  public boolean appliesTo(String messageType) {
    return "setPremise".equals(messageType);
  }
}
