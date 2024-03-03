package ru.otus.hw.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.exceptions.QuestionReadException;

import static org.assertj.core.api.Assertions.assertThatList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@DisplayName("Тестирование получения данных из файла")
@SpringBootTest
public class CsvQuestionDaoTest {

    @MockBean
    private AppProperties appProperties;

    @Autowired
    private QuestionDao dao;

    @BeforeEach
    void setUp() {
        dao = new CsvQuestionDao(appProperties);
    }

    @DisplayName("Успешное чтение из файла с проверкой количества записей")
    @Test
    void successfulQuestionRead() {
        given(appProperties.getTestFileName()).willReturn("questions-test.csv");
        assertThatList(dao.findAll())
                .isNotNull()
                .isNotEmpty()
                .hasSize(4);

    }

    @DisplayName("Ошибка чтения файла")
    @Test
    void getThrowQuestionReadException() {
        given(appProperties.getTestFileName()).willReturn("test_file_name.csv");
        assertThrows(QuestionReadException.class, dao::findAll);
    }

}
