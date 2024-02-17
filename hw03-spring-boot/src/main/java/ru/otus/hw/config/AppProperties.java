package ru.otus.hw.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application")
public class AppProperties implements TestConfig, TestFileNameProvider, LocaleConfig {

    private final int rightAnswersCountToPass;

    private final Locale locale;

    private final Map<Locale, String> fileNameByLocaleTag;

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public String getTestFileName() {
        return fileNameByLocaleTag.get(locale);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
