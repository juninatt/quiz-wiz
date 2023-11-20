document.addEventListener('DOMContentLoaded', (event) => {
     const menuButtons = document.querySelectorAll('.menu-button');

     menuButtons.forEach(button => {
         button.addEventListener('click', function() {
             if (this.id === 'create-quiz-button') {
                 window.location.href = '/menu/quiz-creation';
             } else if (this.id === 'welcome-page-button') {
                 window.location.href = '/menu/welcome';
             } else if (this.id === 'play-quiz-button') {
                 window.location.href = '/menu/quiz-selection';
             } else if(this.id === 'leaderboard-button') {
                 window.location.href = '/menu/leaderboard';
             } else {
                alert('You clicked a menu button!');
             }
         });
     });
 });
