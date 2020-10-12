package com.azoft.energosbyt.prebilling.converter.dto.output;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ImportAccount {

  private List<String> account = new ArrayList<>();

}
