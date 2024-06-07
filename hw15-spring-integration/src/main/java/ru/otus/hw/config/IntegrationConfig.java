package ru.otus.hw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import ru.otus.hw.models.Butterfly;
import ru.otus.hw.models.Caterpillar;
import ru.otus.hw.models.Cocoon;
import ru.otus.hw.models.enums.ButterflyColour;
import ru.otus.hw.models.enums.CocoonColour;

import java.util.Random;
import java.util.random.RandomGenerator;

@Configuration
public class IntegrationConfig {

    private final RandomGenerator randomGenerator = RandomGenerator.getDefault();

    @Bean
    public MessageChannelSpec<?, ?> caterpillarChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> cocoonChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }

    @Bean
    public IntegrationFlow faunaFlow() {
        return IntegrationFlow.from(caterpillarChannel())
                .split()
                .<Caterpillar, Cocoon>transform(caterpillar -> new Cocoon(
                        caterpillar.weight() * 2,
                        caterpillar.length(),
                        caterpillar.diameter(),
                        CocoonColour.values()[new Random().nextInt(CocoonColour.values().length)])
                )
                .<Cocoon, Butterfly>transform(cocoon -> new Butterfly(
                        randomGenerator.nextInt(1, 10),
                        randomGenerator.nextInt(10, 100),
                        ButterflyColour.values()[new Random().nextInt(ButterflyColour.values().length)])
                )
                .<Butterfly>log(LoggingHandler.Level.INFO, "butterfly fly", Message::getPayload)
                .aggregate()
                .channel(cocoonChannel())
                .get();
    }
}
