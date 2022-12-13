package com.noir.webthingauth.repositories.thing;

import com.noir.webthingauth.domain.Gateway;
import com.noir.webthingauth.domain.PGateway;
import com.noir.webthingauth.exceptions.UlAuthException;
import com.noir.webthingauth.exceptions.UlResourceNotFoundException;

import java.util.List;

public interface GatewayRepository {
    List<PGateway> fetchAllGateway()  throws UlAuthException;
    Gateway fetchGateway(String rfid) throws UlResourceNotFoundException;
}
