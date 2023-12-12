/**
 * Manages timing functionality for quiz questions.
 * This class handles starting, updating, and stopping a timer,
 * and communicates with UIManager to update the UI accordingly.
 */
class Timer {
    /**
     * Creates a Timer instance.
     * @param {function} callback - Function to call when the timer expires.
     * @param {UIManager} uiManager - UIManager instance for updating the timer UI.
     */
    constructor(callback, uiManager) {
        this.callback = callback;
        this.uiManager = uiManager;
        this.timerId = null;
        this.duration = 0;
        this.startTime = null;
    }

    /**
     * Starts the timer with a specified duration.
     * @param {number} newDuration - The duration for the timer in seconds.
     */
    start(newDuration) {
        this.stop();  // Stops any existing timer
        this.duration = newDuration;
        this.startTime = Date.now();

        // Set the timer UI to full width at the start
        this.uiManager.updateTimerBarWidth(100);

        // Sets a timeout for when the timer should expire
        this.timerId = setTimeout(() => {
            if (this.callback) {
                this.callback();
            }
        }, this.duration * 1000);

        // Update the UI every second to reflect the remaining time
        this.countdownInterval = setInterval(() => {
            this.updateCountdownVisual();
        }, 1000);
    }

    /**
     * Updates the timer's visual representation.
     * Calculates the elapsed time and updates the timer bar width accordingly.
     */
    updateCountdownVisual() {
        const elapsed = (Date.now() - this.startTime) / 1000;
        const remaining = this.duration - elapsed;
        const percentage = Math.max((remaining / this.duration) * 100, 0);

        this.uiManager.updateTimerBarWidth(percentage);
    }

    /**
     * Stops the timer and clears any ongoing intervals.
     */
    stop() {
        clearTimeout(this.timerId);
        clearInterval(this.countdownInterval);
        this.timerId = null;
    }

    /**
     * Calculates and returns the time used by the timer.
     * @returns {number} The time used in seconds.
     */
    getTimeUsed() {
        if (!this.startTime) {
            return 0; // If the timer never started
        }
        const currentTime = Date.now();
        const elapsedTime = (currentTime - this.startTime) / 1000;
        return Math.min(elapsedTime, this.duration);
    }
}
