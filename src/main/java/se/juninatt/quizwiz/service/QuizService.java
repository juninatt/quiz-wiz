package se.juninatt.quizwiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.juninatt.quizwiz.exception.InvalidQuizException;
import se.juninatt.quizwiz.exception.InvalidQuizTopicException;
import se.juninatt.quizwiz.exception.QuizNotFoundException;
import se.juninatt.quizwiz.exception.QuizPersistenceException;
import se.juninatt.quizwiz.mapper.QuizMapper;
import se.juninatt.quizwiz.model.dto.QuizDTO;
import se.juninatt.quizwiz.model.dto.QuizSummaryDTO;
import se.juninatt.quizwiz.model.dto.QuizSummaryListDTO;
import se.juninatt.quizwiz.model.entity.AnswerOption;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.repository.AnswerOptionRepository;
import se.juninatt.quizwiz.repository.QuestionRepository;
import se.juninatt.quizwiz.repository.QuizRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for handling quiz-related operations.
 * Manages interactions with {@link Quiz}, {@link Question}, and {@link AnswerOption} repositories.
 */
@Service
public class QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;


    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, AnswerOptionRepository answerOptionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerOptionRepository = answerOptionRepository;
    }


    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }


    /**
     * Retrieves a {@link Quiz} based on its topic.
     *
     * @param topic The topic of the quiz to be retrieved.
     * @return The Quiz entity corresponding to the provided topic.
     * @throws InvalidQuizTopicException If the provided topic is null or empty.
     * @throws QuizNotFoundException If no quiz with the given topic is found.
     */
    public Quiz getQuizByTopic(String topic) {
        if (topic == null || topic.isEmpty()) {
            logger.warn("Attempt to search for a quiz with empty or null topic");
            throw new InvalidQuizTopicException("No topic to search for");
        }
        Quiz quiz = quizRepository.findByTopicIgnoreCase(topic)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with topic: '" + topic + "' could not be found."));
        logger.info("Quiz with topic: {} successfully retrieved", topic);
        return quiz;
    }

    /**
     * Creates a {@link QuizSummaryDTO} using the {@link QuizDTO} object.
     *
     * @param quizContent The DTO containing information for quiz creation. Cannot be null.
     * @return A DTO summarizing the created quiz.
     * @throws InvalidQuizException if the provided QuizCreationDTO is null.
     */
    @Transactional
    public QuizSummaryDTO createQuiz(QuizDTO quizContent) {
        if (quizContent == null) {
            logger.error("Attempt to create a quiz with null QuizCreationDTO");
            throw new InvalidQuizException("Quiz cannot be null");
        }
        try {
            Quiz savedQuiz = saveQuiz(quizContent);
            logger.info("Quiz '{}' successfully created", quizContent.topic());
            return createSummary(savedQuiz);
        } catch (RuntimeException exception) {
            logger.error("Failed to create quiz", exception);
            throw new QuizPersistenceException(exception.getMessage(), exception.getCause());
        }
    }

    /**
     * Retrieves a list of {@link Quiz}zes and summarizes them into a {@link QuizSummaryListDTO}.
     *
     * @return A QuizSummaryListDTO containing summaries of all quizzes.
     */
    public QuizSummaryListDTO getQuizSummaryList() {
        List<Quiz> quizzes = quizRepository.findAll();
        List<QuizSummaryDTO> quizSummaries = quizzes.stream()
                .map(this::createSummary)
                .toList();

        return new QuizSummaryListDTO(quizSummaries);
    }

    /**
     * Retrieves a {@link Question} from a specific {@link Quiz} based on the question index.
     *
     * @param questionIndex The index of the question within the quiz.
     * @param quizId The ID of the quiz from which the question is to be retrieved.
     * @return The Question entity corresponding to the question index within the specified quiz.
     * @throws IndexOutOfBoundsException if the question index is out of range (index < 0 || index >= size()).
     */
    public Question getQuestionFromQuiz(int questionIndex, long quizId) {
        List<Question> quizQuestions = questionRepository.findByQuizId(quizId);
        return quizQuestions.get(questionIndex);
    }


    //           <-- PRIVATE METHODS -->

    /**
     * Creates a {@link QuizSummaryDTO} for a given {@link Quiz}.
     *
     * @param quiz The quiz entity to summarize.
     * @return The QuizSummaryDTO with details from the provided quiz entity.
     */
    private QuizSummaryDTO createSummary(Quiz quiz) {
        List<Question> questions = quiz.getQuestions();
        List<String> questionList = questions.stream()
                .map(Question::getQuestionText)
                .collect(Collectors.toList());

        return new QuizSummaryDTO(quiz.getId(), quiz.getTopic(), questionList, getTotalTime(questions), getTotalScore(questions));
    }

    /**
     * Saves a {@link Quiz} entity based on a {@link QuizDTO}.
     *
     * @param quizContent The DTO containing quiz information.
     * @return The saved Quiz entity.
     */
    private Quiz saveQuiz(QuizDTO quizContent) {
        Quiz quiz = QuizMapper.INSTANCE.dtoToEntity(quizContent);
        quizRepository.save(quiz);
        return quiz;
    }

    /**
     * Calculates the total score for a list of {@link Question} objects.
     *
     * @param questions The list of Question entities.
     * @return The total score.
     */
    private int getTotalScore(List<Question> questions) {
        return questions.stream()
                .mapToInt(Question::getPoints)
                .sum();
    }

    /**
     * Calculates the total time of all {@link Question} objects time limits combined  .
     *
     * @param questions The list of Question entities.
     * @return The total time.
     */
    private int getTotalTime(List<Question> questions) {
        return questions.stream()
                .mapToInt(Question::getTimeLimit)
                .sum();
    }

    public Quiz getQuizById(long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow();
    }
}
