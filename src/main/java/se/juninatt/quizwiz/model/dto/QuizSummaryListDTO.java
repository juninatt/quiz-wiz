package se.juninatt.quizwiz.model.dto;

import java.util.List;

public record QuizSummaryListDTO(
        List<QuizSummaryDTO> quizzes
) { }
