package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.config.TestConfig;
import ru.otus.hw.domain.TestResult;

@RequiredArgsConstructor
@Service
public class ResultServiceImpl implements ResultService {

    private final TestConfig testConfig;

    private final LocalizedIOService ioService;

    @Override
    public void showResult(TestResult testResult) {
        ioService.printLine("");
        ioService.printLocalized("Result.service.result");
        ioService.prinfFormattedLineLocalized("Result.service.student",
                testResult.getStudent().getFullName());
        ioService.prinfFormattedLineLocalized("Result.service.answered.count",
                testResult.getAnsweredQuestions().size());
        ioService.prinfFormattedLineLocalized("Result.service.right.answers.count",
                testResult.getRightAnswersCount());

        if (testResult.getRightAnswersCount() >= testConfig.getRightAnswersCountToPass()) {
            ioService.printLocalized("Result.service.success");
            return;
        }
        ioService.printLocalized("Result.service.fail");
    }
}
