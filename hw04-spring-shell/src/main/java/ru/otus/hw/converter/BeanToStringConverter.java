package ru.otus.hw.converter;

import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

public class BeanToStringConverter {
    public static String convertStudent(Student student) {
        return String.format("Вы вошли как студент %s", student.getFullName());
    }

    public static String convertTestResult(TestResult testResult) {
        return String.format("Студент %s ответил на %d вопросов",
                testResult.getStudent().getFullName(), testResult.getAnsweredQuestions().size());
    }
}
