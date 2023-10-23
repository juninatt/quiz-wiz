package se.juninatt.quizwiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.juninatt.quizwiz.model.dto.QuizCreationDTO;
import se.juninatt.quizwiz.model.entity.Quiz;

@Mapper
public interface QuizMapper {

    QuizMapper INSTANCE = Mappers.getMapper( QuizMapper.class );

    Quiz quizCreationDTOToQuiz(QuizCreationDTO quizCreationDTO);
}
