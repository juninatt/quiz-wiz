package se.juninatt.quizwiz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import se.juninatt.quizwiz.TestUtl.TestObjectFactory;
import se.juninatt.quizwiz.config.TestDataSourceConfig;
import se.juninatt.quizwiz.model.entity.GameSummary;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Import(TestDataSourceConfig.class)
@DisplayName("GameSummary Repository:")
public class GameSummaryRepositoryTest {

    @Autowired
    private GameSummaryRepository gameSummaryRepository;

    @Nested
    @DisplayName("FindAllByOrderByTimeUsedPercentageAsc() - Tests")
    class FindAllByOrderByTimeUsedPercentageAscTest {

        @Test
        @DisplayName("Returns non-empty list with expected length")
        public void testMethod_returned_list_hasExpectedLength() {
            // Arrange
            GameSummary summery1 = TestObjectFactory.createDefaultGameSummary();
            GameSummary summery2 = TestObjectFactory.createDefaultGameSummary();
            gameSummaryRepository.save(summery1);
            gameSummaryRepository.save(summery2);

            // Act
            List<GameSummary> result = gameSummaryRepository.findAllByOrderByTimeUsedPercentageAsc();

            // Assert
            assertThat(result).isNotEmpty();
            assertThat(result).hasSize(2);
        }

        @Test
        @DisplayName("Returns correctly sorted list of game summaries")
        public void testMethod_returned_list_isCorrectlySorted() {
            // Arrange
            GameSummary summary1 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(50.0);
            GameSummary summary2 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(30.0);
            gameSummaryRepository.save(summary1);
            gameSummaryRepository.save(summary2);

            // Act
            List<GameSummary> result = gameSummaryRepository.findAllByOrderByTimeUsedPercentageAsc();

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
            List<GameSummary> result = gameSummaryRepository.findAllByOrderByTimeUsedPercentageAsc();

            // Assert
            assertThat(result).isEmpty();
        }
    }

    @Nested
    @DisplayName("FindAllByOrderByCompletionPercentageDesc() - Test")
    class FindAllByOrderByCompletionPercentageDescTest {

        @Test
        @DisplayName("Returns non-empty list with expected length")
        public void testMethod_returned_list_hasExpectedLength() {
            // Arrange
            GameSummary summery1 = TestObjectFactory.createDefaultGameSummary();
            GameSummary summery2 = TestObjectFactory.createDefaultGameSummary();
            gameSummaryRepository.save(summery1);
            gameSummaryRepository.save(summery2);

            // Act
            List<GameSummary> result = gameSummaryRepository.findAllByOrderByCompletionPercentageDesc();

            // Assert
            assertThat(result).isNotEmpty();
            assertThat(result).hasSize(2);
        }

        @Test
        @DisplayName("Returns correctly sorted list of game summaries")
        public void testMethod_returned_list_isCorrectlySorted() {
            // Arrange
            GameSummary summary1 = TestObjectFactory.createGameSummaryWithCompletionPercentage(50.0);
            GameSummary summary2 = TestObjectFactory.createGameSummaryWithCompletionPercentage(30.0);
            gameSummaryRepository.save(summary1);
            gameSummaryRepository.save(summary2);

            // Act
            List<GameSummary> result = gameSummaryRepository.findAllByOrderByTimeUsedPercentageAsc();

            // Assert
            assertThat(result).isNotEmpty();
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getCompletionPercentage())
                    .isGreaterThan(result.get(1).getCompletionPercentage());
        }

        @Test
        @DisplayName("Returns empty list when no game summaries are found")
        public void testMethod_returned_list_isEmpty() {
            // Act
            List<GameSummary> result = gameSummaryRepository.findAllByOrderByCompletionPercentageDesc();

            // Assert
            assertThat(result).isEmpty();
        }
    }

    @Nested
    @DisplayName("FindAllByOrderByTotalScoreDesc() - Tests")
    class FindAllByOrderByTotalScoreDescTest {

        @Test
        @DisplayName("Returns non-empty list with expected length")
        public void testMethod_returned_list_hasExpectedLength() {
            // Arrange
            GameSummary summary1 = TestObjectFactory.createGameSummaryWithTotalScore(100);
            GameSummary summary2 = TestObjectFactory.createGameSummaryWithTotalScore(200);
            gameSummaryRepository.save(summary1);
            gameSummaryRepository.save(summary2);

            // Act
            List<GameSummary> result = gameSummaryRepository.findAllByOrderByTotalScoreDesc();

            // Assert
            assertThat(result).isNotEmpty();
            assertThat(result).hasSize(2);
        }

        @Test
        @DisplayName("Returns correctly sorted list of game summaries")
        public void testMethod_returned_list_isCorrectlySorted() {
            // Arrange
            GameSummary summary1 = TestObjectFactory.createGameSummaryWithTotalScore(100);
            GameSummary summary2 = TestObjectFactory.createGameSummaryWithTotalScore(200);
            gameSummaryRepository.save(summary1);
            gameSummaryRepository.save(summary2);

            // Act
            List<GameSummary> result = gameSummaryRepository.findAllByOrderByTotalScoreDesc();

            // Assert
            assertThat(result).isNotEmpty();
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getTotalScore())
                    .isGreaterThan(result.get(1).getTotalScore());
        }

        @Test
        @DisplayName("Returns empty list when no game summaries are found")
        public void testMethod_returned_list_isEmpty() {
            // Act
            List<GameSummary> result = gameSummaryRepository.findAllByOrderByTotalScoreDesc();

            // Assert
            assertThat(result).isEmpty();
        }
    }
}
