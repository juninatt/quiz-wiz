package se.juninatt.quizwiz.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import se.juninatt.quizwiz.test_util.TestObjectFactory;
import se.juninatt.quizwiz.tests_config.implementation.TestDatabaseImplementation;
import se.juninatt.quizwiz.exception.InvalidQuizException;
import se.juninatt.quizwiz.exception.InvalidQuizTopicException;
import se.juninatt.quizwiz.exception.QuizNotFoundException;
import se.juninatt.quizwiz.model.dto.QuizDTO;
import se.juninatt.quizwiz.model.dto.QuizSummaryDTO;
import se.juninatt.quizwiz.model.dto.QuizSummaryListDTO;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.repository.QuestionRepository;
import se.juninatt.quizwiz.repository.QuizRepository;

import java.util.List;

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

    @AfterEach
    void cleanUp() {
        questionRepository.deleteAll();
        questionRepository.deleteAll();
    }

    @Nested
    @DisplayName("Tests for method: getAllQuizzes()")
    class GetAllQuizzesTest {

        @Disabled
        @Test
        @DisplayName("Successfully retrieves all quizzes when there are multiple quizzes")
        void testGetAllQuizzes_WithMultipleQuizzes() {
            // Arrange - Add multiple quizzes
            Quiz quiz1 = TestObjectFactory.createQuizWithTopic("Topic One");
            Quiz quiz2 = TestObjectFactory.createQuizWithTopic("Topic Two");
            quizRepository.save(quiz1);
            quizRepository.save(quiz2);

            // Act
            List<Quiz> allQuizzes = quizService.getAllQuizzes();

            // Assert
            assertThat(allQuizzes).isNotNull();
            assertThat(allQuizzes).hasSize(2);
            assertThat(allQuizzes).contains(quiz1, quiz2);
        }

        @Disabled
        @Test
        @DisplayName("Successfully retrieves an empty list when there are no quizzes")
        void testGetAllQuizzes_EmptyRepository() {
            // Act
            List<Quiz> allQuizzes = quizService.getAllQuizzes();

            // Assert
            assertThat(allQuizzes).isNotNull();
            assertThat(allQuizzes).isEmpty();
        }

    }


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
            QuizDTO quizDTO = TestObjectFactory.createQuizCreationDTOWithQuestions();

            // Act
            QuizSummaryDTO quizSummaryDTO = quizService.createQuiz(quizDTO);

            // Assert
            assertThat(quizSummaryDTO).isNotNull();
            assertThat(quizSummaryDTO.topic()).isEqualTo(quizDTO.topic());
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

    @Nested
    @DisplayName("Tests for method: getQuizSummaryList()")
    class GetQuizSummaryListTest {

        @Disabled
        @Test
        @DisplayName("Successfully retrieves a non-empty list of quiz summaries")
        void testGetQuizSummaryList_NonEmpty() {
            // Arrange
            Quiz quiz = TestObjectFactory.createDefaultQuiz();
            quizRepository.save(quiz);

            // Act
            QuizSummaryListDTO result = quizService.getQuizSummaryList();

            // Assert
            assertThat(result.quizzes()).isNotEmpty();
            assertThat(result.quizzes().get(0).id()).isEqualTo(quiz.getId());
        }

        @Disabled
        @Test
        @DisplayName("Returns an empty list when no quizzes are present")
        void testGetQuizSummaryList_Empty() {
            // Act
            QuizSummaryListDTO result = quizService.getQuizSummaryList();

            // Assert
            assertThat(result.quizzes()).isEmpty();
        }
    }
}
