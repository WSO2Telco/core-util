package com.wso2telco.core.pcrservice.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SectorUtil {

    private static Logger log = LoggerFactory.getLogger(SectorUtil.class);

    public static String getSectorIdFromUrl(String callbackUrl) {
        String sector = null;
        try {
            if (callbackUrl.startsWith("http")) {
                URL url = new URL(callbackUrl);
                sector = url.getHost();
            } else {
                URL url = new URL("http://" + callbackUrl);
                sector = url.getHost();
            }
        } catch (MalformedURLException e) {
            log.error("malformed url found! continues with the given Url : {}", callbackUrl);
            sector = callbackUrl;
        }

        if (sector == null || sector.equals("")) {
            log.error("malformed url found! continues with the given Url : {}", callbackUrl);
            sector = callbackUrl;
        }
        return sector;
    }

}
