package com.ktoth.listener;

import com.ktoth.ElasticsearchTestContext;
import com.ktoth.service.ElasticsearchClient;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class ElasticsearchTestResultListener implements ITestListener {

    private static final String testRunId = String.valueOf(LocalDateTime.now().atOffset(ZoneOffset.UTC).toEpochSecond());

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ElasticsearchTestContext.getClient().postTestResult(iTestResult, testRunId, "SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ElasticsearchTestContext.getClient().postTestResult(iTestResult, testRunId, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        ElasticsearchTestContext.getClient().postTestResult(iTestResult, testRunId, "SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ElasticsearchTestContext.getClient().postTestRun(iTestContext, testRunId);
    }

}
