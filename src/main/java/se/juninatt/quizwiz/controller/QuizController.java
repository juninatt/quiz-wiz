package se.juninatt.quizwiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.juninatt.quizwiz.mapper.QuizMapper;
import se.juninatt.quizwiz.model.dto.QuestionDTO;
import se.juninatt.quizwiz.model.dto.QuizDTO;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.service.QuizService;

/**
 * The QuizController class handles all web requests for creating, managing,
 * and playing quizzes.
 * It utilizes the {@link QuizService} for interactions with {@link Quiz} data and constructs
 * the model for the view templates.
 */
@Controller
@RequestMapping("/quiz")
public class QuizController {
    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;


    /**
     * Handles the creation of a new {@link Quiz}.
     *
     * @param quizContent The content of the quiz from the request body.
     * @return A ResponseEntity indicating the outcome of the create operation.
     */
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizContent) {
        logger.info("Received request to create new quiz " + quizContent);
        quizService.createQuiz(quizContent);

        return ResponseEntity.ok("Quiz successfully created");
    }

    /**
     * Starts a new quiz game for the given quiz ID and prepares the view.
     *
     * @param quizId the ID of the quiz to start.
     * @param model the {@link Model} to hold attributes for the view template.
     * @return the name of the template to render the quiz game.
     */
    @GetMapping("/start/{quizId}")
    public String startQuizGame(@PathVariable long quizId, Model model) {
        logger.info("Received request to start quiz:" + quizId);
        model.addAttribute("quizId", quizId);
        model.addAttribute("topic", quizService.getQuizById(quizId).getTopic());

        return "quiz-game";
    }

    /**
     * Fetches the next question from the quiz corresponding to the given indices.
     *
     * @param quizId        the ID of the quiz.
     * @param questionIndex the index of the question within the quiz.
     * @return a {@link ResponseEntity} containing the question DTO or an error message.
     */
    @GetMapping("/{quizId}/question/{questionIndex}")
    public ResponseEntity<?> getQuestionFromQuiz(@PathVariable long quizId, @PathVariable int questionIndex) {
        logger.info("Received request for next question nr: " + (questionIndex + 1) + ", from quiz: " + quizId);
        Question question = quizService.getQuestionFromQuiz(questionIndex, quizId);
        QuestionDTO questionContent = QuizMapper.INSTANCE.entityToDTO(question);

        return ResponseEntity.ok(questionContent);
    }
}