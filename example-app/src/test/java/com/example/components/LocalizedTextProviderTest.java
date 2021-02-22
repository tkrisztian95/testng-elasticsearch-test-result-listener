package com.example.components;

import com.ktoth.ElasticsearchTestContext;
import com.ktoth.listener.ElasticsearchTestResultListener;
import com.ktoth.service.ElasticsearchClient;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(ElasticsearchTestResultListener.class)
public class LocalizedTextProviderTest {

    @BeforeClass
    public void setUp() {
        ElasticsearchTestContext.setClient(new ElasticsearchClient("http://localhost:9200"));
    }

    @Test(description = "Test say greeting in English")
    public void test_greetingInEnglish() {
        //ARRANGE
        String expectedText = "Hello";
        LocalizedTextProvider localizedTextProvider = new LocalizedTextProvider(LocalizedTextProvider.SupportedLanguage.en_EN);
        //ACT
        String greetingFormat = localizedTextProvider.getGreeting();
        //ASSERT
        assertTrue(greetingFormat.contains(expectedText));
        assertTrue(greetingFormat.contains("%s"));
        assertTrue(greetingFormat.contains("!"));
    }

    @Test(description = "Test say greeting in Hungarian")
    public void test_greetingInHungarian() {
        //ARRANGE
        String expectedText = "Szia";
        LocalizedTextProvider localizedTextProvider = new LocalizedTextProvider(LocalizedTextProvider.SupportedLanguage.hu_HU);
        //ACT
        String greetingFormat = localizedTextProvider.getGreeting();
        //ASSERT
        assertTrue(greetingFormat.contains(expectedText));
        assertTrue(greetingFormat.contains("%s"));
        assertTrue(greetingFormat.contains("!"));
    }

    @Test(description = "Test say bye in English")
    public void test_byeInEnglish() {
        //ARRANGE
        String expectedText = "Bye";
        LocalizedTextProvider localizedTextProvider = new LocalizedTextProvider(LocalizedTextProvider.SupportedLanguage.en_EN);
        //ACT
        String byeFormat = localizedTextProvider.getBye();
        //ASSERT
        assertTrue(byeFormat.contains(expectedText));
        assertTrue(byeFormat.contains("%s"));
        assertTrue(byeFormat.contains("!"));
    }

    @Test(description = "Test say bye in Hungarian")
    public void test_byeInHungarian() {
        //ARRANGE
        String expectedText = "Viszlát";
        LocalizedTextProvider localizedTextProvider = new LocalizedTextProvider(LocalizedTextProvider.SupportedLanguage.hu_HU);
        //ACT
        String byeFormat = localizedTextProvider.getBye();
        //ASSERT
        assertTrue(byeFormat.contains(expectedText));
        assertTrue(byeFormat.contains("%s"));
        assertTrue(byeFormat.contains("!"));
    }

}