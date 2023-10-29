package se.juninatt.quizwiz.TestUtl;


import se.juninatt.quizwiz.model.dto.AnswerOptionCreationDTO;
import se.juninatt.quizwiz.model.dto.QuestionCreationDTO;
import se.juninatt.quizwiz.model.dto.QuizCreationDTO;
import se.juninatt.quizwiz.model.entity.AnswerOption;
import se.juninatt.quizwiz.model.entity.GameSummary;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;

import java.util.Arrays;
import java.util.List;

/**
 * Factory class for creating default test objects of various entity classes.
 */
public class TestObjectFactory {


    // GAME SUMMARY OBJECT CREATION

    /**
     * Creates a default {@link GameSummary} object.
     *
     * @return A default GameSummary object.
     */
    public static GameSummary createDefaultGameSummary() {
        return new GameSummary(100, 75, 90, "2023-10-19");
    }

    public static GameSummary createGameSummaryWithTimeUsedPercentage(int timeUsedPercentage) {
        return new GameSummary(100, timeUsedPercentage, 90, "2023-10-19");
    }

    public static GameSummary createGameSummaryWithCompletionPercentage(int completionPercentage) {
        return new GameSummary(100, 50, completionPercentage, "2023-10-19");
    }

    public static GameSummary createGameSummaryWithTotalScore(int totalScore) {
        return new GameSummary(totalScore, 50, 90, "2023-10-19");
    }

    // QUESTION OBJECT CREATION

    /**
     * Creates a default {@link Question} object.
     *
     * @return A default Question object.
     */
    public static Question createDefaultQuestion() {
        return new Question("Sample Question?",
                createDefaultAnswerOptions(), 30, 10);
    }

    // QUIZ OBJECT CREATION

    /**
     * Creates a default {@link Quiz} object containing a default question.
     *
     * @return A default Quiz object.
     */
    public static Quiz createDefaultQuiz() {
        return createQuizWithTopic("Western Movies");
    }

    public static Quiz createQuizWithTopic(String topic) {
        Quiz quiz = new Quiz(topic, null);
        return quiz;
    }

    public static List<AnswerOption> createDefaultAnswerOptions() {
        return List.of(
                new AnswerOption("Yes", false),
                new AnswerOption("No", false),
                new AnswerOption("Maybe", false),
                new AnswerOption("I don´t know", true)
        );
    }

    public static List<AnswerOptionCreationDTO> createDefaultAnswerOptionDTOList() {
        return List.of(
                new AnswerOptionCreationDTO("Yes", false),
                new AnswerOptionCreationDTO("No", false),
                new AnswerOptionCreationDTO("Maybe", false),
                new AnswerOptionCreationDTO("I don´t know", true)
        );
    }

    // QUIZ DTO CREATION


    public static QuizCreationDTO createQuizCreationDTOWithTopicAndQuestions(String topic, List<QuestionCreationDTO> questions) {
        return new QuizCreationDTO(topic, questions);
    }

    public static QuizCreationDTO createDefaultQuizCreationDTO() {
        return createQuizCreationDTOWithTopicAndQuestions("Default topic", null);
    }

    public static QuizCreationDTO createQuizCreationDTOWithQuestions() {
        QuestionCreationDTO question1 = new QuestionCreationDTO("What is the capital of Sweden?", 1, 1, createDefaultAnswerOptionDTOList());
        QuestionCreationDTO question2 = new QuestionCreationDTO("What is the square root of 121?", 1, 1, createDefaultAnswerOptionDTOList());

        return new QuizCreationDTO("Quiz with questions", Arrays.asList(question1, question2));
    }
}
