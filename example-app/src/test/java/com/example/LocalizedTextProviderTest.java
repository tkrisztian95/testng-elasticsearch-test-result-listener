package com.example;

import com.example.components.LocalizedTextProvider;
import com.ktoth.testng.elasticsearch.ElasticsearchTestContext;
import com.ktoth.testng.elasticsearch.ElasticsearchTestResultListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(ElasticsearchTestResultListener.class)
public class LocalizedTextProviderTest {

    @BeforeClass
    public void setUp() {
        ElasticsearchTestContext.init("http://localhost:9200");
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

    @Test(description = "Test say greeting in German")
    public void test_greetingInGerman() {
        //ARRANGE
        String expectedText = "Hallo";
        LocalizedTextProvider localizedTextProvider = new LocalizedTextProvider(LocalizedTextProvider.SupportedLanguage.de_DE);
        //ACT
        String greetingFormat = localizedTextProvider.getGreeting();
        //ASSERT
        assertTrue(greetingFormat.contains(expectedText));
        assertTrue(greetingFormat.contains("%s"));
        assertTrue(greetingFormat.contains("!"));
    }

    @Test(description = "Test say bye in German")
    public void test_byeInGerman() {
        //ARRANGE
        String expectedText = "Tschüss";
        LocalizedTextProvider localizedTextProvider = new LocalizedTextProvider(LocalizedTextProvider.SupportedLanguage.de_DE);
        //ACT
        String byeFormat = localizedTextProvider.getBye();
        //ASSERT
        assertTrue(byeFormat.contains(expectedText));
        assertTrue(byeFormat.contains("%s"));
        assertTrue(byeFormat.contains("!"));
    }
}