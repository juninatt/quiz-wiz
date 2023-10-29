document.addEventListener('DOMContentLoaded', (event) => {
     const menuButtons = document.querySelectorAll('.menu-button');

     menuButtons.forEach(button => {
         button.addEventListener('click', function() {
             if (this.id === 'create-quiz-button') {
                 window.location.href = '/create-quiz-page';
             } else if (this.id === 'welcome-page-button') {
                 window.location.href = '/quizwiz';
             } else {
                 alert('You clicked a menu button!');
             }
         });
     });
 });
