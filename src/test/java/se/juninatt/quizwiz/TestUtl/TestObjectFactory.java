package se.juninatt.quizwiz.TestUtl;


import se.juninatt.quizwiz.model.entity.GameSummary;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;

import java.util.Arrays;

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
        return new GameSummary(100, 75, 90.5, "2023-10-19");
    }

    public static GameSummary createGameSummaryWithTimeUsedPercentage(double timeUsedPercentage) {
        return new GameSummary(100, timeUsedPercentage, 90.5, "2023-10-19");
    }

    public static GameSummary createGameSummaryWithCompletionPercentage(double completionPercentage) {
        return new GameSummary(100, 50.0, completionPercentage, "2023-10-19");
    }

    public static GameSummary createGameSummaryWithTotalScore(int totalScore) {
        return new GameSummary(totalScore, 50.0, 90.5, "2023-10-19");
    }

    // QUESTION OBJECT CREATION

    /**
     * Creates a default {@link Question} object.
     *
     * @return A default Question object.
     */
    public static Question createDefaultQuestion() {
        return new Question("Sample Question?",
                Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4"),
                1, 30, 10);
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
        defaultQuestion.setQuiz(null); // To avoid circular reference
        return new Quiz(topic, Arrays.asList(defaultQuestion));
    }
}
