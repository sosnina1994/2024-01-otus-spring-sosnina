package ru.otus.hw.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;


@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    @NonNull
    private final IOService ioService;

    @NonNull
    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        for (Question question : questionDao.findAll()) {
            ioService.printLine(QuestionStringFormatter.formatQuestion(question));
        }
    }
}
