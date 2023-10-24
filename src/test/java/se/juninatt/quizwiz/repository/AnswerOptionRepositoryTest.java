package se.juninatt.quizwiz.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import se.juninatt.quizwiz.config.TestDatabaseImplementation;

@DisplayName("AnswerOptions Repository:")
public class AnswerOptionRepositoryTest extends TestDatabaseImplementation {
    @Autowired
    AnswerOptionRepository answerOptionRepository;
}