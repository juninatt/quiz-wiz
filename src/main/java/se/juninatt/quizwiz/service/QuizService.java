package se.juninatt.quizwiz.service;

import org.springframework.stereotype.Service;
import se.juninatt.quizwiz.model.entity.GameSummary;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.repository.GameSummaryRepository;
import se.juninatt.quizwiz.repository.QuestionRepository;
import se.juninatt.quizwiz.repository.QuizRepository;

/**
 * Service class responsible for handling quiz-related operations.
 * This service manages interactions with {@link Quiz}, {@link Question}, and {@link GameSummary} repositories.
 */
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final GameSummaryRepository gameSummaryRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, GameSummaryRepository gameSummaryRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.gameSummaryRepository = gameSummaryRepository;
    }
}
