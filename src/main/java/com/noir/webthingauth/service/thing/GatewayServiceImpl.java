package com.noir.webthingauth.service.thing;

import com.noir.webthingauth.domain.Gateway;
import com.noir.webthingauth.domain.PGateway;
import com.noir.webthingauth.exceptions.UlAuthException;
import com.noir.webthingauth.exceptions.UlResourceNotFoundException;
import com.noir.webthingauth.repositories.thing.GatewayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class GatewayServiceImpl implements GatewayService {
    @Autowired
    GatewayRepository gatewayRepository;


    @Override
    public List<PGateway> getAllGateways() throws UlAuthException {
        return gatewayRepository.fetchAllGateway();
    }

    @Override
    public Gateway getGateway(String rfid) throws UlAuthException {
        return gatewayRepository.fetchGateway(rfid);
    }
}
