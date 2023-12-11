
/**
 * PopupManager handles the creation and management of popups in the DOM.
 */
class PopupManager {

    /**
     * Constructs a new PopupManager instance and initializes a div element
     * to serve as the background for the popup.
     */
    constructor() {
        this.popupBackground = document.createElement('div');
        this.popupBackground.className = 'popup-background';
    }

    /**
     * Displays a popup with customizable content and an onClose callback.
     * The popup includes a default layout and buttons for saving the score and closing the popup.
     */
    showPopup(onClose) {
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

        this.popupBackground.appendChild(popup);
        document.body.appendChild(this.popupBackground);

        // Add event listeners to the buttons
        document.getElementById('save-score').addEventListener('click', submitQuizResult);
        document.getElementById('close-popup').addEventListener('click', () => {
            this.closePopup();
            if (typeof onClose === 'function') {
                onClose();
            }
        });
    }

    /**
     * Closes and removes the popup from the document.
     */
    closePopup() {
        this.popupBackground.remove();
    }
}
