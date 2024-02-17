package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocalizedIOServiceImpl implements LocalizedIOService {

    private final IOService ioService;

    private final LocalizedMessagesService messageService;

    @Override
    public void printLine(String code) {
        ioService.printLine(code);
    }

    @Override
    public void printFormattedLine(String code, Object... args) {
        ioService.printFormattedLine(code, args);
    }

    @Override
    public String readStringWithPrompt(String code) {
        return ioService.readStringWithPrompt(code);
    }

    @Override
    public int readIntForRange(int min, int max, String errorMessage) {
        return ioService.readIntForRange(min, max, errorMessage);
    }

    @Override
    public int readIntForRangeWithPrompt(int min, int max, String prompt, String errorMessage) {
        return ioService.readIntForRangeWithPrompt(min, max, prompt, errorMessage);
    }

    @Override
    public void printLocalized(String code) {
        ioService.printLine(messageService.getMessage(code));
    }

    @Override
    public void prinfFormattedLineLocalized(String code, Object... args) {
        ioService.printFormattedLine(messageService.getMessage(code, args));
    }

    @Override
    public String readStringWithPromtLocalized(String promptCode) {
        return ioService.readStringWithPrompt(messageService.getMessage(promptCode));
    }

    @Override
    public String getMessage(String code, Object... args) {
        return messageService.getMessage(code, args);
    }
}
