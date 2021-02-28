package com.ktoth.testng.elasticsearch;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.testng.ITestResult;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

class TestResultDocument {

    /**
     * Elastic built-in supported date-time format
     * See: https://www.elastic.co/guide/en/elasticsearch/reference/2.0/mapping-date-format.html#built-in-date-formats
     */
    DateTimeFormatter basicDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");

    public static final String TYPE = "testng-test-result";

    @JsonProperty("testRunId")
    private String testRunId;

    @JsonProperty("testClass")
    private String testClass;

    @JsonProperty("testMethodName")
    private String testMethodName;

    @JsonProperty("testMethodQualifiedName")
    private String testMethodQualifiedName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

    @JsonProperty("executionDate")
    private String executionDate;

    @JsonProperty("executionThreadId")
    private String executionThreadId;

    @JsonProperty("executionThreadName")
    private String executionThreadName;

    @JsonProperty("meta")
    private HashMap<String, String> meta;

    public TestResultDocument(String testRunId, Thread thread, ITestResult result, String status, HashMap<String, String> meta) {
        this.testRunId = testRunId;
        this.status = status;
        this.testClass = result.getTestClass().getName();
        this.testMethodName = result.getMethod().getMethodName();
        this.testMethodQualifiedName = result.getMethod().getQualifiedName();
        this.description = result.getMethod().getDescription();
        this.executionDate = ZonedDateTime.now().format(basicDateTimeFormatter);
        this.executionThreadId = String.valueOf(thread.getId());
        this.executionThreadName = thread.getName();
        this.meta = meta;
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

    public HashMap<String, String> getMeta() {
        return meta;
    }

    public void setMeta(HashMap<String, String> meta) {
        this.meta = meta;
    }
}

