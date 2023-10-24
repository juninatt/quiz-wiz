package se.juninatt.quizwiz.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.juninatt.quizwiz.TestUtl.TestObjectFactory;
import se.juninatt.quizwiz.config.implementation.TestDatabaseImplementation;
import se.juninatt.quizwiz.model.entity.GameSummary;
import se.juninatt.quizwiz.repository.GameSummaryRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GameSummary Service:")
public class GameSummaryServiceTest extends TestDatabaseImplementation   {
    @Autowired
    GameSummaryService gameSummaryService;
    @Autowired
    GameSummaryRepository gameSummaryRepository;


    @Nested
    @DisplayName("GetLeaderBoardByTimePercentage() - Tests")
    class GetLeaderBoardByTimePercentageTest {

        @Test
        @DisplayName("Returns empty list when database is empty")
        public void testMethod_returns_emptyList_whenDatabaseIsEmpty() {
            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByTimePercentage();

            // Assert
            assertThat(leaderBoard).isEmpty();
        }

        @Test
        @DisplayName("Returns list of game summaries of expected length")
        public void testMethod_returnsList_ofExpectedLength() {
            // Arrange
            GameSummary gameSummary1 = TestObjectFactory.createDefaultGameSummary();
            GameSummary gameSummary2 = TestObjectFactory.createDefaultGameSummary();

            gameSummaryRepository.saveAllAndFlush(List.of(gameSummary1, gameSummary2));

            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByTimePercentage();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(2);
        }

        @Test
        @DisplayName("Returns a list sorted by time percentage descending when values are ascending")
        public void testMethod_returnsList_correctlySorted_ofExpectedLength() {
            // Arrange
            GameSummary gameSummary1 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(13);
            GameSummary gameSummary2 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(21);
            GameSummary gameSummary3 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(69);

            gameSummaryRepository.saveAllAndFlush(List.of(gameSummary1, gameSummary2, gameSummary3));

            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByTimePercentage();

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
            GameSummary gameSummary1 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(69);
            GameSummary gameSummary2 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(13);
            GameSummary gameSummary3 = TestObjectFactory.createGameSummaryWithTimeUsedPercentage(27);

            gameSummaryRepository.saveAllAndFlush(List.of(gameSummary1, gameSummary2, gameSummary3));

            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByTimePercentage();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(3);
            assertThat(leaderBoard.get(0).getTimeUsedPercentage())
                    .isLessThan(leaderBoard.get(1).getTimeUsedPercentage());
        }
    }

    @Nested
    @DisplayName("GetLeaderBoardByCompletionPercentage() - Tests")
    class GetLeaderBoardByCompletionPercentageTest {

        @Test
        @DisplayName("Returns empty list when database is empty")
        public void testMethod_returns_emptyList_whenDatabaseIsEmpty() {
            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByCompletionPercentage();

            // Assert
            assertThat(leaderBoard).isEmpty();
        }

        @Test
        @DisplayName("Returns list of game summaries of expected length")
        public void testMethod_returnsList_ofExpectedLength() {
            // Arrange
            GameSummary gameSummary1 = TestObjectFactory.createDefaultGameSummary();
            GameSummary gameSummary2 = TestObjectFactory.createDefaultGameSummary();

            gameSummaryRepository.saveAllAndFlush(List.of(gameSummary1, gameSummary2));

            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByCompletionPercentage();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(2);
        }

        @Test
        @DisplayName("Returns a list sorted by completion percentage descending when values are ascending")
        public void testMethod_returnsList_correctlySorted_ofExpectedLength() {
            // Arrange
            GameSummary gameSummary1 = TestObjectFactory.createGameSummaryWithCompletionPercentage(13);
            GameSummary gameSummary2 = TestObjectFactory.createGameSummaryWithCompletionPercentage(21);
            GameSummary gameSummary3 = TestObjectFactory.createGameSummaryWithCompletionPercentage(69);

            gameSummaryRepository.saveAllAndFlush(List.of(gameSummary1, gameSummary2, gameSummary3));

            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByCompletionPercentage();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(3);
            assertThat(leaderBoard.get(0).getCompletionPercentage())
                    .isGreaterThan(leaderBoard.get(1).getCompletionPercentage());
        }

        @Test
        @DisplayName("Returns a list sorted by completion percentage descending when values are random")
        public void testMethod_returnsList_correctlySorted_whenValuesAreRandom() {
            // Arrange
            GameSummary gameSummary1 = TestObjectFactory.createGameSummaryWithCompletionPercentage(69);
            GameSummary gameSummary2 = TestObjectFactory.createGameSummaryWithCompletionPercentage(13);
            GameSummary gameSummary3 = TestObjectFactory.createGameSummaryWithCompletionPercentage(27);

            gameSummaryRepository.saveAllAndFlush(List.of(gameSummary1, gameSummary2, gameSummary3));

            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByCompletionPercentage();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(3);
            assertThat(leaderBoard.get(0).getCompletionPercentage())
                    .isGreaterThan(leaderBoard.get(1).getCompletionPercentage());
        }
    }

    @Nested
    @DisplayName("GetLeaderBoardByTotalScore() - Tests")
    class GetLeaderBoardByTotalScoreTest {

        @Test
        @DisplayName("Returns empty list when database is empty")
        public void testMethod_returns_emptyList_whenDatabaseIsEmpty() {
            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByTotalScore();

            // Assert
            assertThat(leaderBoard).isEmpty();
        }

        @Test
        @DisplayName("Returns list of game summaries of expected length")
        public void testMethod_returnsList_ofExpectedLength() {
            // Arrange
            GameSummary gameSummary1 = TestObjectFactory.createDefaultGameSummary();
            GameSummary gameSummary2 = TestObjectFactory.createDefaultGameSummary();
            gameSummaryRepository.saveAllAndFlush(List.of(gameSummary1, gameSummary2));

            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByTotalScore();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(2);
        }

        @Test
        @DisplayName("Returns a list sorted by total score descending when values are ascending")
        public void testMethod_returnsList_correctlySorted_ofExpectedLength() {
            // Arrange
            GameSummary gameSummary1 = TestObjectFactory.createGameSummaryWithTotalScore(13);
            GameSummary gameSummary2 = TestObjectFactory.createGameSummaryWithTotalScore(21);
            GameSummary gameSummary3 = TestObjectFactory.createGameSummaryWithTotalScore(69);

            gameSummaryRepository.saveAllAndFlush(List.of(gameSummary1, gameSummary2, gameSummary3));

            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByTotalScore();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(3);
            assertThat(leaderBoard.get(0).getTotalScore())
                    .isGreaterThan(leaderBoard.get(1).getTotalScore());
        }

        @Test
        @DisplayName("Returns a list sorted by total score descending when values are random")
        public void testMethod_returnsList_correctlySorted_whenValuesAreRandom() {
            // Arrange
            GameSummary gameSummary1 = TestObjectFactory.createGameSummaryWithTotalScore(69);
            GameSummary gameSummary2 = TestObjectFactory.createGameSummaryWithTotalScore(13);
            GameSummary gameSummary3 = TestObjectFactory.createGameSummaryWithTotalScore(27);

            gameSummaryRepository.saveAllAndFlush(List.of(gameSummary1, gameSummary2, gameSummary3));

            // Act
            List<GameSummary> leaderBoard = gameSummaryService.getLeaderBoardByTotalScore();

            // Assert
            assertThat(leaderBoard).isNotEmpty();
            assertThat(leaderBoard).hasSize(3);
            assertThat(leaderBoard.get(0).getTotalScore())
                    .isGreaterThan(leaderBoard.get(1).getTotalScore());
        }
    }
}
