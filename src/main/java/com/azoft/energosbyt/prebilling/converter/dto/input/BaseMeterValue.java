package com.azoft.energosbyt.prebilling.converter.dto.input;

import java.util.Date;
import lombok.Data;

@Data
public class BaseMeterValue {
  String accountNumber;
  String branch;
  String meterNumber;
  String meterId;
  String t1 ;
  String t2;
  String t3;
  String app_id;
  String channel;
  Date mvDate;
}
