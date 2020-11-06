package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseOffice;
import com.azoft.energosbyt.prebilling.converter.dto.output.Division;
import com.azoft.energosbyt.prebilling.converter.dto.output.ImportDivision;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BaseOfficeToImportDivisionConverter extends AbstractConverter<BaseOffice, ImportDivision> {

    @Override
    public ImportDivision convert(BaseOffice input, Map<String, Object> messageHeaders) {
       ImportDivision output = new ImportDivision();
       String systemCode = getInformSystem(input, messageHeaders);
       List<Division> divisions = new ArrayList<>();
       input.getDivisions().forEach(inputDivision -> {
           inputDivision.getBranches().forEach(branch -> {

           });
       });

        return null;
    }
}
