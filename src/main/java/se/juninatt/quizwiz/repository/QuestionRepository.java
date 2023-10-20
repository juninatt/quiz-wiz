package se.juninatt.quizwiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.juninatt.quizwiz.model.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
