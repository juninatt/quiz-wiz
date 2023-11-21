class Timer {
    constructor(callback) {
        this.callback = callback;
        this.timerId = null;
        this.duration = 0; // Initial duration, will be updated per question
    }

    start() {
        if (this.timerId) {
            this.stop();
        }
        this.startTime = Date.now();
        this.timerId = setTimeout(() => {
            if (this.callback) {
                this.callback();
            }
        }, this.duration * 1000);

        // Start updating the countdown visual
        this.countdownInterval = setInterval(() => {
            this.updateCountdownVisual();
        }, 1000); // Update every second
    }

    updateDuration(newDuration) {
        this.duration = newDuration;
    }

    updateCountdownVisual() {
        const elapsed = (Date.now() - this.startTime) / 1000;
        const remaining = this.duration - elapsed;
        const widthPercentage = (remaining / this.duration) * 100;
        document.getElementById('timer-progress').style.width = `${Math.max(widthPercentage, 0)}%`;
    }

    stop() {
        clearTimeout(this.timerId);
        clearInterval(this.countdownInterval);
        this.timerId = null;
    }

     getTimeUsed() {
            if (!this.startTime) {
                return 0; // If the timer never started
            }
            const currentTime = Date.now();
            const elapsedTime = (currentTime - this.startTime) / 1000; // Convert milliseconds to seconds
            return Math.min(elapsedTime, this.duration); // Return the lesser of elapsed time or duration
        }
}
