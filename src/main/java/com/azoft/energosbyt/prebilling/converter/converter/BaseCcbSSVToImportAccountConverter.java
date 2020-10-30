package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbSSV;
import com.azoft.energosbyt.prebilling.converter.dto.output.Account;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BaseCcbSSVToImportAccountConverter implements Converter<BaseCcbSSV, ImportAccount> {

  @Autowired
  private BaseCcbSSVToAccountConverter baseCcbSSVToAccountConverter;

  @Override
  public ImportAccount convert(BaseCcbSSV input) {

    Account account = baseCcbSSVToAccountConverter.convert(input);
    List<Account> accounts = new ArrayList<>();
    accounts.add(account);

    ImportAccount result = new ImportAccount();
    result.setAccount(accounts);

    return result;
  }

}
