package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.domain.Student;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final LocalizedIOService ioService;

    @Override
    public Student determineCurrentStudent() {
        var firstName = ioService.readStringWithPromtLocalized("Student.first.name");
        var lastName = ioService.readStringWithPromtLocalized("Student.last.name");
        return new Student(firstName, lastName);
    }
}
