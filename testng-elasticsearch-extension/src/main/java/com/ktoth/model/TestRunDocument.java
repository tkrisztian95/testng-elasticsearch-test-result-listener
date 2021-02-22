package com.ktoth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestRunDocument {


    /**
     * Elastic built-in supported date-time format
     * See: https://www.elastic.co/guide/en/elasticsearch/reference/2.0/mapping-date-format.html#built-in-date-formats
     */
    DateTimeFormatter basicDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");

    public static final String type = "testng-test-run";

    @JsonProperty("runId")
    private String runId;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("finishDate")
    private String finishDate;

    public TestRunDocument(String runId, Date startDate, Date finishDate) {
        this.runId = runId;
        this.startDate = basicDateTimeFormatter.format(startDate.toInstant().atZone(ZoneId.systemDefault()));
        this.finishDate = basicDateTimeFormatter.format(finishDate.toInstant().atZone(ZoneId.systemDefault()));
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
