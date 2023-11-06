package se.juninatt.quizwiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.juninatt.quizwiz.model.dto.AnswerOptionDTO;
import se.juninatt.quizwiz.model.dto.QuestionDTO;
import se.juninatt.quizwiz.model.dto.QuizDTO;
import se.juninatt.quizwiz.model.entity.AnswerOption;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;

/**
 * Interface for mapping Data Transfer Objects (DTOs) to Entities and vice versa in the context of quizzes.
 * This is essential for transferring data between different architectural layers.
 */
@Mapper
public interface QuizMapper {

    /**
     * Singleton instance of the QuizMapper interface.
     */
    QuizMapper INSTANCE = Mappers.getMapper( QuizMapper.class );

    /**
     * Maps a {@link QuizDTO} object to a {@link Quiz} entity.
     *
     * @param quizDTO The DTO object containing quiz creation information.
     * @return The mapped Quiz entity.
     */
    Quiz dtoToEntity(QuizDTO quizDTO);

    /**
     * Maps an {@link AnswerOptionDTO} object to an {@link AnswerOption} entity.
     *
     * @param answerOptionDTO The DTO object containing answer option creation information.
     * @return The mapped AnswerOption entity.
     */
    AnswerOption dtoToEntity(AnswerOptionDTO answerOptionDTO);

    /**
     * Converts a {@link QuestionDTO} to a {@link Question} entity.
     *
     * @param questionDTO the DTO containing question data.
     * @return a Question entity corresponding to the provided DTO.
     */
    Question dtoToEntity(QuestionDTO questionDTO);

    /**
     * Converts a {@link Quiz} entity to a {@link QuizDTO}.
     *
     * @param quizEntity The entity containing quiz data.
     * @return A QuizDTO corresponding to the provided entity.
     */
    QuizDTO entityToDTO(Quiz quizEntity);

    /**
     * Converts a {@link Question} entity to a {@link QuestionDTO}.
     *
     * @param questionEntity  The entity containing question data.
     * @return A QuestionDTO corresponding to the provided entity.
     */
    QuestionDTO entityToDTO(Question questionEntity);
}
