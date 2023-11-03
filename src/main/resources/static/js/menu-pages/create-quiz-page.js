document.addEventListener('DOMContentLoaded', (event) => {
  initializeForm();
});

// Variables to keep track of the quiz state
let questionCount = 0;
const questions = [];
let quizTopic = "";

// Entry point for dynamic form
function initializeForm() {
  const html = `
    <label for="topic">Enter your quiz's topic:</label>
    <input type="text" id="topic" name="topic">
    <button onclick="collectTopic()">Next</button>
  `;
  document.getElementById("form-container").innerHTML = html;
}


// Collect topic and move to the first question
function collectTopic() {
  const topic = document.getElementById("topic").value;
  if (!topic) {
    alert("Topic is required!");
    return;
  }

quizTopic = topic;

  nextQuestion();
}

// Generate and display the next question scene
function nextQuestion() {
  const html = `
    <label for="question">Enter your question: ${(questionCount + 1)}</label>
    <input type="text" id="question" name="question">
    <label for="timeLimit">Time Limit (seconds):</label>
    <input type="number" id="timeLimit" name="timeLimit" value="1">
    <label for="points">Points:</label>
    <input type="number" id="points" name="points" value="1">
    <button onclick="collectQuestion()">Next</button>
  `;
  document.getElementById("form-container").innerHTML = html;
}


// Collect the question and move to answer options
function collectQuestion() {
  const question = document.getElementById("question").value;
  const timeLimit = document.getElementById("timeLimit").value;
  const points = document.getElementById("points").value;

  if (!question || !timeLimit || !points) {
    alert("All fields are required!");
    return;
  }

  questions.push({
    question,
    timeLimit,
    points,
    options: [],
    correctOption: null,
  });

  // Display answer options
  nextAnswerOptions();
}


// Generate and display the next answer options scene
function nextAnswerOptions() {
  const html = `
    <label>Enter answer options:</label>
    <input type="text" id="option1" class="answer-option" placeholder="Option 1">
    <input type="text" id="option2" class="answer-option" placeholder="Option 2">
    <input type="text" id="option3" class="answer-option" placeholder="Option 3">
    <input type="text" id="option4" class="answer-option" placeholder="Option 4">
    <label for="correctOption">Select the correct answer:</label>
    <select id="correctOption" onchange="highlightCorrectOption()">
      <option value="1">Option 1</option>
      <option value="2">Option 2</option>
      <option value="3">Option 3</option>
      <option value="4">Option 4</option>
    </select>
    <button onclick="collectAnswers()">Next</button>
  `;
  document.getElementById("form-container").innerHTML = html;
}

function highlightCorrectOption() {
  // Remove any existing highlight
  document.querySelectorAll('.answer-option').forEach(el => {
    el.classList.remove('highlight-correct');
  });

  // Highlight the correct option
  const correctOption = document.getElementById("correctOption").value;
  document.getElementById(`option${correctOption}`).classList.add('highlight-correct');
}

// Collect answers and correct option, then move to next question
function collectAnswers() {
  const options = [
    document.getElementById("option1").value,
    document.getElementById("option2").value,
    document.getElementById("option3").value,
    document.getElementById("option4").value,
  ];

  if (options.some(option => !option)) {
    alert("All answer options are required!");
    return;
  }

  const correctOption = document.getElementById("correctOption").value;
  questions[questionCount].options = options;
  questions[questionCount].correctOption = parseInt(correctOption, 10);

  questionCount++;

  if (questionCount === 5) {
    completeQuiz();
  } else {
    nextQuestion();
  }
}

function collectAllAnswerOptions() {
  return questions.map(q => q.options).flat();
}

function completeQuiz() {
  console.log("Quiz completed", questions);

  const summaryHtml = `
    <div class="summary-container">
      <h3>Quiz Preview: <span class="title-value">${quizTopic}</span></h3>
      <ul class="summary-list">
        ${questions.map((q, index) => `
          <li class="summary-item">
            <div class="question-row">
              <span class="question-label">Question ${index + 1}: </span>
              <span class="question-text">${q.question}</span>
            </div>
            <div class="info-row">
              <span class="time-container">
                <span class="time-label">Time Limit:</span>
                <span class="time-value">${q.timeLimit} s</span>
              </span>
              <span class="points-container">
                <span class="points-label">Points:</span>
                <span class="points-value">${q.points}</span>
              </span>
            </div>
          </li>
        `).join('')}
      </ul>
      <button class="summary-button" onclick="sendQuizToBackend()">Confirm</button>
      <button class="summary-button" onclick="returnToCreateQuizPage()">Cancel</button>
    </div>
  `;


const summaryElement = document.getElementById("form-container");
summaryElement.innerHTML = summaryHtml;
}

function cancelQuiz() {
  // Reset quiz state
  questionCount = 0;
  questions.length = 0;
  quizTopic = "";

  // Go back to initial form
  initializeForm();
}

function sendQuizToBackend() {
  // Transform the questions to match the QuestionCreationDTO
  const formattedQuestions = questions.map(q => ({
    questionText: q.question,
    timeLimit: q.timeLimit,
    points: q.points,
    answerOptions: q.options.map((option, index) => ({
      optionText: option,
      // Use integers instead of booleans
      isCorrectAnswer: (q.correctOption === index + 1) ? 1 : 0  // Use integers 1 or 0
    }))
  }));

  const quiz = {
    topic: quizTopic,
    questions: formattedQuestions
  };

  fetch('/create-quiz', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(quiz)
  })
  .then(response => response.text())
  .then(data => {
    window.location.href = '/quizwiz';
    console.log('Success:', data);
  })
  .catch(error => {
    console.error('Error:', error);
  });
}


