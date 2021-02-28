package com.ktoth.testng.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;

import java.net.URI;

class ElasticsearchClient {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";

    public static final String INDEX_MAPPING_TEST_RESULTS = "testng-test-result";
    public static final String INDEX_MAPPING_TEST_RUNS = "testng-test-run";

    private URI uri;

    public ElasticsearchClient(URI uri) {
        this.uri = uri;
    }

    public void postDocument(final TestResultDocument result) {
        try {
            Unirest.post(uri + "/" + INDEX_MAPPING_TEST_RESULTS + "/" + TestResultDocument.TYPE)
                    .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                    .body(MAPPER.writeValueAsString(result)).asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postDocument(final TestRunDocument data) {
        try {
            Unirest.post(uri + "/" + INDEX_MAPPING_TEST_RUNS + "/" + TestRunDocument.TYPE)
                    .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                    .body(MAPPER.writeValueAsString(data)).asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
