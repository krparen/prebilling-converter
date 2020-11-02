package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbPremise;
import com.azoft.energosbyt.prebilling.converter.dto.output.Address;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseCcbPremiseToImportAddressConverter implements Converter<BaseCcbPremise, ImportAddress> {

  @Autowired
  private BaseCcbPremiseToAddressConverter baseCcbPremiseToAddressConverter;

  @Override
  public ImportAddress convert(BaseCcbPremise input) {

    Address address = baseCcbPremiseToAddressConverter.convert(input);
    List<Address> addresses = new ArrayList<>();
    addresses.add(address);

    ImportAddress result = new ImportAddress();
    result.setAddresses(addresses);

    return result;
  }

}
