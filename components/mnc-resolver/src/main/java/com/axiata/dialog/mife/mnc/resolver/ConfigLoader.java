package com.axiata.dialog.mife.mnc.resolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utils.CarbonUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ConfigLoader {

    private Log log = LogFactory.getLog(ConfigLoader.class);

    
    private MCCConfiguration mccConfig;
    private static ConfigLoader loader = new ConfigLoader();

    private ConfigLoader() {
        try {
            this.mccConfig = initMccConfig();
        } catch (JAXBException e) {
            log.error("Error while initiating custom config files", e);
        }
    }

    public static ConfigLoader getInstance() {
        return loader;
    }


    private MCCConfiguration initMccConfig() throws JAXBException {
        String configPath = CarbonUtils.getCarbonConfigDirPath() + File.separator + "MobileCountryConfig.xml";
        File file = new File(configPath);
        JAXBContext ctx = JAXBContext.newInstance(MCCConfiguration.class);
        Unmarshaller um = ctx.createUnmarshaller();
        return (MCCConfiguration) um.unmarshal(file);
    }

    public MCCConfiguration getMobileCountryConfig(){
        return mccConfig;
    }

}
