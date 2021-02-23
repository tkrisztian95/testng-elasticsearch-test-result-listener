package com.example.suites;

import com.ktoth.testng.elasticsearch.ElasticsearchTestContext;
import com.ktoth.testng.elasticsearch.ElasticsearchTestResultListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ElasticsearchTestResultListener.class)
public class ParallelClassesTestTwo {
    @BeforeClass
    public void beforeClass() {
        ElasticsearchTestContext.init("http://localhost:9200");
        long id = Thread.currentThread().getId();
        System.out.println("Before test-class. Thread id is: " + id);
    }

    @Test
    public void testMethodOne() {
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);
    }

    @Test
    public void testMethodTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method Two. Thread id is: " + id);
    }

    @AfterClass
    public void afterClass() {
        long id = Thread.currentThread().getId();
        System.out.println("After test-class. Thread id is: " + id);
    }

}
