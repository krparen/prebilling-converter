package com.azoft.energosbyt.prebilling.converter.dto.input;

import lombok.Data;

@Data
public class StatementConstructDetail {
  String constructDetailId;
  String accountId;
  String startDate;
  String endDate;
  String office;
  String district;
}
