package com.azoft.energosbyt.prebilling.converter.dto.input;

import lombok.Data;

@Data
public class BaseMeterSP {
  String systemId;
  String id;
  String personalAccountId;
  String objectId;
  String accountType;
  String status;
}
