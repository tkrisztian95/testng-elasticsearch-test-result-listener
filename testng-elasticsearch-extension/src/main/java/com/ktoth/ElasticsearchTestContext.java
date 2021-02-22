package com.ktoth;

import com.ktoth.service.ElasticsearchClient;

public class ElasticsearchTestContext {
    private static ThreadLocal<ElasticsearchClient> clientThreadLocal = new InheritableThreadLocal<>();

    private ElasticsearchTestContext() {
    }

    public static ElasticsearchClient getClient() {
        if (clientThreadLocal.get() == null) {
            throw new RuntimeException("ElasticsearchClient in ElasticsearchTestContext is not set. Please set the client with ElasticsearchTestContext.setClient(...) method before using the ElasticsearchTestResultListener in your TestNG tests. Note that the client will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        } else {
            return (ElasticsearchClient) clientThreadLocal.get();
        }
    }

    public static void setClient(ElasticsearchClient client) {
        clientThreadLocal.set(client);
    }

    public static void removeClient() {
        clientThreadLocal.remove();
    }
}
