package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.models.Caterpillar;
import ru.otus.hw.models.enums.CaterpillarColour;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class CaterpillarService {

    private final CaterpillarGateway caterpillarGateway;

    private final RandomGenerator randomGenerator = RandomGenerator.getDefault();


    public void startGenerateCaterpillarLoop() {
        ForkJoinPool.commonPool().execute(() -> {
            List<Caterpillar> caterpillars = generateCaterpillars();
            caterpillarGateway.process(caterpillars);
        });
    }

    private List<Caterpillar> generateCaterpillars() {
        int caterpillarCount = randomGenerator.nextInt(10, 100);
        List<Caterpillar> caterpillars = new ArrayList<>(caterpillarCount);
        for (int i = 0; i < caterpillarCount; i++) {
            caterpillars.add(generateCaterpillar());
        }
        return caterpillars;
    }

    private Caterpillar generateCaterpillar() {
        int weight = randomGenerator.nextInt(5, 10);
        int length = randomGenerator.nextInt(20, 30);
        int diameter = randomGenerator.nextInt(20, 30);
        CaterpillarColour color = CaterpillarColour.values()[new Random().nextInt(CaterpillarColour.values().length)];
        return new Caterpillar(weight, length, diameter, color);
    }

}
