package com.noir.webthingauth.domain;

public class Thing {

    private Integer deviceId;
    private String rfid;
    private String name;
    private String[] type;
    private String webId;
    private String gatewayId;

    public Thing(Integer deviceId, String rfid, String name, String[] type, String webId, String gatewayId) {
        this.deviceId = deviceId;
        this.rfid = rfid;
        this.name = name;
        this.type = type;
        this.webId = webId;
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

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }
}
