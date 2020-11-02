package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseMeterValue;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportMeasure;
import com.azoft.energosbyt.prebilling.converter.dto.output.Measure;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseMeterValueToImportMeasureConverter implements Converter<BaseMeterValue, ImportMeasure> {

  @Autowired
  private BaseMeterValueToMeasureConverter baseMeterValueToMeasureConverter;

  @Override
  public ImportMeasure convert(BaseMeterValue input) {
    Measure measure = baseMeterValueToMeasureConverter.convert(input);
    List<Measure> measures = new ArrayList<>();
    measures.add(measure);

    ImportMeasure importMeasure = new ImportMeasure();
    importMeasure.setMeasures(measures);
    return importMeasure;
  }
}
