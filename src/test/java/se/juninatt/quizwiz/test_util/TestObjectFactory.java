package se.juninatt.quizwiz.test_util;


import se.juninatt.quizwiz.model.dto.AnswerOptionDTO;
import se.juninatt.quizwiz.model.dto.QuestionDTO;
import se.juninatt.quizwiz.model.dto.QuizDTO;
import se.juninatt.quizwiz.model.entity.AnswerOption;
import se.juninatt.quizwiz.model.entity.Leaderboard;
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
     * Creates a default {@link Leaderboard} object.
     *
     * @return A default GameSummary object.
     */
    public static Leaderboard createDefaultGameSummary() {
        return new Leaderboard("Sigmund", "5/10", 75, 90, "2023-10-19");
    }

    public static Leaderboard createGameSummaryWithTimeUsedPercentage(int timeUsedPercentage) {
        return new Leaderboard("Göran", "5/10", timeUsedPercentage, 90, "2023-10-19");
    }

    public static Leaderboard createGameSummaryWithTotalScorePercentage(int totalScorePercentage) {
        return new Leaderboard("Karen", "10/20", 50, totalScorePercentage, "2023-10-19");
    }

    public static Leaderboard createGameSummaryWithScore(String score) {
        return new Leaderboard("Safari", score, 50, 90, "2023-10-19");
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
        return new Quiz(topic, List.of(new Question()));
    }

    public static List<AnswerOption> createDefaultAnswerOptions() {
        return List.of(
                new AnswerOption("Yes", 0),
                new AnswerOption("No", 0),
                new AnswerOption("Maybe", 0),
                new AnswerOption("I don´t know", 1)
        );
    }

    public static List<AnswerOptionDTO> createDefaultAnswerOptionDTOList() {
        return List.of(
                new AnswerOptionDTO("Yes", 0),
                new AnswerOptionDTO("No", 0),
                new AnswerOptionDTO("Maybe", 0),
                new AnswerOptionDTO("I don´t know", 1)
        );
    }

    // QUIZ DTO CREATION


    public static QuizDTO createQuizCreationDTOWithTopicAndQuestions(String topic, List<QuestionDTO> questions) {
        return new QuizDTO(topic, questions);
    }

    public static QuizDTO createDefaultQuizCreationDTO() {
        return createQuizCreationDTOWithTopicAndQuestions("Default topic", null);
    }

    public static QuizDTO createQuizCreationDTOWithQuestions() {
        QuestionDTO question1 = new QuestionDTO("What is the capital of Sweden?", 1, 1, createDefaultAnswerOptionDTOList());
        QuestionDTO question2 = new QuestionDTO("What is the square root of 121?", 1, 1, createDefaultAnswerOptionDTOList());

        return new QuizDTO("Quiz with questions", Arrays.asList(question1, question2));
    }
}
