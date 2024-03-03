package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printLocalized("TestService.execute.greeting");
        ioService.printLine("");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question : questions) {
            var maxQuestionSize = question.answers().size() - 1;

            var answerIdx = ioService.readIntForRangeWithPrompt(0, maxQuestionSize, formatQuestion(question),
                    ioService.getMessage("TestService.execute.number.format.read.error", maxQuestionSize));
            Answer answer = question.answers().get(answerIdx);

            var isAnswerValid = answer.isCorrect();
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

    private String formatQuestion(Question question) {
        String res = String.format("%s: \n", question.text());
        int num = 0;
        for (Answer answer : question.answers()) {
            res = res.concat(String.format("%d) %s\t", num++, answer.text()));
        }
        return res.concat("\n");
    }
}
