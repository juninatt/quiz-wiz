package se.juninatt.quizwiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.juninatt.quizwiz.model.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
