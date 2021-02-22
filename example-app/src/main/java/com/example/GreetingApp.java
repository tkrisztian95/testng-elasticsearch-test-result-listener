package com.example;

import com.example.components.LocalizedTextProvider;

public class GreetingApp {


    public static void main(String[] args) {
        sayHello(getName(args));
    }

    public static String getName(final String[] args) {
        final String name = args[0];
        return name;
    }

    public static void sayHello(final String name) {
        LocalizedTextProvider textProvider = new LocalizedTextProvider(LocalizedTextProvider.SupportedLanguage.en_EN);
        String greetingText = textProvider.getGreeting();
        System.out.println(String.format(greetingText, name));
    }
}
