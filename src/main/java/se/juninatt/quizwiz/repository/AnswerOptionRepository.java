package se.juninatt.quizwiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.juninatt.quizwiz.model.entity.AnswerOption;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
}
