package se.juninatt.quizwiz.TestUtl;


import se.juninatt.quizwiz.model.dto.QuizCreationDTO;
import se.juninatt.quizwiz.model.entity.AnswerOption;
import se.juninatt.quizwiz.model.entity.GameSummary;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;

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
        Question defaultQuestion = createDefaultQuestion();
        Quiz quiz = new Quiz(topic, null);
        //defaultQuestion.setQuiz(); // To avoid circular reference
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

    // QUIZ DTO CREATION


    public static QuizCreationDTO createQuizCreationDTOWithTopic(String topic) {
        return new QuizCreationDTO(topic, null);
    }

    public static QuizCreationDTO createDefaultQuizCreationDTO() {
        return createQuizCreationDTOWithTopic("Default topic");
    }
}
