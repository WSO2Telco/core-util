package com.wso2telco.dbutils;

import org.junit.Test;

import com.wso2telco.core.dbutils.exception.BusinessException;
import com.wso2telco.core.dbutils.util.UserManageHealper;

import junit.framework.TestCase;

public class UserManagerTest extends TestCase {

	@Test
	public void test_0() {
		try {
			assertEquals(UserManageHealper.getInstace().getUser("Basic YWRtaW46YWRtaW4="), "admin");
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_1() {
		try {
			UserManageHealper.getInstace().getUser("YWRtaW46YWRtaW4=");
			assertTrue(true);
		} catch (BusinessException e) {
		}
	}

	@Test
	public void test_2() {
		try {
			UserManageHealper.getInstace().getUser("YWRtaW46YWRtaW4=");
			assertTrue(true);
		} catch (BusinessException e) {
		}
	}

	@Test
	public void test_3() {
		try {
			UserManageHealper.getInstace().getUser("Basic ");
			assertTrue(true);
		} catch (BusinessException e) {
		} // And the output is: some string
		
	}

	@Test
	public void test_4() {
		try {
			UserManageHealper.getInstace().getUser("Basic YWRtaW5hZG1pbg==");
			assertTrue(true);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
		} // And the output is: some string
	}

	@Test
	public void test_5() {
		try {
			UserManageHealper.getInstace().getUser("Basic YWRtaW4=");
			assertTrue(true);
		} catch (BusinessException e) {
		} // And the output is: some string
		
	}
}
