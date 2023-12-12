/**
 * Main script for the Quiz application.
 * Handles the quiz game logic, including loading questions, handling user interactions,
 * and managing the quiz state.
 */
const quizContainer = document.getElementById('quiz-container');
const quizId = quizContainer.getAttribute('quiz-id');
let currentQuestionIndex = 0;
const buttonHandler = new ButtonHandler('next-question-button');
const quizResult = new QuizResult(Number(quizId));
const popupManager = new PopupManager();
const uiManager = new UIManager();

/**
 * Function to be called when the timer expires.
 */
const handleTimerExpiry = () => {
    uiManager.disableAllOptions();
    finalizeQuestion();
};

const questionTimer = new Timer(handleTimerExpiry, uiManager);

/**
 * Handles click events on the quiz buttons.
 */
const handleButtonClick = () => {
    buttonHandler.setHidden(true);
    currentQuestionIndex++;
    if (currentQuestionIndex === 4) {
        buttonHandler.setText('Finish');
        buttonHandler.setEventListener('click', showFinishPopup);
    }
    loadQuestion(currentQuestionIndex);
};

/**
 * Shows the finish popup.
 */
const showFinishPopup = () => {
    popupManager.showPopup(resetQuizState);
};

/**
 * Asynchronously loads a quiz question by its index.
 * Fetches question data and handles display and errors.
 * @param {number} questionIndex - Index of the question to load.
 */
const loadQuestion = async (questionIndex) => {
    try {
        const response = await fetch(`/quiz/${quizId}/question/${questionIndex}`);
        const questionData = await processFetchResponse(response);
        displayQuestionWithOptions(questionData);
    } catch (error) {
        console.error(`Could not fetch the question: ${error.message}`);
    }
};

/**
 * Handles the response from a fetch request.
 * Throws an error if the response status is not OK, otherwise it returns the parsed JSON from the response.
 *
 * @param {Response} response - The response object from a fetch request.
 * @returns {Promise<Object>} A promise that resolves to the parsed JSON of the response.
 * @throws {Error} Throws an error if the response status is not 'OK'.
 */
const processFetchResponse = (response) => {
    if (!response.ok) {
        throw new Error(`HTTP status ${response.status}`);
    }
    return response.json();
};

/**
 * Handles errors during the fetch process.
 * Logs the error message to the console.
 * @param {Error} error - The error to handle.
 */
const handleFetchError = (error) => {
    console.error(`Could not fetch the question: ${error.message}`);
};

/**
 * Fetches data from a given URL and returns the JSON response.
 * @param {string} url - The URL to fetch data from.
 * @returns {Promise<Object>} The fetched question data.
 */
const fetchQuestionData = async (url) => {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error(`HTTP status ${response.status}`);
    }
    return response.json();
};

/**
 * Populates the HTML with the current quiz question and options.
 * @param {Object} questionData - Data of the current question.
 */
const displayQuestionWithOptions = (questionData) => {
    uiManager.updateQuestionText(questionData.questionText);
    uiManager.clearOptions();

    questionData.answerOptions.forEach(option => {
        uiManager.addOption(option.optionText, option.isCorrectAnswer, (element) => selectOption(element, questionData.points));
    });

    questionTimer.start(questionData.timeLimit);
};

/**
 * Handles the selection of a quiz option.
 * Marks the selected option, disables further options, and finalizes the question.
 * @param {HTMLElement} optionElement - The selected option element.
 * @param {number} questionPointsReward - Points rewarded for the question.
 */
const selectOption = (optionElement, questionPointsReward) => {
    if (document.querySelector('.option.selected')) return;

    questionTimer.stop();
    uiManager.markOptionSelected(optionElement);
    uiManager.disableAllOptions();

    const isCorrect = optionElement.dataset.isCorrect === '1';
    if (isCorrect) {
        quizResult.addToScore(questionPointsReward);
        uiManager.markOptionCorrect(optionElement);
    } else {
        uiManager.markOptionIncorrect(optionElement);
    }
    finalizeQuestion();
};

/**
 * Finalizes the question, highlighting the correct answer and updating the score.
 */
const finalizeQuestion = () => {
    quizResult.addTimeUsedSec(Math.round(questionTimer.getTimeUsed()));
    uiManager.highlightCorrectOption();
    buttonHandler.setHidden(false);
};

/**
 * Sends the quiz results to be stored and processed.
 */
const submitQuizResult = () => {
    const apiHandler = new RestClient();
    quizResult.setPlayer(document.getElementById('username').value);
    quizResult.submit();
};

/**
 * Resets the quiz game to its starting state and redirects to the quiz selection page.
 */
const resetQuizState = () => {
    currentQuestionIndex = 0;
    window.location.href = '/menu/quiz-selection';
};

document.addEventListener('DOMContentLoaded', () => {
    buttonHandler.setText('Next Question');
    buttonHandler.setEventListener('click', handleButtonClick);
    loadQuestion(currentQuestionIndex);
});
