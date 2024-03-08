package ru.otus.hw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.hw.converter.BeanToStringConverter;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;
import ru.otus.hw.service.ResultService;
import ru.otus.hw.service.StudentService;
import ru.otus.hw.service.TestService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final TestService testService;

    private final StudentService studentService;

    private final ResultService resultService;

    private Student student;

    private TestResult testResult;

    @ShellMethod(value = "Start all process for testing command", key = {"s", "start"})
    public void startTesting() {
        login();
        test();
        results();
    }

    @ShellMethod(value = "Login as student command", key = {"log", "l"})
    public String login() {
        student = studentService.determineCurrentStudent();
        testResult = null;
        return BeanToStringConverter.convertStudent(student);
    }

    @ShellMethod(value = "Start test command", key = {"testing", "t"})
    @ShellMethodAvailability(value = "isStudentDetermine")
    public String test() {
        testResult = testService.executeTestFor(student);
        return BeanToStringConverter.convertTestResult(testResult);
    }

    private Availability isStudentDetermine() {
        return student == null ?
                Availability.unavailable("Login as student first") : Availability.available();
    }

    @ShellMethod(value = "Show results testing command", key = {"r", "res"})
    @ShellMethodAvailability(value = "isTestResultsExist")
    public void results() {
        resultService.showResult(testResult);
    }

    private Availability isTestResultsExist() {
        return testResult == null ?
                Availability.unavailable("Test first") : Availability.available();
    }
}
