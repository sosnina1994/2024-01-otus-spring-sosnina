package ru.otus.hw.commands;

import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ConsoleCommands {

    @ShellMethod(value = "Open h2 console", key = "oc")
    public String openConsole() {
        try {
            Console.main();
            return "Успешно запущена консоль БД!";
        } catch (Exception ex) {
            return String.format("При открытии консоли БД произошла ошибка %s!",
                    ex.getLocalizedMessage());
        }
    }
}
