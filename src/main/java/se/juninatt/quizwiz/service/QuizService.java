package se.juninatt.quizwiz.service;

import org.springframework.stereotype.Service;
import se.juninatt.quizwiz.model.entity.GameSummary;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.repository.GameSummaryRepository;
import se.juninatt.quizwiz.repository.QuestionRepository;

/**
 * Service class responsible for handling quiz-related operations.
 * This service manages interactions with {@link Quiz}, {@link Question}, and {@link GameSummary} repositories.
 */
@Service
public class QuizService {


    private final QuestionRepository questionRepository;
    private final GameSummaryRepository gameSummaryRepository;

    public QuizService(QuestionRepository questionRepository, GameSummaryRepository gameSummaryRepository) {
        this.questionRepository = questionRepository;
        this.gameSummaryRepository = gameSummaryRepository;
    }
}
