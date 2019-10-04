package com.wso2telco.core.dbutils.util;

import com.wso2telco.core.dbutils.exception.BusinessException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserManageHealperTest {
    private UserManageHealper userManageHealper;

    @BeforeTest
    public void Initialtest() {
        userManageHealper = UserManageHealper.getInstace();
    }

    @Test
    public void TestgetUser_whenValidUser() throws BusinessException {
        Assertions.assertThat(userManageHealper.getUser("Basic YWRtaW46YWRtaW4=")).isEqualTo("admin");
    }

    @Test
    public void TestgetUser_whenInvalidUser() throws BusinessException {
        Assertions.assertThat(userManageHealper.getUser("Basic YWRtaW46YWRtaW4=")).doesNotContain("chamara");

    }

    @Test(expectedExceptions = BusinessException.class)
    public void TestgetUser_whenHeaderNull() throws BusinessException {
        userManageHealper.getUser(null);

    }

    @Test(expectedExceptions = BusinessException.class)
    public void TestgetUser_whenBasicNotInHeader() throws BusinessException {
        Assertions.assertThat(userManageHealper.getUser("YWRtaW46YWRtaW4=")).doesNotContain("admin");
    }

    @Test(expectedExceptions = BusinessException.class)
    public void TestgetUser_whenHeaderLengthTooShort() throws BusinessException {
        userManageHealper.getUser("Basic");
    }

    @Test(expectedExceptions = BusinessException.class)
    public void TestgetUser_invalidFormat() throws BusinessException{
        userManageHealper.getUser("invalid");
    }


}
