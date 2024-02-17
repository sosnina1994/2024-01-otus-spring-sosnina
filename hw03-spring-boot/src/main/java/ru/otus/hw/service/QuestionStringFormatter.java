package ru.otus.hw.service;

import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

public class QuestionStringFormatter {
    public static String formatQuestion(Question question) {
        if (question == null || question.text().trim().isEmpty()) {
            return null;
        }
        String res = String.format("%s: \n", question.text());
        if (question.answers() == null || question.answers().isEmpty()) {
            return res;
        }
        int num = 0;
        for (Answer answer : question.answers()) {
            if (answer.text() == null || answer.text().trim().isEmpty()) {
                continue;
            }
            res = res.concat(String.format("%d)%s\t", num++, answer.text().trim()));
        }
        return res.concat("\n");
    }
}
