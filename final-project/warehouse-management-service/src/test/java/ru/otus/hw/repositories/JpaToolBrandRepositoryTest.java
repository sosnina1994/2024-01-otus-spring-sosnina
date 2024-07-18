package ru.otus.hw.repositories;

import lombok.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import ru.otus.hw.models.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;


@DisplayName("JPA-Репозиторий для работы с производителями инструмента")
@DataJpaTest
class JpaToolBrandRepositoryTest {

    @Autowired
    private ToolBrandRepository toolBrandRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Получение списка всех производителей инструмента")
    void findAll() {
        var brands = toolBrandRepository.findAll();
        var expectedBrands = getEntities();

        assertThat(brands).containsExactlyElementsOf(expectedBrands);
        brands.forEach(System.out::println);
    }

    @Test
    @DisplayName("Сохранение нового производителя инструмента")
    void create() {
        var brand = new ToolBrand(0L, "ТЕСТ");
        entityManager.merge(brand);

        toolBrandRepository.save(brand);

        entityManager.detach(brand);

        assertThat(brand.getId()).isGreaterThan(0);
        val findToolBrand = entityManager.find(ToolBrand.class, brand.getId());
        assertThat(findToolBrand)
                .usingRecursiveComparison()
                .isEqualTo(brand);

    }

    private List<ToolBrand> getEntities() {
        return IntStream.range(1, 4).boxed()
                .map(id -> entityManager.find(ToolBrand.class, id))
                .toList();
    }
}