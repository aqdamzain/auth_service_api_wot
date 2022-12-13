package com.noir.webthingauth.service.thing;

import com.noir.webthingauth.domain.Gateway;
import com.noir.webthingauth.domain.PGateway;
import com.noir.webthingauth.exceptions.UlAuthException;

import java.util.List;

public interface GatewayService {
    List<PGateway> getAllGateways() throws UlAuthException;

    Gateway getGateway(String rfid) throws UlAuthException;
}
