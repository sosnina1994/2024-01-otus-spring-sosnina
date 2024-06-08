package ru.otus.hw.actuator;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import ru.otus.hw.repositories.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BookCountActuator.class,
    properties = { "spring.sql.init.mode=never" })
@EnableAutoConfiguration
@AutoConfigureMockMvc
@DisplayName("Тестирование кастомного актуатора")
class BookCountActuatorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    @DisplayName("Отображение количества книг в метриках")
    void getBookCount() throws Exception {
        when(bookRepository.count()).thenReturn(2L);

        mockMvc.perform(get("/actuator"))
                .andExpect(status().isOk());
    }
}
