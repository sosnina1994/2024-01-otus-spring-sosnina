package ru.otus.hw.service;

public interface LocalizedIOService extends LocalizedMessagesService, IOService {
    void printLocalized(String code);

    void printFormattedLineLocalized(String code, Object... args);

    String readStringWithPromtLocalized(String promptCode);
}
