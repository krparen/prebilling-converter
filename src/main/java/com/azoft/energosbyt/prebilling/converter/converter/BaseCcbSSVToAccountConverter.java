package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbSSV;
import com.azoft.energosbyt.prebilling.converter.dto.input.StatementConstructDetail;
import com.azoft.energosbyt.prebilling.converter.dto.output.Account;
import com.azoft.energosbyt.prebilling.converter.service.ReferenceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BaseCcbSSVToAccountConverter implements Converter<BaseCcbSSV, Account> {

    @Autowired
    private ReferenceQueryService referenceQueryService;

    @Override
    public Account convert(BaseCcbSSV input, Map<String, Object> messageHeaders) {
        Account account = new Account();
        account.setInform_system(getInformSystem(input, messageHeaders));
        account.setExt_id(input.getStatementConstructId());
        account.setNumber(input.getAccountNumber());
        account.setIs_enabled(input.getEffectiveStatus());
        account.setBegin_date(getBeginDate(input));
        account.setEnd_date(getEndDate(input));
        account.setExt_id_company(input.getDivision());
        account.setExt_id_division(getExtIdDivision(input));
        account.setExt_id_district(getExtIdDistrict(input));
        account.setExt_id_person(input.getPersonId());

        return account;
    }

    private String getInformSystem(BaseCcbSSV input, Map<String, Object> messageHeaders) {

        String systemId = null;

        if (messageHeaders.get("system_id") != null) {
            systemId = (String) messageHeaders.get("system_id");
        }

        if (systemId == null) {
            systemId = input.getSystemId();
        }

        return referenceQueryService.getInformSystemCode(systemId);
    }


    private String getExtIdDivision(BaseCcbSSV input) {

        if (input.getStatementConstructDetail() == null || input.getStatementConstructDetail().isEmpty()) {
            return null;
        }

        // TODO: сделать нормальный выбор элемента из списка, когда появится логика выбора
        return input.getStatementConstructDetail().get(0).getOffice();
    }

    private String getExtIdDistrict(BaseCcbSSV input) {

        if (input.getStatementConstructDetail() == null || input.getStatementConstructDetail().isEmpty()) {
            return null;
        }

        // TODO: сделать нормальный выбор элемента из списка, когда появится логика выбора
        return input.getStatementConstructDetail().get(0).getDistrict();
    }

    private LocalDate getBeginDate(BaseCcbSSV input) {

        if (input.getStatementConstructDetail() == null || input.getStatementConstructDetail().isEmpty()) {
            return null;
        }

        return input.getStatementConstructDetail().stream()
                .map(StatementConstructDetail::getStartDate)
                .min(LocalDate::compareTo).get();
    }

    private LocalDate getEndDate(BaseCcbSSV input) {

        if (input.getStatementConstructDetail() == null || input.getStatementConstructDetail().isEmpty()) {
            return null;
        }

        List<StatementConstructDetail> constructDetailsWithNullEndDate = input.getStatementConstructDetail().stream()
                .filter(constructDetail -> constructDetail.getEndDate() == null)
                .collect(Collectors.toList());

        if (!constructDetailsWithNullEndDate.isEmpty()) {
            return null;
        }

        return input.getStatementConstructDetail().stream()
                .map(StatementConstructDetail::getEndDate)
                .max(LocalDate::compareTo).get();
    }
}
