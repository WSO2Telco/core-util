package com.wso2telco.core.dbutils.util;


import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class ApprovalRequestTest {
    private ApprovalRequest approvalRequest;
    @BeforeTest
    public void initiatetest(){
        approvalRequest = new ApprovalRequest();
        approvalRequest.setTaskId("1");
        approvalRequest.setCreditPlan("family");
        approvalRequest.setDescription("description");
        approvalRequest.setSelectedRate("5%");
        approvalRequest.setStatus("on");
        approvalRequest.setSelectedTier("unlimited");

    }
    @Test
    public void testgetCreditPlan(){
        Assertions.assertThat(approvalRequest.getCreditPlan()).isEqualTo("family");
    }
    @Test
    public void testgetDescription(){
        Assertions.assertThat(approvalRequest.getDescription()).isEqualTo("description");
    }
    @Test
    public void testgetSelectedRate(){
        Assertions.assertThat(approvalRequest.getSelectedRate()).isEqualTo("5%");
    }
    @Test
    public void testgetSelectedTier(){
        Assertions.assertThat(approvalRequest.getSelectedTier()).isEqualTo("unlimited");
    }
    @Test
    public void testgetStatus(){
        Assertions.assertThat(approvalRequest.getStatus()).isEqualTo("on");
    }
    @Test
    public void testgetTaskId(){
        Assertions.assertThat(approvalRequest.getTaskId()).isEqualTo("1");
    }
}