package com.ktoth.testng.elasticsearch;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.UUID;

/**
 * TestNG test listener to intercept and send test results into Elasticsearch
 */
public class ElasticsearchTestResultListener implements ITestListener {

    private static ElasticsearchClient client;
    private static final String URL = "testng.elasticsearch.url";
    private static String testRunId;

    private static ThreadLocal<HashMap<String, String>> metaThreadLocal = new InheritableThreadLocal<>();

    public static String getTestRunId() {
        return testRunId;
    }

    public static ElasticsearchClient getClient() {
        return client;
    }

    public static void putTestResultMeta(String key, String value) {
        if (metaThreadLocal.get() == null) {
            metaThreadLocal.set(new HashMap<>());
        }
        metaThreadLocal.get().put(key, value);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //skip
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        client.postDocument(new TestResultDocument(testRunId, Thread.currentThread(), iTestResult, "SUCCESS", metaThreadLocal.get()));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        client.postDocument(new TestResultDocument(testRunId, Thread.currentThread(), iTestResult, "FAILED", metaThreadLocal.get()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        client.postDocument(new TestResultDocument(testRunId, Thread.currentThread(), iTestResult, "SKIPPED", metaThreadLocal.get()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //skip
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        testRunId = UUID.randomUUID().toString();
        String elasticUrl = System.getProperty(URL);
        if (elasticUrl == null || elasticUrl.isEmpty()) {
            throw new RuntimeException("Missing property '" + URL + "' must be set and cannot be null or empty!");
        }
        try {
            client = new ElasticsearchClient(new URI(elasticUrl));
        } catch (URISyntaxException e) {
            throw new RuntimeException("Property '" + URL + "' must contain a valid URL value!");
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        client.postDocument(new TestRunDocument(testRunId, iTestContext.getStartDate(), iTestContext.getEndDate()));
    }

}
