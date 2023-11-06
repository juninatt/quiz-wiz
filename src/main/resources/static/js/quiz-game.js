document.addEventListener('DOMContentLoaded', () => {
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

  if (currentQuestionIndex ==  6) {
    submitQuizResults();
  }
  // Show the 'Next Question' button
    nextQuestionButton.style.display = 'block';

    nextQuestionButton.style.display = 'block';
}

// Initializes
function initializeNextQuestionButton() {
    nextQuestionButton = document.createElement('button');
    nextQuestionButton.id = 'next-question-button';
    nextQuestionButton.textContent = 'Next Question';
    nextQuestionButton.addEventListener('click', () => loadQuestionData(currentQuestionIndex));
    document.getElementById('quiz-container').appendChild(nextQuestionButton);
}

  function loadQuestionData(questionIndex) {
  console.log('Loading question data')

    nextQuestionButton.style.display = 'none';

    // Placeholder URL - update with the actual URL to fetch question data
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
        // Implement a user-friendly way to display this error, like an alert or a message in the DOM.
      });
  }


  function submitQuizResults() {
    fetch(`/submit-quiz/${quizId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ correctAnswers: correctAnswersCount })
    })
    .then(handleResultsResponse)
    .catch(error => {
      console.error('Failed to submit the quiz results:', error.message);
      // Implement a user-friendly way to display this error
    });
  }

  function handleResultsResponse(response) {
    if (!response.ok) {
      throw new Error(`HTTP status ${response.status}`);
    }
    return response.json().then(resultData => {
      // Handle the response data
      // For example, show a message to the user with their score
    });
  }

  initializeNextQuestionButton();
  // Load the initial question data from the server
  loadQuestionData(currentQuestionIndex);



});
