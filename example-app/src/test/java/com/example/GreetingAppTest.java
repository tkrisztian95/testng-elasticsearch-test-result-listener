package com.example;

import com.ktoth.testng.elasticsearch.ElasticsearchTestContext;
import com.ktoth.testng.elasticsearch.ElasticsearchTestResultListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ElasticsearchTestResultListener.class)
public class GreetingAppTest {

    @BeforeClass
    public void setUp() {
        ElasticsearchTestContext.init("http://localhost:9200");
    }

    /**
     * This test should fail
     */
    @Test(description = "An always fails test")
    public void test_fail() {
        Assert.fail("This test intended to fail, again and again!");
    }

    /**
     * This test should pass
     */
    @Test(description = "An always success test")
    public void test_success() {
        Assert.assertTrue(true);
    }

    @Test(description = "Test get name from application run args")
    public void testGetName() {
        //ARRANGE
        final String[] args = new String[]{"Krisztián"};
        //ACT
        final String name = GreetingApp.getName(args);
        //ASSERT
        Assert.assertEquals(name, "Krisztián");
    }

}
