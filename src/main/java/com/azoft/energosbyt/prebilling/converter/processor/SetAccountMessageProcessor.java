package com.azoft.energosbyt.prebilling.converter.processor;

import com.azoft.energosbyt.prebilling.converter.converter.BaseCcbSSVToImportAccountConverter;
import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbSSV;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportAccount;
import com.azoft.energosbyt.prebilling.converter.service.RabbitService;
import java.util.Map;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetAccountMessageProcessor extends InputMessageProcessor {

  @Autowired
  private BaseCcbSSVToImportAccountConverter baseCcbSSVToImportAccountConverter;
  @Autowired
  private RabbitService rabbitService;

  @Override
  public void process(Message inputMessage) {
    BaseCcbSSV input = rabbitService.deserializeBodyAsType(inputMessage, BaseCcbSSV.class);

    MessageProperties outputProperties = new MessageProperties();
    for (Map.Entry<String, Object> entry : inputMessage.getMessageProperties().getHeaders().entrySet()) {
      outputProperties.setHeader(entry.getKey(), entry.getValue());
    }

    ImportAccount output = baseCcbSSVToImportAccountConverter.convert(input);
    rabbitService.send(outputQueueName, outputProperties, output);
  }

  @Override
  public boolean appliesTo(String messageType) {
    return messageType.equals("setAccount");
  }
}
