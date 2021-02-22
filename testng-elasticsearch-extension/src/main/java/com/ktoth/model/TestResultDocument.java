package com.ktoth.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.testng.ITestResult;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TestResultDocument {

    /**
     * Elastic built-in supported date-time format
     * See: https://www.elastic.co/guide/en/elasticsearch/reference/2.0/mapping-date-format.html#built-in-date-formats
     */
    DateTimeFormatter basicDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");

    public static final String type = "testng-test-result";

    @JsonProperty("testRunId")
    private String testRunId;

    @JsonProperty("testClass")
    private String testClass;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    @JsonProperty("executionDate")
    private String executionDate;

    @JsonProperty("environment")
    private HashMap<String, String> environment;

    public TestResultDocument(String testRunId, ITestResult result, String status) {
        this.testRunId = testRunId;
        this.status = status;
        this.testClass = result.getTestClass().getName();
        this.description = result.getMethod().getDescription();
        this.executionDate = ZonedDateTime.now().format(basicDateTimeFormatter);
    }

    public String getTestClass() {
        return testClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public HashMap<String, String> getEnvironment() {
        return environment;
    }

    public void setEnvironment(HashMap<String, String> environment) {
        this.environment = environment;
    }
}

