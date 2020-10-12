package com.azoft.energosbyt.prebilling.converter.dto.output;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ImportMeasure {
  private List<Measure> measure = new ArrayList<>();
}
