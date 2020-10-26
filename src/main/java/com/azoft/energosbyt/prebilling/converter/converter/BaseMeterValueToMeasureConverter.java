package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseMeterValue;
import com.azoft.energosbyt.prebilling.converter.dto.output.Measure;
import com.azoft.energosbyt.prebilling.converter.dto.output.RegisterValue;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class BaseMeterValueToMeasureConverter implements Converter<BaseMeterValue, Measure> {
  @Override
  public Measure convert(BaseMeterValue input) {
    Measure output = new Measure();
    output.setInform_system(input.getApp_id());
    output.setAccount_number(input.getAccountNumber());
    output.setExt_id_meter(input.getMeterId());
    output.setMeter_number(input.getMeterNumber());
    output.setMeasure_date(input.getMvDate());
    fillRegisters(input, output);
    output.setId_source(input.getApp_id());
    output.setId_channel(input.getChannel());
    return output;
  }

  private void fillRegisters(BaseMeterValue input, Measure output) {
    List<RegisterValue> registerValues = new ArrayList<>();


    String value = getValue(input);


    RegisterValue registerValue = new RegisterValue();
    registerValue.setValue(value);
    registerValue.setExt_id_register(UUID.randomUUID().toString());

    registerValues.add(registerValue);
    output.setRegister_value(registerValues);
  }

  private String getValue(BaseMeterValue input) {
    String value = "";
    if (Strings.isNotBlank(input.getT1())) {
      value += input.getT1();
    }

    if (Strings.isNotBlank(input.getT2())) {
      value += "," + input.getT2();
    }

    if (Strings.isNotBlank(input.getT3())) {
      value += "," + input.getT3();
    }
    return value;
  }
}
