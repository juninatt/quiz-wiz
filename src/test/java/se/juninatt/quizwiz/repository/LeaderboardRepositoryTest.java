package se.juninatt.quizwiz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.juninatt.quizwiz.TestUtl.TestObjectFactory;
import se.juninatt.quizwiz.config.implementation.TestDatabaseImplementation;
import se.juninatt.quizwiz.model.entity.Leaderboard;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GameSummary Repository:")
public class LeaderboardRepositoryTest extends TestDatabaseImplementation {
    @Autowired
    private LeaderboardRepository leaderboardRepository;


    @Nested
    @DisplayName("FindAllByOrderByTimeUsedPercentageAsc() - Tests")
    class FindAllByOrderByTimeUsedPercentageAscTest {

        @Test
        @DisplayName("Returns non-empty list with expected length")
        public void testMethod_returned_list_hasExpectedLength() {
            // Arrange
            Leaderboard summery1 = TestObjectFactory.createDefaultGameSummary();
            Leaderboard summery2 = TestObjectFactory.createDefaultGameSummary();
            leaderboardRepository.save(summery1);
            leaderboardRepository.save(summery2);

            // Act
            List<Leaderboard> result = leaderboardRepository.findAllByOrderByTimeUsedPercentageAsc();

            // Assert
            assertThat(result).isNotEmpty();
            assertThat(result).hasSize(2);
        }

        @Test
        @DisplayName("Returns correctly sorted list of game summaries")
        public void testMethod_returned_list_isCorrectlySorted() {
            // Arrange
            Leaderboard summary1 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(50);
            Leaderboard summary2 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(30);
            leaderboardRepository.save(summary1);
            leaderboardRepository.save(summary2);

            // Act
            List<Leaderboard> result = leaderboardRepository.findAllByOrderByTimeUsedPercentageAsc();

            // Assert
            assertThat(result).isNotEmpty();
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getTimeUsedPercentage())
                    .isLessThan(result.get(1).getTimeUsedPercentage());
        }

        @Test
        @DisplayName("Returns empty list when no game summaries are found")
        public void testMethod_returned_list_isEmpty() {
            // Act
            List<Leaderboard> result = leaderboardRepository.findAllByOrderByTimeUsedPercentageAsc();

            // Assert
            assertThat(result).isEmpty();
        }
    }
}
