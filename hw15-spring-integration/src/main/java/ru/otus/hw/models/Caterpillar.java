package ru.otus.hw.models;

import ru.otus.hw.models.enums.CaterpillarColour;

public record Caterpillar(int weight, int length, int diameter, CaterpillarColour color) {
}

