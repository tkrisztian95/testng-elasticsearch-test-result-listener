package com.ktoth.testng.elasticsearch;

import java.util.UUID;

public class ElasticsearchTestContext {

    private static String runId;
    private static ElasticsearchClient globalClient;

    private ElasticsearchTestContext() {
    }

    static ElasticsearchClient getClient() {
        if (globalClient == null) {
            throw new RuntimeException("ElasticsearchClient in ElasticsearchTestContext is not set. Please set the client with ElasticsearchTestContext.setClient(...) method before using the ElasticsearchTestResultListener in your TestNG tests.");
        } else {
            return globalClient;
        }
    }

    static void setClient(ElasticsearchClient client) {
        globalClient = client;
    }

    static void removeClient() {
        globalClient = null;
    }

    static void generateRunId() {
        runId = UUID.randomUUID().toString();
    }

    static String getRunId() {
        return runId;
    }

    public static void init(String elastic_host) {
        if (runId == null) {
            generateRunId();
        }
        if (globalClient == null) {
            setClient(new ElasticsearchClient(elastic_host));
        }
    }
}
