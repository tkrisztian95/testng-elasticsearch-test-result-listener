package com.ktoth.testng.elasticsearch;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG test listener to capture and send test results into Elasticsearch
 */
public class ElasticsearchTestResultListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult iTestResult) {
        //skip
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ElasticsearchClient client = ElasticsearchTestContext.getClient();
        String testRunId = ElasticsearchTestContext.getRunId();
        Long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        client.postTestResult(iTestResult, testRunId, threadId, threadName, "SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ElasticsearchClient client = ElasticsearchTestContext.getClient();
        String testRunId = ElasticsearchTestContext.getRunId();
        Long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        client.postTestResult(iTestResult, testRunId, threadId, threadName, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ElasticsearchClient client = ElasticsearchTestContext.getClient();
        String testRunId = ElasticsearchTestContext.getRunId();
        Long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        client.postTestResult(iTestResult, testRunId, threadId, threadName, "SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //skip
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        //skip
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ElasticsearchClient client = ElasticsearchTestContext.getClient();
        String testRunId = ElasticsearchTestContext.getRunId();
        client.postTestRun(iTestContext, testRunId);
    }

}
