package com.noir.webthingauth.domain;

public class PGateway {
    private Integer deviceId;
    private String name;
    private String host;

    public PGateway(Integer deviceId, String name, String host) {
        this.deviceId = deviceId;
        this.name = name;
        this.host = host;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
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
}
