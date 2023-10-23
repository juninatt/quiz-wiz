package se.juninatt.quizwiz.model.dto;

import se.juninatt.quizwiz.model.entity.Question;

import java.util.List;

public record QuizCreationDTO (
        String topic,
        List<Question> questions
) {

}
