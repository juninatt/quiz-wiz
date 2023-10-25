package se.juninatt.quizwiz.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import se.juninatt.quizwiz.exception.InvalidInputException;
import se.juninatt.quizwiz.exception.QuizNotFoundException;

/**
 * Global Exception Handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle {@link RuntimeException} throughout the application.
     *
     * @param exception The RuntimeException that was thrown.
     * @return The ModelAndView object directing to the error page and including error details.
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException exception) {
        logger.error("An error occurred: ", exception);

        ModelAndView mav = new ModelAndView("error-page");
        mav.addObject("errorMessage", "An unexpected error has occurred.");
        mav.addObject("errorDetails", exception.getMessage());

        return mav;
    }

    @ExceptionHandler(InvalidInputException.class)
    public ModelAndView handleInvalidInputException(InvalidInputException e) {
        logger.error("An error occurred: ", e);

        ModelAndView mav = new ModelAndView("error-page");
        mav.addObject("errorMessage", "Invalid input provided.");
        mav.addObject("errorDetails", e.getMessage());
        mav.setStatus(InvalidInputException.class.getAnnotation(ResponseStatus.class).value());  // Set HTTP status to 404

        return mav;
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ModelAndView handleQuizNotFoundException(QuizNotFoundException e) {
        logger.error("An error occurred: ", e);

        ModelAndView mav = new ModelAndView("error-page");
        mav.addObject("errorMessage", "The quiz you're looking for could not be found.");
        mav.addObject("errorDetails", e.getMessage());
        mav.setStatus(QuizNotFoundException.class.getAnnotation(ResponseStatus.class).value());  // Set HTTP status to 404

        return mav;
    }
}
