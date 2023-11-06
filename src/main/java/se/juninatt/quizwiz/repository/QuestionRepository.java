package se.juninatt.quizwiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.juninatt.quizwiz.model.entity.Question;

import java.util.List;

/**
 * Repository interface for {@link Question} entity.
 * This interface is used for accessing the database layer to perform CRUD operations on questions.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    /**
     * Finds all {@link Question} entities by their associated quiz ID.
     *
     * @param quizId the ID of the quiz for which to retrieve the questions.
     * @return a list of {@link Question} entities. If no questions are found, this method returns an empty list.
     */
    List<Question> findByQuizId(long quizId);
}
