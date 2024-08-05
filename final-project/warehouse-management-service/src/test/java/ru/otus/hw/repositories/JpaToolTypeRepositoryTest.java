package ru.otus.hw.repositories;

import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import ru.otus.hw.models.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("JPA-Репозиторий для работы с типами инструмента")
@DataJpaTest
class JpaToolTypeRepositoryTest {

    @Autowired
    private ToolTypeRepository toolTypeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Получение списка всех типов инструмента")
    void findAll() {
        var toolsTypes = toolTypeRepository.findAll();
        var expectedToolTypes = getDbToolTypes();

        assertThat(toolsTypes).containsExactlyElementsOf(expectedToolTypes);
        toolsTypes.forEach(System.out::println);
    }

    @Test
    @DisplayName("Сохранение нового типа инструмента")
    void create() {
        var newToolType = new ToolType(0L, "ТЕСТ");
        entityManager.merge(newToolType);

        toolTypeRepository.save(newToolType);

        entityManager.detach(newToolType);

        assertThat(newToolType.getId()).isGreaterThan(0);
        val findToolType = entityManager.find(ToolType.class, newToolType.getId());
        assertThat(findToolType)
                .usingRecursiveComparison()
                .isEqualTo(newToolType);

    }

    private List<ToolType> getDbToolTypes() {
        return IntStream.range(1, 4).boxed()
                .map(id -> entityManager.find(ToolType.class, id))
                .toList();
    }
}