package com.azoft.energosbyt.prebilling.converter.converter;

import com.azoft.energosbyt.prebilling.converter.dto.input.BaseCcbPremise;
import com.azoft.energosbyt.prebilling.converter.dto.output.Address;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class BaseCcbPremiseToAddressConverter extends AbstractConverter<BaseCcbPremise, Address> {

    private static final String REGION_QUERY =
            "SELECT out_value FROM get_one_references('R_STATE','CCB','ID','CCB','DESC')\n" +
                    "WHERE in_value = :state;";


    @Override
    public Address convert(BaseCcbPremise input) {
        Address address = new Address();
        address.setInform_system(getInformSystemCode(input.getSystem_id()));
        address.setExt_id(input.getPremiseId());
        address.setIndex(input.getPostal());
        address.setRegion(getRegion(input.getState()));
        address.setArea(input.getCounty());
        address.setTown_type(input.getTaxVendorGeographicalCode());
        address.setTown(input.getCity());
        address.setSettlement(input.getHumanSettlement());
        address.setStreet_type(input.getHouseTypeDesc());
        address.setStreet(input.getStreet());
        address.setHouse(input.getHouse());
        address.setBlock(input.getHousing());
        address.setFlat(input.getApartment());
        address.setAddress_full(getFullAddress(input));
        address.setHouse_fias(input.getCharFias());
        return address;
    }

    private String getFullAddress(BaseCcbPremise input) {

        String address = input.getCounty() + " " + input.getCity() + " " + input.getHumanSettlement() +
                " " + input.getStreet() + " " + input.getHouse();

        if (Strings.isNotBlank(input.getHousing())) {
            address += "/" + input.getHousing();
        }

        if (Strings.isNotBlank(input.getApartment())) {
            address += "-" + input.getApartment();
        }

        return address.replaceAll("null", "").replaceAll(" ", " ");
    }

    private String getRegion(String state) {

        if (Strings.isBlank(state)) {
            return null;
        }

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("state", state);
        List<String> queryResult = jdbcTemplate.queryForList(REGION_QUERY, namedParameters, String.class);

        if (queryResult.isEmpty()) {
            return null;
        }

        return queryResult.stream()
                .max(Comparator.comparing(String::length))
                .orElse(null);
    }
}
