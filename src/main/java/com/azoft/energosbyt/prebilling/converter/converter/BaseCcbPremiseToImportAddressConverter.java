package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbPremise;
import com.azoft.energosbyt.prebilling.converter.dto.output.Address;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportAddress;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BaseCcbPremiseToImportAddressConverter implements Converter<BaseCcbPremise, ImportAddress> {

  @Override
  public ImportAddress convert(BaseCcbPremise input) {

    Address address = getAddress(input);
    List<Address> addresses = new ArrayList<>();
    addresses.add(address);

    ImportAddress result = new ImportAddress();
    result.setAddress(addresses);

    return result;
  }

  private Address getAddress(BaseCcbPremise input) {
    Address address = new Address();
    address.setExt_id(input.getPremiseId());
    address.setIndex(input.getPostal());
    address.setRegion(input.getState());
    address.setArea(input.getCounty());
    address.setTown_type(input.getTaxVendorGeographicalCode());
    address.setTown(input.getCity());
    address.setSettlement(input.getHumanSettlement());
    address.setStreet_type(input.getHouseTypeDesc());
    address.setStreet(input.getStreet());
    address.setHouse(input.getHouse());
    address.setBlock(input.getHousing());
    address.setFlat(input.getApartment());
    address.setAddress_full("ПУСТЫШКА ПОЛНЫЙ АДРЕС"); // TODO: выяснить как заполнять и заполнить
    address.setHouse_fias(input.getCharFias());
    return address;
  }
}
