const quizContainer = document.getElementById('quiz-container');
  const quizId = quizContainer.getAttribute('data-quiz-id');
  let currentQuestionIndex = 0;
  let correctAnswersCount = 0;
  let nextQuestionButton = document.getElementById('next-question-button');


// Populate html with quiz content and add event listener to option elements
function displayQuestion(questionData) {
console.log('Displaying question ' + (currentQuestionIndex + 1))
  const questionTextElement = document.getElementById('question-text');
  const optionsContainer = document.getElementById('options');

  // Set the question text
  questionTextElement.textContent = questionData.questionText;
  optionsContainer.innerHTML = ''; // Clear previous options

  // Iterate over the answer options and create elements for each
  questionData.answerOptions.forEach((option) => {
    const optionElement = document.createElement('div');
    optionElement.className = 'option';
    optionElement.textContent = option.optionText;
    optionElement.dataset.isCorrect = option.isCorrectAnswer; // Store the correct answer flag in a data attribute
    optionElement.addEventListener('click', function() { selectOption(optionElement); });
    optionsContainer.appendChild(optionElement);
  });
}

// Handle option selection and generate next question
function selectOption(optionElement) {
console.log('Option selected')
  // Prevent multiple choices if any option has already been selected
  if (document.querySelector('.option.selected')) {
    return;
  }

  // Mark the chosen option as selected to prevent further interactions
  optionElement.classList.add('selected');

  // Disable all options after a choice has been made
  const allOptions = document.querySelectorAll('.option');
  allOptions.forEach(option => {
    option.removeEventListener('click', selectOption);
  });

  // Check if the selected option is correct
  const isCorrect = optionElement.dataset.isCorrect === '1';
  if (isCorrect) {
    // Increment the score and apply a green border to the option element
    correctAnswersCount++;
    optionElement.classList.add('correct');
  } else {
    // Apply the red border to th option element
    optionElement.classList.add('incorrect');
  }

  // Apply green border to the correct option if not chosen
  const correctOption = Array.from(allOptions).find(option => option.dataset.isCorrect === '1');
  if (correctOption) {
    correctOption.classList.add('correct');
  }
  currentQuestionIndex++;

  if (currentQuestionIndex ==  5) {
    console.log('Quiz is complete, ready to submit');
    nextQuestionButton.textContent = 'Finish';
    nextQuestionButton.removeEventListener('click', handleNextQuestionClick);
    nextQuestionButton.addEventListener('click', submitQuizResults);
  }
    nextQuestionButton.style.display = 'block';
}

function handleNextQuestionClick() {
  loadQuestionData(currentQuestionIndex);
}

function initializeNextQuestionButton() {
    nextQuestionButton = document.createElement('button');
    nextQuestionButton.id = 'next-question-button';
    nextQuestionButton.textContent = 'Next Question';
    nextQuestionButton.addEventListener('click', handleNextQuestionClick);
    document.getElementById('quiz-container').appendChild(nextQuestionButton);
}

  function loadQuestionData(questionIndex) {
  console.log('Loading question data')

    nextQuestionButton.style.display = 'none';

    fetch(`/next-question/${quizId}/question/${questionIndex}`)
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP status ${response.status}`);
        }
        return response.json();
      })
      .then(questionData => {
        displayQuestion(questionData);
      })
      .catch(error => {
        console.error('Could not fetch the question:', error.message);
      });
  }


function submitQuizResults() {
  console.log('Submitting quiz results');
  fetch(`/submit-quiz/${quizId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ correctAnswers: correctAnswersCount })
  })
  .then(response => response.json())
  .then(data => {
    showPopup(data);
  })
  .catch(error => {
    console.error('Failed to submit the quiz results:', error.message);
  });
}

function showPopup(quizData) {
  const popupBackground = document.createElement('div');
  popupBackground.className = 'popup-background';

  const popup = document.createElement('div');
  popup.className = 'popup';
  popup.innerHTML = `
    <h2>Great job, ${quizData.score}!</h2>
    <p>Enter your name to save your score:</p>
    <input type="text" id="username" placeholder="Your Name">
    <button class="quiz-game-button" onclick="saveScore()">Save Score</button>
    <button class="quiz-game-button" onclick="closePopup()">Close</button>
  `;

  popupBackground.appendChild(popup);
  document.body.appendChild(popupBackground);
}

function saveScore() {
  const quizId = quizContainer.getAttribute('data-quiz-id');
  const totalQuestions = 5;

  const totalScorePercentage = Math.round((correctAnswersCount / totalQuestions) * 100);
  const currentDate = new Date().toISOString().split('T')[0];
  const playerName = document.getElementById('username').value;

  const payload = {
    player: playerName,
    totalScore: totalScorePercentage,
    timeUsedPercentage: null,
    completionPercentage: null,
    date: currentDate,
    quiz: { id: quizId }
  };
  const endpoint = `/submit-quiz/${quizId}`;

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

function closePopup() {
  const popupBackground = document.querySelector('.popup-background');
  if (popupBackground) {
    document.body.style.overflow = ''; // Enable body scrolling
    popupBackground.remove();
  }
}

document.addEventListener('DOMContentLoaded', () => {

  initializeNextQuestionButton();
  loadQuestionData(currentQuestionIndex);

});
