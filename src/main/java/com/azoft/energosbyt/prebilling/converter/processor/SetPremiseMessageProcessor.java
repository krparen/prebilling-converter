package com.azoft.energosbyt.prebilling.converter.processor;

import com.azoft.energosbyt.prebilling.converter.converter.Converter;
import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbPremise;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportAddress;
import com.azoft.energosbyt.prebilling.converter.service.RabbitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SetPremiseMessageProcessor extends InputMessageProcessor<BaseCcbPremise, ImportAddress> {

  public SetPremiseMessageProcessor(Converter<BaseCcbPremise, ImportAddress> converter,
                                    ObjectMapper mapper,
                                    RabbitService rabbitService) {
    super(converter, mapper, rabbitService);
  }

  @Override
  protected Class<BaseCcbPremise> getInputClass() {
    return BaseCcbPremise.class;
  }

  @Override
  public boolean appliesTo(String messageType) {
    return "setPremise".equals(messageType);
  }
}
