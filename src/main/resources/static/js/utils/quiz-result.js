/**
 * Represents the result of a quiz game a player wishes to post to the Leaderboard.
 * This class tracks the quiz ID, player's name, score, and total time used.
 */
class QuizResult {

    /**
     * Constructs a new QuizResult instance.
     */
    constructor(quizId) {
        this.quizId = quizId;
        this.player = '';
        this.score = 0;
        this.timeUsedSec = 0;
    }

    /**
     * Adds points to the current score.
     * This is called when a player answers a question correctly.
     */
    addToScore(points) {
        this.score += points;
    }

    /**
     * Adds time to the total time used.
     * This is called after each question to track how long the quiz is taking.
     */
    addTimeUsedSec(seconds) {
        this.timeUsedSec += seconds;
    }

    /**
     * Sets the player's name for this quiz result.
     * This is set when the player decides to save the result to the Leaderboard.
     */
    setPlayer(playerName) {
        this.player = playerName;
    }

     /**
     * Submits the quiz result to the server.
     */
    submit() {
        const apiHandler = new RestClient();
        return apiHandler.sendRequest('/leaderboard/save', 'POST', this)
            .then(() => console.log('Quiz results submitted'))
            .catch(error => console.error('Error saving the score:', error.message));
    }
}
