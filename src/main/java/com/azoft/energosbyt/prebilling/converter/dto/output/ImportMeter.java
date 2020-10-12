package com.azoft.energosbyt.prebilling.converter.dto.output;

import java.util.List;
import lombok.Data;

@Data
public class ImportMeter {
  private List<Meter> meter;
}
