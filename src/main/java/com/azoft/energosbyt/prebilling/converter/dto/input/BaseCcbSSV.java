package com.azoft.energosbyt.prebilling.converter.dto.input;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class BaseCcbSSV {
  String system_id;
  String syncRequestId;
  String statementConstructId;
  String personId;
  String effectiveStatus;
  String statementAddressSource;
  String mailingPremise;
  String statementRouteType;
  String numberOfCopies;
  String accountNumber;
  String premiseId;
  String division;
  List<StatementConstructDetail> statementConstructDetail = new ArrayList<>();
}

