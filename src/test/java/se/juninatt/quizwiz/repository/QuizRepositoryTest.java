package se.juninatt.quizwiz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import se.juninatt.quizwiz.config.TestDataSourceConfig;
import se.juninatt.quizwiz.model.entity.Quiz;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("Quiz Repository:")
@Import(TestDataSourceConfig.class)
public class QuizRepositoryTest {
    @Autowired
    private QuizRepository quizRepository;

    @Nested
    @DisplayName("FindByTopicIgnoreCase(String) - Tests")
    class FindByTopicIgnoreCaseTest {

        @Test
        @DisplayName("Returns optional containing quiz with expected topic")
        public void testMethod_returns_quiz_withExpectedTopic() {
            // Arrange
            String topic = "Example Topic";
            Quiz quiz = new Quiz(topic, new ArrayList<>());
            quizRepository.save(quiz);

            // Act
            Optional<Quiz> foundQuiz = quizRepository.findByTopicIgnoreCase(topic);

            // Assert
            assertThat(foundQuiz).isPresent();
            assertThat(foundQuiz.get().getTopic()).isEqualTo(topic);
        }

        @Test
        @DisplayName("Returns empty optional when no matching topic is found")
        public void testMethod_returns_emptyOptional_whenNoQuizExists() {
            // Arrange
            String nonExistingTopic = "Non Existing Topic";

            // Act
            Optional<Quiz> foundQuiz = quizRepository.findByTopicIgnoreCase(nonExistingTopic);

            // Assert
            assertThat(foundQuiz).isEmpty();
        }

        @Test
        @DisplayName("Returns empty optional when topic input is null")
        public void testMethod_returns_emptyOptional_whenInputIsNull() {
            // Act
            Optional<Quiz> foundQuiz = quizRepository.findByTopicIgnoreCase(null);

            // Assert
            assertThat(foundQuiz).isEmpty();
        }

        @Test
        @DisplayName("Test findByTopicIgnoreCase with empty topic")
        public void testFindByEmptyTopicIgnoreCase() {
            // Act
            Optional<Quiz> foundQuiz = quizRepository.findByTopicIgnoreCase("");

            // Assert
            assertThat(foundQuiz).isEmpty();
        }
    }
}
