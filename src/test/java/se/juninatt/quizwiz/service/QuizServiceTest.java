package se.juninatt.quizwiz.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.juninatt.quizwiz.TestUtl.TestObjectFactory;
import se.juninatt.quizwiz.config.implementation.TestDatabaseImplementation;
import se.juninatt.quizwiz.exception.InvalidQuizException;
import se.juninatt.quizwiz.exception.InvalidQuizTopicException;
import se.juninatt.quizwiz.exception.QuizNotFoundException;
import se.juninatt.quizwiz.model.dto.QuizCreationDTO;
import se.juninatt.quizwiz.model.dto.QuizSummaryDTO;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.repository.QuestionRepository;
import se.juninatt.quizwiz.repository.QuizRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Quiz Service: ")
public class QuizServiceTest extends TestDatabaseImplementation {
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;


    @Nested
    @DisplayName("Tests for method: GetQuizByTopic(String);")
    class GetQuizByTopicTest {

        @Test
        @DisplayName("Returns quiz with matching topic")
        public void testMethod_returns_quiz_withExpectedTopic() {
            String topic = "Canadian History";
            Quiz quiz = TestObjectFactory.createQuizWithTopic(topic);
            quizRepository.save(quiz);

            // Act
            Quiz retrievedQuiz = quizService.getQuizByTopic(topic);

            // Assert
            assertThat(retrievedQuiz).isNotNull();
            assertThat(retrievedQuiz.getTopic()).isEqualTo(topic);
        }

        @Test
        @DisplayName("Throws exception when input is an empty string")
        public void testMethod_throwsException_whenInputIsBlank() {
            // Arrange
            String emptyString = "";

            // Act and Assert
            assertThatThrownBy(() -> quizService.getQuizByTopic(emptyString))
                    .isInstanceOf(InvalidQuizTopicException.class)
                    .hasMessage("No topic to search for");
        }

        @Test
        @DisplayName("Throws exception when input null")
        public void testMethod_throwsException_whenInputIsNull() {
            // Act and Assert
            assertThatThrownBy(() -> quizService.getQuizByTopic(null))
                    .isInstanceOf(InvalidQuizTopicException.class)
                    .hasMessage("No topic to search for");
        }

        @Test
        @DisplayName("Throws QuizNotFoundException when no matching topic is found")
        public void testMethod_throwsException_whenTopicIsNotFound() {
            // Arrange
            String nonExistentTopic = "Non Existent Topic";

            // Act and Assert
            assertThatThrownBy(() -> quizService.getQuizByTopic(nonExistentTopic))
                    .isInstanceOf(QuizNotFoundException.class)
                    .hasMessage("Quiz with topic: '" + nonExistentTopic + "' could not be found.");
        }
    }


    @Nested
    @DisplayName("Tests for method: createQuiz(QuizCreationDTO);")
    class CreateQuizTest {

        @Test
        @DisplayName("Successfully creates a quiz and returns summary")
        public void testMethod_createsQuiz_ReturnsSummary() {
            // Arrange
            QuizCreationDTO quizCreationDTO = TestObjectFactory.createQuizCreationDTOWithQuestions();

            // Act
            QuizSummaryDTO quizSummaryDTO = quizService.createQuiz(quizCreationDTO);

            // Assert
            assertThat(quizSummaryDTO).isNotNull();
            assertThat(quizSummaryDTO.topic()).isEqualTo(quizCreationDTO.topic());
        }

        @Test
        @DisplayName("Throws exception when quizCreationDTO is null")
        public void testMethod_throwsException_WhenQuizCreationDTOIsNull() {
            // Act and Assert
            assertThatThrownBy(() -> quizService.createQuiz(null))
                    .isInstanceOf(InvalidQuizException.class)
                    .hasMessage("Quiz cannot be null");
        }
    }
}
