package com.azoft.energosbyt.prebilling.converter.processor;

import com.azoft.energosbyt.prebilling.converter.converter.BaseCcbSSVToImportAccountConverter;
import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbSSV;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportAccount;
import com.azoft.energosbyt.prebilling.converter.service.RabbitService;
import org.springframework.amqp.core.Message;
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
    ImportAccount output = baseCcbSSVToImportAccountConverter.convert(input);
    rabbitService.send(outputQueueName, inputMessage.getMessageProperties(), output);
  }

  @Override
  public boolean appliesTo(String messageType) {
    return messageType.equals("setAccount");
  }
}
