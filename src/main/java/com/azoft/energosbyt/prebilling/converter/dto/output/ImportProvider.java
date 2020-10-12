package com.azoft.energosbyt.prebilling.converter.dto.output;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ImportProvider {

  private List<Provider> provider = new ArrayList<>();

}
