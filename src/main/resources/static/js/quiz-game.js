const quizContainer = document.getElementById('quiz-container');
const quizId = quizContainer.getAttribute('quiz-id');
let currentQuestionIndex = 0;
const buttonHandler = new ButtonHandler('next-question-button');
const quizResult = new QuizResult(Number(quizId));
const popupManager = new PopupManager();
const questionTimer = new Timer(handleTimerExpiry);


function handleButtonClick() {
    buttonHandler.setHidden(true);
    currentQuestionIndex++;
    if (currentQuestionIndex === 4) {
        buttonHandler.setText('Finish');
        buttonHandler.setEventListener('click', () => popupManager.showPopup(resetQuizState));
        }
        loadQuestion(currentQuestionIndex);
}

// Load the question located at the index passed to the function
function loadQuestion(questionIndex) {
    fetch(`/quiz/${quizId}/question/${questionIndex}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP status ${response.status}`);
            }
            return response.json();
        })
        .then(questionData => {
            displayQuestionWithOptions(questionData);
        })
        .catch(error => {
            console.error('Could not fetch the question:', error.message);
        });
}

// Populate HTML with quiz question and options
function displayQuestionWithOptions(questionData) {
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

    optionElement.classList.add('selected');
    disableAllOptions();
    const isCorrect = optionElement.dataset.isCorrect === '1';
    if (isCorrect) {
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
}

function finalizeQuestion() {
    quizResult.addTimeUsedSec(Math.round(questionTimer.getTimeUsed()));
    highlightCorrectOption();
    buttonHandler.setHidden(false);
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

// Send result of the quiz game to be stored as a leaderboard object in the database
function submitQuizResult() {
    // Initialize the API handler
    const apiHandler = new RestClient();
    // Set the player name in the quizResult from the input field
    quizResult.setPlayer(document.getElementById('username').value);

    // Send the quizResult object to be stored in the database
    quizResult.submit();
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
