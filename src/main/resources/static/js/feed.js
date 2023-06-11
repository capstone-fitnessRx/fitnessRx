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



$(document).ready(function() {
    var commentDropLink = $(".dropdown-toggle");
    var commentDropDownDisplay = $(".dropdown-comments");

    commentDropLink.on("click", function() {
        commentDropDownDisplay.toggleClass("show");
    });

    $(document).on("click", function(event) {
        if (!commentDropLink.is(event.target) && !commentDropDownDisplay.has(event.target).length) {
            commentDropDownDisplay.removeClass("show");
        }
    });
});