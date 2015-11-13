package com.ec.erp.common.utils;

import java.util.Map;

import com.ec.erp.common.utils.EcUrl;

public class EcUrlUtils {
    private Map<String, EcUrl > velocityUrl;

    public void setVelocityUrl(Map<String, EcUrl> velocityUrl) {
        this.velocityUrl = velocityUrl;
    }

    public EcUrl getEcUrl(String key) {
        EcUrl org = velocityUrl.get(key);
        EcUrl ecUrl = org.clone();
        ecUrl.setEcUrl(org);
        return ecUrl;
    }

}
