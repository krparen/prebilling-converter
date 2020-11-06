package com.azoft.energosbyt.prebilling.converter.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseOffice implements SystemIdHolder {

    @JsonProperty("system_id")
    String systemId;
    String syncRequestId;
    List<ClDivision> divisions = new ArrayList<>();

    @Data
    public static class ClDivision {
        String division;
        List<ClBranch> branches = new ArrayList<>();
    }

    @Data
    public static class ClBranch {
        String code;
        String name;
        List<ClOffice> offices = new ArrayList<>();
    }

    @Data
    public static class ClOffice {
        String code;
        String name;
        String address;
    }
}

