package com.azoft.energosbyt.prebilling.converter.dto.output;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ImportServiceProvider {

  public List<ServiceProvider> service_provider = new ArrayList<>();

}
