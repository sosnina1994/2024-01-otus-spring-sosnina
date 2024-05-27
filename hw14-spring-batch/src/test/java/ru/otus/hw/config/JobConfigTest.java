package ru.otus.hw.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw.models.mongo.MongoAuthor;
import ru.otus.hw.models.mongo.MongoBook;
import ru.otus.hw.models.mongo.MongoComment;
import ru.otus.hw.models.mongo.MongoGenre;
import ru.otus.hw.repositories.mongo.MongoAuthorRepository;
import ru.otus.hw.repositories.mongo.MongoBookRepository;
import ru.otus.hw.repositories.mongo.MongoCommentRepository;
import ru.otus.hw.repositories.mongo.MongoGenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.hw.config.JobConfig.IMPORT_FROM_DATABASE_JOB_NAME;
import static ru.otus.hw.config.JobConfig.MILLIS_PARAM_NAME;

@SpringBootTest
@AutoConfigureDataMongo
@SpringBatchTest
class JobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private MongoBookRepository bookRepository;

    @Autowired
    private MongoGenreRepository genreRepository;

    @Autowired
    private MongoAuthorRepository authorRepository;

    @Autowired
    private MongoCommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    @DisplayName("Тест выгрузки записей в MongoDB")
    void importFromDatabaseJob() throws Exception {
        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(IMPORT_FROM_DATABASE_JOB_NAME);

        JobParameters parameters = new JobParametersBuilder()
                .addLong(MILLIS_PARAM_NAME, System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(parameters);
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        assertThat(bookRepository.findAll())
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(getExampleBookList());

        assertThat(commentRepository.findAll())
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(getExampleCommentList());

        assertThat(authorRepository.findAll())
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(getExampleAuthorList());

        assertThat(genreRepository.findAll())
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(getExampleGenreList());
    }

    private List<MongoAuthor> getExampleAuthorList() {
        return List.of(new MongoAuthor("1", "Author_1"),
                new MongoAuthor("2", "Author_2"),
                new MongoAuthor("3", "Author_3"));
    }
    private List<MongoGenre> getExampleGenreList() {
        return List.of(new MongoGenre("1", "Genre_1"),
                new MongoGenre("2", "Genre_2"),
                new MongoGenre("3", "Genre_3"));
    }
    private List<MongoBook> getExampleBookList() {
        List<MongoGenre> genreList = getExampleGenreList();
        List<MongoAuthor> authorList = getExampleAuthorList();

        return List.of(new MongoBook("1", "BookTitle_1", authorList.get(0), genreList.get(0)),
                new MongoBook("2", "BookTitle_2", authorList.get(1), genreList.get(1)),
                new MongoBook("3", "BookTitle_3", authorList.get(2), genreList.get(2)));
    }

    private List<MongoComment> getExampleCommentList() {
        List<MongoBook> bookList = getExampleBookList();

        return List.of(new MongoComment("1", "Book_1_Comment_1", bookList.get(0)),
                new MongoComment("2", "Book_1_Comment_2", bookList.get(0)),
                new MongoComment("3", "Book_1_Comment_3", bookList.get(0)),
                new MongoComment("4", "Book_2_Comment_1", bookList.get(1)),
                new MongoComment("5", "Book_3_Comment_1", bookList.get(2)));
    }
}
