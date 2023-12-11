package se.juninatt.quizwiz.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.juninatt.quizwiz.test_util.TestObjectFactory;
import se.juninatt.quizwiz.tests_config.implementation.TestDatabaseImplementation;
import se.juninatt.quizwiz.model.entity.Leaderboard;
import se.juninatt.quizwiz.repository.LeaderboardRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GameSummary Service:")
public class LeaderboardServiceTest extends TestDatabaseImplementation   {
    @Autowired
    LeaderboardService leaderboardService;
    @Autowired
    LeaderboardRepository leaderboardRepository;


    @Nested
    @DisplayName("GetLeaderBoardByTimePercentage() - Tests")
    class GetLeaderBoardByTimePercentageTest {

        @Test
        @DisplayName("Returns empty list when database is empty")
        public void testMethod_returns_emptyList_whenDatabaseIsEmpty() {
            // Act
            List<Leaderboard> leaderBoard = leaderboardService.getLeaderBoardByTimePercentage();

            // Assert
            assertThat(leaderBoard).isEmpty();
        }

        @Test
        @DisplayName("Returns list of game summaries of expected length")
        public void testMethod_returnsList_ofExpectedLength() {
            // Arrange
            Leaderboard leaderboard1 = TestObjectFactory.createDefaultGameSummary();
            Leaderboard leaderboard2 = TestObjectFactory.createDefaultGameSummary();

            leaderboardRepository.saveAllAndFlush(List.of(leaderboard1, leaderboard2));

            // Act
            List<Leaderboard> leaderBoard = leaderboardService.getLeaderBoardByTimePercentage();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(2);
        }

        @Test
        @DisplayName("Returns a list sorted by time percentage descending when values are ascending")
        public void testMethod_returnsList_correctlySorted_ofExpectedLength() {
            // Arrange
            Leaderboard leaderboard1 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(13);
            Leaderboard leaderboard2 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(21);
            Leaderboard leaderboard3 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(69);

            leaderboardRepository.saveAllAndFlush(List.of(leaderboard1, leaderboard2, leaderboard3));

            // Act
            List<Leaderboard> leaderBoard = leaderboardService.getLeaderBoardByTimePercentage();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(3);
            assertThat(leaderBoard.get(0).getTimeUsedPercentage())
                    .isLessThan(leaderBoard.get(1).getTimeUsedPercentage());
        }

        @Test
        @DisplayName("Returns a list sorted by time percentage descending when values are random")
        public void testMethod_returnsList_correctlySorted_whenValuesAreRandom() {
            // Arrange
            Leaderboard leaderboard1 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(69);
            Leaderboard leaderboard2 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(13);
            Leaderboard leaderboard3 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(27);

            leaderboardRepository.saveAllAndFlush(List.of(leaderboard1, leaderboard2, leaderboard3));

            // Act
            List<Leaderboard> leaderBoard = leaderboardService.getLeaderBoardByTimePercentage();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(3);
            assertThat(leaderBoard.get(0).getTimeUsedPercentage())
                    .isLessThan(leaderBoard.get(1).getTimeUsedPercentage());
        }
    }



    @Nested
    @DisplayName("GetLeaderBoardByTotalScore() - Tests")
    class GetLeaderBoardByTotalScoreTest {

        @Test
        @DisplayName("Returns empty list when database is empty")
        public void testMethod_returns_emptyList_whenDatabaseIsEmpty() {
            // Act
            List<Leaderboard> leaderBoard = leaderboardService.getLeaderboardByScore();

            // Assert
            assertThat(leaderBoard).isEmpty();
        }

        @Test
        @DisplayName("Returns list of game summaries of expected length")
        public void testMethod_returnsList_ofExpectedLength() {
            // Arrange
            Leaderboard leaderboard1 = TestObjectFactory.createDefaultGameSummary();
            Leaderboard leaderboard2 = TestObjectFactory.createDefaultGameSummary();
            leaderboardRepository.saveAllAndFlush(List.of(leaderboard1, leaderboard2));

            // Act
            List<Leaderboard> leaderBoard = leaderboardService.getLeaderboardByScore();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(2);
        }

        @Test
        @DisplayName("Returns a list sorted by total score descending when values are ascending")
        public void testMethod_returnsList_correctlySorted_ofExpectedLength() {
            // Arrange
            Leaderboard leaderboard1 = TestObjectFactory.createGameSummaryWithScore("10/30");
            Leaderboard leaderboard2 = TestObjectFactory.createGameSummaryWithScore("11/30");
            Leaderboard leaderboard3 = TestObjectFactory.createGameSummaryWithScore("15/30");

            leaderboardRepository.saveAllAndFlush(List.of(leaderboard1, leaderboard2, leaderboard3));

            // Act
            List<Leaderboard> leaderBoard = leaderboardService.getLeaderboardByScore();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(3);
            assertThat(leaderBoard.get(0).getScore())
                    .isGreaterThan(leaderBoard.get(1).getScore());
        }

        @Test
        @DisplayName("Returns a list sorted by total score descending when values are random")
        public void testMethod_returnsList_correctlySorted_whenValuesAreRandom() {
            // Arrange
            Leaderboard leaderboard1 = TestObjectFactory.createGameSummaryWithScore("69/100");
            Leaderboard leaderboard2 = TestObjectFactory.createGameSummaryWithScore("13/100");
            Leaderboard leaderboard3 = TestObjectFactory.createGameSummaryWithScore("79/100");

            leaderboardRepository.saveAllAndFlush(List.of(leaderboard1, leaderboard2, leaderboard3));

            // Act
            List<Leaderboard> leaderBoard = leaderboardService.getLeaderboardByScore();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(3);
            assertThat(leaderBoard.get(0).getScore())
                    .isGreaterThan(leaderBoard.get(1).getScore());
        }
    }
}
