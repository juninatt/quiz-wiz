package se.juninatt.quizwiz.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.juninatt.quizwiz.exception.InvalidQuizCreationContentException;
import se.juninatt.quizwiz.exception.InvalidQuizTopicException;
import se.juninatt.quizwiz.exception.QuizNotFoundException;
import se.juninatt.quizwiz.mapper.QuizMapper;
import se.juninatt.quizwiz.model.dto.QuizCreationDTO;
import se.juninatt.quizwiz.model.dto.QuizSummaryDTO;
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
@Component
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;


    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, AnswerOptionRepository answerOptionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerOptionRepository = answerOptionRepository;
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
        if (topic == null || topic.isEmpty())
            throw new InvalidQuizTopicException("No topic to search for");
        return quizRepository.findByTopicIgnoreCase(topic)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with topic: '" + topic + "' could not be found."));
    }

    /**
     * Creates a {@link QuizSummaryDTO} using the {@link QuizCreationDTO} object.
     *
     * @param quizContent The DTO containing information for quiz creation. Cannot be null.
     * @return A DTO summarizing the created quiz.
     * @throws InvalidQuizCreationContentException if the provided QuizCreationDTO is null.
     */
    public QuizSummaryDTO createQuiz(QuizCreationDTO quizContent) {
        if (quizContent == null)
            throw new InvalidQuizCreationContentException("Quiz cannot be null");
        Quiz quiz = saveQuiz(quizContent);
        List<Question> questions = saveQuestions(quizContent, quiz);

        return createSummary(quiz, questions);
    }

    /**
     * Creates a {@link QuizSummaryDTO} based on a {@link Quiz} entity and its associated {@link Question} objects.
     *
     * @param quiz      The Quiz entity.
     * @param questions The list of Question entities associated with the quiz.
     * @return A summary DTO.
     */
    private QuizSummaryDTO createSummary(Quiz quiz, List<Question> questions) {
        List<String> questionList = questions.stream()
                .map(Question::getQuestionText)
                .collect(Collectors.toList());

        return new QuizSummaryDTO(quiz.getTopic(), questionList, getTotalTime(questions), getTotalScore(questions));
    }

    /**
     * Saves {@link Question} associated with a {@link Quiz}.
     *
     * @param quizContent The DTO containing question information.
     * @param quiz        The Quiz entity to associate the questions with.
     * @return The list of saved Question entities.
     */
    private List<Question> saveQuestions(QuizCreationDTO quizContent, Quiz quiz) {
        List<Question> questions = quizContent.questions().stream()
                .map(QuizMapper.INSTANCE::dtoToEntity)
                .toList();

        questions.forEach(question -> {
            question.setQuiz(quiz);
            questionRepository.save(question);
            question.getAnswerOptions().forEach(option -> {
                option.setQuestion(question);
                answerOptionRepository.save(option);
            });
        });
        return questions;
    }

    /**
     * Saves a {@link Quiz} entity based on a {@link QuizCreationDTO}.
     *
     * @param quizContent The DTO containing quiz information.
     * @return The saved Quiz entity.
     */
    private Quiz saveQuiz(QuizCreationDTO quizContent) {
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
}
