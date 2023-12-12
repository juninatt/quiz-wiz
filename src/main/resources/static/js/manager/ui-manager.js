/**
 * Manages the User Interface of the Quiz application.
 * This class centralizes the manipulation of DOM elements related to the quiz,
 * providing a single point of control for UI updates.
 */
class UIManager {
    constructor() {
        // Cache frequently accessed DOM elements to improve performance
        this.questionTextContainer = document.getElementById('question-text');
        this.optionsWrapper = document.getElementById('options');
        this.timerBar = document.getElementById('timer-progress');
    }

    /**
     * Updates the text of the current question.
     * @param {string} text - The text to display for the question.
     */
    updateQuestionText(text) {
        this.questionTextContainer.textContent = text;
    }

    /**
     * Clears all answer options from the display.
     * This is typically used before rendering new options for a new question.
     */
    clearOptions() {
        this.optionsWrapper.innerHTML = '';
    }

    /**
     * Adds an answer option to the display.
     * @param {string} text - The text of the answer option.
     * @param {boolean} isCorrect - Indicates whether this option is the correct answer.
     * @param {function} clickHandler - The function to execute when the option is clicked.
     */
    addOption(text, isCorrect, clickHandler) {
        const optionDiv = document.createElement('div');
        optionDiv.className = 'option';
        optionDiv.textContent = text;
        optionDiv.dataset.isCorrect = isCorrect;
        optionDiv.addEventListener('click', () => clickHandler(optionDiv));
        this.optionsWrapper.appendChild(optionDiv);
    }

    /**
     * Highlights the correct answer option.
     * This is used to provide visual feedback of the correct answer at the end of each question.
     */
    highlightCorrectOption() {
        const correctOption = Array.from(this.optionsWrapper.children)
                                   .find(option => option.dataset.isCorrect === '1');
        if (correctOption) {
            correctOption.classList.add('correct');
        }
    }

    /**
     * Disables all answer options.
     * This prevents further interaction once an answer has been selected.
     */
    disableAllOptions() {
        Array.from(this.optionsWrapper.children).forEach(option => {
            option.classList.add('disabled');
            option.removeEventListener('click', selectOption); // Ensure selectOption is accessible
        });
    }

    /**
     * Marks a selected answer option as chosen by the user.
     * @param {Element} optionElement - The DOM element of the selected option.
     */
    markOptionSelected(optionElement) {
        optionElement.classList.add('selected');
    }

    /**
     * Marks a selected answer option as correct.
     * @param {Element} optionElement - The DOM element of the correct option.
     */
    markOptionCorrect(optionElement) {
        optionElement.classList.add('correct');
    }

    /**
     * Marks a selected answer option as incorrect.
     * @param {Element} optionElement - The DOM element of the incorrect option.
     */
    markOptionIncorrect(optionElement) {
        optionElement.classList.add('incorrect');
    }

    /**
     * Updates the width of the timer bar to visually represent the remaining time.
     * @param {number} percentage - The width percentage of the timer bar.
     */
    updateTimerBarWidth(percentage) {
        if (this.timerBar) {
            this.timerBar.style.width = `${percentage}%`;
        }
    }
}
