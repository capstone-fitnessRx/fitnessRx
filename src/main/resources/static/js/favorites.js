"use strict"

// Get all custom dropdowns
var customDropdowns = document.querySelectorAll('.custom-dropdown');

// Add click event listeners to each dropdown toggle
customDropdowns.forEach(function(dropdown) {
    var toggle = dropdown.querySelector('.custom-dropdown-toggle');
    var content = dropdown.querySelector('.custom-dropdown-content');

    toggle.addEventListener('click', function() {
        content.classList.toggle('show');
    });

    window.onclick = function(event) {
        if (!event.target.matches('.custom-dropdown-toggle')) {
            if (content.classList.contains('show')) {
                content.classList.remove('show');
            }
        }
    };
});