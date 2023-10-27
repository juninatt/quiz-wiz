package se.juninatt.quizwiz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.juninatt.quizwiz.TestUtl.TestObjectFactory;
import se.juninatt.quizwiz.config.implementation.TestDatabaseImplementation;
import se.juninatt.quizwiz.model.entity.Quiz;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Quiz Repository:")
public class QuizRepositoryTest extends TestDatabaseImplementation {
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
            Quiz quiz = TestObjectFactory.createQuizWithTopic(topic);
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
        @DisplayName("Returns empty optional when topic input is blank")
        public void testMethod_returns_emptyOptional_whenInputIsBlank() {
            // Act
            Optional<Quiz> foundQuiz = quizRepository.findByTopicIgnoreCase("");

            // Assert
            assertThat(foundQuiz).isEmpty();
        }
    }
}
