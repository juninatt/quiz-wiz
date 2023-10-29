package se.juninatt.quizwiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.juninatt.quizwiz.model.dto.AnswerOptionCreationDTO;
import se.juninatt.quizwiz.model.dto.QuestionCreationDTO;
import se.juninatt.quizwiz.model.dto.QuizCreationDTO;
import se.juninatt.quizwiz.model.entity.AnswerOption;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;

/**
 * Interface responsible for mapping between DTO and Entity objects related to quizzes.
 */
@Mapper
public interface QuizMapper {

    /**
     * Singleton instance of the QuizMapper interface.
     */
    QuizMapper INSTANCE = Mappers.getMapper( QuizMapper.class );

    /**
     * Maps a {@link QuizCreationDTO} object to a {@link Quiz} entity.
     *
     * @param quizCreationDTO The DTO object containing quiz creation information.
     * @return The mapped Quiz entity.
     */
    Quiz dtoToEntity(QuizCreationDTO quizCreationDTO);

    /**
     * Maps an {@link AnswerOptionCreationDTO} object to an {@link AnswerOption} entity.
     *
     * @param answerOptionDTO The DTO object containing answer option creation information.
     * @return The mapped AnswerOption entity.
     */
    AnswerOption dtoToEntity(AnswerOptionCreationDTO answerOptionDTO);

    Question dtoToEntity(QuestionCreationDTO questionDTO);
}
