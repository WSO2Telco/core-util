package com.wso2telco.core.pcrservice.dao;

import java.util.List;

import org.junit.Test;

import com.wso2telco.core.pcrservice.dao.impl.KeyValueBasedPcrDAOImpl;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;

import junit.framework.TestCase;

/**
 * The class <code>KeyValueBasedPcrDAOImplTest</code> contains tests for the
 * class {@link <code>KeyValueBasedPcrDAOImpl</code>}
 *
 * @author yasith
 * @version $Revision$
 * @pattern JUnit Test Case
 * @generatedBy CodePro at 8/30/16 5:07 PM
 */
public class KeyValueBasedPcrDAOImplTest extends TestCase {

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public KeyValueBasedPcrDAOImplTest(String name) {
		super(name);
	}
	
	@Test
	public void testCreateNewPcrEntry() throws PCRException{
		RequestDTO dto  = new RequestDTO("u", "a", "s");
		RequestDTO dto1 = new RequestDTO("u1", "a1", "s1");
		RequestDTO dto2 = new RequestDTO("u1", "a2", "s1");
		RequestDTO dto3 = new RequestDTO("u3", "a3", "s3");
		RequestDTO dto4 = new RequestDTO("u4", "a4", "s4");
		RequestDTO dto5 = new RequestDTO("u5", "a5", "s5");
		RequestDTO dto6 = new RequestDTO("u6", "a6", "s6");
		RequestDTO dto7 = new RequestDTO("u7", "a7", "s7");
		RequestDTO dto8 = new RequestDTO("u8", "a8", "s8");
		RequestDTO dto9 = new RequestDTO("u9", "a9", "s9");
		RequestDTO dto0 = new RequestDTO("u0", "a0", "s0");
		
		KeyValueBasedPcrDAOImpl keyValueBasedPcrDAOImpl = new KeyValueBasedPcrDAOImpl();
		
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto, "p");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto1, "p1");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto2, "p1");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto3, "p3");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto4, "p4");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto5, "p5");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto6, "p6");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto7, "p7");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto8, "p8");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto9, "p9");
		keyValueBasedPcrDAOImpl.createNewPcrEntry(dto0, "p0");
		
	}
	
	@Test
	public void testgetExistingPCR() throws PCRException{
		
		KeyValueBasedPcrDAOImpl keyValueBasedPcrDAOImpl = new KeyValueBasedPcrDAOImpl();
		RequestDTO requestDTO =  new RequestDTO("u1", "a1", "s1");
		assertEquals(keyValueBasedPcrDAOImpl.getExistingPCR(requestDTO),"p1");
	}
	
	@Test
	public void testcreateNewSPEntry() throws PCRException{
		
		KeyValueBasedPcrDAOImpl keyValueBasedPcrDAOImpl = new KeyValueBasedPcrDAOImpl();
		
		keyValueBasedPcrDAOImpl.createNewSPEntry("s1", "a1", true);
		keyValueBasedPcrDAOImpl.createNewSPEntry("s1", "a2", true);
	}
	
	@Test
	public void testcheckIsRelated() throws PCRException{
		KeyValueBasedPcrDAOImpl keyValueBasedPcrDAOImpl = new KeyValueBasedPcrDAOImpl();
		
		assertTrue(keyValueBasedPcrDAOImpl.checkIsRelated("s1", "a1"));
	}
	
	@Test
	public void testgetRelatedApplicationIdList() throws PCRException{
		KeyValueBasedPcrDAOImpl keyValueBasedPcrDAOImpl = new KeyValueBasedPcrDAOImpl();
		
		List<String> list = keyValueBasedPcrDAOImpl.getApplicationIdList("s1");
		assertEquals(list.get(0),"a2");
		assertEquals(list.get(1), "a1");
		
	}
	
	@Test
	public void testgetAppIdListForUserSectorCombination() throws PCRException{
		KeyValueBasedPcrDAOImpl keyValueBasedPcrDAOImpl = new KeyValueBasedPcrDAOImpl();
		
		List<String> list = keyValueBasedPcrDAOImpl.getAppIdListForUserSectorCombination("u2", "s2");
		//assertNull(list);
		assertTrue(list.isEmpty());
		//assertEquals(list.get(0),"a1");
		//assertEquals(list.get(1), "a2");
		
	}
	
	@Test
	public void testcheckApplicationExists() throws PCRException{
		
		KeyValueBasedPcrDAOImpl keyValueBasedPcrDAOImpl = new KeyValueBasedPcrDAOImpl();
		
		assertTrue(keyValueBasedPcrDAOImpl.checkApplicationExists("s1", "a1"));
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(KeyValueBasedPcrDAOImplTest.class);
	}
}

