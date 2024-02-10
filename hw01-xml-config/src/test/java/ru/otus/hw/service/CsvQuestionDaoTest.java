package ru.otus.hw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.exceptions.QuestionReadException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование получения данных из файла")
public class CsvQuestionDaoTest {

    @DisplayName("Успешное чтение из файла с проверкой количества записей")
    @Test
    void successfulQuestionRead() {
        CsvQuestionDao dao = new CsvQuestionDao(new AppProperties("questions-test.csv"));
        int count = dao.findAll().size();
        assertEquals(4, count);
    }

    @DisplayName("Ошибка чтения файла")
    @Test
    void getThrowQuestionReadException() {
        CsvQuestionDao dao = new CsvQuestionDao(
                new AppProperties("test_file_name.csv"));
        assertThrows(QuestionReadException.class, dao::findAll);
    }
}
