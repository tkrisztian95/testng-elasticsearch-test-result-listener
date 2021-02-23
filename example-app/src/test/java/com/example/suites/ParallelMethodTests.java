package com.example.suites;

import com.ktoth.testng.elasticsearch.ElasticsearchTestContext;
import com.ktoth.testng.elasticsearch.ElasticsearchTestResultListener;
import org.testng.annotations.*;

@Listeners(ElasticsearchTestResultListener.class)
public class ParallelMethodTests {

    @BeforeClass
    public void setUp() {
        ElasticsearchTestContext.init("http://localhost:9200");
    }

    @BeforeMethod
    public void beforeMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-method. Thread id is: " + id);
    }

    @Test
    public void testMethodsOne() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method One. Thread id is: " + id);
    }

    @Test
    public void testMethodsTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Two. Thread id is: " + id);
    }

    @Test
    public void testMethodsThree() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Three. Thread id is: " + id);
    }

    @Test
    public void testMethodsFour() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Four. Thread id is: " + id);
    }

    @Test
    public void testMethodsFive() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Five. Thread id is: " + id);
    }

    @Test
    public void testMethodsSix() {
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Six. Thread id is: " + id);
    }

    @AfterMethod
    public void afterMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("After test-method. Thread id is: " + id);
    }
}
