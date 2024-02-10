package ru.otus.hw.service;

import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

public class QuestionStringFormatter {
    public static String formatQuestion(Question question) {
        String res = String.format("%s: \n", question.text());
        int num = 1;
        for (Answer answer : question.answers()) {
            res = res.concat(String.format("%d %s\t", num++, answer.text()));
        }
        return res.concat("\n");
    }
}
