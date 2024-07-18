package ru.otus.hw.repositories;

import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import ru.otus.hw.dto.*;
import ru.otus.hw.models.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;


@DisplayName("JPA-Репозиторий для работы с инструментами")
@DataJpaTest
class JpaToolRepositoryTest {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Получение списка инструментов")
    void findAll() {
        var tools = toolRepository.findAll();
        var expectedTools = getDbTools();

        assertThat(tools).containsExactlyElementsOf(expectedTools);
        tools.forEach(System.out::println);
    }

    @Test
    @DisplayName("Получение инструмента по id")
    void findById() {
        var tools = getDbTools();
        for (Tool tool : tools) {
            var actualBook = toolRepository.findById(tool.getId());
            assertThat(actualBook).isPresent()
                    .get()
                    .isEqualTo(tool);
        }
    }

    @DisplayName("Сохранение нового инструмента")
    @Test
    void save() {
        val toolType = entityManager.find(ToolType.class, 1);
        val toolBrand = entityManager.find(ToolBrand.class, 1);
        val tool = new Tool(null, "TEST", "TEST", toolType, toolBrand, 10, 10);
        entityManager.merge(tool);

        toolRepository.save(tool);
        entityManager.detach(tool);
        assertThat(tool.getId()).isGreaterThan(0);

        val findTool = entityManager.find(Tool.class, tool.getId());
        assertThat(findTool)
                .usingRecursiveComparison()
                .isEqualTo(tool);
    }

    @DisplayName("Обновление инструмента")
    @Test
    void update() {
        val toolType = entityManager.find(ToolType.class, 1);
        val toolBrand = entityManager.find(ToolBrand.class, 1);
        val expectedTool = new Tool(1L, "TEST", "TEST", toolType, toolBrand, 10, 10);

        var currentTool = entityManager.find(Tool.class, expectedTool.getId());

        assertThat(currentTool)
                .usingRecursiveComparison()
                .isNotEqualTo(expectedTool);

        var returnedTool = toolRepository.save(expectedTool);
        assertThat(returnedTool).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedTool);

        var findTool = entityManager.find(Tool.class, returnedTool.getId());
        assertThat(findTool)
                .usingRecursiveComparison()
                .isEqualTo(returnedTool);
    }

    private List<Tool> getDbTools() {
        return IntStream.range(1, 3).boxed()
                .map(id -> entityManager.find(Tool.class, id))
                .toList();
    }
}