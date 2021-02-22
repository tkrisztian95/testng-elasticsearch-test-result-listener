package com.example.components;

public class LocalizedTextProvider {
    private final SupportedLanguage language;
    public enum SupportedLanguage {
        en_EN,
        hu_HU
    }

    public LocalizedTextProvider(SupportedLanguage language) {
        this.language = language;
    }

    public String getGreeting() {
        switch (language) {
            case hu_HU:
                return "Szia %s!";
            case en_EN:
            default:
                return "Hello %s!";
        }
    }

    public String getBye() {
        switch (language) {
            case hu_HU:
                return "Viszlát %s!";
            case en_EN:
            default:
                return "Bye %s!";
        }
    }
}
