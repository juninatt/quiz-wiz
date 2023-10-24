package se.juninatt.quizwiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {

    @GetMapping("/quizwiz")
    public String showQuiz() {
        return "welcome";
    }
}