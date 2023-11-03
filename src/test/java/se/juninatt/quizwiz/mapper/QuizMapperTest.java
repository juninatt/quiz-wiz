package se.juninatt.quizwiz.mapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.juninatt.quizwiz.TestUtl.TestObjectFactory;
import se.juninatt.quizwiz.model.dto.QuizDTO;
import se.juninatt.quizwiz.model.entity.Quiz;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@DisplayName("Quiz Mapper:")
public class QuizMapperTest {

    @Test
    void mapFromQuizCreationDTOToQuiz() {
        QuizDTO quizDTO = TestObjectFactory.createDefaultQuizCreationDTO();
        Quiz result = QuizMapper.INSTANCE.dtoToEntity(quizDTO);

        assertThat(result.getTopic()).isEqualTo(quizDTO.topic());
    }
}
