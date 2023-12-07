class ButtonHandler {
    constructor(buttonId) {
        this.button = document.getElementById(buttonId);
    }

    setEventListener(eventType, eventHandler) {
        // Remove any existing event listener to avoid duplicate handlers
        this.button.removeEventListener(eventType, this.currentHandler);

        // Add the new event listener
        this.button.addEventListener(eventType, eventHandler);

        // Store the current handler for potential removal later
        this.currentHandler = eventHandler;
    }

    setHidden(hidden) {
        if (hidden) {
            this.button.style.display = 'none';
        } else {
            this.button.style.display = 'block';
        }
    }

    setText(newText) {
        this.button.textContent = newText;
    }
}