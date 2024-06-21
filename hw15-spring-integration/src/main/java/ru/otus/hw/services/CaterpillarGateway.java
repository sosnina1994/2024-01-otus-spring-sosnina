package ru.otus.hw.services;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.Gateway;
import ru.otus.hw.models.Cocoon;
import ru.otus.hw.models.Caterpillar;

import java.util.List;

@MessagingGateway
public interface CaterpillarGateway {

    @Gateway(requestChannel  = "caterpillarChannel", replyChannel = "cocoonChannel")
    List<Cocoon> process(List<Caterpillar> caterpillars);

}
