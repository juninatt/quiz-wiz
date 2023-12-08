const quizContainer = document.getElementById('quiz-container');
const quizId = quizContainer.getAttribute('quiz-id');
const quizTopic = quizContainer.getAttribute('topic');
let currentQuestionIndex = 0;
const buttonHandler = new ButtonHandler('next-question-button');
const quizResult = new QuizResult(Number(quizId));
const popupManager = new PopupManager();
const questionTimer = new Timer(handleTimerExpiry);


function handleButtonClick() {
    loadQuestion(currentQuestionIndex);
}

// Updated loadQuestion function
function loadQuestion(questionIndex) {
    // Hide button before option is chosen
    buttonHandler.setHidden(true);

    fetchQuestionData(questionIndex)
        .then(questionData => {
            displayQuestionWithOptions(questionData);
        })
        .catch(error => {
            console.error('Could not fetch the question:', error.message);
        });
}

// New function to fetch question data
function fetchQuestionData(questionIndex) {
    return fetch(`/quiz/${quizId}/question/${questionIndex}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP status ${response.status}`);
            }
            return response.json();
        });
}

// Populate HTML with quiz question and options
function displayQuestionWithOptions(questionData) {
    console.log('Displaying question ' + (currentQuestionIndex + 1));
    const questionTextContainer = document.getElementById('question-text');
    const optionsWrapper = document.getElementById('options');

    questionTextContainer.textContent = questionData.questionText;
    optionsWrapper.innerHTML = ''; // Clear previous options

    questionData.answerOptions.forEach(answerOption => {
        optionsWrapper.appendChild(createAnswerOptionDiv(answerOption, questionData));
    });
    questionTimer.updateDuration(questionData.timeLimit);
    questionTimer.start();
}

// Create and return a div element for a quiz answer option
function createAnswerOptionDiv(answerOption, questionData) {
    const answerOptionDiv = document.createElement('div');
    answerOptionDiv.className = 'option';
    answerOptionDiv.textContent = answerOption.optionText;
    answerOptionDiv.dataset.isCorrect = answerOption.isCorrectAnswer;
    answerOptionDiv.addEventListener('click', () => selectOption(answerOptionDiv, questionData.points));
    return answerOptionDiv;
}


// Handle option selection and generate next question
function selectOption(optionElement, questionPointsReward) {
    if (document.querySelector('.option.selected')) {
        return;
    }
        if (questionTimer) {
            questionTimer.stop();
        }
    console.log('Option selected');

    optionElement.classList.add('selected');
    disableAllOptions();
    const isCorrect = optionElement.dataset.isCorrect === '1';
    if (isCorrect) {
    console.log(questionPointsReward);
        quizResult.addToScore(questionPointsReward);
        optionElement.classList.add('correct');
    } else {
        optionElement.classList.add('incorrect');
    }
    finalizeQuestion();
}

function handleTimerExpiry() {
    disableAllOptions();
    finalizeQuestion();
    console.log('Time is up! No points awarded for this question.');
}

function finalizeQuestion() {
    quizResult.addTimeUsedSec(Math.round(questionTimer.getTimeUsed()));
    highlightCorrectOption();
    updateAndShowNextButton();
}

// Disable click events and add disabled class to all quiz options
function disableAllOptions() {
    const allOptions = document.querySelectorAll('.option');
    allOptions.forEach(option => {
        option.removeEventListener('click', selectOption);
        option.classList.add('disabled');
    });
}

// Highlight the correct quiz option in the options list
function highlightCorrectOption() {
    const allOptions = document.querySelectorAll('.option');
    const correctOption = Array.from(allOptions).find(option => option.dataset.isCorrect === '1');
    if (correctOption) {
        correctOption.classList.add('correct');
    }
}

// Increment question index and toggle between Next and Finish button states
function updateAndShowNextButton() {
    currentQuestionIndex++;
    if (currentQuestionIndex === 5) {
        buttonHandler.setText('Finish');
        buttonHandler.setEventListener('click', () => popupManager.showPopup(resetQuizState));
        }
    buttonHandler.setHidden(false);
}

// Send result of the quiz game to be stored as a leaderboard object in the database
function submitQuizResult() {
    // Initialize the API handler
    const apiHandler = new RestClient();
    // Set the player name in the quizResult from the input field
    quizResult.setPlayer(document.getElementById('username').value);

    // Send the quizResult object to be stored in the database
    apiHandler.sendRequest('/leaderboard/save', 'POST', quizResult)
        .then(() => {
            console.log('Quiz results submitted');
            resetQuizState();
        })
        .catch(error => {
            console.error('Error saving the score:', error.message);
            resetQuizState();
        });
}

// Reset the quiz game to its starting state and redirect to quiz selection page
function resetQuizState() {
    currentQuestionIndex = 0;
    totalScore = 0;
    totalQuizTime = 0;
    window.location.href = '/menu/quiz-selection';
}


document.addEventListener('DOMContentLoaded', () => {
  buttonHandler.setText('Next Question');
  buttonHandler.setEventListener('click', handleButtonClick)
  loadQuestion(currentQuestionIndex);
});
