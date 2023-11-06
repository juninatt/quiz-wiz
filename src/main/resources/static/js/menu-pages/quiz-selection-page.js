document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll('.clickable-row').forEach(row => {
    row.addEventListener('click', () => {
const quizId = row.getAttribute('data-id');
      window.location.href = `/start-quiz/${quizId}`;
    });
  });
});
