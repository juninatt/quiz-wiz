document.addEventListener('DOMContentLoaded', (event) => {
    const menuButtons = document.querySelectorAll('.menu-button');

    menuButtons.forEach(button => {
        button.addEventListener('mouseover', function() {
            this.style.backgroundColor = "#0077aa";
        });

        button.addEventListener('mouseout', function() {
            this.style.backgroundColor = "#006699";
        });

        button.addEventListener('click', function() {
            alert('You clicked a menu button!');
        });
    });
});
