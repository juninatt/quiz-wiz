package se.juninatt.quizwiz.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;
import se.juninatt.quizwiz.exception.handler.GlobalExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("GlobalExceptionHandler:")
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


    @Test
    @DisplayName("Handles InvalidInputException")
    public void testHandle_InvalidInputException() {
        InvalidQuizTopicException exception = new InvalidQuizTopicException("Invalid input test");
        ModelAndView mav = globalExceptionHandler.handleInvalidInputException(exception);

        assertEquals("error-page", mav.getViewName());
        assertEquals("Invalid input provided.", mav.getModel().get("errorMessage"));
        assertEquals("Invalid input test", mav.getModel().get("errorDetails"));
    }

    @Test
    @DisplayName("Handle QuizNotFoundException")
    public void testHandleQuizNotFoundException() {
        QuizNotFoundException exception = new QuizNotFoundException("Quiz not found test");
        ModelAndView mav = globalExceptionHandler.handleQuizNotFoundException(exception);

        assertEquals("error-page", mav.getViewName());
        assertEquals("The quiz you're looking for could not be found.", mav.getModel().get("errorMessage"));
        assertEquals("Quiz not found test", mav.getModel().get("errorDetails"));
    }

    @Test
    @DisplayName("Handles RuntimeException")
    public void testHandle_RuntimeException() {
        RuntimeException exception = new RuntimeException("Runtime exception test");
        ModelAndView mav = globalExceptionHandler.handleRuntimeException(exception);

        assertEquals("error-page", mav.getViewName());
        assertEquals("An unexpected error has occurred.", mav.getModel().get("errorMessage"));
        assertEquals("Runtime exception test", mav.getModel().get("errorDetails"));
    }
}
