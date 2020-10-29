package com.azoft.energosbyt.prebilling.converter.dto.input;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseCcbProvider {
    String system_id;
    String syncRequestId;

    List <CLProvider> provider = new ArrayList<>();

    @Data
    public static class CLProvider{
        String ext_id;
        String name;
        String inn;
        String kpp;
        String code;
        String balance_fold;
    }
}

