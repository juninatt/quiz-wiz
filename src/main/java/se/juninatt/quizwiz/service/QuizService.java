package se.juninatt.quizwiz.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.juninatt.quizwiz.exception.InvalidInputException;
import se.juninatt.quizwiz.exception.QuizNotFoundException;
import se.juninatt.quizwiz.mapper.QuizMapper;
import se.juninatt.quizwiz.model.dto.QuizCreationDTO;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.repository.QuizRepository;

/**
 * Service class responsible for handling quiz-related operations.
 * This service manages interactions with {@link Quiz} repository.
 */
@Service
@Component
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz createQuiz(QuizCreationDTO quizContent) {
        return quizRepository.save(QuizMapper.INSTANCE.quizCreationDTOToQuiz(quizContent));
    }

    /**
     * Retrieves a {@link Quiz} based on its topic.
     *
     * @param topic The topic of the quiz to be retrieved.
     * @return The Quiz entity corresponding to the provided topic.
     * @throws InvalidInputException If the provided topic is null or empty.
     * @throws QuizNotFoundException If no quiz with the given topic is found.
     */
    public Quiz getQuizByTopic(String topic) {
        if (topic == null || topic.isEmpty())
            throw new InvalidInputException("No topic to search for");
        return quizRepository.findByTopicIgnoreCase(topic)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with topic: '" + topic + "' could not be found."));
    }
}
