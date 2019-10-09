package com.wso2telco.core.dbutils.fileutils;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Properties;


public class PropertyFileReaderTest {

    @BeforeTest
    public void setUp() {
        PropertyFileReaderTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            System.setProperty("carbon.config.dir.path", PropertyFileReaderTest.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFileReader_whenValidPathGiven() {
        Properties properties = PropertyFileReader.getFileReader().getProperties("test.txt");
        Assertions.assertThat(properties.getProperty("key1")).isEqualTo("value1");

    }

    @Test
    public void testGetFileReader_fileNotFoundException() {
        Properties properties = PropertyFileReader.getFileReader().getProperties("wrong.txt");
        Assertions.assertThat(properties.getProperty("key1")).isEqualTo(null);
    }
}
