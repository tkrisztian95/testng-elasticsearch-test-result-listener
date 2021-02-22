package com.ktoth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktoth.model.TestResultDocument;
import com.ktoth.model.TestRunDocument;
import kong.unirest.Unirest;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class ElasticsearchClient {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";

    private final String elastic_host;

    public ElasticsearchClient(String elastic_host) {
        this.elastic_host = elastic_host;
    }

    public void postTestResult(final ITestResult result, final String testRunId, final String status) {
        final TestResultDocument data = new TestResultDocument(testRunId, result, status);
        try {
            Unirest.post(elastic_host + "/testng-test-result/" + TestResultDocument.type)
                    .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                    .body(MAPPER.writeValueAsString(data)).asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postTestRun(final ITestContext result, final String testRunId) {
        final TestRunDocument data = new TestRunDocument(testRunId, result.getStartDate(), result.getEndDate());
        try {
            Unirest.post(elastic_host + "/testng-test-run/" + TestRunDocument.type)
                    .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                    .body(MAPPER.writeValueAsString(data)).asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
