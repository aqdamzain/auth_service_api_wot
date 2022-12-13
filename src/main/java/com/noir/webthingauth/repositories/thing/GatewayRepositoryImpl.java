package com.noir.webthingauth.repositories.thing;

import com.noir.webthingauth.domain.Gateway;
import com.noir.webthingauth.domain.PGateway;
import com.noir.webthingauth.exceptions.UlAuthException;
import com.noir.webthingauth.exceptions.UlResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GatewayRepositoryImpl implements GatewayRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL_GW = "SELECT DEVICE_ID, NAME, HOST FROM TB_GATEWAYS";
    private static final String SQL_FIND_BY_RFID = "SELECT * from TB_GATEWAYS WHERE RFID = ?";


    @Override
    public List<PGateway> fetchAllGateway() throws UlAuthException {
        return jdbcTemplate.query(SQL_FIND_ALL_GW, getAllGwRowMapper);
    }

    @Override
    public Gateway fetchGateway(String rfid) throws UlResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_RFID, new Object[]{rfid}, getGwRowMapper);
        } catch (Exception e) {
            throw new UlResourceNotFoundException("gateway not found");
        }
    }

    private RowMapper<PGateway>  getAllGwRowMapper = (rs, rowNum) -> {
        return new PGateway(rs.getInt("DEVICE_ID"),
                rs.getString("NAME"),
                rs.getString("HOST"));
    };

    private RowMapper<Gateway>  getGwRowMapper = (rs, rowNum) -> {
        return new Gateway(rs.getInt("DEVICE_ID"),
                rs.getString("RFID"),
                rs.getString("NAME"),
                rs.getString("HOST"),
                rs.getString("API_LINK"),
                rs.getString("GATEWAY_ID")
        );
    };
}
