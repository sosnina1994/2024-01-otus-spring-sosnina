package ru.otus.hw.controllers;

import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.test.context.support.*;
import org.springframework.test.web.servlet.*;
import ru.otus.hw.configs.*;
import ru.otus.hw.dto.*;
import ru.otus.hw.exceptions.*;
import ru.otus.hw.services.*;

import javax.sql.*;
import java.util.*;
import java.util.stream.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Тестирование контроллера инструментов")
@WebMvcTest(ToolController.class)
@Import(SecurityConfig.class)
class ToolControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DataSource dataSource;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ToolService toolService;

    @MockBean
    private ToolTypeService toolTypeService;

    @MockBean
    private ToolBrandService toolBrandService;


    @DisplayName("Редирект на страницу login")
    @Test
    void redirectToLoginPage() throws Exception {
        mvc.perform(get("/")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Сохранение нового инструмента")
    void create() throws Exception {
        var toolDto = getExampleTool();
        var toolCreateDto = new ToolCreateDto(toolDto.getId(),
                toolDto.getName(),
                toolDto.getDesignation(),
                toolDto.getType().getId(),
                toolDto.getBrand().getId(),
                toolDto.getBalance(),
                toolDto.getMinBalance()
        );

        given(toolService.create(any())).willReturn(toolDto);
        mvc.perform(post("/api/tools")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(toolCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(toolDto)));

    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Сохранение нового инструмента c невалидным типом")
    void create_withInvalidType() throws Exception {
        var toolDto = getExampleTool();
        var toolCreateDto = new ToolCreateDto(toolDto.getId(),
                toolDto.getName(),
                toolDto.getDesignation(),
                null,
                toolDto.getBrand().getId(),
                toolDto.getBalance(),
                toolDto.getMinBalance()
        );

        given(toolService.create(any())).willReturn(toolDto);

        mvc.perform(post("/api/tools")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(toolCreateDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Сохранение нового инструмента c невалидным брендом")
    void create_withInvalidBrand() throws Exception {
        var toolDto = getExampleTool();
        var toolCreateDto = new ToolCreateDto(toolDto.getId(),
                toolDto.getName(),
                toolDto.getDesignation(),
                toolDto.getType().getId(),
                null,
                toolDto.getBalance(),
                toolDto.getMinBalance()
        );

        given(toolService.create(any())).willReturn(toolDto);

        mvc.perform(post("/api/tools")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(toolCreateDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Обновление инструмента")
    void update() throws Exception {
        var tool = getExampleTool();
        var toolUpdateDto = new ToolUpdateDto(10, 10);

        given(toolService.update(any(), any())).willReturn(tool);

        mvc.perform(patch("/api/tools/%d".formatted(tool.getId()))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(toolUpdateDto)))
                .andExpect(status().isAccepted())
                .andExpect(content().json(mapper.writeValueAsString(tool)));
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Обновление инструмента c несуществующим идентификатором")
    void update_withNotFound() throws Exception {
        var tool = getExampleTool();
        var toolUpdateDto = new ToolUpdateDto(10, 10);

        given(toolService.update(any(), any())).willThrow(NotFoundException.class);

        mvc.perform(patch("/api/tools/%d".formatted(tool.getId()))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(toolUpdateDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Получение списка инструментов")
    void getAll() throws Exception {
        List<ToolDto> exampleList = getExampleTools();

        given(toolService.findAll()).willReturn(exampleList);
        mvc.perform(get("/api/tools"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(exampleList)));
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Получение инструмента по идентификатору")
    void getById() throws Exception {
        var tool = getExampleTool();

        given(toolService.findById(any())).willReturn(tool);

        mvc.perform(get("/api/tools/%d".formatted(tool.getId())))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(tool)));
    }

    @Test
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Получение инструмента c несуществующим идентификатором")
    void getById_withNotFound() throws Exception {
        var tool = getExampleTool();

        given(toolService.findById(any())).willThrow(NotFoundException.class);

        mvc.perform(get("/api/tools/%d".formatted(tool.getId())))
                .andExpect(status().isNotFound());
    }

    private List<ToolDto> getExampleTools() {
        return LongStream.range(1L, 4L).boxed()
                .map(id -> new ToolDto(id, "name %d".formatted(id), "designation %d".formatted(id),
                        new ToolTypeDto(id, "type"), new ToolBrandDto(id, "brand"), 10, 10))
                .toList();
    }

    private ToolDto getExampleTool() {
        given(toolTypeService.findAll()).willReturn(List.of(new ToolTypeDto(1L, "TEST_TYPE")));
        given(toolBrandService.findAll()).willReturn(List.of(new ToolBrandDto(1L, "TEST_BRAND")));

        return new ToolDto(1L, "TEST", "TEST",
                toolTypeService.findAll().get(0),
                toolBrandService.findAll().get(0),
                10, 10);
    }
}