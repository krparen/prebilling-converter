package com.azoft.energosbyt.prebilling.converter.dto.output;

import lombok.Data;

@Data
public class Register {
  private String ext_id_register;
  private String position;
  private String digit_capacity;
  private String unit;
  private String is_empty;
}
