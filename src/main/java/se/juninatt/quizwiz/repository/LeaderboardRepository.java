package se.juninatt.quizwiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.juninatt.quizwiz.model.entity.Leaderboard;

import java.util.List;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    List<Leaderboard> findAllByOrderByTimeUsedPercentageAsc();
    Page<Leaderboard> findAllByOrderByTimeUsedPercentageAsc(Pageable pageable);
}
