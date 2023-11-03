document.addEventListener('DOMContentLoaded', (event) => {
     const menuButtons = document.querySelectorAll('.menu-button');

     menuButtons.forEach(button => {
         button.addEventListener('click', function() {
             if (this.id === 'create-quiz-button') {
                 window.location.href = '/quiz-creation';
             } else if (this.id === 'welcome-page-button') {
                 window.location.href = '/quiz-wiz';
             } else if (this.id === 'play-quiz-button') {
                 window.location.href = '/quiz-selection';
             } else {
                 alert('You clicked a menu button!');
             }
         });
     });
 });
