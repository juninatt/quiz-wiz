class PopupManager {
    constructor() {
        this.popupBackground = document.createElement('div');
        this.popupBackground.className = 'popup-background';
    }

    showPopup() {
        // Create the popup content
        const popup = document.createElement('div');
        popup.className = 'popup';
        popup.innerHTML = `
            <h2>Great job!</h2>
            <p>Enter your name to save your score:</p>
            <input type="text" id="username" placeholder="Your Name">
            <button class="quiz-game-button" id="save-score">Save Score</button>
            <button class="quiz-game-button" id="close-popup">Close</button>
        `;

        // Append the popup to the popup background
        this.popupBackground.appendChild(popup);

        // Append the popup background to the body
        document.body.appendChild(this.popupBackground);

        // Add event listeners to the buttons
        document.getElementById('save-score').addEventListener('click', submitQuizResult);
        document.getElementById('close-popup').addEventListener('click', () => this.closePopup());
    }

    closePopup() {
        // Remove the popup background from the body
        this.popupBackground.remove();
    }
}
