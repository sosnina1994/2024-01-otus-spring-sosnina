package ru.otus.hw.controllers;

import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import ru.otus.hw.dto.*;
import ru.otus.hw.services.*;

import java.util.*;
import java.util.stream.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Тестирование контроллера типов инструмента")
@WebMvcTest(ToolTypeController.class)
class ToolTypeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ToolTypeService toolTypeService;

    @Test
    @DisplayName("Сохранение нового типа инструмента")
    void create() throws Exception {
        var toolTypeDto = getExampleOfToolTypeDto();
        var createDto = new ToolTypeDto(toolTypeDto.getId(), toolTypeDto.getName());

        given(toolTypeService.create(any())).willReturn(toolTypeDto);
        mvc.perform(post("/api/tool-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(toolTypeDto)));

    }

    @DisplayName("Ошибка сохранения комментария (с невалидным текстом)")
    @Test
    void getNotValidException() throws Exception {
        var createDto = new ToolTypeDto(null, null);

        mvc.perform(post("/api/tool-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Получение списка типов инструмента")
    void getAll() throws Exception {
        List<ToolTypeDto> exampleList = getExampleToolTypes();

        given(toolTypeService.findAll()).willReturn(exampleList);
        mvc.perform(get("/api/tool-types"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(exampleList)));
    }

    private ToolTypeDto getExampleOfToolTypeDto() {
        return new ToolTypeDto(1L, "ТЕСТ");
    }

    private List<ToolTypeDto> getExampleToolTypes() {
        return LongStream.range(1L, 4L).boxed()
                .map(id -> new ToolTypeDto(id, "ТЕСТ %d".formatted(id)))
                .toList();
    }
}