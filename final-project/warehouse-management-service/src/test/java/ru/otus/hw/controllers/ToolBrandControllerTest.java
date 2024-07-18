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

@DisplayName("Тестирование контроллера брендов инструмента")
@WebMvcTest(ToolBrandController.class)
class ToolBrandControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ToolBrandService brandService;

    @Test
    @DisplayName("Сохранение нового бренда инструмента")
    void create() throws Exception {
        var toolBrandDto = getExampleOfToolBrandDto();
        var createDto = new ToolBrandDto(toolBrandDto.getId(), toolBrandDto.getName());

        given(brandService.create(any())).willReturn(toolBrandDto);
        mvc.perform(post("/api/tool-brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(toolBrandDto)));

    }

    @DisplayName("Ошибка сохранения бренда инструмента (с невалидным текстом)")
    @Test
    void getNotValidException() throws Exception {
        var createDto = new ToolTypeDto(null, null);

        mvc.perform(post("/api/tool-brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Получение списка типов инструмента")
    void getAll() throws Exception {
        List<ToolBrandDto> exampleList = getExampleToolBrands();

        given(brandService.findAll()).willReturn(exampleList);
        mvc.perform(get("/api/tool-brands"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(exampleList)));
    }

    private ToolBrandDto getExampleOfToolBrandDto() {
        return new ToolBrandDto(1L, "ТЕСТ");
    }

    private List<ToolBrandDto> getExampleToolBrands() {
        return LongStream.range(1L, 4L).boxed()
                .map(id -> new ToolBrandDto(id, "ТЕСТ %d".formatted(id)))
                .toList();
    }
}