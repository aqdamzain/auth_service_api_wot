package com.noir.webthingauth.domain;

public class Gateway {
    private Integer deviceId;
    private String rfid;
    private String name;
    private String host;
    private String apiLink;
    private String gatewayId;

    public Gateway(Integer deviceId, String rfid, String name, String host, String apiLink, String gatewayId) {
        this.deviceId = deviceId;
        this.rfid = rfid;
        this.name = name;
        this.host = host;
        this.apiLink = apiLink;
        this.gatewayId = gatewayId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getApiLink() {
        return apiLink;
    }

    public void setApiLink(String apiLink) {
        this.apiLink = apiLink;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }
}
