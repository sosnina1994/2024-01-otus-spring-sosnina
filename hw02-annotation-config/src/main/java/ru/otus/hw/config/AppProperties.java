package ru.otus.hw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties implements TestFileNameProvider, TestConfig {

    @Value("${test.file.name}")
    private String testFileName;


    @Value("${test.right.answers.count}")
    private int rightAnswersCountToPass;

    @Override
    public String getTestFileName() {
        return testFileName;
    }

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }
}
