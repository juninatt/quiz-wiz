package se.juninatt.quizwiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.juninatt.quizwiz.model.entity.GameSummary;

@Repository
public interface GameSummaryRepository extends JpaRepository<GameSummary, Long> {
}
