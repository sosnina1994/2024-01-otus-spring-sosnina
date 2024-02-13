package ru.otus.hw.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.exceptions.QuestionReadException;

import static org.assertj.core.api.Assertions.assertThatList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование получения данных из файла")
public class CsvQuestionDaoTest {

    @Mock
    private AppProperties appProperties;

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
