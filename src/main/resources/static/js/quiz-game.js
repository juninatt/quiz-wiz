const quizContainer = document.getElementById('quiz-container');
const quizId = quizContainer.getAttribute('data-quiz-id');
const quizTopic = quizContainer.getAttribute('data-topic');
let currentQuestionIndex = 0;
let correctAnswersCount = 0;
let totalScore = 0;
let totalQuizTime = 0;
let nextQuestionButton = document.getElementById('next-question-button');
const popupManager = new PopupManager();
const questionTimer = new Timer(handleTimerExpiry);

// Create and append next question button to quiz container
function initializeNextQuestionButton() {
    nextQuestionButton = document.createElement('button');
    nextQuestionButton.id = 'next-question-button';
    nextQuestionButton.textContent = 'Next Question';
    nextQuestionButton.addEventListener('click', handleNextQuestionClick);
    document.getElementById('quiz-container').appendChild(nextQuestionButton);
}

function handleNextQuestionClick() {
   loadQuestion(currentQuestionIndex);
}

// Increment question index and update the Next button's text and event listener
function loadQuestion(questionIndex) {
    console.log('Loading question ' + (questionIndex + 1));

    nextQuestionButton.style.display = 'none';

    fetch(`/quiz/${quizId}/question/${questionIndex}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP status ${response.status}`);
            }
            return response.json();
        })
        .then(questionData => {
            displayQuestion(questionData);
            questionTimer.updateDuration(questionData.timeLimit);
                    questionTimer.start();
        })
        .catch(error => {
            console.error('Could not fetch the question:', error.message);
        });
}

// Populate html with quiz content and add event listener to option elements
function displayQuestion(questionData) {
    console.log('Displaying question ' + (currentQuestionIndex + 1));
    const questionTextElement = document.getElementById('question-text');
    const optionsContainer = document.getElementById('options');

    // Set the question text
    questionTextElement.textContent = questionData.questionText;
    optionsContainer.innerHTML = ''; // Clear previous options

    // Iterate over the answer options and create elements for each
    questionData.answerOptions.forEach(option => {
        optionsContainer.appendChild(createOptionElement(option, questionData));
    });

    questionTimer.updateDuration(questionData.timeLimit);
    questionTimer.start();
}

// Create and return a quiz option element with event listener
function createOptionElement(option, questionData) {
    const optionElement = document.createElement('div');
    optionElement.className = 'option';
    optionElement.textContent = option.optionText;
    optionElement.dataset.isCorrect = option.isCorrectAnswer;
    optionElement.addEventListener('click', () => selectOption(optionElement, questionData.points));
    return optionElement;
}

// Handle option selection and generate next question
function selectOption(optionElement, questionData) {
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
        correctAnswersCount++;
        totalScore += questionData.points;
        optionElement.classList.add('correct');
    } else {
        optionElement.classList.add('incorrect');
    }
    finalizeQuestion();
}

function handleTimerExpiry() {
    disableAllOptions();
    updateAndShowNextButton();
    console.log('Time is up! No points awarded for this question.');
}

function finalizeQuestion() {
    highlightCorrectOption();
    updateAndShowNextButton();
}

// Disable click events and add disabled class to all quiz options
function disableAllOptions() {
    const allOptions = document.querySelectorAll('.option');
    allOptions.forEach(option => {
        option.removeEventListener('click', selectOption);
        option.classList.add('disabled'); // Add class
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
        nextQuestionButton.textContent = 'Finish';
        nextQuestionButton.removeEventListener('click', handleNextQuestionClick);
        nextQuestionButton.addEventListener('click', () => popupManager.showPopup());
        } else {
        nextQuestionButton.textContent = 'Next Question';
    }
    nextQuestionButton.style.display = 'block';
}

// Submit quiz results and handle response
function submitQuizResult() {
  const timeUsed = questionTimer.getTimeUsed();
  const timeUsedPercentage = (timeUsed / totalQuizTime) * 100;

  const payload = {
    player: document.getElementById('username').value,
    topic: quizTopic,
    score: totalScore,
    totalScorePercentage: Math.round((correctAnswersCount / 5) * 100),
    timeUsedPercentage: timeUsedPercentage,
    date: new Date().toISOString().split('T')[0],
    quiz: { id: quizId }
  };

  const endpoint = `/quiz/submit/${quizId}`;

  fetch(endpoint, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(payload)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP status ${response.status}`);
    }
    return response.json();
  })
  .then(data => {
    console.log('Score saved successfully:', data);
  })
  .catch(error => {
    console.error('Error saving the score:', error.message);
  });
}


document.addEventListener('DOMContentLoaded', () => {
  initializeNextQuestionButton();
  loadQuestion(currentQuestionIndex);
});
