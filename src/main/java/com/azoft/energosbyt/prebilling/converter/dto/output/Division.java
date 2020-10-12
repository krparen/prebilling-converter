package com.azoft.energosbyt.prebilling.converter.dto.output;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Division {
  private String inform_system;
  private String ext_id;
  private String name;
  private String ext_id_company;
  private List<District> district = new ArrayList<>();
}
