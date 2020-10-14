package com.azoft.energosbyt.prebilling.converter.dto.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Account {
  private String inform_system;
  private String ext_id;
  private String number;
  private String is_enabled;
  private LocalDate begin_date;
  private LocalDate end_date;
  private String ext_id_company;
  private String ext_id_division;
  private String ext_id_district;
  private String ext_id_person;
}
