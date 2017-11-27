package com.wso2telco.core.dbutils.util;

/**
 * Copyright (c) 2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * <p>
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "taskId",
        "description",
        "selectedTier",
        "status",
        "creditPlan"
})
public class AppApprovalRequest {

    @JsonProperty("taskId")
    private String taskId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("selectedTier")
    private String selectedTier;
    @JsonProperty("status")
    private String status;
    @JsonProperty("creditPlan")
    private String creditPlan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("taskId")
    public String getTaskId() {
        return taskId;
    }

    @JsonProperty("taskId")
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("selectedTier")
    public String getSelectedTier() {
        return selectedTier;
    }

    @JsonProperty("selectedTier")
    public void setSelectedTier(String selectedTier) {
        this.selectedTier = selectedTier;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("creditPlan")
    public String getCreditPlan() {
        return creditPlan;
    }

    @JsonProperty("creditPlan")
    public void setCreditPlan(String creditPlan) {
        this.creditPlan = creditPlan;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
