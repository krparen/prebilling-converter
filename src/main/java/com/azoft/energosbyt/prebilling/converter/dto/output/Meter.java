package com.azoft.energosbyt.prebilling.converter.dto.output;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Meter {
  private String inform_system;
  private String ext_id;
  private String name;
  private String number;
  private String is_enabled;
  private LocalDateTime begin_date;
  private LocalDateTime end_date;
  private String tariff_zone;
  private String remove_date;
  private List<Register> register;
}
