package com.azoft.energosbyt.prebilling.converter.dto.input;

import java.time.LocalDate;
import lombok.Data;

@Data
public class StatementConstructDetail {
  private String constructDetailId;
  private String accountId;
  private LocalDate startDate;
  private LocalDate endDate;
  private String office;
  private String district;
}
