package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbMeter;
import com.azoft.energosbyt.prebilling.converter.dto.output.Meter;
import com.azoft.energosbyt.prebilling.converter.dto.output.Register;
import com.azoft.energosbyt.prebilling.converter.service.ReferenceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BaseCcbMeterToMeterConverter implements Converter<BaseCcbMeter, Meter> {

    @Autowired
    private ReferenceQueryService referenceQueryService;

    @Override
    public Meter convert(BaseCcbMeter input, Map<String, Object> messageHeaders) {
        Meter output = new Meter();
        output.setInform_system(referenceQueryService.getInformSystemCode(input.getSystem_id()));
        output.setExt_id(input.getMeter());
        output.setName(input.getModel());
        output.setNumber(input.getSerialNumber());
        output.setTariff_zone(input.getMeterConfig().getRegister().size());
        output.setRemove_date(getRemoveDate(input));
        output.setRegister(getRegisters(input));
        return output;
    }

    private List<Register> getRegisters(BaseCcbMeter input) {
        return input.getMeterConfig().getRegister().stream()
                .map(inputRegister -> {
                   Register register = new Register();
                   register.setExt_id_register(inputRegister.getRegisterId());
                   register.setDigit_capacity(inputRegister.getFullScale());
                   register.setUnit(inputRegister.getUnitOfMeasure());
                   return register;
                })
                .collect(Collectors.toList());
    }

    private LocalDate getRemoveDate(BaseCcbMeter input) {

        if (input.getMeterCharacteristic() == null) {
            return null;
        }

        LocalDate verificationDate = input.getMeterCharacteristic().getVerificationDate();
        Integer calibrationInterval = input.getMeterCharacteristic().getCalibrationInterval();

        if (verificationDate == null || calibrationInterval == null) {
            return null;
        }

        return verificationDate.plusYears(calibrationInterval);
    }
}
