package com.azoft.energosbyt.prebilling.converter.converter;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public abstract class AbstractConverter<I, O> implements Converter<I, O> {

    private static final String SYSTEM_QUERY =
            "select out_value from get_one_references('SYSTEMS','ISH','id','PB','id')\n" +
                    "WHERE in_value = :systemName;";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getInformSystemCode(String system) {

        if (Strings.isBlank(system)) {
            return null;
        }

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("systemName", system);
        List<String> queryResult = jdbcTemplate.queryForList(SYSTEM_QUERY, namedParameters, String.class);

        if (queryResult.isEmpty()) {
            return null;
        }

        return queryResult.get(0);
    }
}
