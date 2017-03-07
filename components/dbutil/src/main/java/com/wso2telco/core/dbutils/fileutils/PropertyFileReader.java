package com.wso2telco.core.dbutils.fileutils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utils.CarbonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    private static Log log = LogFactory.getLog(PropertyFileReader.class);
    private static PropertyFileReader propertyFileReader = null;

    private PropertyFileReader() {
    }

    public static PropertyFileReader getFileReader() {
        if (propertyFileReader == null) {
            propertyFileReader = new PropertyFileReader();
        }
        return propertyFileReader;
    }

    public Properties getProperties(String fileName) {
        String configPath = CarbonUtils.getCarbonConfigDirPath() + File.separator + fileName;
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(configPath));
        } catch (FileNotFoundException e) {
            log.error("Error while loading " + fileName + ".properties file", e);
        } catch (IOException e) {
            log.error("Error while loading " + fileName + ".properties file", e);
        } catch (Exception e) {
            log.error("Error while loading " + fileName + ".properties file", e);
        }
        return props;
    }
}
