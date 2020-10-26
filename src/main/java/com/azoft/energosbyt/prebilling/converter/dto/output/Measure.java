package com.azoft.energosbyt.prebilling.converter.dto.output;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Measure {
  private String inform_system;
  private String account_number;
  private String contract_number;
  private String inn;
  private String ext_id_service;
  private String service_name;
  private String ext_id_meter;
  private String meter_number;
  private String ext_id_point_record;
  private String place;
  private String address_full;
  private Date measure_date;
  private String measure_type;
  private String measure_form;
  private List<RegisterValue> register_value = new ArrayList<>();
  private String id_source;
  private String id_channel;
  private String id_variant_getting;
  private String info;
}
