package com.ktoth.testng.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import org.testng.ITestContext;
import org.testng.ITestResult;

class ElasticsearchClient {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";

    private final String elasticHostUrl;

    public ElasticsearchClient(String elasticHostUrl) {
        this.elasticHostUrl = elasticHostUrl;
    }

    public void postTestResult(final ITestResult result, final String testRunId, final Long threadId, final String threadName, final String status) {
        final TestResultDocument data = new TestResultDocument(testRunId, threadId, threadName, result, status);
        try {
            Unirest.post(elasticHostUrl + "/" + Constants.INDEX_MAPPING_TEST_RESULTS + "/" + TestResultDocument.type)
                    .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                    .body(MAPPER.writeValueAsString(data)).asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postTestRun(final ITestContext result, final String testRunId) {
        final TestRunDocument data = new TestRunDocument(testRunId, result.getStartDate(), result.getEndDate());
        try {
            Unirest.post(elasticHostUrl + "/" + Constants.INDEX_MAPPING_TEST_RUNS + "/" + TestRunDocument.type)
                    .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                    .body(MAPPER.writeValueAsString(data)).asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
